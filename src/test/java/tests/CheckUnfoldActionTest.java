package tests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.pageobject.MainPage;
import static org.pageobject.MainPage.*;

@RunWith(Parameterized.class)
    public class CheckUnfoldActionTest extends BaseUITest {

        private final String questionToOpen;
        private final String expAnswer;

        public CheckUnfoldActionTest(String questionToOpen, String expAnswer) {
            this.questionToOpen = questionToOpen;
            this.expAnswer = expAnswer;
        }

        @Parameterized.Parameters
        public static Object [][] getData(){
            return new Object[][]{
                    {HOW_MUCH_QUESTION, HOW_MUCH_ANSWER},
                    {I_WANT_SOME_QUESTION, I_WANT_SOME_ANSWER},
                    {HOW_DO_YOU_CALCULATE_QUESTION, HOW_DO_YOU_CALCULATE_ANSWER},
                    {HOW_MUCH_COSTS_QUESTION, HOW_MUCH_COSTS_ANSWER},
                    {CAN_I_PROLONG_QUESTION, CAN_I_PROLONG_ANSWER},
                    {ABOUT_CHARGER_QUESTION, ABOUT_CHARGER_ANSWER},
                    {CAN_I_CANCEL_QUESTION, CAN_I_CANCEL_ANSWER},
                    {ABOUT_SUBURBS_QUESTION, ABOUT_SUBURBS_ANSWER},
            };
        }
    @Test
    public void checkUnfoldingFQATextTest(){
        MainPage mainPage = new MainPage(driver);

        //Переменные
        String expectedAnswer = expAnswer;
        String question = questionToOpen;


        mainPage.openMainPage();
        mainPage.clickCookie();
        mainPage.scrollToFAQsection();
        mainPage.clickQuestionElement(question);
        String actualAnswer = mainPage.getFaqAnswer();

        Assert.assertEquals("Error", expectedAnswer,actualAnswer);


    }

    }

