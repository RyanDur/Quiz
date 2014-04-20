package pij.ryan.durling.modules;

import com.google.inject.AbstractModule;
import pij.ryan.durling.factories.IdGenerator;
import pij.ryan.durling.factories.IdGeneratorImpl;

public class QuizFactoryModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new IdGeneratorModule());
        bind(IdGenerator.class).to(IdGeneratorImpl.class);
    }
}
