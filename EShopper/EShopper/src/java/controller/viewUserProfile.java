
package controller;


import dal.UserDAO;
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
       
         System.out.println(session);
        if (session != null) {
          
            User user = (User) session.getAttribute("user");
            if (user != null) {
               
                request.getRequestDispatcher("ViewUserProfile.jsp").forward(request, response);
            } else {
               
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
        
           request.setAttribute("sessionTimeout", true);
            request.getRequestDispatcher("ViewUserProfile.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // Lấy dữ liệu mới từ request
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        
        // Lấy thông tin người dùng từ session hoặc cookie
        // Phần này cần phải thay đổi tùy thuộc vào cách bạn xử lý đăng nhập và quản lý phiên.
        User user = (User) request.getSession().getAttribute("user");

        // Cập nhật thông tin người dùng
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);

        // Lưu thông tin người dùng vào cơ sở dữ liệu
        UserDAO userDAO = new UserDAO();
        if (userDAO.update(user)) {
            // Trả về phản hồi thành công
            response.getWriter().write("success");
        } else {
            // Trả về phản hồi thất bại
            response.getWriter().write("failure");
        }
    }
    

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
