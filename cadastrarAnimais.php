<?php
    include 'conexao.php';
    $tipoAnimal=$_POST["tipoAnimal"];
        $nomeContato=$_POST["nomeContato"];
        $telefoneContato=$_POST["telefoneContato"];
        $descricaoAnimal=$_POST["descricaoAnimal"];
        $fotoAnimal=$_POST["fotoAnimal"];

        $filename_path = md5(time().uniqid()).".jpg";
        $decoded=base64_decode($fotoAnimal); 
        file_put_contents("images/".$filename_path,$decoded); 
        $actualpath2=$filename_path;
        $sql = "INSERT INTO `tblanimal`(`fotoAnimal`, `tipoAnimal`, `nomeAnimal`, `obsAnimal`, `telefoneContatoAnimal`) VALUES 
        ('".$actualpath2."','".$tipoAnimal."','".$nomeContato."','".$descricaoAnimal."','".$telefoneContato."')";
   
   if ($con->query($sql) == TRUE) {
       echo "Cadastrado com Sucesso!";
   } else {
       echo "Error: " . $sql . "<br>" . $conn->error;
   }
    
    ?>