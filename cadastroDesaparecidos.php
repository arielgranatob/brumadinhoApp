<?php

include 'conexao.php';

$nomeDesaparecido = $_POST["nomeDesaparecido"];
$fotoDesaparecido = $_POST["fotoDesaparecido"];
$celularDesaparecido = $_POST["celularDesaparecido"];
$caractFisicas = $_POST["caractFisicas"];
$tatuagemDesaparecido = $_POST["tatuagemDesaparecido"];
$fotoTatuagem = $_POST["fotoTatuagem"];
$cicatrizDesaparecido = $_POST["cicatrizDesaparecido"];
$regiaoCicatriz = $_POST["regiaoCicatriz"];
$caractMarcante = $_POST["caractMarcante"];
$nomeDeclarante = $_POST["nomeDeclarante"];
$contatoDeclarante = $_POST["contatoDeclarante"];


$filename_path = md5(time().uniqid()).".jpg";
$decoded=base64_decode($fotoDesaparecido); 
file_put_contents("images/".$filename_path,$decoded); 
$actualpath1=$filename_path;


$filename_path = md5(time().uniqid()).".jpg";
$decoded=base64_decode($fotoTatuagem); 
file_put_contents("images/".$filename_path,$decoded); 
$actualpath2=$filename_path;


$sql = "INSERT INTO `tblpessoa`(`nomePessoa`, `fotoPessoa`, `celularPessoa`, `caracteristicasPessoa`, `tatuagemPessoa`, `cicatrizPessoa`, `fototatuagemPessoa`, `obscicatrizPessoa`, `caractmarcantesPessoa`, `nomedodeclarantePessoa`, `contatodeclarantePessoa`) 
VALUES
('".$nomeDesaparecido."','".$actualpath1."','".$celularDesaparecido."',
'".$caractFisicas."','".$tatuagemDesaparecido."','".$cicatrizDesaparecido."','".$actualpath2."',
'".$regiaoCicatriz."','".$caractMarcante."','".$nomeDeclarante."','".$contatoDeclarante."')" ; 

if ($con->query($sql) == TRUE) {
	echo "Cadastrado com sucesso!";
} else {
	echo "Erro: " . $sql . "<br>" . $conn->error;
}