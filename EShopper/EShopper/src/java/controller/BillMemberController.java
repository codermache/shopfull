

package controller;

import dal.BillDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.BillDetail;

public class BillMemberController extends HttpServlet {
   
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BillMemberController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BillMemberController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
         // Lấy dữ liệu từ form tìm kiếm
    String searchDate = request.getParameter("searchDate");
    
    // Kiểm tra nếu ngày tìm kiếm không rỗng
    if (searchDate != null && !searchDate.isEmpty()) {
        // Xử lý tìm kiếm các hóa đơn cho ngày đã chọn
        Vector<BillDetail> billDetails = (new BillDAO()).getOrderHistoryByDate(searchDate);
        
        // Đặt danh sách hóa đơn vào thuộc tính của request
        request.setAttribute("billDetails", billDetails);
    } else {
        // Nếu không có ngày tìm kiếm, hiển thị tất cả hóa đơn
        int userId = Integer.parseInt(request.getParameter("userId"));
        Vector<BillDetail> billDetails = (new BillDAO()).getOrderHistoryByUserId(userId);
        request.setAttribute("billDetails", billDetails);
    }
    // Chuyển hướng đến trang JSP
    request.getRequestDispatcher("viewAllBills.jsp").forward(request, response);
        
        
         int userId = Integer.parseInt(request.getParameter("userId"));
        BillDAO billDAO = new BillDAO();
        Vector<BillDetail> billDetails = billDAO.getOrderHistoryByUserId(userId);

        request.setAttribute("billDetails", billDetails);
        request.getRequestDispatcher("viewAllBills.jsp").forward(request, response);
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
