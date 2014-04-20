package pij.ryan.durling.factories;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import pij.ryan.durling.serializers.IdSerializer;

@Singleton
public class IdGeneratorImpl implements IdGenerator {
    Integer id;
    private IdSerializer serializer;

    @Inject
    public IdGeneratorImpl(IdSerializer serializer) {
        this.serializer = serializer;
        setUpId(serializer);
    }

    @Override
    public Integer generateId() {
        id += 1;
        serializer.persist(id);
        return id += 1;
    }

    private void setUpId(IdSerializer serializer) {
        if (serializer.dataExists()) {
            id = serializer.getId();
        } else {
            id = 0;
        }
    }
}
