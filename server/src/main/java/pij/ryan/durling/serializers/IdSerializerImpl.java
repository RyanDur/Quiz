package pij.ryan.durling.serializers;

import java.io.*;

public class IdSerializerImpl implements IdSerializer {
    private static String fileName = "Ids.txt";
    private Integer id;

    @Override
    public void persist(Integer id) {
        this.id = id;
        Runtime.getRuntime().addShutdownHook(new Thread(this::serialize));
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

    public void serialize() {
        try {
            FileOutputStream fout = new FileOutputStream(IdSerializerImpl.fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(id);
            oos.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialize() {
        try {
            FileInputStream fin = new FileInputStream(IdSerializerImpl.fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            id = (Integer) ois.readObject();
            ois.close();
            fin.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
