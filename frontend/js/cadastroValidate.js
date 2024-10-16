document.getElementById("buttonCadastrar").addEventListener("click", async function(event) {
    event.preventDefault();

    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const confirmEmail = document.getElementById("confirm-email").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm-password").value;

    if (name === "" || email === "" || confirmEmail === "" || password === "" || confirmPassword === "") {
        alert("Todos os campos devem ser preenchidos");
        return;
    }

    if (email !== confirmEmail) {
        alert("Os e-mails não coincidem");
        return;
    }

    if (password !== confirmPassword) {
        alert("As senhas não coincidem");
        return;
    }

    const payload = {
        name: name,
        email: email,
        password: password
    };

    try {
        const response = await fetch('http://localhost:8080/api/auth/cadastro', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });

        if (response.ok) {
            alert("Cadastro realizado com sucesso!");
            window.location.href = "login.html";
        } else {
            const errorData = await response.json();
            alert("Erro ao realizar cadastro: " + errorData.message);
        }
    } catch (error) {
        console.error("Erro ao conectar ao backend", error);
        alert("Ocorreu um erro ao tentar realizar o cadastro. Por favor, tente novamente.");
    }
});
