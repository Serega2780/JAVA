package servlet;

import model.User;
import service.UserFactoryHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/home")
public class HomeController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
/*
        List<User> listUser = UserDaoFactory.getDaoFactory().createDAO().selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
*/
        String userSession = LoginServlet.isAuthenticated(request.getSession().getId());
        if (userSession.equals("admin")) {
            response.sendRedirect("admin/list");

        } else {
            List<User> listUser = UserFactoryHelper.getDaoFactory().createDAO().selectNotAdmins();
            request.setAttribute("listUser", listUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/user-access.jsp");
            dispatcher.forward(request, response);
        }

    }


}
