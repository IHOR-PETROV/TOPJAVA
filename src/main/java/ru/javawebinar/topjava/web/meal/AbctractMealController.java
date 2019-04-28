package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

public class AbctractMealController {
    private static final Logger LOG = LoggerFactory.getLogger(AbctractMealController.class);

   @Autowired
    private MealService service;

    public List<MealTo> getAll(   ) {
        LOG.info("getAll");
        return service.getAll();
    }

    public Meal get(int id ) {
        LOG.info("get {}", id);
        return service.get(id);
    }

    public Meal create(Meal meal ) {
        LOG.info("create {}", meal);
        checkNew(meal);
        return service.save(meal);
    }

    public void delete(int id ) {
        LOG.info("delete {}", id);
        service.delete(id);
    }

    public void update(Meal meal ) {
        LOG.info("update {} with id={}", meal );
         service.update(meal);
    }


}
