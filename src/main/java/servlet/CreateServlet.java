package servlet;

import DAO.UserDaoFactory;
import DAO.UserDaoFactoryImplJDBC;
import DAO.UserDaoImplJDBC;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/new", "/insert"})
public class CreateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //   ServletActions.insertUser(request, response,true);
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User(name, password, role, email, country);
        UserDaoFactory.getDaoFactory().createDAO().insertUser(newUser);
        response.sendRedirect("admin");
        // new UserDaoImplJDBC().insertUser(newUser);
    }
}
