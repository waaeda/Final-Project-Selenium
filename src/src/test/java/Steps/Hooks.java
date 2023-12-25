package Steps;


import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks {
    @BeforeAll
    public static void beforeAll(){
        System.out.println("tests are started");
    }
    @Before
    public void before(Scenario scenario){
        System.out.println("Scenario: " + scenario.getName() + " is running now");
    }
}
