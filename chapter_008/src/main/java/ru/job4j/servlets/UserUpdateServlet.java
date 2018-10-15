package ru.job4j.servlets;

import ru.job4j.service.User;
import ru.job4j.service.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserUpdateServlet
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class UserUpdateServlet extends HttpServlet {

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
        ValidateService.getInstance().update(user.getId(), user);
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}
