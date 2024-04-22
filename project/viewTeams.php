<!DOCTYPE HTML>
<html>
    <head>
        <link rel="stylesheet" href="style/main.css">
        <title>View Teams</title>
    </head>
    <body>
        <div id="banner">
            <a href="index.html">Go Home</a>
            <h1>Database Project: View Teams</h1>
        </div>
        <!--
            view all teams arranged by conference (sorted alphabetically)
            within each conference, sort by number of wins overall,
            and then number of wins within the same conference
        -->
        <?php
            $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar dbms viewTeams';
            system($command);
        ?>
    </body>
</html>