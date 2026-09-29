<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Game Matchmaking System</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #1e1e2f;
            color: white;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 450px;
            margin: 80px auto;
            background-color: #2d2d44;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 0 15px rgba(0,0,0,0.5);
        }

        h1 {
            text-align: center;
            color: #00d4ff;
        }

        label {
            display: block;
            margin-top: 15px;
            margin-bottom: 5px;
        }

        input, select {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border-radius: 6px;
            border: none;
        }

        button {
            width: 100%;
            padding: 12px;
            margin-top: 25px;
            background-color: #00a8ff;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #0086cc;
        }

        .result {
            margin-top: 25px;
            padding: 15px;
            background-color: #3d3d5c;
            border-radius: 8px;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>🎮 Game Matchmaking</h1>

    <form method="post">

        <label>Player Name:</label>
        <input type="text" name="playerName" required>

        <label>Select Game:</label>
        <select name="game">
            <option value="Valorant">Valorant</option>
            <option value="CS2">Counter-Strike 2</option>
            <option value="PUBG">PUBG</option>
            <option value="Free Fire">Free Fire</option>
        </select>

        <label>Rank:</label>
        <select name="rank">
            <option value="Bronze">Bronze</option>
            <option value="Silver">Silver</option>
            <option value="Gold">Gold</option>
            <option value="Platinum">Platinum</option>
            <option value="Diamond">Diamond</option>
        </select>

        <label>Preferred Role:</label>
        <select name="role">
            <option value="Duelist">Duelist</option>
            <option value="Controller">Controller</option>
            <option value="Sentinel">Sentinel</option>
            <option value="Initiator">Initiator</option>
        </select>

        <button type="submit">Find Match</button>

    </form>

    <%
        String playerName = request.getParameter("playerName");

        if (playerName != null) {
            String game = request.getParameter("game");
            String rank = request.getParameter("rank");
            String role = request.getParameter("role");
    %>

        <div class="result">
            <h2>Match Found! </h2>

            <p><strong>Player:</strong> <%= playerName %></p>
            <p><strong>Game:</strong> <%= game %></p>
            <p><strong>Rank:</strong> <%= rank %></p>
            <p><strong>Role:</strong> <%= role %></p>

            <p>Searching for players with similar rank and preferences...</p>
            <p>✅ Matchmaking request created successfully!</p>
        </div>

    <%
        }
    %>

</div>

</body>
</html>