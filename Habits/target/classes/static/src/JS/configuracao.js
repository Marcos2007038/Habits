document.addEventListener("DOMContentLoaded", function () {

    const btnLogout = document.getElementById("btnLogout");
    const filtroPizza = document.getElementById("filtroPizza");
    const graficoPizza = document.getElementById("graficoPizza");
    const btnPerfil = document.getElementById("perfilBtn");
    const telaConfig = document.getElementById("telaConfig");

    let carregandoPizza = false;

    // 🔥 instância do gráfico de pizza
    let chartPizza = null;

    function Logout() {
        window.location.href = "/logout";
    }

    function mostrarCarregando() {
        console.log("Carregando dados...");
    }

    function esconderCarregando() {
        console.log("Carregamento concluído.");
    }

    /* =======================
       FUNÇÃO PRINCIPAL DE RENDERIZAÇÃO
    ======================== */
    function renderizarGraficosAutomaticamente() {
        PegarApelidoAndEmail();
        // Chamando a função de pegar dados do gráfico de pizza com o filtro "mes"
        pegarDadosGraficoPizza("mes");
    }

    /* =======================
       GRÁFICO DE PIZZA
    ======================== */
    async function pegarDadosGraficoPizza(filtro) {
        if (carregandoPizza) return;

        carregandoPizza = true;
        mostrarCarregando();

        try {
            const response = await fetch("/GraficoPizza", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ filtroPizza: filtro })
            });

            if (!response.ok) throw new Error("Erro ao buscar dados da pizza");

            const data = await response.json();
            console.log("Pizza:", data);
            if (!data || !data.labels || data.labels.length === 0) {
                // Caso não venha dados, remove o gráfico
                removerGraficoPizza();
            } else {
                renderizarGraficoPizza(data);
            }

        } catch (err) {
            console.error(err);
        } finally {
            carregandoPizza = false;
            esconderCarregando();
        }
    }

    function renderizarGraficoPizza(data) {
        const ctx = graficoPizza.getContext("2d");

        if (chartPizza) chartPizza.destroy();

        chartPizza = new Chart(ctx, {
            type: "pie",
            data: {
                labels: data.labels,
                datasets: [{
                    label: "Distribuição de hábitos",
                    data: data.data,
                    backgroundColor: data.backgroundColor
                }]
            }
        });
    }

    function removerGraficoPizza() {
        // Remove o gráfico de pizza, limpa o canvas
        const ctx = graficoPizza.getContext("2d");
        if (chartPizza) {
            chartPizza.destroy(); // Destroi o gráfico de pizza existente
            chartPizza = null; // Limpa a variável do gráfico
        }
        ctx.clearRect(0, 0, graficoPizza.width, graficoPizza.height); // Limpa o canvas
    }

    /* =======================
       EVENTOS
    ======================== */
    filtroPizza.addEventListener("change", () => {
        pegarDadosGraficoPizza(filtroPizza.value);
    });

    btnPerfil.addEventListener("click", () => {
        telaConfig.style.display = "block";
        renderizarGraficosAutomaticamente(); // Chama a função principal para renderizar automaticamente
    });

    btnLogout.addEventListener("click", Logout);

    // 🧠 Quando a página carrega, chama a função principal para renderizar os gráficos automaticamente
    renderizarGraficosAutomaticamente();

});


function PegarApelidoAndEmail() {
    const apelido = document.querySelector(".apelido");
    const email = document.querySelector(".email");  // Corrigido aqui

    fetch('/PegarDadoConfig')
        .then(res => res.json())  // Converte a resposta para JSON
        .then(data => {
            apelido.textContent = data.Apelido;
            email.textContent = data.Email;
        })  // Exibe os dados no console
        .catch(error => console.log('Ao tentar pegar email e apelido:', error));  // Caso haja erro
}
