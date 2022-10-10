package ru.apoltsev;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import ru.apoltsev.data.Elements;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ParameterizedTests {
    @ValueSource(strings = {"Пушкин А.С", "Толстой Л.Н"})
    @ParameterizedTest(name = "Проверка числа результатов поиска в Читай-городе для запроса {0}")
    void chitaiGorodxSearchTests(String testData) {
        open("https://www.chitai-gorod.ru");
        $("input[type=text]").setValue(testData).pressEnter();
        $$(".product-card__info")
                .shouldHave(CollectionCondition.size(24))
                .first()
                .shouldHave(text(testData));
    }

    @CsvSource(value = {
            "Пушкин А.С, Евгений Онегин. Роман в стихах",
            "Толстой Л.Н, Война и мир. Том 1-4 (комплект из 2 книг)"
    })
    @ParameterizedTest(name = "Проверка числа результатов поиска в Читай-городе для запроса {0}")
    void chitaiGorodxSearchDifferentTests(String searchQuery, String expectedText) {
        open("https://www.chitai-gorod.ru");
        $("input[type=text]").setValue(searchQuery).pressEnter();
        $$(".product-card__info")
                .shouldHave(CollectionCondition.size(24))
                .first()
                .shouldHave(text(expectedText));
    }

    static Stream<Arguments> chitaiGorodxSearchDifferentAnotherTestsProvider() {
        return Stream.of(
                Arguments.of(Elements.BOOKS.getElements(), List.of("Новинки литературы", "Лучшие из лучших"
                        , "10 книг, которые помогут справиться со стрессом", "Скоро в продаже", "Развлечение и антистресс")),
                Arguments.of(Elements.SOUVENIRS.getElements(), List.of("Сувениры к празднику", "Дом, Быт, Декор", "Игры и Игрушки", "Личные вещи"
                        , "Мелочи сувенирные", "Сувенирные канцелярские и офисные принадлежности", "Поздравительная атрибутика", "Календари"))
        );
    }

    @MethodSource("chitaiGorodxSearchDifferentAnotherTestsProvider")
    @ParameterizedTest(name = "Проверка отображения названия кнопок для раздела {0}")
    void chitaiGorodxSearchDifferentAnotherTests(String elements, List<String> nameItem) {
        open("https://www.chitai-gorod.ru");
        $$(".nav__item").find(text(String.valueOf(elements))).click();
        List<String> texts = $$(".slider__link").filter(visible).texts();
        Assertions.assertEquals(nameItem, texts);
    }
}