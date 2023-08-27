package ya.praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ya.praktikum.helper.DriverHelper;
import ya.praktikum.pages.MainPage;
import ya.praktikum.pages.ScooterPage;


@RunWith(Parameterized.class)
public class OrderScooter {
    //Подключить правило наследования для запуска браузера
    @Rule
    public DriverHelper driverHelper = new DriverHelper();

    //Объявление переменных для тестовых данных

    private final String buttonOrder;
    private final String name;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final String date;
    private final String period;
    private final String colour;
    private final String comment;

    //Конструктор для данных
    public OrderScooter(String buttonOrder, String name, String lastName, String address, int metroStation, String phone, String date, String period, String colour, String comment) {
        this.buttonOrder = buttonOrder;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.colour = colour;
        this.comment = comment;
    }

    // метод для получения тестовых данных
    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"header", "Мэтью", "Макконахи", "Лондон", 17, "89998765684", "05.07.2023", "пятеро суток", "black", "Побыстрее"},
                {"middle", "Ирина", "Баженова", "Нью-Йорк", 18, "89276549857", "01.01.2024", "пятеро суток", "grey", "Позвонить за час до доставки"},
        };
    }

    @Test
    public void pressOrder() {
        ScooterPage scooterPage = new ScooterPage(driverHelper.getDriver());
        MainPage mainPage = new MainPage(driverHelper.getDriver());

        mainPage.open(); //Открыть страницу
        scooterPage.clickAcceptCookie(); //Нажать куки


        scooterPage.clickOrderButton(this.buttonOrder); //Нажать кнопку "Заказать"
        scooterPage.waitThatOpenNewPage(); //Убедиться, что открылась страница заказа

        // Заполнить формы заказа
        // 1 страница "Для кого самокат"

        scooterPage.fillName(this.name); //Заполнить Имя
        scooterPage.fillLastName(this.lastName); //Заполнить Фамилию
        scooterPage.fillAddress(this.address); //Заполнить Адрес
        scooterPage.clickFieldMetroStation(); //Кликнуть в поле "Станция метро"
        scooterPage.scrollToMetroStation(this.metroStation); //Прокрутить до необходимой станции метро
        scooterPage.clickMetroStation(this.metroStation); // Убедиться, что метро отображается на странице
        scooterPage.fillPhone(this.phone); //Заполнить Телефон
        scooterPage.scrollToNext(); //Прокрутить страницу
        scooterPage.clickButtonNext(); //Кликнуть Далее

        //2 страница "Про аренду"

        scooterPage.fillDate(this.date); //Заполнить дату
        scooterPage.waitChooseDate(); //Подождать, что дата выбралась в календаре
        scooterPage.clickOnCalendar(); //Клик по календарю
        scooterPage.chooseRentalPeriod(this.period); //Выбрать срок аренды
        scooterPage.chooseColour(this.colour); //Выбрать цвет самоката
        scooterPage.fillComment(this.comment); //Оставить комментарий
        scooterPage.clickButtonOrder(); //Нажать Заказать
        scooterPage.waitWindowWarning(); //Подождать появление Предупреждения
        scooterPage.clickOrderConfirm(); //Нажать Да
        scooterPage.waitWindowOrderResult(); //Подождать окно Заказ оформлен
        scooterPage.clickButtonViewStatus(); //Проверить наличие кнопки "Посмотреть статус
        scooterPage.waitDisplayOrder(); //Подождать отображение заказа
    }
}
