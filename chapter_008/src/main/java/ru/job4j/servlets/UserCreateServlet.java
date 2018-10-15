package ru.job4j.servlets;

import ru.job4j.service.User;
import ru.job4j.service.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * UserCreateServlet
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class UserCreateServlet extends HttpServlet {

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        ValidateService.getInstance().add(new User(
                req.getParameter("login"),
                req.getParameter("name"),
                req.getParameter("email"),
                new Date()
        ));
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}
