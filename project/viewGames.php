<!DOCTYPE HTML>
<html>
    <head>
        <link rel="stylesheet" href="style/main.css">
        <title>View Games</title>
    </head>
    <body>
        <div id="banner">
            <a href="index.html">Go Home</a>
            <h2>Database Project<br>View all games played by a given team</h2>
        </div>
        <?php
            $team = $_POST["team"];
            $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar ' .
            'dbms viewGames ' .
            escapeshellarg($team);
            system($command);
        ?>
    </body>
</html>