package ru.job4j.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * UsersController
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class UsersController extends HttpServlet {

    /**
     * logger
     */
    private static final Logger LOG = LogManager.getLogger(UsersController.class.getName());

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("users", ValidateService.getInstance().findAll());
        req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        //noinspection SynchronizationOnLocalVariableOrMethodParameter
        synchronized (session) {
            session.invalidate();
            LOG.info("user logout");
        }
        resp.sendRedirect(String.format("%s/signin", req.getContextPath()));
    }
}
