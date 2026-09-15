package controller;

import dao.DBConnection;
import model.Bus;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SearchBusServlet")
public class SearchBusServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String source = request.getParameter("source");
        String destination = request.getParameter("destination");

        List<Bus> buses = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM buses WHERE source=? AND destination=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, source);
            ps.setString(2, destination);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Bus bus = new Bus();

                bus.setId(rs.getInt("id"));
                bus.setBusName(rs.getString("bus_name"));
                bus.setSource(rs.getString("source"));
                bus.setDestination(rs.getString("destination"));
                bus.setDate(rs.getString("bus_date"));
                bus.setTime(rs.getString("bus_time"));
                bus.setPrice(rs.getDouble("price"));
                bus.setTotalSeats(rs.getInt("total_seats"));

                buses.add(bus);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("buses", buses);

        request.getRequestDispatcher("busList.jsp")
               .forward(request, response);
    }
}