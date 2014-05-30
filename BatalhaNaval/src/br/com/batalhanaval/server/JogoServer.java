package br.com.batalhanaval.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class JogoServer {
	
	private String tabuleiroJogador1[][];
	private String tabuleiroJogador2[][];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			// Instancia o servidor
			ServerSocket server = new ServerSocket(6543);
			
			do {
				
				// Aguarda conexao
				Socket s = server.accept();
				
				// Busca streams de E/S
				BufferedReader entrada = new BufferedReader(new InputStreamReader(
						s.getInputStream()));
				PrintWriter saida = new PrintWriter(s.getOutputStream());
				
				// Imprime requisicao
				String linha;
				linha = entrada.readLine();
				System.out.println(linha);
				
				// Envia dados atraves do Stream
				saida.println("PEPO");
				saida.flush();
				
				// Encerra recursos
				entrada.close();
				saida.close();
				s.close();
			} while(true);
		} catch (UnknownHostException ex) {
			System.out.println("Host desconhecido");
		} catch (IOException ex) {
			System.out.println("Erro na conexao: " + ex.getMessage());
			ex.printStackTrace();
		}

	}
	
	private Boolean iniciaJogo(){
		
		return true;
	}

}
