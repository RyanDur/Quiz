package pij.ryan.durling.modules;

import com.google.inject.AbstractModule;
import pij.ryan.durling.factories.OptionFactory;
import pij.ryan.durling.factories.OptionFactoryImpl;

public class QuizCtrlModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(OptionFactory.class).to(OptionFactoryImpl.class);
    }
}
