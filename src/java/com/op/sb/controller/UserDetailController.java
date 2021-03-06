/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.op.sb.controller;

import com.op.sb.connection.factory.DatabaseResourceFactory;
import com.op.sb.dto.UserDetailDTO;
import com.op.sb.service.Impl.UserDetailServiceImpl;
import com.op.sb.service.UserDetailService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author nisalsp9
 */
@WebServlet(name = "UserDetailController", urlPatterns = {"/userdetail"})
public class UserDetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserDetailController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserDetailController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // processRequest(request, response);
            //System.out.println("llllllllllllllllllllllllllllllllllllllllll");
            BufferedReader reader = request.getReader();

            JSONObject jsonObject = new JSONObject(reader.readLine());

            String name = jsonObject.getString("name");
            int age = jsonObject.getInt("age");
            String home = jsonObject.getString("home");
            String office = jsonObject.getString("office");
            String mobile = jsonObject.getString("mobile");
            String phone = jsonObject.getString("phone");
            String email = jsonObject.getString("email");
            

            UserDetailDTO user = new UserDetailDTO(0,name, age, home, office, mobile, phone, email);

            Connection connection = DatabaseResourceFactory.getFactoryConnection().getConnection();

            UserDetailService service = new UserDetailServiceImpl();

            boolean rst = 0 < service.addNewUser(connection, user);

            if (rst) {

               PrintWriter pw = response.getWriter();
                pw.print("Saved....!");

            } else {

                PrintWriter pw = response.getWriter();
                pw.print("Something went wrong....!");

            }

        } catch (JSONException ex) {
            Logger.getLogger(UserDetailController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDetailController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
