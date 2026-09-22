import pageobjects.MainPage;
import pageobjects.OrderPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderPageTest {

    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    @BeforeEach
    void setUp() {

        ChromeOptions options = new ChromeOptions();

        driver = new ChromeDriver(options);

        driver.get(
                "https://qa-scooter.education-services.ru/"
        );

        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);

        mainPage.closeCookieBanner();
    }

    static Stream<Arguments> orderData() {
        return Stream.of(
                Arguments.of(
                        "Иван",
                        "Иванов",
                        "Москва, улица Пушкина, дом 1",
                        "Черкизовская",
                        "89991234567",
                        "25.09.2026",
                        "сутки",
                        "black",
                        "Позвонить за час"
                ),
                Arguments.of(
                        "Петр",
                        "Петров",
                        "Москва, улица Ленина, дом 10",
                        "Сокольники",
                        "89997654321",
                        "26.09.2026",
                        "двое суток",
                        "grey",
                        "Домофон не работает"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("orderData")
    void orderFromTopButton(
            String name,
            String surname,
            String address,
            String metro,
            String phone,
            String deliveryDate,
            String rentalPeriod,
            String scooterColor,
            String comment
    ) {
        mainPage.clickTopOrderButton();

        fillFirstOrderForm(
                name,
                surname,
                address,
                metro,
                phone
        );

        orderPage.clickNext();

        fillSecondOrderForm(
                deliveryDate,
                rentalPeriod,
                scooterColor,
                comment
        );

        orderPage.clickOrder();

        orderPage.confirmOrder();

        assertTrue(
                orderPage.getOrderModalText()
                        .contains("Заказ оформлен")
        );
    }

    @ParameterizedTest
    @MethodSource("orderData")
    void orderFromBottomButton(
            String name,
            String surname,
            String address,
            String metro,
            String phone,
            String deliveryDate,
            String rentalPeriod,
            String scooterColor,
            String comment
    ) {
        mainPage.clickBottomOrderButton();

        fillFirstOrderForm(
                name,
                surname,
                address,
                metro,
                phone
        );

        orderPage.clickNext();

        fillSecondOrderForm(
                deliveryDate,
                rentalPeriod,
                scooterColor,
                comment
        );

        orderPage.clickOrder();

        orderPage.confirmOrder();

        assertTrue(
                orderPage.getOrderModalText()
                        .contains("Заказ оформлен")
        );
    }

    private void fillFirstOrderForm(
            String name,
            String surname,
            String address,
            String metro,
            String phone
    ) {
        orderPage.fillName(name);
        orderPage.fillSurname(surname);
        orderPage.fillAddress(address);
        orderPage.selectMetro(metro);
        orderPage.fillPhone(phone);
    }

    private void fillSecondOrderForm(
            String deliveryDate,
            String rentalPeriod,
            String scooterColor,
            String comment
    ) {
        orderPage.fillDeliveryDate(deliveryDate);
        orderPage.selectRentalPeriod(rentalPeriod);

        if (scooterColor.equals("black")) {
            orderPage.selectBlackScooter();
        } else {
            orderPage.selectGreyScooter();
        }

        orderPage.fillComment(comment);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}