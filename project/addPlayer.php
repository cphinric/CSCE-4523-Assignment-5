<!DOCTYPE HTML>
<html>
   <head>
      <link rel="stylesheet" href="style/main.css">
      <title>Add a Player</title>
   </head>
   <body>
      <div id="banner">
         <a href="index.html">Go Home</a>
         <h2>Database Project<br>Add a Player</h2>
      </div>
      <?php
         $teamName = $_POST["teamName"];
         $playerName = $_POST["playerName"];
         $position = $_POST["position"];

         $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar dbms addPlayer ' . 
         ' ' . escapeshellarg($teamName) . ' ' . escapeshellarg($playerName) .
         ' ' . escapeshellarg($position);
         
         system($command);
      ?>

   </body>
</html>

