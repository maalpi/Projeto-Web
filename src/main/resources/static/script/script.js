const baseURL = 'http://localhost:8080/animais'; // Substitua pela URL do seu back-end
const formAnimal = document.getElementById('formAnimal');
const botaoFecharDialogo = document.getElementById('fecharDialogo');

let tipoFiltro = ''
let numPage = 0; // Número inicial de itens exibidos

document.getElementById('meuBotao').addEventListener('click', function() {
  var botao = document.getElementById('meuBotao');
  if (botao.innerHTML === 'FAUNA') {
    botao.innerHTML = 'FLORA'; // Muda o texto do botão para 'Novo Texto'
  } else {
    botao.innerHTML = 'FAUNA'; // Retorna ao texto original quando clicado novamente
  }

  var minhaDivEsquerda = document.getElementById('esquerda');
  var minhaDivDireita = document.getElementById('direita');

  // Verifica se a div já tem a classe desejada
  if (minhaDivEsquerda.classList.contains('opacityNONE')) {
      var image = document.getElementById('lado_esquerdo');
      image.classList.add('off_esquerda');
      minhaDivEsquerda.classList.remove('opacityNONE'); // Se tiver, remove a class
      minhaDivEsquerda.classList.add('opacity'); // Se tiver, remove a classee
  } else {
    var image = document.getElementById('lado_esquerdo');
      image.classList.remove('off_esquerda');
    minhaDivEsquerda.classList.remove('opacity'); // Se tiver, remove a class
      minhaDivEsquerda.classList.add('opacityNONE'); // Se não tiver, adiciona a classe
  }

  if (minhaDivDireita.classList.contains('opacityNONE')) {
    var image = document.getElementById('lado_direito');
    image.classList.add('off_direita');
    minhaDivDireita.classList.remove('opacityNONE'); // Se tiver, remove a class
    minhaDivDireita.classList.add('opacity'); // Se tiver, remove a classee
  } else {
    var image = document.getElementById('lado_direito');
    image.classList.remove('off_direita');
    minhaDivDireita.classList.remove('opacity'); // Se tiver, remove a class
    minhaDivDireita.classList.add('opacityNONE'); // Se não tiver, adiciona a classe
}
});



formAnimal.addEventListener('submit', function(event) {
    event.preventDefault(); // Impede o comportamento padrão do formulário
  
    const nome = document.getElementById('nome').value;
    const nomeCientifico = document.getElementById('nomeCientifico').value;
    const descricao = document.getElementById('descricao').value;
    const tipo = document.getElementById('tipo').value;
    const imagem = document.getElementById('imagem').value;
    

  
    const dadosAnimal = {
      nome,
      nomeCientifico,
      descricao,
      tipo,
      imagem
      };
  
    // Requisição POST para cadastrar o médico
    axios.post(baseURL, dadosAnimal)
      .then(response => {
        console.log('Animal cadastrado com sucesso:', response.data);
        // Realizar alguma ação após o cadastro bem-sucedido, se necessário
        setTimeout(function() {
          location.reload();
      }, 1000);
      })
      .catch(error => {
        console.error('Erro ao cadastrar animal:', error);
        // Tratar erros, se houver
      });
  });

  let itensCarregados = [];
  let code = 0;
