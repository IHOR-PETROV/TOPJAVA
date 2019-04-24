package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.Storage;
import ru.javawebinar.topjava.repository.ListStorage;
import ru.javawebinar.topjava.util.DateUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    MealsUtil mealsUtil;
    Storage storage;

    /**
     * A convenience method which can be overridden so that there's no need
     * to call <code>super.init(config)</code>.
     *
     * <p>Instead of overriding {@link #init(ServletConfig)}, simply override
     * this method and it will be called by
     * <code>GenericServlet.init(ServletConfig config)</code>.
     * The <code>ServletConfig</code> object can still be retrieved via {@link
     * #getServletConfig}.
     *
     * @throws ServletException if an exception occurs that
     *                          interrupts the servlet's
     *                          normal operation
     */
    @Override
    public void init() throws ServletException {
        super.init();
        mealsUtil = new MealsUtil();
        LOG.debug("init to mealsUtil " + mealsUtil.getMeals());
        storage = new ListStorage();
        storage.setAll(mealsUtil.getMeals());
        LOG.debug("init to storage " + storage.getAllSorted());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to meals");
        String actuale = request.getParameter("action");
        List<MealTo> listMealTo = mealsUtil.getFilteredMealsTo(LocalTime.of(0, 0), LocalTime.of(23, 0), 2000);
        request.setAttribute("listMealTo", listMealTo);
        LOG.debug("redirect to meals == " + actuale);
        switch (actuale == null ? "null" : actuale) {
            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                Meal meal = storage.get(id);

                request.setAttribute("meal", meal);
                LOG.debug("redirect to edit actuale edit " + meal);
                request.getRequestDispatcher("/edit.jsp").forward(request, response);

            case "null":
                LOG.debug("redirect to meals actuale NULL");
                request.getRequestDispatcher("/meals.jsp").forward(request, response);




        }
        // response.sendRedirect("meals.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/htnl;charset=UTF-8");
        String actuale = request.getParameter("action");
        switch (actuale == null ? "null" : actuale) {

            case "edit":
                Integer idd = Integer.parseInt(request.getParameter("id"));
                LocalDateTime localDateTime = DateUtil.formatLocalDateTimeToString(request.getParameter("time"), "dd.MM.yyyy. HH:mm:ss");
                Integer calories = Integer.parseInt(request.getParameter("calories"));
                String description = request.getParameter("description");
                Meal mealU = new Meal(idd, localDateTime, description, calories);
                storage.update(mealU);
                List<MealTo> listMealTo = mealsUtil.getFilteredMealsTo(LocalTime.of(0, 0), LocalTime.of(23, 0), 2000);
                request.setAttribute("listMealTo", listMealTo);

                for (int i = 0; i < listMealTo.size(); i++) {


                    LOG.info(" EDIT storage.get(idd )  " + storage.get(i).getId() + storage.get(i).getDateTime()
                            + " " + storage.get(i).getDescription() + " " + storage.get(i).getCalories());
                }
                request.getRequestDispatcher("/meals.jsp").forward(request, response);

            case "crate":
                String time = request.getParameter("time");
                LocalDate localDate = LocalDate.parse(time);
                LocalTime localTime = LocalTime.now();
                LocalDateTime localDateTime1 = LocalDateTime.of(localDate, localTime);
                Integer caloriesC = Integer.parseInt(request.getParameter("calories"));
                String descriptionC = request.getParameter("description");
                LOG.info("CREATE   :    " + request.getParameter("time"));

                Meal mealUs = new Meal(localDateTime1, descriptionC, caloriesC);

                storage.save(mealUs);
                List<MealTo> listMealTos = mealsUtil.getFilteredMealsTo(LocalTime.of(0, 0), LocalTime.of(23, 0), 2000);
                request.setAttribute("listMealTo", listMealTos);


                request.getRequestDispatcher("/meals.jsp").forward(request, response);

        }


    }


}
