package ru.job4j.servlets;

import ru.job4j.service.User;
import ru.job4j.service.Validate;
import ru.job4j.service.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * UserUpdateServlet
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class UserUpdateServlet extends HttpServlet {

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
        StringBuilder body = new StringBuilder("<html><head><title>Update user</title></head>");
        String uid = request.getParameter("id");
        User user = service.findById(Integer.valueOf(uid));
        body.append("<body><h2>Update user</h2>");
        body.append("<table><tr><td>ID</td>");
        body.append(String.format("<td>%s</td></tr>", user.getId()));
        body.append(String.format("<form method=\"post\" action=\"%s/edit\">", request.getContextPath()));
        body.append(String.format("<input type=\"hidden\" name=\"id\" value=\"%s\"/>", user.getId()));
        body.append("<tr><td>Login</td><td>");
        body.append(String.format("<input required type=\"text\" name=\"login\" value=\"%s\"></td></tr>", user.getLogin()));
        body.append("<tr><td>Name</td><td>");
        body.append(String.format("<input required type=\"text\" name=\"name\" value=\"%s\"></td></tr>", user.getName()));
        body.append("<tr><td>Email</td><td>");
        body.append(String.format("<input required type=\"text\" name=\"email\" value=\"%s\"></td></tr>", user.getEmail()));
        body.append("<tr><td colspan=\"2\"><input type=\"submit\" value=\"SAVE\"></td></tr>");
        body.append("</form></table></body></html>");
        return body.toString();
    }

    /**
     * Method getHtmlAfterSave
     * @param request object
     * @param result string
     * @return html
     */
    private String getHtmlAfterSave(HttpServletRequest request, String result) {
        StringBuilder html = new StringBuilder("<html><head><title>Update</title></head>");
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
        User user = service.findById(Integer.valueOf(req.getParameter("id")));
        user.setLogin(req.getParameter("login"));
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        String result = service.update(user.getId(), user);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(getHtmlAfterSave(req, result));
        writer.flush();
    }
}
