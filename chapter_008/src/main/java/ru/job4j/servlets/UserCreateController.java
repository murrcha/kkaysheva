package ru.job4j.servlets;

import ru.job4j.service.User;
import ru.job4j.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * UserCreateController
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class UserCreateController extends HttpServlet {

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/UserCreateView.jsp").forward(req, resp);
    }

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
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
