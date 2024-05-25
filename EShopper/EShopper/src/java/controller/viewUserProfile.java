
package controller;


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

public class viewUserProfile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet viewUserProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet viewUserProfile at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession(false); // Lấy session hiện tại (nếu tồn tại)
         System.out.println("thinhh1");
         System.out.println(session);
        if (session != null) {
            System.out.println("thinhh2");
            User user = (User) session.getAttribute("user");
            if (user != null) {
                System.out.println("thinhhok");
                request.getRequestDispatcher("ViewUserProfile.jsp").forward(request, response);
            } else {
                System.out.println("thinhh3");
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
          System.out.println("thinhh4");
           request.setAttribute("sessionTimeout", true);
            request.getRequestDispatcher("ViewUserProfile.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
