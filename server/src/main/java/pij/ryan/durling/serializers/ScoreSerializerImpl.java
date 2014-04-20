package pij.ryan.durling.serializers;

import pij.ryan.durling.models.Score;

import java.io.*;
import java.util.TreeMap;

public class ScoreSerializerImpl implements ScoreSerializer {
    private static String fileName = "Scores.txt";
    private TreeMap<Integer, Score> scores;

    @Override
    public void serialize(TreeMap<Integer, Score> scores) {
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

    @Override
    public void deserialize() {
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

    @Override
    public TreeMap<Integer, Score> getScores() {
        return scores;
    }

    @Override
    public boolean dataExists() {
        return new File(ScoreSerializerImpl.fileName).exists();
    }
}
