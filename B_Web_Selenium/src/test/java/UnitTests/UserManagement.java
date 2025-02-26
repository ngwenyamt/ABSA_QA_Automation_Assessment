package UnitTests;

import MainClasses.MainClass_Junit;
import PageObjects.UserManagement.UserManagementPageObjects;
import org.junit.Test;

/**     * This unit test class is used to test individual methods of the page object for debugging purposes    */


public class UserManagement extends MainClass_Junit{
    @Test
    public void verifyThatUserIsInTheUserListTable() throws InterruptedException {
        //invokes the verifyThatUserIsInTheUserListTable() method
        UserManagementPageObjects userManagementPageObjectsObj = new UserManagementPageObjects(wdriver);
        userManagementPageObjectsObj.verifyThatUserIsInTheUserListTable();
    }
    @Test
    public void verifyThatUserIsAbleToClickAddUserButton() throws InterruptedException {
        //invokes the clickAddUserButton() method
        UserManagementPageObjects clickAddUserButtonObj = new UserManagementPageObjects(wdriver);
        clickAddUserButtonObj.clickAddUserButton();
    }
    @Test
    public void verifyThatYouAreAbleToCaptureUserDetailsAndClickSaveButtonAndUserShouldAppearOnTheList() throws InterruptedException {
        //invokes the clickAddUserButton() method
        UserManagementPageObjects clickAddUserButtonObj = new UserManagementPageObjects(wdriver);
        clickAddUserButtonObj.clickAddUserButton();
        //**********Captures User Details****************
        //invokes the enterFirstName(String fName)  method
        UserManagementPageObjects enterFirstNameObj = new UserManagementPageObjects(wdriver);
        enterFirstNameObj.enterFirstName("FName unit test for debugging");
        //invokes the enterFirstName(String fName)  method
        UserManagementPageObjects enterLastNameObj = new UserManagementPageObjects(wdriver);
        enterLastNameObj .enterLastName("LName unit test for debugging");
        //invokes the enterAddUsername(String Uname) method
        UserManagementPageObjects enterUsernameObj = new UserManagementPageObjects(wdriver);
        enterUsernameObj.enterUsername("Uname");
        //invokes the enterPassword(password) method
        UserManagementPageObjects enterPasswordObj = new UserManagementPageObjects(wdriver);
        enterPasswordObj .enterPassword("1234");
        //invokes the clickCustomerType() method
        UserManagementPageObjects clickCustomerTypeObj = new UserManagementPageObjects(wdriver);
        clickCustomerTypeObj.clickCustomerType("Company AAA");
        //invokes the selectRole() method
        UserManagementPageObjects selectRoleObj = new UserManagementPageObjects(wdriver);
        selectRoleObj.selectRole("Customer");
        //invokes the enterEmail() method
        UserManagementPageObjects enterEmailObj = new UserManagementPageObjects(wdriver);
        enterEmailObj.enterEmail("tebogo@gmail.com") ;
        //invokes the enterMobilePhone() method
        UserManagementPageObjects enterMobilePhoneObj = new UserManagementPageObjects(wdriver);
        enterMobilePhoneObj.enterMobilePhone("08273383");
        //invokes the clickSaveButton() method
        UserManagementPageObjects clickSaveButtonObj = new UserManagementPageObjects(wdriver);
        clickSaveButtonObj.clickSaveButton();
        //invokes the verifyThatUserAppearsInTheTable() method
        UserManagementPageObjects verifyThatUserAppearsInTheTableObj = new UserManagementPageObjects(wdriver);
        verifyThatUserAppearsInTheTableObj.verifyThatUserAppearsInTheTable("FName unit test for debugging","LName unit test for debugging","Uname","Customer","tebogo@gmail.com","08273383");

    }
}
