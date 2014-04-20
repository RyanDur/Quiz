package pij.ryan.durling.modules;

import com.google.inject.AbstractModule;
import pij.ryan.durling.resources.QuizMaker;
import pij.ryan.durling.resources.QuizMakerImpl;
import pij.ryan.durling.resources.QuizMaster;
import pij.ryan.durling.resources.QuizMasterImpl;

public class ServerModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new QuizMakerModule());
        install(new QuizMasterModule());
        bind(QuizMaker.class).to(QuizMakerImpl.class);
        bind(QuizMaster.class).to(QuizMasterImpl.class);
    }
}
