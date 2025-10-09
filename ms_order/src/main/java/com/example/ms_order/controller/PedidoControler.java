package com.example.ms_order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.ms_order.BookDTO;
import com.example.ms_order.model.Pedido;
import com.example.ms_order.repository.PedidoRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos/")

public class PedidoControler {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private RestTemplate restTemplate; // Injeta o RestTemplate
    private final String BOOK_SERVICE_URL = "http://localhost:8080/api/books";

    @PostMapping
    public ResponseEntity<?> createPedido(@RequestBody Pedido pedido) {
        try {
            // 1. Chama o serviço de Livros para verificar se o livro existe
            String url = BOOK_SERVICE_URL + "/" + pedido.getBookId();
            ResponseEntity<BookDTO> response = restTemplate.getForEntity(url, BookDTO.class);

            // Se a chamada foi bem-sucedida (status 200 OK), o livro existe.
            if (response.getStatusCode() == HttpStatus.OK) {
                pedido.setOrderDate(LocalDate.now()); // Define a data do pedido
                Pedido novoPedido = pedidoRepository.save(pedido);
                return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
            } else {
                // Isso é improvável de acontecer se o status não for 200 ou 404
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Resposta inesperada do serviço de livros.");
            }

        } catch (HttpClientErrorException.NotFound e) {
            // 2. Se o serviço de Livros retornar 404, o livro não foi encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro com ID " + pedido.getBookId() + " não encontrado.");
        } catch (Exception e) {
            // 3. Para qualquer outro erro na comunicação
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao comunicar com o serviço de livros: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

}
