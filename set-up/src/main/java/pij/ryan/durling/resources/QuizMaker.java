package pij.ryan.durling.resources;

import java.rmi.RemoteException;

public interface QuizMaker {
    int createQuiz(String title);

    void addQuestion(String question, int score);

    void addAnswer(String answer, boolean value);

    void save();

    boolean validQuiz();

    String getName();

    boolean empty();

    int getId() throws RemoteException;

    String getQuestion();

    int getQuestionValue();

    boolean validQuestion();
}
