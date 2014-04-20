package pij.ryan.durling.factories;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import pij.ryan.durling.serializers.IdSerializer;

@Singleton
public class IdGeneratorImpl implements IdGenerator {
    int id;

    @Inject
    public IdGeneratorImpl(IdSerializer serializer) {
        if (serializer.dataExists()) {
            serializer.deserialize();
            id = serializer.getId();
        } else {
            id = 0;
        }
        Runtime.getRuntime().addShutdownHook(flushHook(serializer));
    }

    @Override
    public int generateId() {
        return id++;
    }

    private Thread flushHook(IdSerializer serializer) {
        return new Thread(() -> flush(serializer));
    }

    private void flush(IdSerializer serializer) {
        serializer.serialize(id);
    }
}
