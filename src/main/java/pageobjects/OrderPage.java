package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // =========================
    // ЛОКАТОРЫ ПЕРВОЙ ФОРМЫ
    // =========================

    // Имя
    private By nameField =
            By.xpath("//input[@placeholder='* Имя']");

    // Фамилия
    private By surnameField =
            By.xpath("//input[@placeholder='* Фамилия']");

    // Адрес
    private By addressField =
            By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    // Станция метро
    private By metroField =
            By.className("select-search__input");

    // Телефон
    private By phoneField =
            By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка «Далее»
    private By nextButton =
            By.xpath("//button[text()='Далее']");

    // =========================
    // ЛОКАТОРЫ ВТОРОЙ ФОРМЫ
    // =========================

    // Дата доставки
    private By deliveryDateField =
            By.xpath("//input[@placeholder='* Когда привезти самокат']");

    // Календарь
    private By datePicker =
            By.className("react-datepicker");

    // Выпадающий список срока аренды
    private By rentalPeriod =
            By.className("Dropdown-placeholder");

    // Чёрный самокат
    private By blackScooter =
            By.id("black");

    // Серый самокат
    private By greyScooter =
            By.id("grey");

    // Комментарий для курьера
    private By commentField =
            By.xpath("//input[@placeholder='Комментарий для курьера']");

    // Кнопка «Заказать» во второй форме
    private By orderButton =
            By.xpath(
                    "//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']"
            );

    // =========================
    // ЛОКАТОРЫ МОДАЛЬНОГО ОКНА
    // =========================

    // Заголовок окна подтверждения
    private By orderModalHeader =
            By.className("Order_ModalHeader__3FDaJ");

    // Кнопка «Да»
    private By confirmButton =
            By.xpath("//button[text()='Да']");

    // =========================
    // КОНСТРУКТОР
    // =========================

    public OrderPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    // =========================
    // ПЕРВАЯ ФОРМА
    // =========================

    // Заполнить имя
    public void fillName(String name) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        nameField
                )
        ).sendKeys(name);
    }

    // Заполнить фамилию
    public void fillSurname(String surname) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        surnameField
                )
        ).sendKeys(surname);
    }

    // Заполнить адрес
    public void fillAddress(String address) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        addressField
                )
        ).sendKeys(address);
    }

    // Выбрать станцию метро
    public void selectMetro(String metro) {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        metroField
                )
        ).click();

        By metroOption =
                By.xpath(
                        "//*[contains(@class, 'select-search__select')]//*[text()='" +
                                metro +
                                "']"
                );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        metroOption
                )
        ).click();
    }

    // Заполнить телефон
    public void fillPhone(String phone) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        phoneField
                )
        ).sendKeys(phone);
    }

    // Нажать «Далее»
    public void clickNext() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        nextButton
                )
        ).click();
    }

    // =========================
    // ВТОРАЯ ФОРМА
    // =========================

    // Указать дату доставки
    public void fillDeliveryDate(String date) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        deliveryDateField
                )
        ).sendKeys(date);

        // Закрываем календарь
        driver.findElement(deliveryDateField)
                .sendKeys(Keys.ESCAPE);

        // Ждём, пока календарь исчезнет
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        datePicker
                )
        );
    }

    // Выбрать срок аренды
    public void selectRentalPeriod(String period) {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        rentalPeriod
                )
        ).click();

        By rentalOption =
                By.xpath(
                        "//div[contains(@class, 'Dropdown-option') and " +
                                "normalize-space(text())='" +
                                period +
                                "']"
                );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        rentalOption
                )
        ).click();
    }

    // Выбрать чёрный самокат
    public void selectBlackScooter() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        blackScooter
                )
        ).click();
    }

    // Выбрать серый самокат
    public void selectGreyScooter() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        greyScooter
                )
        ).click();
    }

    // Заполнить комментарий
    public void fillComment(String comment) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        commentField
                )
        ).sendKeys(comment);
    }

    // Нажать «Заказать»
    public void clickOrder() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        orderButton
                )
        ).click();
    }

    // Нажать «Да»
    public void confirmOrder() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        confirmButton
                )
        ).click();
    }

    // Получить текст окна подтверждения
    public String getOrderModalText() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        orderModalHeader
                )
        ).getText();
    }
}