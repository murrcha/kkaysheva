package ru.job4j.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * AuthFilter
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class AuthFilter implements Filter {

    /**
     * ${@inheritDoc}
     */
    @Override
    public void init(FilterConfig filterConfig) {

    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void destroy() {

    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (request.getRequestURI().contains("signin")) {
            filterChain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            //noinspection SynchronizationOnLocalVariableOrMethodParameter
            synchronized (session) {
                if (session.getAttribute("login") == null) {
                    response.sendRedirect(String.format("%s/signin", request.getContextPath()));
                    return;
                }
            }
            filterChain.doFilter(req, resp);
        }
    }
}
