package ru.job4j.servlets;

import ru.job4j.service.User;
import ru.job4j.service.Validate;
import ru.job4j.service.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * UsersServlet
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class UsersServlet extends HttpServlet {

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
        StringBuilder body = new StringBuilder("<html><head><title>Users</title></head>");
        Collection<User> users = service.findAll();
        body.append("<body><h2>Users list</h2>");
        body.append("<table border=\"1\"><thead><tr align=\"center\">");
        body.append("<td>ID</td><td>Login</td><td>Name</td><td>Email</td><td>Created</td><td>Action</td></tr></thead>");
        for (User user : users) {
            body.append(String.format(
                    "<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>",
                    user.getId(), user.getLogin(),
                    user.getName(), user.getEmail(),
                    user.getCreateDate()));
            body.append(String.format("<td><form method=\"get\" action=\"%s/edit\">", request.getContextPath()));
            body.append(String.format("<input type=\"hidden\" name=\"id\" value=\"%s\"/>", user.getId()));
            body.append("<input type=\"submit\" value=\"EDIT\"/></form></td></tr>");
        }
        body.append("<tr align=\"center\"><td colspan=\"6\">");
        body.append(String.format("<form method=\"get\" action=\"%s/create\">", request.getContextPath()));
        body.append("<input type=\"submit\" value=\"CREATE NEW USER\"></form></td></tr></table></body></html>");
        return body.toString();
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new  PrintWriter(resp.getOutputStream());
        writer.append(getHtml(req));
        writer.flush();
    }
}
