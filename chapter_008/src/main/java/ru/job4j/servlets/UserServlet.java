package ru.job4j.servlets;

import ru.job4j.service.User;
import ru.job4j.service.Validate;
import ru.job4j.service.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * UserServlet
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class UserServlet extends HttpServlet {

    /**
     * service
     */
    private final Validate service = ValidateService.getInstance();

    /**
     * action types
     */
    private enum Action { add, update, delete }

    /**
     * actions map
     */
    private final Map<Action, Function<HttpServletRequest, String>> dispatch = new HashMap<>();

    /**
     * Method add
     * @return result message
     */
    private Function<HttpServletRequest, String> add() {
        return request ->
                service.add(new User(
                        request.getParameter("login"),
                        request.getParameter("name"),
                        request.getParameter("email"),
                        "123",
                        2,
                        new Date()));
    }

    /**
     * Method update
     * @return result message
     */
    private Function<HttpServletRequest, String> update() {
        return request -> {
            String result = "id is null";
            String uid = request.getParameter("id");
            if (uid != null) {
                int id = Integer.valueOf(uid);
                User user = service.findById(id);
                String login = request.getParameter("login");
                if (login != null) {
                    user.setLogin(login);
                }
                String name = request.getParameter("name");
                if (name != null) {
                    user.setName(name);
                }
                String email = request.getParameter("email");
                if (email != null) {
                    user.setEmail(email);
                }
                result = service.update(id, user);
            }
            return result;
        };
    }

    /**
     * Method delete
     * @return result message
     */
    private Function<HttpServletRequest, String> delete() {
        return request -> {
            String result = "id is null";
            String uid = request.getParameter("id");
            if (uid != null) {
                int id = Integer.valueOf(uid);
                result = service.delete(id);
            }
            return result;
        };
    }

    /**
     * Method load action to map
     * @param type action
     * @param handle action
     */
    private void load(Action type, Function<HttpServletRequest, String> handle) {
        dispatch.put(type, handle);
    }

    /**
     * Method doAction
     * @param type action
     * @param request http request
     * @return result message
     */
    private String doAction(Action type, HttpServletRequest request) {
        return dispatch.get(type).apply(request);
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void init() {
        load(Action.add, add());
        load(Action.update, update());
        load(Action.delete, delete());
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(service.findAll().toString());
        writer.flush();
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        resp.setContentType("text/plain");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if (action != null) {
            writer.append(doAction(Action.valueOf(action), req)).flush();
        }
    }
}
