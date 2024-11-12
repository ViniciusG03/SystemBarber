document.getElementById("redefinicao-form").addEventListener("submit", async function (event) {
  event.preventDefault(); 
  
  const email = document.getElementById("email").value;

  if (!email) {
      alert("Por favor, informe seu e-mail.");
      return;
  }

  const payload = {
      email: email
  };

  try {
      const response = await fetch("https://systembarber-production.up.railway.app/api/auth/forgot-password", {
          method: "POST",
          headers: {
              "Content-Type": "application/json"
          },
          body: JSON.stringify(payload)
      });

      if (response.ok) {
          alert("Link de recuperação de senha enviado com sucesso. Verifique seu e-mail.");
      } else {
          const errorData = await response.json();
          alert("Erro ao enviar link de recuperação: " + errorData.message);
      }
  } catch (error) {
      console.error("Erro ao conectar ao backend", error);
      alert("Ocorreu um erro ao tentar enviar o link de recuperação. Por favor, tente novamente.");
  }
});
