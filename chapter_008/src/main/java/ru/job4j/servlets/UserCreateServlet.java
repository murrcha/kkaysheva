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

/**
 * UserCreateServlet
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class UserCreateServlet extends HttpServlet {

    /**
     * service
     */
    private final Validate service = ValidateService.getInstance();

    /**
     * Method getHtml
     * @param request object
     * @return html
     */
    private String getHtml(HttpServletRequest request) {
        StringBuilder html = new StringBuilder("<html><head><title>Update user</title></head>");
        html.append("<body><h2>Create user</h2><table>");
        html.append(String.format("<form method=\"post\" action=\"%s/create\">", request.getContextPath()));
        html.append("<tr><td>Login</td><td>");
        html.append("<input required type=\"text\" name=\"login\" placeholder=\"login\"></td></tr>");
        html.append("<tr><td>Name</td><td>");
        html.append("<input required type=\"text\" name=\"name\" placeholder=\"name\"></td></tr>");
        html.append("<tr><td>Email</td><td>");
        html.append("<input required type=\"text\" name=\"email\" placeholder=\"name@domain.com\"></td></tr>");
        html.append("<tr><td colspan=\"2\"><input type=\"submit\" value=\"CREATE\"></td></tr>");
        html.append("</form></table></body></html>");
        return html.toString();
    }

    /**
     * Method getHtmlAfterCreate
     * @param request object
     * @param result string
     * @return html
     */
    private String getHtmlAfterCreate(HttpServletRequest request, String result) {
        StringBuilder html = new StringBuilder("<html><head><title>Create</title></head>");
        html.append(String.format("<body><h2>%s</h2>", result));
        html.append(String.format("<form method=\"get\" action=\"%s/list\">", request.getContextPath()));
        html.append("<input type=\"submit\" value=\"GO TO USERS LIST\"/></form>");
        html.append("</body></html>");
        return html.toString();
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(getHtml(req));
        writer.flush();
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String result = service.add(new User(
                req.getParameter("login"),
                req.getParameter("name"),
                req.getParameter("email"),
                new Date()
        ));
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(getHtmlAfterCreate(req, result));
        writer.flush();
    }
}
