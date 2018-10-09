package ru.job4j.servlets;

import ru.job4j.service.User;
import ru.job4j.service.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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
    private final ValidateService service = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(service.findAll().toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String uId = req.getParameter("id");
        resp.setContentType("text/plain");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if (action != null) {
            switch (action) {
                case "add":
                    service.add(new User(login, name, email, new Date()));
                    writer.append("add new user").flush();
                    break;
                case "update":
                    if (uId != null) {
                        int id = Integer.valueOf(uId);
                        User user = service.findById(id);
                        if (login != null) {
                            user.setLogin(login);
                        }
                        if (name != null) {
                            user.setName(name);
                        }
                        if (email != null) {
                            user.setEmail(email);
                        }
                        service.update(id, user);
                        writer.append("update user").flush();
                    } else {
                        writer.append("id is null").flush();
                    }
                    break;
                case "delete":
                    if (uId != null) {
                        int id = Integer.valueOf(uId);
                        service.delete(id);
                        writer.append("delete user").flush();
                    } else {
                        writer.append("id is null").flush();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
