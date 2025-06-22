const bird = document.getElementById('bird');
const container = document.getElementById('game-container');
const scoreDisplay = document.getElementById('score');

let birdY = 200;
let velocity = 0;
let gravity = 0.6;
let isGameOver = false;
let score = 0;

function jump() {
  if (!isGameOver) velocity = -10;
}

document.addEventListener('keydown', (e) => {
  if (e.code === 'Space') jump();
});

function createPipe() {
  const pipeGap = 150;
  const pipeTopHeight = Math.floor(Math.random() * 250) + 50;

  const pipeTop = document.createElement('div');
  pipeTop.classList.add('pipe', 'top');
  pipeTop.style.height = pipeTopHeight + 'px';
  pipeTop.style.right = '-60px';

  const pipeBottom = document.createElement('div');
  pipeBottom.classList.add('pipe', 'bottom');
  pipeBottom.style.height = (600 - pipeTopHeight - pipeGap) + 'px';
  pipeBottom.style.right = '-60px';

  container.appendChild(pipeTop);
  container.appendChild(pipeBottom);

  const pipeInterval = setInterval(() => {
    if (isGameOver) {
      clearInterval(pipeInterval);
      pipeTop.remove();
      pipeBottom.remove();
      return;
    }

    let pipeRight = parseInt(pipeTop.style.right);
    if (pipeRight > 400) {
      pipeTop.remove();
      pipeBottom.remove();
      clearInterval(pipeInterval);
    } else {
      pipeRight += 2;
      pipeTop.style.right = pipeRight + 'px';
      pipeBottom.style.right = pipeRight + 'px';

      // Score update
      if (pipeRight === 82) {
        score++;
        scoreDisplay.textContent = 'Score: ' + score;
      }

      // Collision detection
      const birdTop = birdY;
      const birdBottom = birdY + 40;
      const pipeLeft = 400 - pipeRight;
      const pipeRightX = pipeLeft + 60;

      if (
        pipeLeft < 120 &&
        pipeRightX > 80 &&
        (birdTop < pipeTopHeight || birdBottom > pipeTopHeight + pipeGap)
      ) {
        endGame();
      }
    }
  }, 20);
}

function endGame() {
  isGameOver = true;
  alert('Game Over! Final Score: ' + score);
  location.reload();
}

function gameLoop() {
  if (isGameOver) return;

  velocity += gravity;
  birdY += velocity;

  if (birdY < 0) birdY = 0;
  if (birdY > 560) {
    endGame();
    return;
  }

  bird.style.top = birdY + 'px';
  requestAnimationFrame(gameLoop);
}

// Start pipes at intervals
setInterval(() => {
  if (!isGameOver) createPipe();
}, 2000);

// Start game loop
gameLoop();