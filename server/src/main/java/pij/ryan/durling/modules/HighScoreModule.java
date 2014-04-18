package pij.ryan.durling.modules;

import com.google.inject.AbstractModule;
import pij.ryan.durling.factories.ScoreFactory;
import pij.ryan.durling.factories.ScoreFactoryImpl;

public class HighScoreModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ScoreFactory.class).to(ScoreFactoryImpl.class);
    }
}
