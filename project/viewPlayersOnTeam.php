<!DOCTYPE HTML>
<html>
    <head>
        <link rel="stylesheet" href="style/main.css">
        <title>View Players on Team</title>
    </head>
    <body>
        <div id="banner">
            <a href="index.html">Go Home</a>
            <h1>Database Project: View all players on a given team</h1>
        </div>
        <?php
            $team = $_POST["team"];
            $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar dbms viewPlayersOnTeam ' . escapeshellarg($team);
            system($command);
        ?>
    </body>
</html>