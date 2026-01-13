import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;
import static java.util.Collections.singletonList;

public class FortuneCookieControllerTest {
    private static FortuneCookieController goodFactoryController;
    private static FortuneCookieController badFactoryController;

    @BeforeAll
    static void setUpAll() {
        // Создаем контроллер для позитивных предсказаний
        goodFactoryController = create(true);

        // Создаем контроллер для негативных предсказаний
        badFactoryController = create(false);
    }

    private static FortuneCookieController create(boolean isPositive) {
        FortuneConfig config = new FortuneConfig(isPositive);
        FortuneCookieFactory factory = new FortuneCookieFactory(
                config,
                singletonList("positive"),
                singletonList("negative")
        );
        return new FortuneCookieController(factory);
    }

    @Test
    void shouldReturnPositiveFortune() {
        // Действие
        FortuneCookie cookie = goodFactoryController.tellFortune();
        String fortuneText = cookie.getFortuneText();

        // Проверка
        assertEquals("positive", fortuneText,
                "Предсказание должно быть 'positive'. Получено: " + fortuneText);
    }

    @Test
    void shouldReturnNegativeFortune() {
        // Действие
        FortuneCookie cookie = badFactoryController.tellFortune();
        String fortuneText = cookie.getFortuneText();

        // Проверка
        assertEquals("negative", fortuneText,
                "Предсказание должно быть 'negative'. Получено: " + fortuneText);
    }
}