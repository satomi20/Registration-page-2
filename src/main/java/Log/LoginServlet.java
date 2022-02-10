package Log;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String n= req.getParameter("email");
        String p= req.getParameter("password");
        String c = req.getParameter("name");


        if(DAO.isAdmin(n, p)) {
            RequestDispatcher rc = req.getRequestDispatcher("Admin.html");
            rc.include(req, resp);
        } else if(DAO.login(n, p)){
                RequestDispatcher rd = req.getRequestDispatcher("Welcome");
                rd.include(req, resp);
        } else{
            out.print("Sorry username or password error");
            RequestDispatcher rd=req.getRequestDispatcher("index.html");
            rd.include(req,resp);
        }

        out.close();
    }
}

