const express = require('express');
const { createProxyMiddleware } = require('http-proxy-middleware');
const morgan = require('morgan');
const cors = require('cors');

const app = express();
const PORT = 3000;

app.use(morgan('dev'));
app.use(cors());

// 🔹 Rota para o microserviço de livros
app.use('/api/books', createProxyMiddleware({
  target: 'http://localhost:8080/api/books',
  changeOrigin: true,
  pathRewrite: { '^/api/books': '' },
}));

// NOVA ROTA para o serviço de Pedidos
app.use('/api/pedidos', createProxyMiddleware({
    target: 'http://localhost:8081/api/pedidos',
    changeOrigin: true,
    pathRewrite: { '^/api/pedidos': '' },
}));

// 🔹 Exemplo futuro: microserviço de usuários
app.use('/api/users', createProxyMiddleware({
  target: 'http://localhost:8081',
  changeOrigin: true,
  pathRewrite: { '^/api/users': '/api/users' },
}));

// 🔹 Rota principal (teste)
app.get('/', (req, res) => {
  res.send('Gateway ativo e roteando requisições!');
});

app.listen(PORT, () => {
  console.log(`🚀 API Gateway rodando em http://localhost:${PORT}`);
});
