package org.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    protected WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Паттерн для конструирования xpath для вопросов
    private static final String FAQ_QUESTION_PATTERN = ".//div[contains(@id, 'accordion__heading') and (text() = '%s')]";

    // сексция с вопросами
    private By sectionFAQ = By.xpath(".//div[contains(@class, 'FAQ')]");

    //Кнопка куки
    private By cookieButton = By.xpath(".//button[contains(@class, 'App_CookieButton')]");

    // Видная секция ответа после клика
    private By visibleFQAAnwer = By.xpath(".//div[contains(@class, 'accordion__panel') and not(@hidden)]/p");


    //Кнопки заказать
    private By rightUpperCornerOrderButton = By.xpath(".//div[contains(@class, 'Header_Nav')]/button[contains(@class, 'Button_Button')]");
    private By bottomOrderButton = By.xpath(".//div[(contains(@class, 'FinishButton'))]/button");

    //Вопросы FAQ
    public static final String HOW_MUCH_QUESTION = "Сколько это стоит? И как оплатить?";
    public static final String I_WANT_SOME_QUESTION = "Хочу сразу несколько самокатов! Так можно?";
    public static final String HOW_DO_YOU_CALCULATE_QUESTION = "Как рассчитывается время аренды?";
    public static final String HOW_MUCH_COSTS_QUESTION = "Можно ли заказать самокат прямо на сегодня?";
    public static final String CAN_I_PROLONG_QUESTION = "Можно ли продлить заказ или вернуть самокат раньше?";
    public static final String ABOUT_CHARGER_QUESTION = "Вы привозите зарядку вместе с самокатом?";
    public static final String CAN_I_CANCEL_QUESTION = "Можно ли отменить заказ?";
    public static final String ABOUT_SUBURBS_QUESTION = "Я жизу за МКАДом, привезёте?";

    //Ответы FAQ
    public static final String HOW_MUCH_ANSWER = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String I_WANT_SOME_ANSWER = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String HOW_DO_YOU_CALCULATE_ANSWER = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String HOW_MUCH_COSTS_ANSWER = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String CAN_I_PROLONG_ANSWER = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    public static final String ABOUT_CHARGER_ANSWER = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static final String CAN_I_CANCEL_ANSWER = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    public static final String ABOUT_SUBURBS_ANSWER = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";


    //URL сайт
    public static String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru";

    // Открыть страницу
    public MainPage openMainPage() {
        driver.get(MAIN_PAGE_URL);
        return this;
    }

    // Нахождение элемента и клик по нему
    public void findAndClick (By locator){
        WebElement element = driver.findElement(locator);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    //Клик куки
    public void clickCookie (){
        findAndClick(cookieButton);
    }

    //прокрутка до аккордиона
    public void scrollToFAQsection (){
        WebElement element = driver.findElement(sectionFAQ);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //клик по элементу списка
    public void clickQuestionElement(String question){
        String questionLocator = String.format(FAQ_QUESTION_PATTERN, question);
        WebElement element = driver.findElement(By.xpath(questionLocator));
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeToBe(element, "aria-expanded", "true"));
    }

    //Получаем текст ответа
    public String getFaqAnswer(){
        WebElement element = driver.findElement(visibleFQAAnwer);
        String answerText = element.getText();
        return answerText;
    }

    //Клик по кнопке заказать пр.верх
    public void clickRightUpperCornerOrderButton(){
        findAndClick(rightUpperCornerOrderButton);
    }
    //Клик по кнопке заказать низ
    public void clickBottomOrderButton(){
        findAndClick(rightUpperCornerOrderButton);
    }

}