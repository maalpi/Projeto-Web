const baseURL = 'http://localhost:8080/email';
const formMessage = document.getElementById('formMessage');

formMessage.addEventListener('submit', function(event) {
    event.preventDefault(); // Impede o comportamento padrão do formulário
  
    const nome = document.getElementById('nome').value;
    const email = document.getElementById('email').value;
    const telefone = document.getElementById('telefone').value;
    const message = document.getElementById('mensagem').value;

    const mensagem = `Nome: ${nome}\n\nTelefone: ${telefone}\n\n${message}`;

    const dadosMensagem = {
        email,
        mensagem
    }

    axios.post(baseURL, dadosMensagem)
      .then(response => {
        console.log('Mensagem enviada com sucesso:', response.data);
        // Realizar alguma ação após o cadastro bem-sucedido, se necessário
        setTimeout(function() {
          location.reload();
      }, 1000);
      })
      .catch(error => {
        console.error('Erro ao enviar mensagem:', error);
        // Tratar erros, se houver
      });
});