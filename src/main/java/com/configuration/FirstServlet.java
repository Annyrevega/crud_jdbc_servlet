package com.configuration;

import com.configuration.db.database;
import com.configuration.domain.Customer;
import jdk.nashorn.internal.ir.RuntimeNode;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class FirstServlet extends HttpServlet {
    private static final long SerialVersionUID = 1L;
    private database database;

    public FirstServlet() throws SQLException {
        this.database = new database();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":

                    insertCustomer(request, response);

                    break;
                case "/edit":

                    showEditForm(request, response);

                    break;
                case "/delete":
                    deleteForm(request, response);

                    break;
                case "/update":
                    updateCustomer(request, response);

                    break;
                default:
                    listCustomer(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    private void listCustomer(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException, SQLException {
        List<Customer> listCustomer = database.selectAllUser();
        request.setAttribute("listCustomer", listCustomer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer_list.jsp");
        dispatcher.forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException, SQLException {
        long id = Integer.parseInt(request.getParameter("Id"));
        Customer existingCustomer = database.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer-form.jsp");
        request.setAttribute("customer", existingCustomer);
        dispatcher.forward(request, response);

    }


    private void insertCustomer(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException, SQLException {
        String product = request.getParameter("product_name");
        String price = request.getParameter("price");
        String shop = request.getParameter("shop_id");
        Customer customer = new Customer(product, price, shop);
        database.insertSql(customer);
        response.sendRedirect("list");
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException, SQLException {
        long id = Integer.parseInt(request.getParameter("id"));
        String product = request.getParameter("product_name");
        String price = request.getParameter("price");
        String shop =request.getParameter("shop_id");

        Customer book = new Customer(id, product, price, shop);
        database.updateSql(book);
        response.sendRedirect("list");
    }

    private void deleteForm(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException, SQLException {
        long id = Integer.parseInt(request.getParameter("Id"));
        database.deleteSql(id);
        response.sendRedirect("list");
    }


}
