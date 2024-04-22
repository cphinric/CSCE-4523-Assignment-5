<!DOCTYPE HTML>
<html>
    <head>
        <link rel="stylesheet" href="style/main.css">
        <title>View players on team by position</title>
    </head>
    <body>
        <div id="banner">
            <a href="index.html">Go Home</a>
            <h1>Database Project: View all players in a given position on any team</h1>
        </div>
        <?php
            $team = $_POST["team"];
            $position = $_POST["position"];
            $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar ' .
            'dbms viewPlayersOnTeamByPosition ' .
            escapeshellarg($team) . ' ' . 
            escapeshellarg($position);
            system($command);
        ?>
    </body>
</html>