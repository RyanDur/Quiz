package pij.ryan.durling.modules;

import com.google.inject.AbstractModule;
import pij.ryan.durling.controllers.HighScoreCtrl;
import pij.ryan.durling.controllers.HighScoreCtrlImpl;
import pij.ryan.durling.controllers.QuizCtrl;
import pij.ryan.durling.controllers.QuizCtrlImpl;

public class QuizMasterModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new QuizCtrlModule());
        install(new HighScoreModule());
        bind(QuizCtrl.class).to(QuizCtrlImpl.class);
        bind(HighScoreCtrl.class).to(HighScoreCtrlImpl.class);
    }
}
