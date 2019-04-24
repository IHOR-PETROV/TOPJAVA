package ru.javawebinar.topjava.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.exception.NotExistStorageException;
import ru.javawebinar.topjava.model.Meal;

public abstract class AbstractStorage<In> implements Storage {
    private static final Logger log = LoggerFactory.getLogger(AbstractStorage.class);

    protected abstract In getIndex(Integer id);

    protected abstract void doSave(Meal m);
    protected abstract Meal doGet(In ind);

    protected abstract boolean isExits(In ind);

    protected abstract void doUpdate(Meal m, In ind);

    public void save (Meal m){
       // final In ind = getExistedIndex(m.getId());
        doSave(m);
    }


    public void update(Meal m) {
        log.debug("Update "+ m.getId());
        final In ind = getExistedIndex(m.getId());
        log.debug("Update "+ m.getId()+" "+m.getDateTime()+" "+m.getDescription()+" "+m.getCalories());
        doUpdate(m,ind);
    }


    public Meal get(Integer id) {
        log.debug("Get " + id);
        final In ind = getExistedIndex(id);
        return doGet(ind);
    }


    private In getExistedIndex(Integer id) {
        log.debug("getExistedIndex "+id);
        final In ind = getIndex(id);
        if (!isExits(ind)) {
            log.debug("No " + id + " getExistedIndex");
            throw new NotExistStorageException(id);
        }
        return ind;
    }


}
