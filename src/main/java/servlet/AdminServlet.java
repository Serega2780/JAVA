package servlet;

import DAO.UserDaoFactory;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       for(Map.Entry<String,String> entry : HomeController.sessions.entrySet()){
           if (entry.getKey().equals(request.getSession().getId())&&entry.getValue().equals("admin")){
               List<User> listUser = UserDaoFactory.getDaoFactory().createDAO().selectAllUsers();
               request.setAttribute("listUser", listUser);
               RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
               dispatcher.forward(request, response);
               break;
           }
       }
     //   if(request.getSession().getId().equals(HomeController.sessions.))


    }
}
