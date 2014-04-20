package pij.ryan.durling.serializers;

import java.io.*;

public class IdSerializerImpl implements IdSerializer {
    private static String fileName = "Scores.txt";
    private int id;

    @Override
    public void serialize(int id) {
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

    @Override
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean dataExists() {
        return new File(IdSerializerImpl.fileName).exists();
    }
}
