async function carregarImagensCortesMasculinos() {
    const apiKey = 'UBJusR1aDxFKz8L9uK1EB2THJfuJBPfdbWk80uiHmqncDksfCqcZtkxz'; 
    const randomPage = Math.floor(Math.random() * 10) + 1;  // Gera um número aleatório de página entre 1 e 10
    const url = `https://api.pexels.com/v1/search?query=men's haircut&per_page=4&page=${randomPage}`;

    try {
        const response = await fetch(url, {
            headers: {
                Authorization: apiKey
            }
        });

        if (!response.ok) {
            throw new Error('Erro ao buscar imagens');
        }

        const data = await response.json();
        
        const galeria = document.querySelector('#galeria .fotosGaleria');
        galeria.innerHTML = '';  // Limpa a galeria

        data.photos.forEach(photo => {
            const img = document.createElement('img');
            img.src = photo.src.large;  // URL da imagem de boa qualidade
            img.alt = 'Foto de corte masculino';
            galeria.appendChild(img);
        });
    } catch (error) {
        console.error('Erro ao carregar imagens de cortes masculinos:', error);
    }
}
