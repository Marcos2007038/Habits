const form = document.getElementById("form");

form.addEventListener("submit", function(e) {
    e.preventDefault(); // impede envio automático

    const email = document.getElementById('input_email').value;
    const senha = document.getElementById('input_senha').value;

    if(!TratandoDados(email, senha)){ // validação
        mostrarInformacao("INFORMAÇÃO", "Email ou senha estão incorretos. Por favor, tente novamente!");
        return; // não envia
    }

    form.submit(); // envia o formulário se estiver correto
});




    

(function() {
  
/*Mostrar Senha*/
const div = document.getElementById('status_eye');
const img_eye = document.querySelector('.img_eye');
const campo_senha = document.querySelector('.input_senha');

div.addEventListener('click', function() {
    if(campo_senha.type=="password"){
        campo_senha.type="text";
        img_eye.src="../Img/eye.svg";
    }else {
        campo_senha.type = 'password'; // oculta a senha
        img_eye.src="../Img/eye-slash.svg";
    }
});

})();



/*Redirecionando para cadastrar */
const Tag_a = document.getElementById('Tag_a');
Tag_a.addEventListener('click', function() {
    window.location.href = "cadastrar_user.php";
});




/* Tratando os dados */
function TratandoDados(email, senha) {
    // Verifica se o email é um Gmail válido
    if (!email.match(/[a-z0-9._%+-]+@gmail\.com/i)) {
        return false;
    }
    // Verifica se a senha tem mais de 8 caracteres
    if (senha.length <= 8) return false;

    let temMaiuscula = false;
    let temNumero = false;
    let temArroba = false;

    for (let char of senha) {
        if (char >= 'A' && char <= 'Z') temMaiuscula = true;
        if (char >= '0' && char <= '9') temNumero = true;
        if (char === '@') temArroba = true;
    }

    // Retorna true apenas se todos os critérios forem atendidos
    return temMaiuscula && temNumero && temArroba;
}





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