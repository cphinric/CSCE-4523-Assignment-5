<!DOCTYPE HTML>
<html>
   <head>
      <link rel="stylesheet" href="style/main.css">
      <title>Add a Game</title>
   </head>
   <body>
      <div id="banner">
         <a href="index.html">Go Home</a>
         <h2>Database Project<br>Add a Game</h2>
      </div>
      <?php
         $teamID1 = $_POST["teamID1"];
         $teamID2 = $_POST["teamID2"];
         $score1 = $_POST["score1"];
         $score2 = $_POST["score2"];
         $date = $_POST["date"];

         $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar ' .
         'dbms addGame ' .
         escapeshellarg($teamID1) . ' ' .
         escapeshellarg($teamID2) . ' ' . 
         escapeshellarg($score1) . ' ' .
         escapeshellarg($score2) . ' ' . 
         escapeshellarg($date);
         
         system($command);
      ?>

   </body>
</html>

