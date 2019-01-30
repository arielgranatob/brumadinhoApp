<?php

include 'conexao.php';

// getPost(<nome_do_campo>,<eh_obrigatorio>)
function getPost($campo,$obrigat) {
	$dado = filter_input ( 'INPUT_POST' , $campo, FILTER_SANITIZE_SPECIAL_CHARS);
	if(is_null($dado) && $obrigat === true ? $vazio[] = $campo : null );
	return $dado;
}

// getImage(<foto/imagem>)
function getImage($foto) {
	if(is_null($foto)) {
		return null;
	}
	else {
		$filename_path = md5(time().uniqid()).".jpg";
		$decoded=base64_decode($foto); 
		file_put_contents("images/".$filename_path,$decoded); 
		return $filename_path;
	}
}

$nomeDesaparecido = getPost("nomeDesaparecido",true);
$fotoDesaparecido = getPost("fotoDesaparecido",false);
$celularDesaparecido = getPost("celularDesaparecido",true);
$caractFisicas = getPost("caractFisicas",true);
$tatuagemDesaparecido = getPost("tatuagemDesaparecido",false);
$fotoTatuagem = getPost("fotoTatuagem",false);
$cicatrizDesaparecido = getPost("cicatrizDesaparecido",false);
$regiaoCicatriz = getPost("regiaoCicatriz",false);
$caractMarcante = getPost("caractMarcante",false);
$nomeDeclarante = getPost("nomeDeclarante",true);
$contatoDeclarante = getPost("contatoDeclarante",true);

$actualpath1=getImage($fotoDesaparecido);
$actualpath2=getImage($fotoTatuagem);


// stat com retorno 0 = erro ; retorno = 1 sucesso; 
if (!is_null($vazio)) {
	$erro = array('stat' => 0 , 'msg' => 'Campos obrigatórios vazios', 'fields' => $vazio );
	echo json_encode($erro);
}
else {
	$sql = "INSERT INTO `tblpessoa`(`nomePessoa`, `fotoPessoa`, `celularPessoa`, `caracteristicasPessoa`, `tatuagemPessoa`, `cicatrizPessoa`, `fototatuagemPessoa`, `obscicatrizPessoa`, `caractmarcantesPessoa`, `nomedodeclarantePessoa`, `contatodeclarantePessoa`) 
		VALUES
		('".$nomeDesaparecido."','".$actualpath1."','".$celularDesaparecido."',
		'".$caractFisicas."','".$tatuagemDesaparecido."','".$cicatrizDesaparecido."','".$actualpath2."',
		'".$regiaoCicatriz."','".$caractMarcante."','".$nomeDeclarante."','".$contatoDeclarante."')" ; 

		if ($con->query($sql) == TRUE) {
			$result = array('stat' => 1, 'msg' => 'Cadastro com sucesso!');
		} else {
			$result = array('stat' => 0, 'msg' => "Erro: " . $sql . " - " . $conn->error);
		}

		echo json_encode($result);
}
