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

    private TreeMap<Integer, Quiz> available;
    private TreeMap<Integer, Quiz> closed;
    private OptionFactory optionFactory;

    @Inject
    public QuizCtrlImpl(OptionFactory optionFactory, QuizSerializer quizSerializer) {
        this.optionFactory = optionFactory;
        setupQuizzes(quizSerializer);
        Runtime.getRuntime().addShutdownHook(flushHook(quizSerializer));
    }

    @Override
    public void add(Quiz quiz) throws RemoteException {
        available.put(quiz.getId(), quiz);
    }

    @Override
    public Quiz getQuiz(int id) {
        return available.get(id);
    }

    @Override
    public Set<QuizOption> getQuizOptions() {
        return getOptions(available);
    }

    @Override
    public Set<QuizOption> getClosedOptions() {
        return getOptions(closed);
    }

    @Override
    public void open(int quizId) {
        Quiz quiz = closed.remove(quizId);
        available.put(quizId, quiz);
    }

    @Override
    public void close(int quizId) {
        Quiz quiz = available.remove(quizId);
        closed.put(quizId, quiz);
    }

    private Thread flushHook(QuizSerializer serializer) {
        return new Thread(() -> flush(serializer));
    }

    private void flush(QuizSerializer serializer) {
        serializer.serialize(available,closed);
    }

    private void setupQuizzes(QuizSerializer quizSerializer) {
        if (quizSerializer.dataExists()) {
            quizSerializer.deserialize();
            closed = quizSerializer.getClosed();
            if (closed == null) closed = new TreeMap<>();
            available = quizSerializer.getQuizzes();
            if (available == null) available = new TreeMap<>();
        } else {
            available = new TreeMap<>();
            closed = new TreeMap<>();
        }
    }

    private Set<QuizOption> getOptions(TreeMap<Integer, Quiz> quizzes) {
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
