package controller;

import dao.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/BookBusServlet")
public class BookBusServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String busIdParameter = request.getParameter("busId");
        if (busIdParameter == null || busIdParameter.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "A bus ID is required.");
            return;
        }

        int busId;
        try {
            busId = Integer.parseInt(busIdParameter);
        } catch (NumberFormatException exception) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The bus ID must be numeric.");
            return;
        }
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int seat = Integer.parseInt(request.getParameter("seat"));

        try {

            Connection con = DBConnection.getConnection();

            String check = "SELECT * FROM bookings WHERE bus_id=? AND seat_no=?";

            PreparedStatement ps1 = con.prepareStatement(check);

            ps1.setInt(1, busId);
            ps1.setInt(2, seat);

            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {

                response.getWriter().println(
                    "Seat already booked!"
                );

                return;
            }

            String sql =
                "INSERT INTO bookings(bus_id, passenger_name, passenger_email, seat_no) VALUES(?,?,?,?)";

            PreparedStatement ps2 = con.prepareStatement(sql);

            ps2.setInt(1, busId);
            ps2.setString(2, name);
            ps2.setString(3, email);
            ps2.setInt(4, seat);

            ps2.executeUpdate();

            con.close();

            response.getWriter().println(
                "Booking Successful!"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}