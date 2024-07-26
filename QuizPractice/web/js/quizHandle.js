let pg = 0;
//const quizData = [
//    {
//        "question": "Which is the most appropriate section for statements that are believed to be true in the absence of proof or definitive knowledge in a vision and scope template?",
//        "options": [
//            "Business requirements section",
//            "Success metrics section",
//            "Business opportunity section",
//            "Business assumptions and dependencies section"
//        ],
//        "correctAnswer": "Business assumptions and dependencies section",
//        "lesson": "The 'Business assumptions and dependencies section' is used for statements believed to be true without definitive proof in a vision and scope template."
//    }
//];
let currentQuestionIndex = 0;
let correctAnswers = 0;
let incorrectAnswers = 0;
const questionElement = document.getElementById('question');
const optionsElement = document.getElementById('options');
const progressElement = document.getElementById('progress');
const feedbackElement = document.getElementById('feedback');
const dontKnowElement = document.getElementById('dontKnow');
const resultsElement = document.getElementById('results');
const correctSound = document.getElementById('correctSound');
const incorrectSound = document.getElementById('incorrectSound');
function initializeQuiz() {
    currentQuestionIndex = 0;
    correctAnswers = 0;
    incorrectAnswers = 0;
    pg = 0;
    resultsElement.innerHTML = '';
    resetDontKnowButton();
    loadQuestion();
    updateProgress();
}

function loadQuestion() {
    const currentQuestion = quizData[currentQuestionIndex];
    questionElement.textContent = currentQuestion.question;
    optionsElement.innerHTML = '';
    feedbackElement.innerHTML = '';
    feedbackElement.className = 'feedback';
    dontKnowElement.style.display = 'block';

    currentQuestion.options.forEach((option, index) => {
        const button = document.createElement('button');
        button.className = 'option';
        button.textContent = `${index + 1}.  ${option.answer_detail}`;  // Changed this line
        button.addEventListener('click', () => checkAnswer(option));
        optionsElement.appendChild(button);
    });

    dontKnowElement.onclick = showCorrectAnswer;
}

function checkAnswer(selectedAnswer) {
    console.log(selectedAnswer.answer_detail);
    const currentQuestion = quizData[currentQuestionIndex];
    console.log(currentQuestion.correctAnswer);
    if (selectedAnswer.answer_detail === currentQuestion.correctAnswer) {
        correctSound.play();
        feedbackElement.textContent = "Correct!";
        feedbackElement.className = 'feedback correct';
        correctAnswers++;
    } else {
        incorrectSound.play();
        feedbackElement.textContent = `Incorrect. The correct answer is: ${currentQuestion.correctAnswer}`;
        feedbackElement.className = 'feedback incorrect';
        incorrectAnswers++;
    }

    disableOptions();
    updateProgress();
    setTimeout(nextQuestion, 4000);
}

function showCorrectAnswer() {
    const currentQuestion = quizData[currentQuestionIndex];
    feedbackElement.style.display = 'block';
    feedbackElement.textContent = `Answer: ${currentQuestion.correctAnswer}`;
    feedbackElement.className = 'feedback';
    incorrectAnswers++;
    disableOptions();
    updateProgress();
    setTimeout(nextQuestion, 4000);
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
    resultsElement.innerHTML = `
            <h2>Quiz Results</h2>
            <p>Correct Answers: ${correctAnswers}</p>
            <p>Incorrect Answers: ${incorrectAnswers}</p>
            <p>Total Questions: ${quizData.length}</p>
            <button class="try-again" onclick="reload()">Try Again</button>
        `;
    progressElement.style.width = '100%';
}

function reload() {
    window.location.reload();
}

// Start the quiz
initializeQuiz();