// Função para exibir a lista de médicos
function listarAnimais() {
  axios.get(`${baseURL}?tipo=${tipoFiltro}&page=${numPage}&`)
    .then(response => {
      console.log(tipoFiltro);

        const listaAnimais = document.getElementById('listaAnimais');
        const tabelaAnimais = document.getElementById('minhaTabela');

        var tbody = tabelaAnimais.getElementsByTagName('tbody')[0];
        
        //lIMPANDO A TABELA ANTES DE IMPRIMIR NOVAMENTE
        if (code == 1){
          tbody.innerHTML = '';
        }

        // Percorre os dados recebidos e cria elementos HTML para cada médico
        if (response.data && typeof response.data === 'object') {
            response.data.content.forEach(animal => {
                
                var novaLinha = document.createElement("tr");
                for (var propriedade in animal) {
                  var novaCelula = document.createElement("td");
                  novaCelula.textContent = animal[propriedade]; // Texto para a nova célula
                  novaLinha.appendChild(novaCelula);
                  
                }
                
                const btnAtualizar = document.createElement('button');
                btnAtualizar.textContent = 'Atualizar';
                btnAtualizar.classList.add('botaoPadrao');
                btnAtualizar.onclick = function() {

                  var dialogo = document.getElementById('dialogo');

                  var opcoesNome = document.getElementById('novoNome');
                  var opcoesNomeCientifico = document.getElementById('novoNomeCientifico');
                  var opcoesTipo = document.getElementById('novoTipo');
                  var opcoesImagem = document.getElementById('novoImagem');
                  var opcoesDescricao = document.getElementById('novoDescricao');
                  
                  opcoesNome.value = animal.nome;
                  opcoesNomeCientifico.value = animal.nomeCientifico;
                  opcoesTipo.value = animal.tipo;
                  opcoesImagem.value = animal.imagem;
                  opcoesDescricao.value = animal.descricao;

                  console.log(animal.id);
                  
                  dialogo.showModal();
                  exibirFormularioAtualizacao(animal.id);
                  
                  fundoEscurecido.style.display = 'block';
                
                    //exibirFormularioAtualizacao(animal.id, animal.nome);
                };

                const btnExcluir = document.createElement('button');
                btnExcluir.textContent = 'Excluir';
                btnExcluir.classList.add('botaoPadrao');
                btnExcluir.classList.add('apagar');
                btnExcluir.onclick = function() {
                if (confirm('Tem certeza que deseja excluir este animal?')) {
                    excluirAnimal(animal.id);
                }
                };

                var novaCelula = document.createElement("td");
                novaCelula.appendChild(btnAtualizar);
                novaCelula.appendChild(btnExcluir);
                novaLinha.appendChild(novaCelula);
                tbody.appendChild(novaLinha);
              });
            }
      })
    .catch(error => {
      console.error('Erro ao listar Animais:', error);
    });
}

botaoFecharDialogo.addEventListener('click', function() {
  dialogo.close();
  fundoEscurecido.style.display = 'none';
});


function exibirFormularioAtualizacao(idAnimal){
    const btnConfirmarAtualizacao = document.getElementById('btnAtt');
    btnConfirmarAtualizacao.onclick = function() {
      event.preventDefault();
      //PEGANDO OS NOVOS DADOS
      var novoNome = document.getElementById('novoNome').value;
      var novoNomeCientifico = document.getElementById('novoNomeCientifico').value;
      var novoTipo = document.getElementById('novoTipo').value;
      var novoImagem = document.getElementById('novoImagem').value;
      var novoDescricao = document.getElementById('novoDescricao').value;

      
      const dadosAtualizados = {
        id: idAnimal,
        nome: novoNome,
        nomeCientifico: novoNomeCientifico,
        descricao: novoDescricao,
        tipo: novoTipo,
        imagem: novoImagem,
        
         // Adicione outros campos que deseja atualizar
      };
  
  
      atualizarAnimal(dadosAtualizados);
    };
  }

function atualizarAnimal(dadosAtualizados) {
    axios.put(baseURL, dadosAtualizados)
      .then(response => {
        
        console.log('Animal atualizado com sucesso:', response.data);
        // Ocultar o formulário após a atualização bem-sucedida

        dialogo.close();
        fundoEscurecido.style.display = 'none';
        // Atualizar a lista de médicos após a atualização
        setTimeout(function() {
          location.reload();
      }, 1000);
        
        })
      .catch(error => {
        console.error('Erro ao atualizar animal:', error);
      });
    }

function excluirAnimal(idAnimal) {
    axios.delete(`${baseURL}/${idAnimal}`)
      .then(response => {
        console.log('Animal excluído com sucesso');
          // Atualizar a lista de médicos após a exclusão
          setTimeout(function() {
            location.reload();
        }, 1000);
      })
      .catch(error => {
        console.error('Erro ao excluir animal:', error);
      });
  }


  //LOGICA DE PAGINAÇÃO
  const botaoMaisItens = document.getElementById('botaoMaisItens');
  botaoMaisItens.addEventListener('click', aumentarItensExibidos);

  function aumentarItensExibidos() {
    numPage += 1; // Aumenta o número de itens a serem exibidos em 5
    code = 0;
    listarAnimais(); // Chama a função para buscar e exibir os novos itens
  }
  
  //LOGICA DE FILTRAR
  const filtro = document.getElementById('filtro');
  filtro.addEventListener('submit', function(event) {
    event.preventDefault(); // Impede o comportamento padrão do formulário
    numPage=0;
    tipoFiltro = document.getElementById('filtroTipo').value;
    code = 1;
    listarAnimais();
    });

listarAnimais();


