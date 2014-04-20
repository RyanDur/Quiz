package pij.ryan.durling.factories;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import pij.ryan.durling.serializers.IdSerializer;

@Singleton
public class IdGeneratorImpl implements IdGenerator {
    Integer id;

    @Inject
    public IdGeneratorImpl(IdSerializer serializer) {
        setUpId(serializer);
        serializer.persist(id);
    }

    @Override
    public Integer generateId() {
        return id++;
    }

    private void setUpId(IdSerializer serializer) {
        if (serializer.dataExists()) {
            id = serializer.getId();
        } else {
            id = 0;
        }
    }
}
