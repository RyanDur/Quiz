package pij.ryan.durling.serializers;

import pij.ryan.durling.models.Quiz;

import java.io.*;
import java.util.TreeMap;

public class QuizSerializerImpl implements QuizSerializer {

    private static String fileName = "Quizzes.txt";
    private TreeMap<Integer, Quiz> quizzes;

    @Override
    public void serialize(TreeMap<Integer, Quiz> quizzes) {
        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(quizzes);
            oos.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deserialize() {
        try {
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            quizzes = (TreeMap<Integer, Quiz>) ois.readObject();
            ois.close();
            fin.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TreeMap<Integer, Quiz> getQuizzes() {
        return quizzes;
    }

    @Override
    public boolean dataExists() {
        return new File(fileName).exists();
    }
}
