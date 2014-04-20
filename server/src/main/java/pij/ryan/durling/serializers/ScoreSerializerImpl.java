package pij.ryan.durling.serializers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pij.ryan.durling.models.Score;

import java.io.*;
import java.util.TreeMap;

public class ScoreSerializerImpl extends Serializer implements ScoreSerializer {
    private static final Logger log = LoggerFactory.getLogger(ScoreSerializerImpl.class);
    private static String fileName = "server/Scores.txt";
    private TreeMap<Integer, Score> scores;

    @Override
    public void persist(TreeMap<Integer, Score> scores) {
        this.scores = scores;
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

    @Override
    public void serialize() {
        try {
            FileOutputStream fout = new FileOutputStream(ScoreSerializerImpl.fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(scores);
            oos.close();
            fout.close();
        } catch (IOException e) {
            log.error("Problem with serialization", e);
        }
    }

    private void deserialize() {
        try {
            FileInputStream fin = new FileInputStream(ScoreSerializerImpl.fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            scores = (TreeMap<Integer, Score>) ois.readObject();
            ois.close();
            fin.close();
        } catch (IOException | ClassNotFoundException e) {
            log.error("Problem with deserialization", e);
        }
    }
}
