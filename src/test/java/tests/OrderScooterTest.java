package tests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.pageobject.ForWhomOrderPage;
import org.pageobject.MainPage;


@RunWith(Parameterized.class)
public class OrderScooterTest extends BaseUITest {

    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String phone;
    private final String date;
    private final String periodOfTime;
    private final String color;
    private final String comment;

    public OrderScooterTest(String name, String surname, String address, String station, String phone, String date, String periodOfTime, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.periodOfTime = periodOfTime;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getDate(){
        return new Object[][]{
                {"Пузенька","фывфыв","фывфывфыв","Сокольники","12312312312","05-05-2014","сутки","черный","привет",},
                {"Кусься","Дуська","Свереповска","Сокольники","12312312312","05-05-2012","сутки","черный","привет",},
        };
    }

    @Test
    public void orderCheckViaUpperButtonTest (){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickRightUpperCornerOrderButton();
        ForWhomOrderPage orderPage = new ForWhomOrderPage(driver);
        orderPage.fillInAndCompleteFirstForm(name, surname, address, station, phone);
        orderPage.fillInAndCompleteSecondForm(date,periodOfTime,color,comment);
        orderPage.clickConfirmationYesButton();
        Assert.assertTrue("Ошибка: Элемент страницы не найден", orderPage.yourOrderIssuedWindowIsPresent());
    }

    @Test
    public void orderCheckViaBottomButtonTest (){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickBottomOrderButton();

        ForWhomOrderPage orderPage = new ForWhomOrderPage(driver);
        orderPage.fillInAndCompleteFirstForm(name, surname, address, station, phone);
        orderPage.fillInAndCompleteSecondForm(date,periodOfTime,color,comment);
        orderPage.clickConfirmationYesButton();
        Assert.assertTrue("Ошибка: Элемент страницы не найден", orderPage.yourOrderIssuedWindowIsPresent());
    }
}
