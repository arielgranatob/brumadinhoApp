<?php
  $host = "arielgranato.com.br";
          $db_name = "arielgranato_brumadinhoApp";
          $username = "arielgranato_userbrumadinhoApp";
          $password = "vYp]e6uZzrjP";
    $con = mysqli_connect($host, $username,$password,$db_name);

        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }
        ?>