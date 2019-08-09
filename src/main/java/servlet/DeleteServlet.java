package servlet;

import DAO.UserDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //ServletActions.deleteUser(request, response,true);
        int id = Integer.parseInt(request.getParameter("id"));
        UserDaoFactory.getDaoFactory().createDAO().deleteUser(id);
        response.sendRedirect("admin");
    }

}
