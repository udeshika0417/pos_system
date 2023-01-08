package lk.ijse.dep7.pos.api;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lk.ijse.dep7.pos.dto.CustomerDTO;
import lk.ijse.dep7.pos.utill.DBConnection;

@WebServlet(name = "customerServlet", value = "/customers")
public class CustomerServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /*
         * 1.DB Connect*/
        try {
            Connection connection = DBConnection.getConnection();
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT * FROM customers");

            List<CustomerDTO> customers = new ArrayList<>();

            while(resultSet.next()) {
                customers.add(new CustomerDTO(resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address")));


            }
            Jsonb jsonb = JsonbBuilder.create();
            String json = jsonb.toJson(customers);

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println(json);

            /*
         * 2.Fetch Customers
         * 3.Convert to JSON array
         * 4.Send back to the client
         * */
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void destroy() {
    }
}