package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.util.List;

public interface MealRepository {
    Meal save(Meal meal );

    void delete(int id );

    Meal get(int id );

    List<MealTo> getAll( );
}
