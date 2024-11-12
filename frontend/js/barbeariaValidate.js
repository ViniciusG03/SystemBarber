async function cadastrarBarbearia() {
    const nome = document.getElementById("nome").value;
    const email = document.getElementById("email").value;
    const telefone = document.getElementById("telefone").value;
    const formasPagamento = document.getElementById("pagamento").value;
    const quantidadeFuncionarios = document.getElementById("funcionarios").value;
    const proprietario = document.getElementById("proprietario").value;
    const diasServico = document.getElementById("dias_servico").value;
    const localizacao = document.getElementById("localizacao").value;
    const horarioFuncionamento = document.getElementById("horario_funcionamento").value;

    const barbeariaData = {
        nome: nome,
        email: email,
        telefone: telefone,
        formasPagamento: formasPagamento,
        quantidadeFuncionarios: quantidadeFuncionarios,
        proprietario: proprietario,
        diasServico: diasServico,
        localizacao: localizacao,
        horarioFuncionamento: horarioFuncionamento
    };

    // Envia os dados para o backend usando fetch
    try {
        const response = await fetch("https://systembarber-production.up.railway.app/api/barbearias", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(barbeariaData)
        });

        if (response.ok) {
            alert("Barbearia cadastrada com sucesso!");
            const data = await response.json();
            console.log("Resposta da API:", data);
        } else {
            throw new Error("Erro ao cadastrar barbearia.");
        }
    } catch (error) {
        console.error("Erro:", error);
        alert("Ocorreu um erro ao cadastrar a barbearia. Tente novamente.");
    }
}
