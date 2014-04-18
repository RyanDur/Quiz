package acceptance.steps.pij.ryan.durling.factories;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.factories.IdGenerator;
import pij.ryan.durling.factories.IdGeneratorImpl;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IdGeneratorSteps {

    private IdGenerator idGenerator;
    private int length;
    private Set<Integer> set;

    @Given("^there is a id generator$")
    public void there_is_a_id_generator() throws Throwable {
        idGenerator = new IdGeneratorImpl();
    }

    @When("^it generates (\\d+) ids$")
    public void it_generates_ids(int ids) throws Throwable {
        length = ids;
        set = new HashSet<>();
        for(int i = 0; i < ids; i++) {
            set.add(idGenerator.generateId());
        }
    }

    @Then("^they are all unique$")
    public void they_are_all_unique() throws Throwable {
        assertThat(set.size(), is(equalTo(length)));
    }
}
