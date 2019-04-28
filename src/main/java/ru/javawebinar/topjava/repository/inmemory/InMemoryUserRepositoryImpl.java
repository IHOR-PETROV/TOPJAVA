package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> userMap = new TreeMap<Integer, User>();

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return userMap.remove(id) != null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);

        return userMap.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        List<User> listSort = new ArrayList<>(userMap.values());
        Collections.sort(listSort, Comparator.comparing((Function<User, String>)
                AbstractNamedEntity::getName).thenComparing(User::getEmail));
        return listSort;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        for (Map.Entry<Integer, User> entry : userMap.entrySet()) {
            User user = entry.getValue();
            if (user.getEmail().equals(email)) return user;
        }

        return null;
    }
}
