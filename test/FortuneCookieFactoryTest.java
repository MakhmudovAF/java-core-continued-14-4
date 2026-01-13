import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static java.util.Collections.singletonList;

public class FortuneCookieFactoryTest {
    private FortuneCookieFactory factory;

    @BeforeEach
    void setUp() {
        // Создаем новую фабрику перед каждым тестом
        FortuneConfig config = new FortuneConfig(true);
        factory = new FortuneCookieFactory(
                config,
                singletonList("positive"),
                singletonList("negative")
        );
    }

    @Test
    void shouldIncrementCountByOneAfterOneCookieBaked() {
        // Действие
        factory.bakeFortuneCookie();

        // Проверка
        assertEquals(1, factory.getCookiesBaked(),
                "Счетчик должен быть равен 1 после выпечки одной печеньки");
    }

    @Test
    void shouldIncrementCountByTwoAfterTwoCookiesBaked() {
        // Действие
        factory.bakeFortuneCookie();
        factory.bakeFortuneCookie();

        // Проверка
        assertEquals(2, factory.getCookiesBaked(),
                "Счетчик должен быть равен 2 после выпечки двух печенек");
    }

    @Test
    void shouldSetCounterToZeroAfterResetCookieCreatedCall() {
        // Действие 1: выпекаем печеньку
        factory.bakeFortuneCookie();

        // Проверка 1: счетчик должен быть 1
        assertEquals(1, factory.getCookiesBaked(),
                "Счетчик должен быть равен 1 после выпечки печеньки");

        // Действие 2: сбрасываем счетчик
        factory.resetCookiesCreated();

        // Проверка 2: счетчик должен быть 0
        assertEquals(0, factory.getCookiesBaked(),
                "Счетчик должен быть равен 0 после вызова resetCookiesCreated()");
    }
}