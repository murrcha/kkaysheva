package ru.job4j.servlets;

import ru.job4j.service.User;
import ru.job4j.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserUpdateController
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class UserUpdateController extends HttpServlet {

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user",
                ValidateService
                        .getInstance()
                        .findById(Integer.valueOf(req.getParameter("id"))));
        req.getRequestDispatcher("/WEB-INF/views/UserEditView.jsp").forward(req, resp);
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        User user = ValidateService.getInstance().findById(Integer.valueOf(req.getParameter("id")));
        user.setLogin(req.getParameter("login"));
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        user.setRole(Integer.valueOf(req.getParameter("role")));
        ValidateService.getInstance().update(user.getId(), user);
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
