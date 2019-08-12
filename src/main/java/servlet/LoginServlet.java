package servlet;

import service.UserFactoryHelper;

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
public class LoginServlet extends HttpServlet {
    public static Map<String, String> sessions;

    public static String isAuthenticated(String sessionId) {

        try {
            for (Map.Entry<String, String> entry : sessions.entrySet()) {
                if (entry.getKey().equals(sessionId)) {
                    return entry.getValue();

                }

            }
        } catch (NullPointerException e) {

        }
        return "";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("login");
        String password = request.getParameter("password");
        String role = UserFactoryHelper.getDaoFactory().createDAO().selectUserByRole(name, password);
        sessions = new HashMap<>();
        System.out.println(request.getSession().getId());
        sessions.put(request.getSession().getId(), role);
        System.out.println(sessions.toString());
        response.sendRedirect("home");
   /*     switch (role) {
            case ("admin"):
                System.out.println(request.getSession().getId());
                sessions.put(request.getSession().getId(), "admin");
                System.out.println(sessions.toString());
                response.sendRedirect("admin");
                break;
            case ("user"):
                System.out.println(request.getSession().getId());
                sessions.put(request.getSession().getId(), "user");
                System.out.println(sessions.toString());
                response.sendRedirect("user");
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
        */

    }
}
