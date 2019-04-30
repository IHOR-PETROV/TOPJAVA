package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {
    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcMealRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertMeal = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("meals")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

    }

    private final SimpleJdbcInsert insertMeal;

    @Override
    public Meal save(Meal meal, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("dateTime", meal.getDateTime())
                .addValue("description", meal.getCalories())
                .addValue("calories", meal.getCalories());

        if (meal.isNew()) {
            Number nemKey = insertMeal.executeAndReturnKey(map);
            meal.setId(nemKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE meals SET datetime=:datetime, description=:description, calories=:calories, WHERE id=:id", map) == 0) {
            return null;
        }


        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals WHERE id=?",id)!=0;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> meals = jdbcTemplate.query("SELECT * FROM meals WHERE id=?", ROW_MAPPER,id);
        return  DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> meals = jdbcTemplate.query("SELECT * FROM meals WHERE id=?", ROW_MAPPER,userId);

        return  meals;    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {

        return null;//getAllFiltered(userId, meal -> Util.isBetween(meal.getDateTime(), startDate, endDate));;
    }
//    private List<Meal> getAllFiltered(int userId, Predicate<Meal> filter) {
//        TreeSet<Meal> meals = (TreeSet<Meal>) getAll(userId).stream().collect(Collectors.toSet());
//        return meals == null ? Collections.emptyList() :
//                meals.getCollection().stream()
//                        .filter(filter)
//                        .sorted(Comparator.comparing(Meal::getDateTime).reversed())
//                        .collect(Collectors.toList());
//    }
}
