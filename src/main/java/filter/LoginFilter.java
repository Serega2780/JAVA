package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
//"/admin", "/user", "/home"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String role = (String) req.getSession().getAttribute(req.getSession().getId());
        System.out.println(req.getRequestURI());

        if (role != null || req.getRequestURI().endsWith("/login") && req.getMethod().equals("POST")) {
            if (req.getRequestURI().endsWith("/login") && req.getMethod().equals("GET")) {
                res.sendRedirect("admin/list");
            } else {
                chain.doFilter(request, response);
            }

        } else {
            //res.sendRedirect("login");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }

    }

}
