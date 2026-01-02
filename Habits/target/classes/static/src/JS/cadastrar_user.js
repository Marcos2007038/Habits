const form = document.getElementById('form');
// const resultado = document.getElementById('resultado');

form.addEventListener('submit', function(event) {
    event.preventDefault(); 

  
    const apelido = document.querySelector('.campo_apelido').value;
    const email = document.querySelector('.campo_email').value;
    const senha = document.querySelector('.campo_senha').value;
    const senhaConfirm = document.querySelector('.campo_senha_Confirm').value;
   
    console.log(senha);
    const Dados = {
            "apelido":apelido,
            "email":email,
            "senha":senha,
            "senhaConfirm":senhaConfirm
    };

    console.log(Dados);

    const baseURL = window.location.origin; // ex: 'http://localhost:3000'
    fetch(`${baseURL}/cadastrar`, { // substitua pela URL do seu servidor
        method: 'POST', // método de envio
        headers: {
            'Content-Type': 'application/json' // diz que os dados são JSON
        },
        body: JSON.stringify(Dados) // transforma o objeto JS em JSON
    })
    .then(response => response.json()) // transforma a resposta em JSON
    .then(data => {
        console.log('Sucesso:', data);
        if(!data["status"]){
            mostrarInformacao("INFORMAÇÃO",data.message);
        }else{
            window.location.href = "login.html?msg=Usuario cadastrado com sucesso";
        }
    })
    .catch(error => {
        mostrarInformacao("ERRO","Erro no sistema interno!")
    });
   

    
});



/*Redirecionando para cadastrar */
const Tag_a = document.getElementById('Tag_a');
Tag_a.addEventListener('click', function() {
     window.location.href = "login.html";
});



/*Modal*/
function mostrarInformacao(titulo,texto){
    
    // Pegando o titulo e texto e botão, e overlay
    const overlay=document.querySelector('#overlay');
    const titulo_info=document.querySelector('.h2_titulo_modal');
    const texto_info=document.querySelector('.p_texto_modal');
    const botao=document.querySelector('#botao_modal_infor');

    overlay.style.display="block";

    titulo_info.textContent=titulo; 
    texto_info.textContent=texto;


    botao.addEventListener("click", () => {
        overlay.style.display="none";
    });

}








