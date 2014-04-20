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
import java.util.concurrent.ConcurrentSkipListMap;

@Singleton
public class QuizCtrlImpl implements QuizCtrl {

    private ConcurrentSkipListMap<Integer, Quiz> available;
    private ConcurrentSkipListMap<Integer, Quiz> closed;
    private OptionFactory optionFactory;

    @Inject
    public QuizCtrlImpl(OptionFactory optionFactory, QuizSerializer quizSerializer) {
        this.optionFactory = optionFactory;
        setupQuizzes(quizSerializer);
        quizSerializer.persist(available, closed);
    }

    @Override
    public synchronized void add(Quiz quiz) throws RemoteException {
        available.put(quiz.getId(), quiz);
    }

    @Override
    public synchronized Quiz getQuiz(int id) {
        return available.get(id);
    }

    @Override
    public synchronized Set<QuizOption> getQuizOptions() {
        return getOptions(available);
    }

    @Override
    public synchronized Set<QuizOption> getClosedOptions() {
        return getOptions(closed);
    }

    @Override
    public synchronized void open(int quizId) {
        Quiz quiz = closed.remove(quizId);
        available.put(quizId, quiz);
    }

    @Override
    public synchronized void close(int quizId) {
        Quiz quiz = available.remove(quizId);
        closed.put(quizId, quiz);
    }

    private void setupQuizzes(QuizSerializer quizSerializer) {
        if (quizSerializer.dataExists()) {
            closed = quizSerializer.getClosed();
            available = quizSerializer.getAvailable();
        } else {
            available = new ConcurrentSkipListMap<>();
            closed = new ConcurrentSkipListMap<>();
        }
    }

    private Set<QuizOption> getOptions(ConcurrentSkipListMap<Integer, Quiz> quizzes) {
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
}
