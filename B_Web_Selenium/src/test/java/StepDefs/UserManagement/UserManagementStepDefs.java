package StepDefs.UserManagement;

import MainClasses.MainClass_BDD;
import PageObjects.UserManagement.UserManagementPageObjects;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.net.MalformedURLException;

/**
 * This class contains the step definitions for the User Management feature.
 */
public class UserManagementStepDefs extends MainClass_BDD {
    protected static UserManagementPageObjects userPageObjects;

    // Constructor to initialize UserManagementPageObjects
    public UserManagementStepDefs() {
        userPageObjects = new UserManagementPageObjects(wdriver);
    }
    // Step definition to verify that the user is on the User List Table page
    @And("User is on the User List Table page")
    public void userIsOnTheUserListTablePage() throws MalformedURLException, InterruptedException {
        userPageObjects.verifyThatUserIsInTheUserListTable();
    }
    // Step definition to click the add user button
    @And("^User clicked add user button$")
    public void userClickedAddUserButton() {
        userPageObjects.clickAddUserButton();
    }
    // Step definition to capture user details
    @When("^User capture first name as \"([^\"]*)\", last name as \"([^\"]*)\", username as \"([^\"]*)\", password as \"([^\"]*)\", customer as \"([^\"]*)\", role as \"([^\"]*)\", email as \"([^\"]*)\", cell as \"([^\"]*)\"$")
    public void userCaptureFirstNameAsLastNameAsUsernameAsPasswordAsCustomerAsRoleAsEmailAsCellAs(String firstName, String lastName, String username, String password, String customer, String role, String email, String cell) throws Throwable {
        userPageObjects.enterFirstName(firstName);
        userPageObjects.enterLastName(lastName);
        userPageObjects.enterUsername(username);
        userPageObjects.enterPassword(password);
        userPageObjects.clickCustomerType(customer);
        userPageObjects.selectRole(role);
        userPageObjects.enterEmail(email);
        userPageObjects.enterMobilePhone(cell);
    }
    // Step definition to click the save button
    @And("^User clicks the save button$")
    public void userClicksTheSaveButton() {
        userPageObjects.clickSaveButton();
    }
    // Step definition to verify that the user appears in the User List Table
    @Then("^User should be added and appear in the User List Table as first name as \"([^\"]*)\", last name as \"([^\"]*)\", customer as \"([^\"]*)\", role as \"([^\"]*)\", email as \"([^\"]*)\", cell as \"([^\"]*)\"$")
    public void userShouldBeAddedAndAppearInTheUserListTableAsFirstNameAsLastNameAsCustomerAsRoleAsEmailAsCellAs(String firstName, String lastName, String customer, String role, String email, String cell) throws Throwable {
        userPageObjects.verifyThatUserAppearsInTheTable(firstName, lastName, customer, role, email, cell);
    }
}