package acceptance.steps.pij.ryan.durling.factories;

import acceptance.steps.pij.ryan.durling.registry.Quiz;

public interface QuizClient {
    Quiz create(String name);

    Quiz get(int i);
}
