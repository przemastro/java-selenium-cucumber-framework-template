package steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import pages.HomePage;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.LoggedInUserPage;

/**
 * Created by Przemek on 21.04.2017.
 */
public class TestSteps extends MainTest {


	private HomePage homePage;
	private LoggedInUserPage loggedInUserPage;

	/**
	 * As mentioned in MainTest @Before and @After annotations are used at the beginning and the end of
	 * the test scenario.
	 */
	@Before
	public void beforeScenario() {beforeClass();}

	@After
	public void afterScenario() {afterClass();}

	/**
	 * Usually in my frameworks I don't use BDD layer and steps methods are in fact tests.
	 * As you can see I use chaining and in my opinion it is enough to understand what does test do.
     */

	@Given("^I navigate to (.+) page$")
	public void navigateToPage(String url) throws Throwable {
		homePage = new HomePage(event_driver)
				.navigateToLoggedInPage(url);
	}

	@When("^I login with (.+) and (.+)$")
	public void logInToTheService(String username, String password) {
		homePage = new HomePage(event_driver)
				.login(username, password);
	}

	@When("^I navigate to (.+) event$")
	public void navigateToEvent(String event) {
		loggedInUserPage = new LoggedInUserPage(event_driver)
				.selectEventType(event)
		        .selectRandomEvent();
	}

	@When("^I Add the first active selection to the betslip$")
	public void addActiveSelectionToTheBetslip() {
		loggedInUserPage = new LoggedInUserPage(event_driver)
				.addActiveBet();
	}

	@When("^I Place a (.+) pounds bet$")
	public void placePoundsBet(String value) {
		loggedInUserPage = new LoggedInUserPage(event_driver)
				.getUserBalance()
				.insertBetValue(value)
		        .placeBet();
	}

	@Then("^To return: value is equal (.+)$")
	public void toReturnValueIsEqual(String toReturn) {
		loggedInUserPage = new LoggedInUserPage(event_driver)
				.verifyToReturnValue(toReturn);
	}

	@Then("^Total stake: value is equal (.+)$")
	public void totalStakeValueIsEqual(String totalStake) {
		loggedInUserPage = new LoggedInUserPage(event_driver)
				.verifyTotalStakeValue(totalStake);
	}

	@Then("^user balance is updated by (.+)$")
	public void userBalanceIsUpdatedByBet(String bet) {
		loggedInUserPage = new LoggedInUserPage(event_driver)
				.verifyUserBalanceIsUpdated(bet);
	}

}
