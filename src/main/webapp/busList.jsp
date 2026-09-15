<%@ page import="java.util.List" %>
    <%@ page import="model.Bus" %>

        <!DOCTYPE html>
        <html>

        <head>
            <title>Available Buses</title>

            <style>
                body {
                    font-family: Arial;
                    background: #f5f5f5;
                }
                
                table {
                    width: 90%;
                    margin: 40px auto;
                    border-collapse: collapse;
                    background: white;
                }
                
                th,
                td {
                    padding: 12px;
                    border: 1px solid #ddd;
                    text-align: center;
                }
                
                th {
                    background: #007bff;
                    color: white;
                }
                
                a {
                    background: green;
                    color: white;
                    padding: 8px;
                    text-decoration: none;
                }
            </style>
        </head>

        <body>

            <h1 style="text-align:center;">Available Buses</h1>

            <table>

                <tr>
                    <th>Bus Name</th>
                    <th>Source</th>
                    <th>Destination</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Price</th>
                    <th>Seats</th>
                    <th>Book</th>
                </tr>

                <%
    List<Bus> buses =
        (List<Bus>) request.getAttribute("buses");

    for (Bus bus : buses) {
%>

                    <tr>

                        <td>
                            <%= bus.getBusName() %>
                        </td>

                        <td>
                            <%= bus.getSource() %>
                        </td>

                        <td>
                            <%= bus.getDestination() %>
                        </td>

                        <td>
                            <%= bus.getDate() %>
                        </td>

                        <td>
                            <%= bus.getTime() %>
                        </td>

                        <td>₹
                            <%= bus.getPrice() %>
                        </td>

                        <td>
                            <%= bus.getTotalSeats() %>
                        </td>

                        <td>
                            <a href="booking.jsp?busId=<%= bus.getId() %>">
                Book
            </a>
                        </td>

                    </tr>

                    <%
    }
%>

            </table>

        </body>

        </html>