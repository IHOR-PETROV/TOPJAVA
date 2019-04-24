package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import javax.swing.plaf.PanelUI;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateMealsUtil {


    public CreateMealsUtil() {}
    public static List<Meal> getMeals(){


        return  Arrays.asList(

                new Meal(0,LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(1 ,LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(2,LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(3,LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 2001),
                new Meal(4 ,LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 2500),
                new Meal(5 ,LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 2510)
        );}



}
