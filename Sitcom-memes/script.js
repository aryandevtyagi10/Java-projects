// Sample meme data (replace with API or database in production)
const memes = [
    {
        title: "Chandler's Sarcasm",
        sitcom: "Friends",
        image: "https://via.placeholder.com/300x200.png?text=Chandler+Meme",
        tags: ["friends", "chandler", "sarcasm"],
        download: "chandler_meme.jpg"
    },
    {
        title: "Dwight's Beet Farm",
        sitcom: "The Office",
        image: "https://via.placeholder.com/300x200.png?text=Dwight+Meme",
        tags: ["the office", "dwight", "beets"],
        download: "dwight_meme.jpg"
    },
    {
        title: "Kramer's Entrance",
        sitcom: "Seinfeld",
        image: "https://via.placeholder.com/300x200.png?text=Kramer+Meme",
        tags: ["seinfeld", "kramer", "funny"],
        download: "kramer_meme.jpg"
    },
    {
        title: "Drake Meme",
        sitcom: "Trending",
        image: "https://via.placeholder.com/300x200.png?text=Drake+Meme",
        tags: ["trending", "drake", "meme"],
        download: "drake_meme.jpg"
    }
];

// Function to display memes
function displayMemes(memeList) {
    const memeGrid = document.getElementById('memeGrid');
    memeGrid.innerHTML = '';

    memeList.forEach(meme => {
        const memeCard = document.createElement('div');
        memeCard.classList.add('meme-card');
        memeCard.innerHTML = `
            <img src="${meme.image}" alt="${meme.title}">
            <h3>${meme.title} (${meme.sitcom})</h3>
            <a href="${meme.download}" download>Download Meme</a>
        `;
        memeGrid.appendChild(memeCard);
    });
}

// Search function
function searchMemes() {
    const searchInput = document.getElementById('searchInput').value.toLowerCase();
    const filteredMemes = memes.filter(meme =>
        meme.title.toLowerCase().includes(searchInput) ||
        meme.sitcom.toLowerCase().includes(searchInput) ||
        meme.tags.some(tag => tag.toLowerCase().includes(searchInput))
    );
    displayMemes(filteredMemes);
}

// Initial display
document.addEventListener('DOMContentLoaded', () => {
    displayMemes(memes);
});
