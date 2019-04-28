package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public interface MealService {

    Meal save(Meal user);

    void delete(int id) throws NotFoundException;

    Meal get(int id) throws NotFoundException;

     void update(Meal user);

    List<MealTo> getAll();
}