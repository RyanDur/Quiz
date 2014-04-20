package pij.ryan.durling.serializers;

public interface IdSerializer {
    void serialize(int id);

    void deserialize();

    int getId();

    boolean dataExists();
}
