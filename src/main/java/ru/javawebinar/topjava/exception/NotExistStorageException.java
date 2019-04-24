package ru.javawebinar.topjava.exception;

public class NotExistStorageException extends MealException {
    public NotExistStorageException(Integer id) {
        super("Meal " + id + " not exist", id);
    }
}
