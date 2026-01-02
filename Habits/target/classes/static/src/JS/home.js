/* ===================== ELEMENTOS ===================== */
const content = document.querySelector(".content");
const form = document.getElementById("formHabito");
const modalHabito = document.getElementById("Modal_cadastrar_habito");
const perfilTela = document.getElementById("telaConfig");
const hotbarBtns = document.querySelectorAll(".hotbar-btn");


/* ===================== HOTBAR ===================== */
function setHotbarAtivo(btn) {
    hotbarBtns.forEach(b => b.classList.remove("ativo"));
    btn.classList.add("ativo");
}

/* ===================== MODAL HÁBITO ===================== */
function abrirModalHabito() {
    modalHabito.style.display = "flex";
    perfilTela.style.display = "none";
    document.body.style.overflow = "hidden";
    content.classList.add("blur");
    setHotbarAtivo(document.querySelector(".hotbar-btn.add"));
}

function fecharModalHabito() {
    modalHabito.style.display = "none";
    document.body.style.overflow = "auto";
    content.classList.remove("blur");
    setHotbarAtivo(document.querySelector("#homeBtn"));
    form.reset();
}

// Fechar modal clicando fora
modalHabito.addEventListener("click", e => {
    if (e.target === modalHabito) fecharModalHabito();
});

/* ===================== PERFIL / CONFIG ===================== */
function abrirPerfil() {
    perfilTela.style.display = "flex";
    modalHabito.style.display = "none";
    document.body.style.overflow = "hidden";
    content.classList.add("blur");
    setHotbarAtivo(document.querySelector("#perfilBtn"));
}

function fecharPerfil() {
    perfilTela.style.display = "none";
    document.body.style.overflow = "auto";
    content.classList.remove("blur");
    setHotbarAtivo(document.querySelector("#homeBtn"));
}

// Fechar clicando fora da modal-box do perfil
perfilTela.addEventListener("click", e => {
    if (e.target === perfilTela) fecharPerfil();
});

/* ===================== HOTBAR CLICK ===================== */
hotbarBtns.forEach(btn => {
    btn.addEventListener("click", () => {
        if (btn.classList.contains("add")) abrirModalHabito();
        else if (btn.id === "perfilBtn") abrirPerfil();
        else abrirHome();
    });
});

/* ===================== HOME ===================== */
function abrirHome() {
    perfilTela.style.display = "none";
    modalHabito.style.display = "none";
    content.style.display = "flex";
    document.body.style.overflow = "auto";
    content.classList.remove("blur");
    setHotbarAtivo(document.querySelector("#homeBtn"));
}




/* ===================== HÁBITOS ===================== */

async function pegarHabitos() {
    const baseURL = window.location.origin;
    console.log("%cGET /habitos", "color: blue; font-weight:bold;");

    const res = await fetch(`${baseURL}/ListarHabitos`);

    if (!res.ok) {
        console.warn("Backend retornou erro:", res.status);
        renderizarHabitos([]); // 👈 força estado vazio
        return;
    }

    const dados = await res.json();
    console.log(dados);

    if (!Array.isArray(dados)) {
        console.warn("Resposta não é um array:", dados);
        renderizarHabitos([]); // 👈 força estado vazio
        return;
    }

    renderizarHabitos(dados);
}


async function cadastrarHabito(novoHabito) {
    const baseURL = window.location.origin;
    console.log("%cPOST /habitos", "color: purple; font-weight:bold;");
    console.log("Dados enviados:", novoHabito);
    await fetch(`${baseURL}/cadastrarHabito`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(novoHabito)
    });
    await pegarHabitos();
}

function renderizarHabitos(habitos) {
    content.innerHTML = "";

    if (!habitos || habitos.length === 0) {
        content.innerHTML = "<p style='text-align:center;color:#777'>Nenhum hábito cadastrado</p>";
        return;
    }

    habitos.forEach(habito => {
        const card = document.createElement("div");
        card.className = "card-habito";
        card.style.borderLeft = `5px solid ${habito.cor || "#A8DADC"}`;
        card.innerHTML = `
            <div style="display:flex; align-items:center; gap:10px;">
                <input type="checkbox" ${habito.concluido ? "checked" : ""}>
                <div class="card-titulo ${habito.concluido ? "concluido" : ""}">${habito.titulo}</div>
            </div>
            <button class="btn-excluir">Excluir</button>
        `;

        const checkbox = card.querySelector("input[type='checkbox']");
        const titulo = card.querySelector(".card-titulo");




        checkbox.addEventListener("change", async () => {
            habito.concluido = checkbox.checked;
            console.log("%cPOST /habitos/concluir", "color: orange; font-weight:bold;");
            console.log("Dados enviados:", { id: habito.id, concluido: checkbox.checked });


             const baseURL = window.location.origin;
            await fetch(`${baseURL}/Atualizar`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ id: habito.id, concluido: checkbox.checked })
            });
            titulo.classList.toggle(checkbox.checked);
            await pegarHabitos();
        });



        card.querySelector(".btn-excluir").addEventListener("click", async () => {
            console.log("%cPOST /habitos/excluir", "color: red; font-weight:bold;");
            console.log("Dados enviados:", { id: habito.id });
            console.log(habito.id);
            const baseURL = window.location.origin;
            await fetch(`${baseURL}/Deletar/${habito.id}`, {
                method: "DELETE",  // Método DELETE aqui
                headers: {
                    "Content-Type": "application/json"
                },
            });
            await pegarHabitos();
        });

        content.appendChild(card);
    });
}



/* ===================== FORMULÁRIO ===================== */

form.addEventListener("submit", e => {
    e.preventDefault();

    // Mapa de cores por tag
    const cores = {
        estudar: "#4CAF50",
        treinar: "#FF9800",
        saude: "#E91E63",
        relacao: "#9C27B0",
        alimentacao: "#8BC34A",
        trabalho: "#2196F3",
        financas: "#795548",
        espiritualidade: "#3F51B5",
        lazer: "#00BCD4"
    };

    const tagSelecionada = document.getElementById("tag").value;

    const novoHabito = {
        titulo: document.getElementById("titulo").value.trim(),
        tag: tagSelecionada, // value do select
        descricao: document.getElementById("descricao").value.trim(),
        data: document.getElementById("data").value,
        concluido: false,
        cor: cores[tagSelecionada] || "#A8DADC", // pega a cor do mapa ou padrão
        repetir: document.getElementById("repetir").checked
    };

    console.log("Enviando para backend:", structuredClone(novoHabito));
    cadastrarHabito(novoHabito);
    fecharModalHabito();
});


/* ===================== INIT ===================== */
abrirHome();
pegarHabitos();




  /*Colocar a Data Atual*/
  const dataAtual = new Date().toISOString().split('T')[0];
  document.getElementById('data').value = dataAtual;



