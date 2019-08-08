package servlet;

import model.User;
import service.UserService;
import service.UserServiceHibernate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ServletActions {
    public static void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    public static void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = UserService.getInstance().getDAO().selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    public static void insertUser(HttpServletRequest request, HttpServletResponse response, boolean isHibernate)
            throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User(name, email, country);
        if (!isHibernate) {
            UserService.getInstance().getDAO().insertUser(newUser);
        } else {
            UserServiceHibernate.getInstance().getDAOH().insertUserH(newUser);
        }
        response.sendRedirect("list");
    }

    protected static void updateUser(HttpServletRequest request, HttpServletResponse response, boolean isHibernate)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User user = new User(id, name, email, country);

        if (!isHibernate) {
            UserService.getInstance().getDAO().updateUser(user);
        } else {
            UserServiceHibernate.getInstance().getDAOH().updateUserH(user);
        }
        response.sendRedirect("list");
    }

    protected static void deleteUser(HttpServletRequest request, HttpServletResponse response, boolean isHibernate)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (!isHibernate) {
            UserService.getInstance().getDAO().deleteUser(id);
        } else {
            UserServiceHibernate.getInstance().getDAOH().deleteUserH(id);
        }
        response.sendRedirect("list");

    }

    protected static void listUser(HttpServletRequest request, HttpServletResponse response, boolean isHibernate)
            throws IOException, ServletException {
        List<User> listUser;
        if (!isHibernate) {
            listUser = UserService.getInstance().getDAO().selectAllUsers();
        } else {
            listUser = UserServiceHibernate.getInstance().getDAOH().selectAllUsersH();
        }
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

}
