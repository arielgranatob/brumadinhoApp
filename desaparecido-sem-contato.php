<?php

include "conexao.php";

$nomeDesaparecido = strtoupper($_GET['nome']);

$sql = "SELECT * FROM `tblpessoa` WHERE `nomePessoa`='".$nomeDesaparecido."'";
$result = $con->query($sql);

$site = file_get_contents("http://brumadinho.vale.com/listagem-pessoas-sem-contato.html");
$tags = strtoupper(htmlentities($site));
$pattern = '/'.$nomeDesaparecido.'/';
if (preg_match($pattern, $tags) || $result->num_rows != 0) {
	echo json_encode(array('find' => true));
} else {
	echo json_encode(array('find' => false));		
}