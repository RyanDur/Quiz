package pij.ryan.durling.factories;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import pij.ryan.durling.models.*;

import java.rmi.RemoteException;

@Singleton
public class QuizFactoryImpl implements QuizFactory {
    private IdGenerator idGenerator;

    @Inject
    public QuizFactoryImpl(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public Quiz createQuiz(String title) throws RemoteException {
        return new QuizImpl(title, idGenerator.generateId());
    }

    @Override
    public Question createQuestion(String question, int score) throws RemoteException {
        return new QuestionImpl(question, score);
    }

    @Override
    public Answer createAnswer(String answer, boolean value) throws RemoteException {
        return new AnswerImpl(answer, value);
    }
}
