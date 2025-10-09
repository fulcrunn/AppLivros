package com.example.ms_book.model;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity // indica que a classe é uma entidade JPA (do banco de dados)
@Getter
@Setter
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author; 
    private LocalDate publishedDate;
    private String publisher;
    private BigDecimal price;

}
