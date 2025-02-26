package PageObjects.UserManagement;

import MainClasses.MainClass_Junit;
import com.vividsolutions.jts.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.io.*;

/**
 * This class contains the page objects and reusable methods for user management functionalities.
 */
public class UserManagementPageObjects extends MainClass_Junit {
    public UserManagementPageObjects(WebDriver wdriver) {
        super(wdriver);
    }

    @FindBy(how = How.XPATH, using = "//button[@type='add']")
    public WebElement addUserButton;

    @FindBy(how = How.XPATH, using = "//table[@table-title='Smart Table example']")
    public WebElement userTable;

    @FindBy(how = How.XPATH, using = "//input[@name='FirstName']")
    public WebElement firstNameInputText;

    @FindBy(how = How.XPATH, using = "//input[@name='LastName']")
    public WebElement lastNameInputText;

    @FindBy(how = How.XPATH, using = "//input[@name='UserName']")
    public WebElement usernameInputText;

    @FindBy(how = How.XPATH, using = "//input[@name='Password']")
    public WebElement passwordInputText;

    @FindBy(how = How.XPATH, using = "//input[@value='15']")
    public WebElement CompanyAAARadioBoxValue;

    @FindBy(how = How.XPATH, using = "//input[@value='16']")
    public WebElement CompanyBBBRadioBoxValue;

    @FindBy(how = How.XPATH, using = "//select[@name='RoleId']")
    public WebElement roleDropDownClick;

    @FindBy(how = How.XPATH, using = "//input[@name='Email']")
    public WebElement emailInputText;

    @FindBy(how = How.XPATH, using = "//input[@name='Mobilephone']")
    public WebElement mobilePhoneInputText;

    @FindBy(how = How.XPATH, using = "//button[text()='Save']")
    public WebElement saveButton;

    // Method to verify that the user is in the user list table
    public void verifyThatUserIsInTheUserListTable() {
        enablers.waitForElementToBeVisible(userTable);
        enablers.captureTestEvidence("UserIsOnTheListTablePage");
        log.info("User is in the user list table page");
    }

    // Method to click the add user button
    public void clickAddUserButton() {
        enablers.waitForElementToBeVisible(addUserButton).click();
        enablers.captureTestEvidence("UserClickedAddUserButton");
        log.info("User clicked the add user button");
    }

    // Method to enter the first name
    public void enterFirstName(String fName) {
        enablers.waitForElementToBeVisible(firstNameInputText).sendKeys(fName);
        log.info("Company captured first name");
    }

    // Method to enter the last name
    public void enterLastName(String lName) {
        enablers.waitForElementToBeVisible(lastNameInputText).sendKeys(lName);
        log.info("Company captured last name");
    }

    // Method to enter the username
    public void enterUsername(String uName) {
        String RandomUsername = enablers.generateRandomString(uName);
        String filePath = "src/test/java/resources/GeneratedUsername.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(RandomUsername);
            log.info("Username is written successfully into a text file");
        } catch (IOException e) {
            e.printStackTrace();
        }

        enablers.waitForElementToBeVisible(usernameInputText).sendKeys(RandomUsername);
        log.info("UserManagement captured username");
    }

    // Method to enter the password
    public void enterPassword(String password) {
        enablers.waitForElementToBeVisible(passwordInputText).sendKeys(password);
        log.info("Company captured password");
    }

    // Method to select the customer type
    public void clickCustomerType(String cType) {
        if (cType.equals("Company AAA")) {
            enablers.waitForElementToBeVisible(CompanyAAARadioBoxValue).click();
            log.info("Company AAA type is selected");
        }
        if (cType.equals("Company BBB")) {
            enablers.waitForElementToBeVisible(CompanyBBBRadioBoxValue).click();
            log.info("Company BBB type is selected");
        }
        if (cType.equals("")) {
            log.info("Company type not provided!");
        }
    }

    // Method to select the role
    public void selectRole(String role) {
        enablers.waitForElementToBeVisible(roleDropDownClick).click();
        WebElement option;
        option = wdriver.findElement(By.xpath("//option[text()='" + role + "']"));
        enablers.waitForElementToBeVisible(option).click();
        log.info("UserManagement selected the role" + role);
    }

    // Method to enter the email
    public void enterEmail(String email) {
        enablers.waitForElementToBeVisible(emailInputText).sendKeys(email);
        log.info("UserManagement captured the email");
    }

    // Method to enter the mobile phone
    public void enterMobilePhone(String mobilePhone) {
        enablers.waitForElementToBeVisible(mobilePhoneInputText).sendKeys(mobilePhone);
        enablers.captureTestEvidence("UserCapturedDetails");
        log.info("UserManagement captured the mobile phone");
    }

    // Method to click the save button
    public void clickSaveButton() {
        enablers.waitForElementToBeVisible(saveButton).click();
        log.info("UserManagement clicked save button");
        enablers.captureTestEvidence("UserClickedSaveButton");
    }

    // Method to verify that the user appears in the table
    public void verifyThatUserAppearsInTheTable(String firstName, String lastName, String customer, String role, String email, String cell) {
        String filePath = "src/test/java/resources/GeneratedUsername.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String randomUsername = reader.readLine();

            if (randomUsername != null) {
                String getFName = wdriver.findElement(By.xpath("(//table[@class='smart-table table table-striped']//tr)[4]/td[1]")).getText();
                String getLName = wdriver.findElement(By.xpath("(//table[@class='smart-table table table-striped']//tr)[4]/td[2]")).getText();
                String getUsername = wdriver.findElement(By.xpath("(//table[@class='smart-table table table-striped']//tr)[4]/td[3]")).getText();
                String getCustomer = wdriver.findElement(By.xpath("(//table[@class='smart-table table table-striped']//tr)[4]/td[5]")).getText();
                String getRole = wdriver.findElement(By.xpath("(//table[@class='smart-table table table-striped']//tr)[4]/td[6]")).getText();
                String getEmail = wdriver.findElement(By.xpath("(//table[@class='smart-table table table-striped']//tr)[4]/td[7]")).getText();
                String getCell = wdriver.findElement(By.xpath("(//table[@class='smart-table table table-striped']//tr)[4]/td[8]")).getText();

                if (getFName.equals(firstName) && getLName.equals(lastName) && getUsername.equals(randomUsername) && getCustomer.equals(customer) && getRole.equals(role) && getEmail.equals(email) && getCell.equals(cell)) {
                    Assert.isTrue(true);
                    log.info("User successfully added to the list");
                } else {
                    log.info("User is not found on the list or one of the user details mismatch");
                    log.info("Expected: " + firstName + " ," + lastName + ", " + randomUsername + " ," + customer + ", " + role + ", " + email + " ," + cell + " ");
                    log.info("Found: " + firstName + " ," + lastName + " ," + randomUsername + " ," + customer + ", " + role + " ," + email + ", " + cell + " ");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}