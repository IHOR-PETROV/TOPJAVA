package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ListStorage extends AbstractStorage<Integer> {
    List<Meal> listMeal = new ArrayList<>();
   private static AtomicInteger atomicInteger = new AtomicInteger(6);

    public   void setMeal(Meal meal){
        Integer autoInt = atomicInteger.getAndIncrement();
        meal.setId(autoInt);
        ;


    }

    @Override
    public void clear() {
        listMeal.clear();
    }


    @Override
    protected void doUpdate(Meal m, Integer ind) {
        listMeal.set(ind,m);

    }

    @Override
    public void doSave(Meal m) {
        Integer autoint = atomicInteger.getAndIncrement();
        m.setId(autoint);
        if(!listMeal.add(m)) System.out.println("doSave FALSE :: "+m.getId());
        else System.out.println("doSave TRUE :: "+listMeal.get(getIndex( m.getId())).getId());
    }

    @Override
    protected Integer getIndex(Integer id) {
        for (int i = 0; i < listMeal.size(); i++) {
            if (listMeal.get(i).getId().equals(id)) {
                return i;
            }
        }
            return null;
    }


    @Override
    public Meal doGet(Integer ind) {

        return listMeal.get(ind);
    }

    @Override
    protected boolean isExits(Integer ind) {
        return ind!= null;
    }


    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Meal> getAllSorted() {
        return listMeal;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void setAll(List l) {
        listMeal.addAll(l);
    }
}