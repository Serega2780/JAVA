package servlet;

import DAO.UserDaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class FilterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("login");
        String password = request.getParameter("password");
        String role = UserDaoFactory.getDaoFactory().createDAO().selectUserByRole(name, password);
        HomeController.sessions = new HashMap<>();
        switch (role) {
            case ("admin"):
                System.out.println(request.getSession().getId());
                HomeController.sessions.put(request.getSession().getId(), "admin");
                System.out.println(HomeController.sessions.toString());
                response.sendRedirect("admin");
                break;
            case ("user"):
                System.out.println(request.getSession().getId());
                HomeController.sessions.put(request.getSession().getId(), "user");
                System.out.println(HomeController.sessions.toString());
                response.sendRedirect("user");
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }
}
