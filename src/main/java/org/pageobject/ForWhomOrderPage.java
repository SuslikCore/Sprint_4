package org.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForWhomOrderPage {
    protected WebDriver driver;

    public ForWhomOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // поле имя
    private By nameLocator = By.xpath(".//input[contains(@placeholder, 'Имя')]");

    // поле фамилия
    private By surnameLocator = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");

    // поле адрес
    private By addressLocator = By.xpath(".//input[contains(@placeholder, 'Адрес')]");

    // поле станция
    private By subwayStationLocator = By.xpath(".//input[contains(@placeholder, 'Станция')]");

    // поле номер телефона
    private By phoneNumberLocator = By.xpath(".//input[contains(@placeholder, 'Телефон')]");

    // кнопка далее
    private By nextButtonLocator = By.xpath(".//button[text() = 'Далее']");

    //конструктор создания xPath для выбора станции
    private static final String STATION_PATTERN = ".//div[contains(text(), '%s')]/parent::button";

    //конструктор создания xPath для выбора периода аренды
    private static final String RENT_PERIOD_PICKER_PATTERN = ".//div[text() = '%s']";

    //Поле выбора даты
    private By datePickerLocator = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");

    //Выпадающий список
    private By RentDateDropListLocator = By.xpath(".//div[@class = 'Dropdown-control']");

    // Чекбокс черный цвет самоката
    private By checkBoxBlackColorLocator = By.xpath(".//input[@id = 'black']");

    // Чекбокс серый цвет самоката
    private By checkBoxGreyColorLocator = By.xpath(".//input[@id = 'grey']");

    // Чекбокс серый цвет самоката
    private By commentForCourierLocator = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");

    // Кнопка заказать
    private By orderButtonLocator = By.xpath(".//div[contains(@class, 'Order')]/button[text() = 'Заказать']");

    // Кнопка да подтверждения заказа
    private By confirmationWindowYesButton = By.xpath(".//button[text() = 'Да']");

    // Окно заказ оформлен
    private By yourOrderIsIssuedWindow = By.xpath(".//div[text() = 'Заказ оформлен']/parent::div");


    // Методы
    // Нахождение элемента и клик по нему
    public void findAndClick (By locator){
        WebElement element = driver.findElement(locator);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    //  Нахождение элемента, клик и заполнение
    public void insertData (By locator, String insertDate){
        WebElement element = driver.findElement(locator);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        element.clear();
        element.sendKeys(insertDate);
    }

    // Заполнение поля имя
    public void insertNameData (String insertData){
        insertData (nameLocator,insertData);
    }

    // Заполнение поля фамилия
    public void insertSurnameData (String insertData){
        insertData (surnameLocator,insertData);
    }

    // Заполнение поля адрес
    public void insertAddressData (String insertData){
        insertData (addressLocator,insertData);
    }

    // Заполнение поля станция
    public void insertStationData (String stationNameInput){
        findAndClick(subwayStationLocator);
        String stationName = String.format(STATION_PATTERN, stationNameInput);
        WebElement dropListElement = driver.findElement(By.xpath(stationName));
        dropListElement.click();
    }

    // Заполнение поля номер
    public void insertPhoneData (String insertData){
        insertData (phoneNumberLocator,insertData);
    }

    // Клик по кнопке далее
    public void clickNextButton (){
        findAndClick(nextButtonLocator);
    }

    // Заполнение даты
    public void insertDate (String dateData){
        WebElement element = driver.findElement(datePickerLocator);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.click();
        element.sendKeys(dateData);
        element.sendKeys(Keys.ENTER);
    }

    // Выбор периода аренды
    public void chooseRentPeriod (String daysPeriod){
        findAndClick(RentDateDropListLocator);
        String fullPathLocator = String.format(RENT_PERIOD_PICKER_PATTERN, daysPeriod);
        WebElement dropDownElement = driver.findElement(By.xpath(fullPathLocator));
        dropDownElement.click();
    }

    //Выбор чекбокса цвета самоката
    public void chooseColor (String color){
        if(color == "черный"){
            findAndClick(checkBoxBlackColorLocator);
        } else {
            findAndClick(checkBoxGreyColorLocator);
        }
    }

    // Заполнение комментария
    public void insertComment (String insertData){
        insertData (commentForCourierLocator,insertData);
    }

    //Клик по кнопке заказать
    public void clickOrderButton (){
        findAndClick(orderButtonLocator);
    }

    // клик по кнопке да в окне подтверждения заказа
    public void clickConfirmationYesButton(){
        findAndClick(confirmationWindowYesButton);
    }

    //Присутсвие окна успешного оформления заказа на странице
    public boolean yourOrderIssuedWindowIsPresent(){
        return !driver.findElements(yourOrderIsIssuedWindow).isEmpty();
    }


}

