package pij.ryan.durling.serializers;

import pij.ryan.durling.models.Quiz;

import java.io.*;
import java.util.TreeMap;

public class QuizSerializerImpl implements QuizSerializer {

    private static String fileName = "Quizzes.txt";
    private TreeMap<Integer, Quiz> available;
    private TreeMap<Integer, Quiz> closed;

    @Override
    public void persist(TreeMap<Integer, Quiz> quizzes, TreeMap<Integer, Quiz> closed) {
        this.available = quizzes;
        this.closed = closed;
        Runtime.getRuntime().addShutdownHook(new Thread(this::serialize));
    }

    @Override
    public TreeMap<Integer, Quiz> getAvailable() {
        if (available == null) deserialize();
        if (available == null) available = new TreeMap<>();
        return available;
    }

    @Override
    public TreeMap<Integer, Quiz> getClosed() {
        if (closed == null) deserialize();
        if (closed == null) closed = new TreeMap<>();
        return closed;
    }

    @Override
    public boolean dataExists() {
        return new File(fileName).exists();
    }

    private void serialize() {
        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(available);
            oos.writeObject(closed);
            oos.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deserialize() {
        try {
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            available = (TreeMap<Integer, Quiz>) ois.readObject();
            closed = (TreeMap<Integer, Quiz>) ois.readObject();
            ois.close();
            fin.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
