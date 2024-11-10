async function carregarBackgroundImagem() {
    const apiKey = 'UBJusR1aDxFKz8L9uK1EB2THJfuJBPfdbWk80uiHmqncDksfCqcZtkxz';
    const randomPage = Math.floor(Math.random() * 10) + 1;  
    const url = `https://api.pexels.com/v1/search?query=barber%20shop&per_page=1&page=${randomPage}`;

    try {
        const response = await fetch(url, {
            headers: {
                Authorization: apiKey
            }
        });

        if (!response.ok) {
            throw new Error('Erro ao buscar imagem');
        }

        const data = await response.json();

        if (data.photos.length > 0) {
            const backgroundImageUrl = data.photos[0].src.large2x;

            // Aplicação do estilo de fundo
            const headerElement = document.querySelector('header');
            headerElement.style.backgroundImage = `url(${backgroundImageUrl})`;
            headerElement.style.backgroundSize = 'cover';
            headerElement.style.backgroundPosition = 'center';
            headerElement.style.backgroundRepeat = 'no-repeat';
        }
    } catch (error) {
        console.error('Erro ao carregar imagem de fundo:', error);
    }
}