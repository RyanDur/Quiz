package pij.ryan.durling.serializers;

public interface IdSerializer {
    void persist(Integer id);

    Integer getId();

    boolean dataExists();
}
