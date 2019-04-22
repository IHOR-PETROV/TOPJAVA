package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        getStreamFilteredWithExceeded(mealList,
                LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
////
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> listUserMealWithExceed = new ArrayList<>();
        Map<LocalDate, Integer> map = new HashMap();
        for (UserMeal userMeal : mealList) {
            map.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(),
                    (a, b) -> b + userMeal.getCalories());
        }


        for (UserMeal userMeal : mealList) {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                listUserMealWithExceed.add(new UserMealWithExceed(
                        userMeal.getDateTime(),
                        userMeal.getDescription(),
                        userMeal.getCalories(),
                        map.getOrDefault(userMeal.getDateTime().toLocalDate(), 100) > caloriesPerDay));
            }
        }


        return listUserMealWithExceed;
    }

    public static List<UserMealWithExceed> getStreamFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, List<UserMeal>> localDateListMap = mealList.stream().collect(
                Collectors.groupingBy(u -> u.getDateTime().toLocalDate()));


        return mealList.stream().filter(userMeal -> TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime) == true)
                .map(userMeal -> new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(),
                        localDateListMap.get(userMeal.getDateTime().
                                toLocalDate()).stream()
                                .mapToInt(u -> u.getCalories()).sum() > caloriesPerDay)
                ).collect(Collectors.toList());


    }


}
