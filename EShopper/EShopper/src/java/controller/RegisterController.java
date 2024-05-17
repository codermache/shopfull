/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;


public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("Register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        UserDAO userDAO = new UserDAO();
        String mess = "";
        String username = req.getParameter("username").trim();
        String pass = req.getParameter("password").trim();
        String name = req.getParameter("name").trim();
        String email = req.getParameter("email").trim();
        String phone = req.getParameter("phone").trim();
        String address = req.getParameter("address").trim();

        Vector<User> users = (new UserDAO()).getAll();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                req.setAttribute("duplicateUsername", "Username already exists");
                req.getRequestDispatcher("Register.jsp").forward(req, resp);
            }
        }
        //check max length
        if (username.length() > 63 || pass.length() > 255
                || username.length() > 255
                || email.length() > 255
                || phone.length() > 10) {
            mess = "Your input have reached max length!";
            req.setAttribute("mess", mess);
            req.getRequestDispatcher("Register.jsp").forward(req, resp);
            return;
        }

        //check validate mail
        String mailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (!email.matches(mailRegex)) {
            mess = "The Email is invalid !";
            req.setAttribute("mess", mess);
            req.getRequestDispatcher("Register.jsp").forward(req, resp);
            return;
        }

        //check if the moblie is in right fomat and length
        String moblieRegex = "(09|03|07|08|05)+([0-9]{8})";
        if (!phone.matches(moblieRegex) || phone.length() != 10) {
            mess = "The phone number is invalid";
            req.setAttribute("mess", mess);
            req.getRequestDispatcher("Register.jsp").forward(req, resp);
            return;
        }

        String user = new UserDAO().getUserRegister(username, pass, name, email, phone, address);
        req.setAttribute("registerSuccess", "Register successful");
        req.getRequestDispatcher("Login.jsp").forward(req, resp);
    }
}
