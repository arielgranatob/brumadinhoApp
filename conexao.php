<?php
  $host = "localhost";
          $db_name = "bd_brumadinhoApp";
          $username = "root";
          $password = "";
    $con = mysqli_connect($host, $username,$password,$db_name);

        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }
        ?>