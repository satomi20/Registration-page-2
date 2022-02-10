package Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");


        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User userModel = new User(name, email, password);



        if (DAO.saveUser(userModel)) {
            resp.sendRedirect("index.html");
        } else {
            String errorMessage = "User Available";
            HttpSession regSession = req.getSession();
            regSession.setAttribute("RegError", errorMessage);
            resp.sendRedirect("registration.html");
        }

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
