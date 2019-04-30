package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))

public class MealServiceTest {
    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }
    @Autowired
    MealService mealService;



    @Test
    public void get() {
       Meal meal = mealService.get(100002,ADMIN_ID);
        assertMatch(meal,MEAL_1);

    }
    @Test(expected = NotFoundException.class)
    public void deletedNotFound() throws Exception {
        mealService.delete(1,100 );
    }

    @Test
    public void delete() {
        mealService.delete(100002,ADMIN_ID );
        assertMatch(mealService.getAll(ADMIN_ID),MEAL_2 );

    }

    @Test
    public void getBetweenDates() {
    }

    @Test
    public void getBetweenDateTimes() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
        Meal update = new Meal();
        update.setId(100002);
        update.setCalories(52151);
    //    update.setDateTime(LocalDateTime.of(2018, Month.JUNE, 6, 11, 0));
        update.setDescription("ЗАВТРУЛЧ");
        mealService.update(update,100 );
        assertMatch(mealService.get(100002,000), update);
    }

    @Test
    public void create() {
    }
}