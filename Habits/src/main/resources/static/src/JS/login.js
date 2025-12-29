


(async () => {

    const params = new URLSearchParams(window.location.search);

    // const erro = params.get("erro");
    const msg  = params.get("msg");
    if(msg){
        mostrarInformacao("SUCESSO",msg);
    }

    const erro  = params.get("erro");
    if(erro){
        mostrarInformacao("ERRO",erro);
    }


})();




/*Mostrar Senha*/
const div = document.getElementById('status_eye');
const img_eye = document.querySelector('.img_eye');
const campo_senha = document.querySelector('.input_senha');

div.addEventListener('click', function() {
    if(campo_senha.type=="password"){
        campo_senha.type="text";
        img_eye.src="../../src/img/icones/eye.svg";
    }else {
        campo_senha.type = 'password'; // oculta a senha
        img_eye.src="../../src/img/icones/eye-slash.svg";
    }
});



/*Redirecionando para cadastrar */
const Tag_a = document.getElementById('Tag_a');
Tag_a.addEventListener('click', function() {
    window.location.href = "cadastrar_user.html";
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