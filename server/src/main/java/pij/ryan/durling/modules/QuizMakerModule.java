package pij.ryan.durling.modules;

import com.google.inject.AbstractModule;
import pij.ryan.durling.controllers.QuizCtrl;
import pij.ryan.durling.controllers.QuizCtrlImpl;
import pij.ryan.durling.factories.QuizFactory;
import pij.ryan.durling.factories.QuizFactoryImpl;

public class QuizMakerModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new QuizCtrlModule());
        install(new QuizFactoryModule());
        bind(QuizCtrl.class).to(QuizCtrlImpl.class);
        bind(QuizFactory.class).to(QuizFactoryImpl.class);
    }
}
