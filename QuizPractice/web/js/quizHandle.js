let pg = 0;
let currentQuestionIndex = 0;
let correctAnswers = 0;
let incorrectAnswers = 0;
let correctQuestions = [];
let incorrectQuestions = [];
const questionElement = document.getElementById('question');
const optionsElement = document.getElementById('options');
const progressElement = document.getElementById('progress');
const feedbackElement = document.getElementById('feedback');
const dontKnowElement = document.getElementById('dontKnow');
const nextQuestionElement = document.getElementById('next');
const resultsElement = document.getElementById('results');
const correctSound = document.getElementById('correctSound');
const incorrectSound = document.getElementById('incorrectSound');

function initializeQuiz() {
    pg = 0;
    currentQuestionIndex = 0;
    correctAnswers = 0;
    incorrectAnswers = 0;
    correctQuestions = [];
    incorrectQuestions = [];
    resultsElement.innerHTML = '';
    resetDontKnowButton();
    loadQuestion();
    updateProgress();
}

function tryAgain() {
    window.location.reload();
}

function loadQuestion() {
    const currentQuestion = quizData[currentQuestionIndex];
    questionElement.textContent = currentQuestion.question;
    optionsElement.innerHTML = '';
    feedbackElement.innerHTML = '';
    feedbackElement.className = 'feedback';
    dontKnowElement.style.display = 'block';
    nextQuestionElement.style.display='none';

    currentQuestion.options.forEach((option, index) => {
        const button = document.createElement('button');
        button.className = 'option';
        button.textContent = `${index + 1}. ${option.answer_detail}`;
        button.addEventListener('click', () => checkAnswer(option));
        optionsElement.appendChild(button);
    });

    dontKnowElement.onclick = showCorrectAnswer;
}

function checkAnswer(selectedAnswer) {
    const currentQuestion = quizData[currentQuestionIndex];
    if (selectedAnswer.answer_detail === currentQuestion.correctAnswer) {
        correctSound.play();
        feedbackElement.textContent = "Correct!";
        feedbackElement.className = 'feedback correct';
        correctAnswers++;
        correctQuestions.push(currentQuestion.question); // Store the question number
    } else {
        incorrectSound.play();
        feedbackElement.textContent = `Incorrect. The correct answer is: ${currentQuestion.correctAnswer}`;
        feedbackElement.className = 'feedback incorrect';
        incorrectAnswers++;
        incorrectQuestions.push(currentQuestion.question); // Store the question number
    }
    nextQuestionElement.style.display = 'block';
    disableOptions();
    updateProgress();
}

function nextQues() {
    setTimeout(nextQuestion, 100);
}

function showCorrectAnswer() {
    const currentQuestion = quizData[currentQuestionIndex];
    feedbackElement.style.display = 'block';
    feedbackElement.textContent = `Answer: ${currentQuestion.correctAnswer}`;
    feedbackElement.className = 'feedback';
    incorrectAnswers++;
    disableOptions();
    updateProgress();
    nextQuestionElement.style.display = 'block';
}

function disableOptions() {
    const options = optionsElement.getElementsByClassName('option');
    for (let option of options) {
        option.disabled = true;
    }
    dontKnowElement.style.pointerEvents = 'none';
}

function resetDontKnowButton() {
    dontKnowElement.style.pointerEvents = 'auto';
    dontKnowElement.style.display = 'block';
}

function nextQuestion() {
    currentQuestionIndex++;
    if (currentQuestionIndex < quizData.length) {
        loadQuestion();
        resetDontKnowButton();
    } else {
        showResults();
    }
}

function updateProgress() {
    const progress = ((currentQuestionIndex + 1) * pg / quizData.length) * 100;
    pg = 1;
    progressElement.style.width = `${progress}%`;
}

function showResults() {
    questionElement.textContent = "Quiz completed!";
    optionsElement.innerHTML = '';
    dontKnowElement.style.display = 'none';
    feedbackElement.style.display = 'none';
    nextQuestionElement.style.display = 'none';
    resultsElement.innerHTML = `
        <h2>Quiz Results</h2>
        <p>Correct Answers: ${correctAnswers}</p>
        <p>Incorrect Answers: ${incorrectAnswers}</p>
        <p>Total Questions: ${quizData.length}</p>
        
        <button class="try-again" onclick="tryAgain()">Try Again</button>
    `;
    progressElement.style.width = '100%';
}

// Start the quiz
initializeQuiz();
