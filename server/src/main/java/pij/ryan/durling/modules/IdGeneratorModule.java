package pij.ryan.durling.modules;

import com.google.inject.AbstractModule;
import pij.ryan.durling.serializers.IdSerializer;
import pij.ryan.durling.serializers.IdSerializerImpl;

public class IdGeneratorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IdSerializer.class).to(IdSerializerImpl.class);
    }
}
