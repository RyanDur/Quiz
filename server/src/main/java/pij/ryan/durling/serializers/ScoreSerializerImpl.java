package pij.ryan.durling.serializers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pij.ryan.durling.models.Score;

import java.io.*;
import java.util.concurrent.ConcurrentSkipListMap;

public class ScoreSerializerImpl extends Serializer implements ScoreSerializer {
    private static final Logger log = LoggerFactory.getLogger(ScoreSerializerImpl.class);
    private static String fileName = "server/Scores.txt";
    private ConcurrentSkipListMap<Integer, Score> scores;

    @Override
    public void persist(ConcurrentSkipListMap<Integer, Score> scores) {
        this.scores = scores;
    }

    @Override
    public ConcurrentSkipListMap<Integer, Score> getScores() {
        deserialize();
        if (scores == null) scores = new ConcurrentSkipListMap<>();
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
            log.info("Scores Saved");
        } catch (IOException e) {
            log.error("Problem with serialization", e);
        }
    }

    private void deserialize() {
        try {
            FileInputStream fin = new FileInputStream(ScoreSerializerImpl.fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            scores = (ConcurrentSkipListMap<Integer, Score>) ois.readObject();
            ois.close();
            fin.close();
            log.info("Scores Retrieved");
        } catch (IOException | ClassNotFoundException e) {
            log.error("Problem with deserialization", e);
        }
    }
}
