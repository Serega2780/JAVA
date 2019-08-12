package Filters;

import servlet.LoginServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebFilter("/*")
//"/admin", "/user", "/home"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (LoginServlet.sessions == null) {
            LoginServlet.sessions = new HashMap<>();
        }
        String userSession = LoginServlet.isAuthenticated(req.getSession().getId());
        if (!userSession.isEmpty() || req.getRequestURI().endsWith("/login")) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/login");
            //("index.jsp");
        }


    }

    @Override
    public void destroy() {
    }
}
