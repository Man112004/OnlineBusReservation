<!DOCTYPE html>
<html>

<head>
    <title>Book Bus</title>

    <style>
        body {
            font-family: Arial;
            background: #f2f2f2;
        }
        
        .box {
            width: 400px;
            margin: 80px auto;
            background: white;
            padding: 30px;
        }
        
        input {
            width: 95%;
            padding: 10px;
            margin: 10px 0;
        }
        
        button {
            width: 100%;
            padding: 12px;
            background: green;
            color: white;
            border: none;
        }
    </style>
</head>

<body>

    <div class="box">

        <h2>Book Your Ticket</h2>

        <form action="BookBusServlet" method="post">

            <input type="hidden" name="busId" value="${param.busId}">

            <input type="text" name="name" placeholder="Passenger Name" required>

            <input type="email" name="email" placeholder="Email" required>

            <input type="number" name="seat" placeholder="Seat Number" min="1" required>

            <button type="submit">
            Book Ticket
        </button>

        </form>

    </div>

</body>

</html>