package br.com.batalhanaval.client;

import javax.swing.JLabel;

/*
 * Essa classe foi extendida de Jlabel para montar o ambiente
 * os atributos coluna e linha indicam a posi��o do componente
 * na matriz.
 * Serve para que ao clicar seja poss�vel pegar a posi��o e enviar
 * para o servidor
 * */

public class MyLabel extends JLabel {

	private Integer coluna;
	private Integer linha;
	
	public Integer getcoluna() {
		return coluna;
	}
	public void setcoluna(Integer coluna) {
		this.coluna = coluna;
	}
	public Integer getlinha() {
		return linha;
	}
	public void setlinha(Integer linha) {
		this.linha = linha;
	}
	

	
	
	
	
}
