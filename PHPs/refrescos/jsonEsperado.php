<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrInstalacionEsperado = array();

$arrEsperado["peticion"] = "add";

$arrInstalacionEsperado["valor"] = "5 (Un int)";
$arrInstalacionEsperado["cantidad"] = "474 (Un int)";




$arrEsperado["depositoAdd"] = $arrInstalacionEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoAnnadir($recibido){
	
	$auxCorrecto = false;
	
	if(isset($recibido["peticion"]) && $recibido["peticion"] ="add" && isset($recibido["depositoAdd"])){
		
		$auxInstalacion = $recibido["depositoAdd"];
		if(isset($auxInstalacion["valor"]) && isset($auxInstalacion["cantidad"]) ){
			$auxCorrecto = true;
		}
		
	}
	
	
	return $auxCorrecto;
	
}
