package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public DateUtil() {}
    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
    public static LocalDateTime formatLocalDateTimeToString(String localDateTime ,String pattern){
        return LocalDateTime.parse(localDateTime,DateTimeFormatter.ofPattern(pattern));
    }
}
