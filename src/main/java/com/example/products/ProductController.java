package com.example.products;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Slf4j
@WebServlet("/products")
public class ProductController extends HttpServlet {
  ProductDAO service;

  @Override
  public void init(ServletConfig config) throws ServletException {
    service = new ProductDAO();
    service.open();
  }

  @Override
  public void destroy() {
    service.close();
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getParameter("action");
    String method = req.getMethod();
    String view = "";

    log.info("action: {}", action);
    log.info("method: {}", method);

    if(action == null) {
      resp.sendRedirect("/products?action=list");
    } else {
      switch (action) {
        case "list":
          view = getAllProducts(req, resp);
          req.getRequestDispatcher(view).forward(req, resp);
          break;
        case "info":
          view = getProductById(req, resp);
          req.getRequestDispatcher(view).forward(req, resp);
          break;
        case "add":
          view = addProduct(req, resp);
          if(method.equals("POST")) {
            resp.sendRedirect(view);
          } else if(method.equals("GET")) {
            req.getRequestDispatcher(view).forward(req, resp);
          }
          break;
        case "update":
          view = updateProduct(req, resp);
          if(method.equals("POST")) {
            resp.sendRedirect(view);
          } else if(method.equals("GET")) {
            req.getRequestDispatcher(view).forward(req, resp);
          }
          break;
        case "delete":
          view = deleteProductById(req, resp);
          resp.sendRedirect(view);
          break;
      }
    }
  }

  private String deleteProductById(HttpServletRequest req, HttpServletResponse resp) {
    int id = Integer.parseInt(req.getParameter("id"));
    service.deleteProductById(id);
    return "productList.jsp";
  }

  private String updateProduct(HttpServletRequest req, HttpServletResponse resp) {
    String method = req.getMethod();
    String view = "";

    if(method.equals("POST")) {
      Product product = new Product(
          Integer.parseInt(req.getParameter("id")),
          req.getParameter("name"),
          Integer.parseInt(req.getParameter("price")),
          req.getParameter("manufacturer"),
          Date.valueOf(req.getParameter("manufacturingDate"))
      );
      service.updateProduct(product);
      view = "/products?action=list";
    } else if(method.equals("GET")) {
      int id = Integer.parseInt(req.getParameter("id"));
      Product product = service.findById(id);
      req.setAttribute("product", product);
      view = "productUpdate.jsp";
    }
    return view;
  }

  private String addProduct(HttpServletRequest req, HttpServletResponse resp) {
    String method = req.getMethod();
    String view = "";

    if(method.equals("POST")) {
      Product product = new Product(
          0,
          req.getParameter("name"),
          Integer.parseInt(req.getParameter("price")),
          req.getParameter("manufacturer"),
          Date.valueOf(req.getParameter("manufacturingDate"))
      );
      service.addProduct(product);
      view = "/products?action=list";
    } else if(method.equals("GET")) {
      view = "productAdd.jsp";
    }
    return view;
  }

  private String getProductById(HttpServletRequest req, HttpServletResponse resp) {
    int id = Integer.parseInt(req.getParameter("id"));
    Product product = service.findById(id);
    req.setAttribute("product", product);
    return "productInfo.jsp";
  }

  private String getAllProducts(HttpServletRequest req, HttpServletResponse resp) {
    List<Product> products = service.findAll();
    req.setAttribute("products", products);
    return "productList.jsp";
  }
}
