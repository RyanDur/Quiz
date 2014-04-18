package pij.ryan.durling.factories;

public class IdGeneratorImpl implements IdGenerator {
    int id;

    public IdGeneratorImpl() {
        id = 0;
    }

    @Override
    public int generateId() {
        return id++;
    }
}
