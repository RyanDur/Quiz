package pij.ryan.durling.modules;

import com.google.inject.AbstractModule;
import pij.ryan.durling.factories.ScoreFactory;
import pij.ryan.durling.factories.ScoreFactoryImpl;
import pij.ryan.durling.serializers.ScoreSerializer;
import pij.ryan.durling.serializers.ScoreSerializerImpl;

public class HighScoreModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ScoreFactory.class).to(ScoreFactoryImpl.class);
        bind(ScoreSerializer.class).to(ScoreSerializerImpl.class);
    }
}
