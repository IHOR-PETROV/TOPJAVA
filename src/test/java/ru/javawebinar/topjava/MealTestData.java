package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class MealTestData {
    public static final Meal MEAL_1  = new Meal(100002,LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510)  ;
    public static final Meal MEAL_2 = new Meal(100003,LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500)  ;

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }
    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }
    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }

}

