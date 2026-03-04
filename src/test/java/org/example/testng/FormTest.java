package org.example.testng;

import org.example.pom.FormPom;
import org.example.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class FormTest {

    static public WebDriver driver;
    static public String URL = "https://demoqa.com/";
    static public String FIRST_NAME = "Vadim";
    static public String LAST_NAME = "Podcovali";
    static public String EMAIL = "example@gmail.com";
    static public String GENDER = "Male";
    static public String NUMBER = "0791111110";
    static public String DAY = "23";
    static public String MONTH = "February";
    static public String YEAR = "2026";
    static public String SUBJECT = "Maths";
    static public String STATE = "Rajasthan";
    static public String CITY = "Jaipur";
    /*static public String HOBBY = "Sports";*/
    static public String SUBMIT;

    @BeforeMethod
    public void beforeMethod() {
        driver = Driver.getAutoLocalDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void formTest() {
        System.out.println("Start test");
        driver.get(URL);
        FormPom formPom = new FormPom(driver);
        formPom.clickForms();
        formPom.pause(1000);
        formPom.clickPracticeForm();
        formPom.closeAdvert();
        formPom.setFirstName(FIRST_NAME);
        formPom.setLastName(LAST_NAME);
        formPom.setEmail(EMAIL);
        formPom.setGender(GENDER);
        formPom.setUserNumber(NUMBER);
        formPom.setDateOfBirth(DAY, MONTH, YEAR);
        /*formPom.setHobbies(HOBBY);*/
        formPom.setSubject(SUBJECT);
        formPom.pause(1000);
        formPom.setState(STATE);
        formPom.setCity(CITY);
        formPom.clickButtonSubmit();
        formPom.pause(5000);

        String actualName = formPom.getTableDataByLabel("Student Name");
        Assert.assertEquals(actualName, FIRST_NAME + " " + LAST_NAME);

        System.out.println("Finish test");

    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
