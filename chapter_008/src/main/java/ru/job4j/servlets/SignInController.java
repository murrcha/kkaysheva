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
 * SignInController
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SignInController extends HttpServlet {

    /**
     * logger
     */
    private static final Logger LOG = LogManager.getLogger(SignInController.class.getName());

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/SignInView.jsp").forward(req, resp);
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int existsId = ValidateService.getInstance().isSuccessAuth(login, password);
        if (existsId != -1) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            int role = ValidateService.getInstance().findById(existsId).getRole();
            session.setAttribute("role", role);
            LOG.info(String.format("sign in user login=%s, role=%s", login, role));
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "invalid login or password");
            LOG.info("invalid login or password");
            doGet(req, resp);
        }
    }
}
