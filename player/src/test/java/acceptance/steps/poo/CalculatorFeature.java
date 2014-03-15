package acceptance.steps.poo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import poo.Calculator;
import poo.CalculatorImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class CalculatorFeature {

    private Calculator calculator;
    private int actual;

    @Given("^I have a calculator$")
    public void I_have_a_calculator() throws Throwable {
        calculator = new CalculatorImpl();
    }

    @When("^I add (\\d+) and (\\d+)$")
    public void I_add_and(int arg1, int arg2) throws Throwable {
        actual = calculator.add(arg1, arg2);
    }

    @Then("^I should have (\\d+)$")
    public void I_should_have(int expected) throws Throwable {
        assertThat(actual, is(equalTo(expected)));
    }

    @When("^I subtract (\\d+) from (\\d+)$")
    public void I_subtract_from(int arg1, int arg2) throws Throwable {
        actual = calculator.subtract(arg1, arg2);
    }
}
