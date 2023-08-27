package ya.praktikum;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ya.praktikum.pages.MainPage;
import ya.praktikum.helper.DriverHelper;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class checkingTextInSectionFAQ {
    //Подключить правило наследования для запуска браузера
    @Rule
    public DriverHelper driverHelper = new DriverHelper();

    //Объявление переменных для тестовых данных
    private final int id;
    private final String question;
    private final String answer;

    //Конструктор для данных
    public checkingTextInSectionFAQ(int id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    // метод для получения тестовых данных
    @Parameterized.Parameters
    public static Object[][] listFaq() {
        return new Object[][]{
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void checkTabFaq() {
        MainPage mainPage = new MainPage(driverHelper.getDriver());

        mainPage.open();//Открыть страницу
        mainPage.scrollToFAQ(); //Пролистать до раздела Вопросы о важном


        WebElement tabTitle = mainPage.getQuestion(this.id); //Взять вопрос
        WebElement tabDescription = mainPage.getAnswer(this.id); //Взять ответ
        mainPage.scrollToTabTitle(tabTitle); // Прокрутить страницу до вопроса
        mainPage.waitThatTabTitleDisplayed(this.id); // Убедиться, что вопрос отображается на странице
        tabTitle.click();
        mainPage.waitThatTabTextDisplayed(this.id); // Убедиться, что ответ отображается на странице
        assertEquals(this.question, tabTitle.getText()); // сравнить ожидаемый и отображаемый вопрос
        assertEquals(this.answer, tabDescription.getText()); // сравнить ожидаемый и отображаемый ответ
    }
}