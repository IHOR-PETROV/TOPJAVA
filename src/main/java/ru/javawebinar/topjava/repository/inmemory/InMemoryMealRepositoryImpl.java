package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    private Map<Integer, List<Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal MEAL : MealsUtil.MEALS) {
            SecurityUtil.setUserId(1);
            save(MEAL);
            SecurityUtil.setUserId(0);
        }

    }

    @Override
    public Meal save(Meal meal ) {

        if (!repository.containsKey(SecurityUtil.authUserId())) {

            repository.put(SecurityUtil.authUserId(), new ArrayList<Meal>());
        }
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.get(SecurityUtil.authUserId()).add(meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.get(SecurityUtil.authUserId()).set(getIndexElementMeal(   meal.getId()), meal);
    }

    @Override
    public void delete(int id ) {

        repository.get(SecurityUtil.authUserId()).remove(getIndexElementMeal(  id));
    }


    @Override
    public Meal get(int id ) {
        return repository.get(SecurityUtil.authUserId()).get(getIndexElementMeal(   id));
    }


    @Override
    public List<MealTo> getAll( ) {
        Collection collection = MealsUtil.getWithExcess(repository.get(SecurityUtil.authUserId()),MealsUtil.DEFAULT_CALORIES_PER_DAY);
        List <MealTo>list = new ArrayList(collection);
        list.sort((Comparator<MealTo>) (m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()));
        return list;
    }

    public int getIndexElementMeal(  Integer id) {
        for (int i = 0; i < repository.get(SecurityUtil.authUserId()).size(); i++) {
            if (repository.get(SecurityUtil.authUserId()).get(i).getId() == id) return i;
        }
        return (Integer) null;
    }
}

