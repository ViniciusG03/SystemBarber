async function agendarHorario() {
    const urlParams = new URLSearchParams(window.location.search);
    const idBarbearia = urlParams.get('id');
    
    const nome = document.getElementById("nome").value;
    const email = document.getElementById("email").value;
    const telefone = document.getElementById("telefone").value;
    const pagamento = document.getElementById("pagamento").value;
    const dataHorario = document.getElementById("dataHorario").value;

    const agendamento = {
        idBarbearia: parseInt(idBarbearia, 10), 
        nomeCliente: nome,
        emailCliente: email,
        telefoneCliente: telefone,
        formaPagamento: pagamento.toUpperCase(),
        dataHorario: dataHorario
    };

    try {
        const response = await fetch("systembarber-production.up.railway.app/api/agendamentos", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(agendamento)
        });

        if (response.ok) {
            alert("Agendamento realizado com sucesso!");
        } else {
            console.error("Erro ao realizar agendamento:", await response.text());
        }
    } catch (error) {
        console.error("Erro ao conectar com o backend:", error);
    }
}
