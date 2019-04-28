package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {
    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
     }

    @Override
    public Meal save(Meal user) {
        return repository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
       repository.delete(id);
    }

    @Override
    public Meal get(int id) throws NotFoundException {

        return repository.get(id);
    }

    @Override
    public void update(Meal user) {
        repository.save(user);
    }

    @Override
    public List<MealTo> getAll() {



        return (List)repository.getAll();
    }
}