package pij.ryan.durling.controllers;

import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.resources.QuizServer;

import java.util.Set;

public class QuizPlayerImpl implements QuizPlayer {
    private QuizServer quizServer;
    private QuizElements quizElements;
    private Menu menu;
    private Quiz quiz;


    public QuizPlayerImpl(QuizServer quizServer, QuizElements quizElements) {
        this.quizServer = quizServer;
        this.quizElements = quizElements;
    }

    @Override
    public Menu getMenu() {
        menu = quizElements.getMenu(quizServer.getQuizOptions());
        return menu;
    }

    @Override
    public void chooseQuiz(int choice) {
        quiz = quizServer.getQuiz(menu.getQuizId(choice));
    }

    @Override
    public Set<Question> getQuestions() {
        return quiz.getQuestions();
    }

    @Override
    public String getName() {
        return quiz.getName();
    }
}
