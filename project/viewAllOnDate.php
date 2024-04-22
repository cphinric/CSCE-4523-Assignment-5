<!DOCTYPE HTML>
<html>
    <head>
        <link rel="stylesheet" href="style/main.css">
        <title>View games by date</title>
    </head>
    <body>
        <div id="banner">
            <a href="index.html">Go Home</a>
            <h1>Database Project: View all results on a given date</h1>
        </div>
        <?php
            $date = $_POST["date"];
            $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar dbms viewAllOnDate ' . escapeshellarg($date);
            system($command);
        ?>
    </body>
</html>