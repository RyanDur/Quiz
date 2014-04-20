package pij.ryan.durling.serializers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pij.ryan.durling.models.Quiz;

import java.io.*;
import java.util.concurrent.ConcurrentSkipListMap;

public class QuizSerializerImpl extends Serializer implements QuizSerializer {
    private static final Logger log = LoggerFactory.getLogger(QuizSerializerImpl.class);
    private static String fileName = "server/Quizzes.txt";
    private ConcurrentSkipListMap<Integer, Quiz> available;
    private ConcurrentSkipListMap<Integer, Quiz> closed;

    @Override
    public void persist(ConcurrentSkipListMap<Integer, Quiz> open, ConcurrentSkipListMap<Integer, Quiz> closed) {
        this.available = open;
        this.closed = closed;
        Runtime.getRuntime().addShutdownHook(new Thread(this::serialize));
    }

    @Override
    public ConcurrentSkipListMap<Integer, Quiz> getAvailable() {
        if (available == null) deserialize();
        if (available == null) available = new ConcurrentSkipListMap<>();
        return available;
    }

    @Override
    public ConcurrentSkipListMap<Integer, Quiz> getClosed() {
        if (closed == null) deserialize();
        if (closed == null) closed = new ConcurrentSkipListMap<>();
        return closed;
    }

    @Override
    public boolean dataExists() {
        return new File(fileName).exists();
    }

    @Override
    public void serialize() {
        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(available);
            oos.writeObject(closed);
            oos.close();
            fout.close();
            log.info("Quizzes Saved");
        } catch (IOException e) {
            log.error("Problem with serialization", e);
        }
    }

    private void deserialize() {
        try {
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            available = (ConcurrentSkipListMap<Integer, Quiz>) ois.readObject();
            closed = (ConcurrentSkipListMap<Integer, Quiz>) ois.readObject();
            ois.close();
            fin.close();
            log.info("Quizzes Retrieved");
        } catch (IOException | ClassNotFoundException e) {
            log.error("Problem with deserialization", e);
        }
    }
}
