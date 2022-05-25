package ls.cg.controler;

import ls.cg.model.Product;
import ls.cg.service.ProductServiceiml;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/Products")
public class ProductServlet extends HttpServlet {
    ProductServiceiml productServiceimls = new ProductServiceiml();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productServiceimls.delete(id);
        response.sendRedirect("/Products");
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        Product product = new Product(id,name,price);
        productServiceimls.update(id,product);
        response.sendRedirect("/Products");
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        Product product = new Product(id,name,price);
        productServiceimls.save(product);
        response.sendRedirect("/Products");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            case "delete":
                showDeleteForm(request,response);
                break;
            default:
                showListPage(request, response);
        }

    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/delete.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productServiceimls.findById(id);
        request.setAttribute("spCanXoa",product);
        requestDispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/edit.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productServiceimls.findById(id);
        request.setAttribute("spCanSua",product);
        requestDispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showListPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/template.jsp");
        List<Product> products = productServiceimls.finAll();
        request.setAttribute("danhSach", products);
        requestDispatcher.forward(request, response);
    }


}
