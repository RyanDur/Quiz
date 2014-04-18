package pij.ryan.durling.controllers;

import pij.ryan.durling.factories.OptionFactory;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class QuizCtrlImpl implements QuizCtrl {

    private final TreeMap<Integer, Quiz> quizzes;
    private OptionFactory optionFactory;

    public QuizCtrlImpl(OptionFactory optionFactory) {
        this.optionFactory = optionFactory;
        quizzes = new TreeMap<>();
    }

    @Override
    public void add(Quiz quiz) throws RemoteException {
        quizzes.put(quiz.getId(), quiz);
    }

    @Override
    public Set<QuizOption> getQuizOptions() {
        Set<QuizOption> options = new HashSet<>();
        quizzes.values().forEach(quiz -> {
            try {
                options.add(optionFactory.createQuizOption(quiz.getId(), quiz.getName()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        return options;
    }

    @Override
    public Quiz getQuiz(int id) {
        return quizzes.get(id);
    }
}
