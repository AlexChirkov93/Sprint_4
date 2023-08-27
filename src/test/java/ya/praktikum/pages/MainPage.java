package ya.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ya.praktikum.helper.ActionHelper;
import ya.praktikum.helper.EnvConfig;

public class MainPage {
    private final WebDriver driver;

    //блок вопрос-ответ на главной странице
    private final By sectionFAQ = By.className("Home_FourPart__1uthg");
    // n-ый вопрос
    private final String questionPrefix = "accordion__heading-";
    // n-ый ответ
    private final String answerPrefix = "accordion__panel-";


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Открыть страницу и пролистать до раздела "Вопросы о важном"
     */
    public void open() {
        driver.get(EnvConfig.BASE_URL);
    }

    public void scrollToFAQ() {
        ActionHelper.scrollTo(driver, sectionFAQ);
    }

    /**
     * Проверка FAQ
     */
    public WebElement getQuestion(int id) {
        return driver.findElement(By.id(questionPrefix + id));
    }

    public WebElement getAnswer(int id) {
        return driver.findElement(By.id(answerPrefix + id));
    }

    public void scrollToTabTitle(WebElement tabTitle) {
        ActionHelper.doScrolling(driver, tabTitle);
    }

    public void waitThatTabTitleDisplayed(int id) {
        ActionHelper.waitDisplayedElement(driver, By.id(questionPrefix + id));
    }

    public void waitThatTabTextDisplayed(int id) {
        ActionHelper.waitDisplayedElement(driver, By.id(answerPrefix + id));
    }

}
