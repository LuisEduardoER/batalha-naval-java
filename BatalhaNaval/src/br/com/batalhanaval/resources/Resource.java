package br.com.batalhanaval.resources;

public class Resource {
	
	private static Integer TAM_MATRIZ = 10;				// Tamanho do tabuleiro
	private static Integer CEL_PORTA_AVIOES = 5; 		// Distribuída em forma de T no tabuleiro
	private static Integer CEL_FRAGATA = 4; 			// Distribuida em linha ou coluna
	private static Integer CEL_TORPEDEIROS = 3; 		// Distribuida em linha ou coluna
	private static Integer CEL_CONTRA_TORPEDEIROS = 2;  // Distribuida em linha ou coluna
	private static Integer CEL_SUBMARINOS = 1; 			// Distribuida em linha ou coluna
	
	public static Integer getTAM_MATRIZ(){
		return TAM_MATRIZ;
	}
	
	public static Integer getCEL_PORTA_AVIOES() {
		return CEL_PORTA_AVIOES;
	}

	public static void setCEL_PORTA_AVIOES(Integer cEL_PORTA_AVIOES) {
		CEL_PORTA_AVIOES = cEL_PORTA_AVIOES;
	}

	public static Integer getCEL_FRAGATA() {
		return CEL_FRAGATA;
	}

	public static void setCEL_FRAGATA(Integer cEL_FRAGATA) {
		CEL_FRAGATA = cEL_FRAGATA;
	}

	public static Integer getCEL_TORPEDEIROS() {
		return CEL_TORPEDEIROS;
	}

	public static void setCEL_TORPEDEIROS(Integer cEL_TORPEDEIROS) {
		CEL_TORPEDEIROS = cEL_TORPEDEIROS;
	}

	public static Integer getCEL_CONTRA_TORPEDEIROS() {
		return CEL_CONTRA_TORPEDEIROS;
	}

	public static void setCEL_CONTRA_TORPEDEIROS(Integer cEL_CONTRA_TORPEDEIROS) {
		CEL_CONTRA_TORPEDEIROS = cEL_CONTRA_TORPEDEIROS;
	}

	public static Integer getCEL_SUBMARINOS() {
		return CEL_SUBMARINOS;
	}

	public static void setCEL_SUBMARINOS(Integer cEL_SUBMARINOS) {
		CEL_SUBMARINOS = cEL_SUBMARINOS;
	}

}
