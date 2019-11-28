package servlet;

import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;
import service.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private UserService userService;

    public void init() {
//        userService = new UserServiceJdbcImpl(new UserJdbcDAO(DBHelper.getInstance().getConnection()));
                userService = new UserServiceHibernateImpl(new UserHibernateDAO(new ServiceSessionFactory()
                        .getSessionFactory().openSession()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.deleteUser(id);
        response.sendRedirect("list");
    }
}
