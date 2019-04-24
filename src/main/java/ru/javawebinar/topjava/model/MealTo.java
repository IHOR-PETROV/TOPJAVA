package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealTo {
    private final LocalDateTime dateTime;

    private  String description;

    private final int calories;

    private final boolean excess;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Integer id;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }

    public MealTo(Integer id,LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
        this.id = id;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
}