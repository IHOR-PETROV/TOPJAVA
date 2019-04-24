package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal {
    private LocalDateTime dateTime;

    private String description;

    private Integer calories;

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        setMeal(dateTime, description, calories);
    }

    public Meal(int id, LocalDateTime dateTime, String description, int calories) {
        setMeal(dateTime, description, calories);
        this.id = id;

    }

    public void setMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;

    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public Integer getId() {
        return id;
    }
}
