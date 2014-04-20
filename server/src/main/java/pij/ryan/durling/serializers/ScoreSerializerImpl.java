package pij.ryan.durling.serializers;

import pij.ryan.durling.models.Score;

import java.io.*;
import java.util.TreeMap;

public class ScoreSerializerImpl implements ScoreSerializer {
    private static String fileName = "Scores.txt";
    private TreeMap<Integer, Score> scores;

    @Override
    public void persist(TreeMap<Integer, Score> scores) {
        this.scores = scores;
        Runtime.getRuntime().addShutdownHook(new Thread(this::serialize));
    }

    @Override
    public TreeMap<Integer, Score> getScores() {
        deserialize();
        if (scores == null) scores = new TreeMap<>();
        return scores;
    }

    @Override
    public boolean dataExists() {
        return new File(ScoreSerializerImpl.fileName).exists();
    }

    private void deserialize() {
        try {
            FileInputStream fin = new FileInputStream(ScoreSerializerImpl.fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            scores = (TreeMap<Integer, Score>) ois.readObject();
            ois.close();
            fin.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void serialize() {
        try {
            FileOutputStream fout = new FileOutputStream(ScoreSerializerImpl.fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(scores);
            oos.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
