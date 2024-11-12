document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const email = document.getElementById("email").value;
    const senha = document.getElementById("password").value;

    try {
        const response = await fetch('https://systembarber-production.up.railway.app/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email: email, senha: senha })
        });

        if (response.ok) {
            const data = await response.json();
            alert("Login realizado com sucesso!");
            localStorage.setItem("token", data.token); 
            window.location.href = "index.html";
        } else {
            const errorData = await response.json();
            alert("Erro ao realizar login: " + errorData.message);
        }
    } catch (error) {
        console.error("Erro ao conectar ao backend", error);
        alert("Ocorreu um erro ao tentar realizar o login. Por favor, tente novamente.");
    }
});