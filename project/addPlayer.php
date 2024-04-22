<!DOCTYPE HTML>
<html>
   <head>
      <link rel="stylesheet" href="style/main.css">
      <title>Add a Game</title>
   </head>
   <body>
      <div id="banner">
         <a href="index.html">Go Home</a>
         <h1>Database Project: Add a Player</h1>
      </div>
      <?php
         $playerID = $_POST["playerID"];
         $teamID = $_POST["teamID"];
         $name = $_POST["name"];
         $position = $_POST["position"];

         $command = 'java dbms addPlayer ' . escapeshellarg($playerID) . 
         ' ' . escapeshellarg($teamID) . ' ' . escapeshellarg($name) .
         ' ' . escapeshellarg($position);
         
         system($command);
      ?>

   </body>
</html>

