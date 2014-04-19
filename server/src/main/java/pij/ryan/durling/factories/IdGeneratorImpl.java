package pij.ryan.durling.factories;

import com.google.inject.Singleton;

@Singleton
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
