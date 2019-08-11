package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin", "/user", "/home"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            String userSession = LoginServlet.isAuthenticated(req.getSession().getId());
            if (userSession.equals("admin") || userSession.equals("user")) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("index.jsp");
            }

        }
    }

    @Override
    public void destroy() {
    }
}
