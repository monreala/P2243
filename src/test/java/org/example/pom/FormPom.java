package org.example.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.example.utils.Utils;

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

    public String getTableDataByLabel(String labelParam){
        WebElement data = driver.findElement(By.xpath("//table//*[text()='" + labelParam + "']/../*[2]"));
        return data.getText();
    }

    public void setCity(String cityParam){
        scrollToElement(city);
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", city);
        pause(500);
        city.click();
        pause(500);
        WebElement ddCity = driver.findElement(By.xpath("//*[text()='" + cityParam + "']"));
        ddCity.click();
    }

    public void setState(String stateParam){
        scrollToElement(state);
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", state);
        pause(500);
        state.click();
        pause(500);
        WebElement ddState = driver.findElement(By.xpath("//*[text()='" + stateParam + "']"));
        ddState.click();
    }
    /*
        public void setHobbies(String hobbiesParam){
            WebElement hobby = driver.findElement(By.xpath("//*[@id='hobbiesWrapper']//label[text()=" + hobbiesParam + "'Sports']/../input"));
            hobby.sendKeys(" ");
        }
    */
    public void setSubject(String subjectParam){
        subjectsInput.sendKeys(subjectParam);
        subjectsInput.sendKeys(Keys.ENTER);

    }

    public void clickButtonSubmit() {
        scrollToElement(buttonSubmit);
        js.executeScript("arguments[0].click();", buttonSubmit);
    }

    public void setDateOfBirth(String day, String month, String year) {
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
    }

    public void setUserNumber(String numberParam){
        userNumber.clear();
        userNumber.sendKeys(numberParam);
    }

    public void setGender(String genderParam) {
        WebElement gender = driver.findElement(By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']"));
        gender.click();
    }

    public void setEmail(String emailParam) {
        userEmail.clear();
        userEmail.sendKeys(emailParam);
    }

    public void setLastName(String lastNameParam) {
        lastName.clear();
        lastName.sendKeys(lastNameParam);
    }

    public void setFirstName(String firstNameParam) {
        firstName.clear();
        firstName.sendKeys(firstNameParam);
    }

    public void clickPracticeForm() {
        scrollToElement(practiceForm);
        js.executeScript("arguments[0].click();", practiceForm);
    }
    public void clickForms() {
        scrollToElement(forms);
        js.executeScript("arguments[0].click();", forms);
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
}
