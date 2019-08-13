package servlet;

import model.User;
import service.UserFactoryHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
 /*
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = null;
        String name = request.getParameter("login");
        String password = request.getParameter("password");
        if (!name.isEmpty() && !password.isEmpty()) {
            user = UserFactoryHelper.getDaoFactory().createDAO().selectUserByRole(name, password);
        }

        if (user != null) {
            request.getSession().setAttribute(request.getSession().getId(), user.getRole());
            switch (user.getRole()) {
                case ("admin"):
                    response.sendRedirect("admin/list");
                    break;
                case ("user"):
                    response.sendRedirect("user");
                    break;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }

    }
}
