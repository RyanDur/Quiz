package pij.ryan.durling.serializers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pij.ryan.durling.messages.SerializerMessages;

import java.io.*;

public class IdSerializerImpl extends Serializer implements IdSerializer {
    private static final Logger log = LoggerFactory.getLogger(IdSerializerImpl.class);
    private static String fileName = SerializerMessages.ID_FILE;
    private Integer id;

    @Override
    public void persist(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        deserialize();
        if(id == null) id = 0;
        return id;
    }

    @Override
    public boolean dataExists() {
        return new File(IdSerializerImpl.fileName).exists();
    }

    @Override
    public void serialize() {
        try {
            FileOutputStream fout = new FileOutputStream(IdSerializerImpl.fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(id);
            oos.close();
            fout.close();
            log.info(SerializerMessages.ID_SAVED);
        } catch (IOException e) {
            log.error(SerializerMessages.PROBLEM_SER, e);
        }
    }

    private void deserialize() {
        try {
            FileInputStream fin = new FileInputStream(IdSerializerImpl.fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            id = (Integer) ois.readObject();
            ois.close();
            fin.close();
            log.info(SerializerMessages.ID_GET);
        } catch (IOException | ClassNotFoundException e) {
            log.error(SerializerMessages.PROBLEM_DESER, e);
        }
    }
}
