package pij.ryan.durling.controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import pij.ryan.durling.factories.OptionFactory;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.serializers.QuizSerializer;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

@Singleton
public class QuizCtrlImpl implements QuizCtrl {

    private final TreeMap<Integer, Quiz> quizzes;
    private OptionFactory optionFactory;
    private QuizSerializer quizSerializer;
    private final TreeMap<Integer, Quiz> closed;

    @Inject
    public QuizCtrlImpl(OptionFactory optionFactory, QuizSerializer quizSerializer) {
        this.optionFactory = optionFactory;
        this.quizSerializer = quizSerializer;
        if (this.quizSerializer.dataExists()) {
            this.quizSerializer.deserialize();
            quizzes = quizSerializer.getQuizzes();
            System.out.println(quizzes);
        } else {
            quizzes = new TreeMap<>();
        }
        closed = new TreeMap<>();
        Runtime.getRuntime().addShutdownHook(flushHook());
    }

    @Override
    public void add(Quiz quiz) throws RemoteException {
        quizzes.put(quiz.getId(), quiz);
    }

    @Override
    public Quiz getQuiz(int id) {
        return quizzes.get(id);
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
    public Set<QuizOption> getClosedOptions() {
        Set<QuizOption> options = new HashSet<>();
        closed.values().forEach(quiz -> {
            try {
                options.add(optionFactory.createQuizOption(quiz.getId(), quiz.getName()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        return options;
    }

    @Override
    public void open(int quizId) {
        Quiz quiz = closed.remove(quizId);
        quizzes.put(quizId, quiz);
    }

    @Override
    public void close(int quizId) {
        Quiz quiz = quizzes.remove(quizId);
        closed.put(quizId, quiz);
    }

    private Thread flushHook() {
        return new Thread(this::flush);
    }

    private void flush() {
        quizSerializer.serialize(quizzes);
    }
}
