package lk.ijse.dep7.pos.api;

import java.io.*;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lk.ijse.dep7.pos.Customer;

@WebServlet(name = "customerServlet", value = "/customer-servlet")
public class CustomerServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Customer customer = new Customer("C002","Kasun","Gallee");
        Jsonb jsonb = JsonbBuilder.create();
        String json =jsonb.toJson(customer);
        System.out.println(json);
        out.println(json);

        out.println("{\"id\":\"C001\"}");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println(request.getContentType());
//        System.out.println(request.getHeader("Content-Type"));
        if (!(request.getContentType()).equals("application/json")) {
            //ToDo : send and error message

        }
        else {

            StringBuilder body = new StringBuilder();
            request.getReader().lines().forEach(l-> body.append(l+"\n"));
            Jsonb jsonb = JsonbBuilder.create();
            Customer customer=jsonb.fromJson(body.toString(),Customer.class);

            System.out.println(customer);
            System.out.println(customer.getId());
            System.out.println(customer.getName());
            System.out.println(customer.getAddress());
        }
    }

    public void destroy() {
    }
}