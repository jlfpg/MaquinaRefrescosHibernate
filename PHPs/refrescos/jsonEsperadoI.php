<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrInstalacionEsperado = array();

$arrEsperado["peticion"] = "add";

$arrInstalacionEsperado["clave"] = "5 (Un int)";
$arrInstalacionEsperado["cantidad"] = "474 (Un int)";




$arrEsperado["dispensadorAdd"] = $arrInstalacionEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoAnnadir($recibido){
	
	$auxCorrecto = false;
	
	if(isset($recibido["peticion"]) && $recibido["peticion"] ="add" && isset($recibido["dispensadorAdd"])){
		
		$auxInstalacion = $recibido["dispensadorAdd"];
		if(isset($auxInstalacion["clave"]) && isset($auxInstalacion["cantidad"]) ){
			$auxCorrecto = true;
		}
		
	}
	
	
	return $auxCorrecto;
	
}
