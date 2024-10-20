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
    public void orderCheckTest (){

        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickCookie();
        mainPage.clickRightUpperCornerOrderButton();

        ForWhomOrderPage orderPage = new ForWhomOrderPage(driver);
        orderPage.insertNameData(name);
        orderPage.insertSurnameData(surname);
        orderPage.insertAddressData(address);
        orderPage.insertStationData(station);
        orderPage.insertPhoneData(phone);
        orderPage.clickNextButton();

        orderPage.insertDate(date);
        orderPage.chooseRentPeriod(periodOfTime);
        orderPage.chooseColor(color);
        orderPage.insertComment(comment);
        orderPage.clickOrderButton();
        orderPage.clickConfirmationYesButton();
        Assert.assertTrue("Ошибка: Элемент страницы не найден", orderPage.yourOrderIssuedWindowIsPresent());




    }
}
