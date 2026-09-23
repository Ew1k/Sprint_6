package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // =========================
    // ЛОКАТОРЫ
    // =========================

    // Верхняя кнопка «Заказать»
    // Родитель: div class="Header_Nav__AGCXC"
    private By topOrderButton =
            By.xpath(
                    "//div[contains(@class, 'Header_Nav__AGCXC')]//button[text()='Заказать']"
            );

    // Нижняя кнопка «Заказать»
    // Родитель: div class="Home_FinishButton__1_cWm"
    private By bottomOrderButton =
            By.xpath(
                    "//div[contains(@class, 'Home_FinishButton__1_cWm')]//button[text()='Заказать']"
            );

    // Кнопка закрытия cookie
    private By cookieButton =
            By.id("rcc-confirm-button");

    // =========================
    // КОНСТРУКТОР
    // =========================

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    // =========================
    // ЗАКАЗ
    // =========================

    // Нажать верхнюю кнопку «Заказать»
    public void clickTopOrderButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(topOrderButton)
        ).click();
    }

    // Нажать нижнюю кнопку «Заказать»
    public void clickBottomOrderButton() {

        WebElement button = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        bottomOrderButton
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                button
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        bottomOrderButton
                )
        ).click();
    }

    // =========================
    // COOKIE
    // =========================

    // Закрыть окно с cookie
    public void closeCookieBanner() {

        WebElement button = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        cookieButton
                )
        );

        // Нажимаем через JavaScript,
        // чтобы перекрывающий элемент не мешал клику
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                button
        );
    }

    // =========================
    // FAQ
    // =========================

    // Нажать на вопрос
    public void clickQuestion(int questionNumber) {

        By question =
                By.id("accordion__heading-" + questionNumber);

        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        question
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        question
                )
        ).click();
    }

    // Получить текст ответа
    public String getAnswerText(int questionNumber) {

        By answer =
                By.xpath(
                        "//*[@id='accordion__panel-" +
                                questionNumber +
                                "']//p"
                );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        answer
                )
        ).getText();
    }
}