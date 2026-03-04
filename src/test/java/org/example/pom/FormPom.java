package org.example.pom;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.example.utils.Utils;

import java.io.ByteArrayInputStream;

public class FormPom {

    static public WebDriver driver;
    static public JavascriptExecutor js;

    @FindBy(xpath = "//*[text()='Forms']")
    WebElement forms;

    @FindBy(xpath = "//*[text()='Practice Form']")
    WebElement practiceForm;

    @FindBy(xpath = "//*[@id='firstName']")
    WebElement firstName;

    @FindBy(xpath = "//*[@id='lastName']")
    WebElement lastName;

    @FindBy(xpath = "//*[@id='userEmail']")
    WebElement userEmail;

    @FindBy(xpath = "//*[@id='userNumber']")
    WebElement userNumber;

    @FindBy(id = "dateOfBirthInput")
    WebElement dateOfBirthInput;

    @FindBy(className = "react-datepicker__month-select")
    WebElement monthSelect;

    @FindBy(className = "react-datepicker__year-select")
    WebElement yearSelect;
    /*
        @FindBy(xpath = "//*[@id='hobbies-checkbox-1']")
        WebElement hobby;
    */
    @FindBy(xpath = "//*[@id='submit']")
    WebElement buttonSubmit;

    @FindBy(xpath = "//*[@id='subjectsInput']")
    WebElement subjectsInput;

    @FindBy(xpath = "//*[@id='state']")
    WebElement state;

    @FindBy(xpath = "//*[@id='city']")
    WebElement city;


    public FormPom(WebDriver driverParam) {
        driver = driverParam;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Get table data by label: {labelParam}")
    public String getTableDataByLabel(String labelParam){
        takeScreenshot("Before getting table data: " + labelParam);
        WebElement data = driver.findElement(By.xpath("//table//*[text()='" + labelParam + "']/../*[2]"));
        String text = data.getText();
        takeScreenshot("After getting table data: " + labelParam);
        return text;
    }

    @Step("Set city: {cityParam}")
    public void setCity(String cityParam){
        takeScreenshot("Before setting city");
        scrollToElement(city);
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", city);
        pause(500);
        city.click();
        pause(500);
        WebElement ddCity = driver.findElement(By.xpath("//*[text()='" + cityParam + "']"));
        ddCity.click();
        takeScreenshot("After setting city");
    }

    @Step("Set state: {stateParam}")
    public void setState(String stateParam){
        takeScreenshot("Before setting state");
        scrollToElement(state);
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", state);
        pause(500);
        state.click();
        pause(500);
        WebElement ddState = driver.findElement(By.xpath("//*[text()='" + stateParam + "']"));
        ddState.click();
        takeScreenshot("After setting state");
    }
    /*
        public void setHobbies(String hobbiesParam){
            WebElement hobby = driver.findElement(By.xpath("//*[@id='hobbiesWrapper']//label[text()=" + hobbiesParam + "'Sports']/../input"));
            hobby.sendKeys(" ");
        }
    */
    @Step("Set subject: {subjectParam}")
    public void setSubject(String subjectParam){
        takeScreenshot("Before setting subject");
        subjectsInput.sendKeys(subjectParam);
        subjectsInput.sendKeys(Keys.ENTER);
        takeScreenshot("After setting subject");
    }

    @Step("Click Submit button")
    public void clickButtonSubmit() {
        takeScreenshot("Before clicking Submit");
        scrollToElement(buttonSubmit);
        js.executeScript("arguments[0].click();", buttonSubmit);
        takeScreenshot("After clicking Submit");
    }

    @Step("Set date of birth: {day}.{month}.{year}")
    public void setDateOfBirth(String day, String month, String year) {
        takeScreenshot("Before setting date of birth");
        dateOfBirthInput.click();

        org.openqa.selenium.support.ui.Select yearDropdown =
                new org.openqa.selenium.support.ui.Select(yearSelect);
        yearDropdown.selectByVisibleText(year);

        org.openqa.selenium.support.ui.Select monthDropdown =
                new org.openqa.selenium.support.ui.Select(monthSelect);
        monthDropdown.selectByVisibleText(month);

        WebElement dayElement = driver.findElement(By.xpath(
                "//div[contains(@class,'react-datepicker__day') and text()='" + day + "']"
        ));
        dayElement.click();
        takeScreenshot("After setting date of birth");
    }

    @Step("Set user number: {numberParam}")
    public void setUserNumber(String numberParam){
        takeScreenshot("Before setting user number");
        userNumber.clear();
        userNumber.sendKeys(numberParam);
        takeScreenshot("After setting user number");
    }

    @Step("Set gender: {genderParam}")
    public void setGender(String genderParam) {
        takeScreenshot("Before setting gender");
        WebElement gender = driver.findElement(By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']"));
        gender.click();
        takeScreenshot("After setting gender");
    }

    @Step("Set email: {emailParam}")
    public void setEmail(String emailParam) {
        takeScreenshot("Before setting email");
        userEmail.clear();
        userEmail.sendKeys(emailParam);
        takeScreenshot("After setting email");
    }

    @Step("Set last name: {lastNameParam}")
    public void setLastName(String lastNameParam) {
        takeScreenshot("Before setting last name");
        lastName.clear();
        lastName.sendKeys(lastNameParam);
        takeScreenshot("After setting last name");
    }
    @Step("Set first name: {firstNameParam}")
    public void setFirstName(String firstNameParam) {
        takeScreenshot("Before setting first name");
        firstName.clear();
        firstName.sendKeys(firstNameParam);
        takeScreenshot("After setting first name");
    }

    @Step("Click Practice Form")
    public void clickPracticeForm() {
        takeScreenshot("Before clicking Practice Form");
        scrollToElement(practiceForm);
        js.executeScript("arguments[0].click();", practiceForm);
        takeScreenshot("After clicking Practice Form");
    }

    @Step("Click Forms")
    public void clickForms() {
        takeScreenshot("Before clicking Forms");
        scrollToElement(forms);
        js.executeScript("arguments[0].click();", forms);
        takeScreenshot("After clicking Forms");
    }

    public void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Step("Close adverts")
    public void closeAdvert() {
        try {
            js.executeScript("var elem = document.evaluate(\"//*[@id='adplus-anchor']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "if(elem) elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
        try {
            js.executeScript("var elem = document.evaluate(\"//footer\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "if(elem) elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
        try {
            js.executeScript(
                    "document.querySelectorAll('iframe, #fixedban, .ad, #adplus-anchor, #adplus-slide-panel, #close-fixedban').forEach(function(e){e.remove();});" +
                    "var all = document.querySelectorAll('*');" +
                    "for(var i=0;i<all.length;i++){" +
                    "  var s = window.getComputedStyle(all[i]);" +
                    "  if(s.position==='fixed' && all[i].tagName!=='HTML' && all[i].tagName!=='BODY' && all[i].id!=='app'){all[i].remove();}" +
                    "}"
            );
        } catch (Exception ignored) {}
    }
    private void takeScreenshot(String stepName) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(stepName, "image/png", new ByteArrayInputStream(screenshot), ".png");
        } catch (Exception e) {
            Allure.addAttachment("Screenshot Error", e.toString());
        }
    }
}
