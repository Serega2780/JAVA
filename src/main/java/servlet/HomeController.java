package servlet;

import DAO.UserDaoFactory;
import DAO.UserDaoImplJDBC;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/")
public class HomeController extends HttpServlet {

    protected static Map<String, String> sessions;

    public void init() {

     /*   userService = UserService.getInstance();
        userService.getDAO().createTable();



        UserDaoImplJDBC userDaoImplJDBC = (UserDaoImplJDBC) UserDaoFactory.getDaoFactory().createDAO();
        userDaoImplJDBC.createTable();
        */

    }

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

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }


}
