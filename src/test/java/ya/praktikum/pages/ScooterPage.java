package ya.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import ya.praktikum.helper.ActionHelper;
import ya.praktikum.helper.EnvConfig;
import java.util.Objects;

public class ScooterPage {
    private final WebDriver driver;

    //Принять куки
    private final By cookieAcceptButton = By.className("App_CookieButton__3cvqF");
    //Кнопка заказа в хэдере
    private final By orderButtonHeader = By.xpath(".//button[starts-with(@class,'Button_Button')]");
    //Кнопка заказа посередине
    private final By orderMiddleButton = By.xpath(".//button[contains(@class,'Button_Middle')]");
    // Заголовок заказа Для кого самокат
    private final By orderHeader = By.xpath(".//div[starts-with(@class,'Order_Header')]");
    //Инпут имя
    private final By inputName = By.cssSelector("input[placeholder*='Имя']");
    //Инпут фамилия
    private final By inputLastName = By.cssSelector("input[placeholder*='Фамилия']");
    //Инпут адрес
    private final By inputAddress = By.cssSelector("input[placeholder*='Адрес: куда']");
    //Поле Станция метро
    private final By fieldMetroStation = By.className("select-search__input");
    //Cтанция метро
    private final String findMetroStation = ".//button[contains(@class,'Order_SelectOption') and @value='";
    //Инпут номер телефона
    private final By inputPhone = By.cssSelector("input[placeholder*='Телефон:']");
    //Кнопка Далее
    private final By buttonNext = By.xpath(".//button[starts-with(@class,'Button_Button_') and text()='Далее']");
    //Инпут Когда привезти самокат
    private final By fillDate = By.cssSelector("input[placeholder*='Когда привезти самокат']");
    //Выбор даты в календаре
    private final By dateOnCalendar = By.xpath(".//div[contains(@class,'react-datepicker__day--selected')]");
    //Инпут Срок аренды
    private final By rentalPeriod = By.cssSelector("[class='Dropdown-placeholder']");
    //Инпут Комментарий
    private final By inputComment = By.cssSelector("input[placeholder*='Комментарий']");
    //Кнопка Заказать
    private final By buttonOrder = By.xpath(".//button[contains(@class,'Button_Middle') and text()='Заказать']");
    //Модалка Хотите оформить заказ?
    private final By placedOrder = By.xpath(".//div[contains(@class,'Order_ModalHeader')]");
    //Кнопка "Да" в модалке Хотите оформить заказ?
    private final By buttonOrderConfirm = By.xpath(".//button[starts-with(@class,'Button_Button_') and text()='Да']");
    //Модалка Заказ оформлен
    private final By windowWithOrder = By.xpath(".//div[contains(@class,'Order_ModalHeader') and text()='Заказ оформлен']");
    //Кнопка Посмотреть статус в модалке Заказ оформлен
    private final By showStatusButton = By.xpath(".//button[starts-with(@class,'Button_Button_') and text()='Посмотреть статус']");
    //Кнопка Отменить заказ
    private final By buttonCancel = By.xpath(".//button[starts-with(@class,'Button_Button_') and text()='Отменить заказ']");

    public ScooterPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Оформление заказа
     */

    public void clickOrderButton(String buttonOrder) {
        if (Objects.equals(buttonOrder, "header")) {
            this.clickButtonOrderHeader(); //Нажать кнопку Заказать в хэдере
        } else {
            this.scrollToOrderMiddleButton(); //Прокрутить страницу
            this.clickButtonOrderBottom(); //Нажать кнопку Заказать внизу страницы
        }
    }

    public void clickAcceptCookie() {
        try {
            driver.findElement(cookieAcceptButton).click();
        } catch (NoSuchElementException ignored) {
        }
    }

    public void clickButtonOrderHeader() {
        driver.findElement(orderButtonHeader).click();
    }

    public void scrollToOrderMiddleButton() {
        ActionHelper.scrollTo(driver, orderMiddleButton, EnvConfig.TIMEOUT_DEFAULT);
    }

    public void clickButtonOrderBottom() {
        driver.findElement(orderMiddleButton).click();
    }

    public void waitThatOpenNewPage() {
        ActionHelper.waitDisplayedElement(driver, orderHeader);
    }

    public void fillName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }

    public void fillLastName(String lastName) {
        driver.findElement(inputLastName).sendKeys(lastName);
    }

    public void fillAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }

    public void clickFieldMetroStation() {
        driver.findElement(fieldMetroStation).click();
    }

    public void scrollToMetroStation(int metroStation) {
        ActionHelper.scrollTo(driver, By.xpath(findMetroStation + metroStation + "']"));
    }

    public void clickMetroStation(int metroStation) {
        driver.findElement(By.xpath(findMetroStation + metroStation + "']")).click();
    }

    public void fillPhone(String phone) {
        driver.findElement(inputPhone).sendKeys(phone);
    }

    public void scrollToNext() {
        ActionHelper.scrollTo(driver, this.buttonNext);
    }

    public void clickButtonNext() {
        driver.findElement(this.buttonNext).click();
    }

    public void fillDate(String date) {
        driver.findElement(fillDate).sendKeys(date);
    }

    public void waitChooseDate() {
        ActionHelper.waitDisplayedElement(driver, dateOnCalendar);
    }

    public void clickOnCalendar() {
        driver.findElement(dateOnCalendar).click();
    }

    public void chooseRentalPeriod(String period) {
        driver.findElement(rentalPeriod).click();
        //выбор периода из выпадающего списка
        String chosePeriod = ".//div[@class='Dropdown-option' and text()='";
        driver.findElement(By.xpath(chosePeriod + period + "']")).click();
    }

    public void chooseColour(String colour) {
        driver.findElement(By.id(colour)).click();
    }

    public void fillComment(String comment) {
        driver.findElement(inputComment).sendKeys(comment);
    }

    public void clickButtonOrder() {
        driver.findElement(buttonOrder).click();
    }

    public void waitWindowWarning() {
        ActionHelper.waitDisplayedElement(driver, placedOrder);
    }

    public void clickOrderConfirm() {
        driver.findElement(buttonOrderConfirm).click();
    }

    public void waitWindowOrderResult() {
        ActionHelper.waitDisplayedElement(driver, windowWithOrder);
    }

    public void clickButtonViewStatus() {
        driver.findElement(showStatusButton).click();
    }

    public void waitDisplayOrder() {
        ActionHelper.waitDisplayedElement(driver, buttonCancel);
    }

}

