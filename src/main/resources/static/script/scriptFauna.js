const baseURL = 'http://localhost:8080/animais'; // Substitua pela URL do seu back-end

let tipoFiltro = ''
let numPage = 10; // Número inicial de itens exibidos

// const botaoFecharDialogo = document.getElementById('fecharDialogo');

let code =0;
// Função para exibir a lista de médicos
function listarAnimais() {
    axios.get(`${baseURL}?tipo=${tipoFiltro}&size=${numPage}&`)
      .then(response => {
        console.log(tipoFiltro);
  
        const cardsContainer = document.getElementById('cardsContainer');
          
          //lIMPANDO A TABELA ANTES DE IMPRIMIR NOVAMENTE
          if (code == 1){
            cardsContainer.innerHTML = '';
          }
  
          // Percorre os dados recebidos e cria elementos HTML para cada médico
          if (response.data && typeof response.data === 'object') {
            response.data.content.forEach(animal => {
                const card = criarCard(animal); // Função para criar um card para um animal
                cardsContainer.appendChild(card);
            });
            }
        })
      .catch(error => {
        console.error('Erro ao listar Animais:', error);
      });
  }

const dialogo = document.getElementById('dialogo');
  // Função para criar um card para um animal
function criarCard(animal) {
    const card = document.createElement('div');
    card.classList.add('card');

    // Adiciona informações básicas do animal no card
    card.innerHTML = `
        <img src="${animal.imagem}"></img>
        <span class="tag">${animal.tipo}</span>
        <div class="name">${animal.nome}</div>
        <p>${animal.nomeCientifico}</p>
        <!-- Mais informações podem ser adicionadas aqui -->

        <button class="btn-more-info">Detalhes</button>
        <div class="more-info" style="display: none;">
            <!-- Informações adicionais do animal serão exibidas aqui -->
        </div>
    `;

    // Adiciona evento de clique para exibir mais informações do animal
    const btnMoreInfo = card.querySelector('.btn-more-info');
    const moreInfoDiv = card.querySelector('.more-info');
    btnMoreInfo.addEventListener('click', () => {
        const modalInfo = document.getElementById('modal-info');
        modalInfo.innerHTML = `
            <a href="${animal.imagem}" target="_blank">
                <img src="${animal.imagem}"></img>
            </a>
            <span class="tag">${animal.tipo}</span>
            <span class="tag">${animal.nomeCientifico}</span>
            <div class="name">${animal.nome}</div>
            <p>${animal.nomeCientifico}</p>
            <p class="desc">${animal.descricao}</p>
            <!-- Mais informações podem ser adicionadas aqui -->
            
        `;
        
        dialogo.showModal();
        fundoEscurecido.style.display = 'block';
        dialogo.style.display = 'block';
        
    });

    return card;
}

// botaoFecharDialogo.addEventListener('click', function() {
//     dialogo.style.display = 'none';
//     dialogo.close();
//     fundoEscurecido.style.display = 'none';
//   });

const dialogoContainer = document.querySelector('.dialogo-container');

// Adiciona evento para fechar o diálogo ao clicar fora dele
dialogoContainer.addEventListener('click', (event) => {
    if (event.target === dialogoContainer) {
        dialogo.style.display = 'none';
        dialogo.close();
        fundoEscurecido.style.display = 'none';
    }
});

const filtro = document.getElementById('filtro');
  filtro.addEventListener('submit', function(event) {
    event.preventDefault(); // Impede o comportamento padrão do formulário
    tipoFiltro = document.getElementById('filtroTipo').value;
    code = 1;
    listarAnimais();
    });


listarAnimais();
