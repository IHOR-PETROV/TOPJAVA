package ru.javawebinar.topjava.exception;

public class MealException extends RuntimeException {

    private final Integer id;
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public MealException(String message, Integer id) {
        super(message);
        this.id = id;
    }

}
