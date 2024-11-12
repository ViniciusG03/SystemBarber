async function buscarBarbearias() {
    // Obtém a localidade digitada pelo usuário
    const localizacao = document.getElementById("localizacao").value;

    // Envia a requisição para o backend
    const response = await fetch(`https://systembarber-production.up.railway.app/api/barbearias/buscar?localizacao=${encodeURIComponent(localizacao)}`);
    const barbearias = await response.json();

    // Limpa os resultados anteriores
    const resultados = document.getElementById("resultados");
    resultados.innerHTML = '';

    // Cria um card para cada barbearia retornada
    barbearias.forEach(barbearia => {
        const card = document.createElement("div");
        card.className = "cards";

        card.innerHTML = `
            <img src="../images/img1.jpg" alt="Foto da barbearia">
            <div>
                <h3>${barbearia.nome}</h3> <br>
                <p>Aberto: ${barbearia.horarioFuncionamento}</p>
                <p>${barbearia.localizacao}</p>
                <p>${barbearia.diasServico}</p> <br>
                <a href="perfilBarbearia.html?id=${barbearia.id}">Conferir horários disponíveis -></a>
            </div>
        `;

        resultados.appendChild(card);
    });  
}
