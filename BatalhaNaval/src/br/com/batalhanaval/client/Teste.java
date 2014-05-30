package br.com.batalhanaval.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class Teste extends JFrame {

	private JPanel contentPane = new JPanel();
	private static Integer TAM_MATRIZ = 10; // Matriz de 10x10		
	MyLabel matrizJogador1[][];
	MyLabel matrizJogador2[][];

	JPanel tabuleiroJogador1 = new JPanel();
	JPanel tabuleiroJogador2 = new JPanel();
	
	public MyLabel[][] atualizaTabuleiro(JPanel tabuleiro){
		// cria nova matriz de 10x10		
		MyLabel matriz[][] = new MyLabel[TAM_MATRIZ][TAM_MATRIZ];
						 
		tabuleiro.removeAll(); // limpa da tela a matriz anterior				
		tabuleiro.setLayout(new GridLayout(TAM_MATRIZ, TAM_MATRIZ)); // configura a tela

		// monta a matriz e joga na tela
		for (int yy = 0; yy < TAM_MATRIZ; yy++) {
			for (int xx = 0; xx < TAM_MATRIZ; xx++) {			  
			  matriz[xx][yy] = new MyLabel(); //popula a matriz para mostrar na tela
					  
			  matriz[xx][yy].setBounds(20,20,90,90);  
					  
			  // Seta a imagem de fundo
			  matriz[xx][yy].setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/batalhanaval/resources/agua.jpg")));
					  
			  tabuleiro.add(matriz[xx][yy]); // adiciona na tela
			}
		}
		
		return matriz;
	}
	
	public void reiniciarPartida(){
		
		tabuleiroJogador1.removeAll();
		tabuleiroJogador2.removeAll();
		
		matrizJogador1 = atualizaTabuleiro(tabuleiroJogador1);
		matrizJogador2 = atualizaTabuleiro(tabuleiroJogador2);
		
		contentPane.add(tabuleiroJogador1);
		contentPane.add(tabuleiroJogador2);

		
	}
	
	public int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teste frame = new Teste();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Teste() {
		setTitle("Batalha Naval");

		reiniciarPartida();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 432);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		tabuleiroJogador1.setBounds(10, 30, 280, 191);
		contentPane.add(tabuleiroJogador1);
		
		
		tabuleiroJogador2.setBounds(313, 30, 280, 191);
		contentPane.add(tabuleiroJogador2);
		
		JLabel lblTabuleiroOponente = new JLabel("Seu Tabuleiro");
		lblTabuleiroOponente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTabuleiroOponente.setBounds(312, 11, 150, 14);
		contentPane.add(lblTabuleiroOponente);
		
		JLabel label = new JLabel("Tabuleiro Oponente");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 11, 150, 14);
		contentPane.add(label);

		JLabel lblNewLabel = new JLabel("Porta Avi\u00F5es:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 232, 80, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Fragata:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 247, 72, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel label_2 = new JLabel("Porta Avi\u00F5es:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(313, 232, 80, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Fragata:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(313, 247, 80, 14);
		contentPane.add(label_3);
		
		
		
		JButton btnNewButton = new JButton("Atirar!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 // Seta a imagem de fundo
				int vl1 = randInt(0, 9);
				int vl2 = randInt(0, 9);
				  matrizJogador1[vl1][vl2].setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/batalhanaval/resources/explosao3.jpg")));
					
			}
		});
		btnNewButton.setBounds(23, 316, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reiniciar Partida");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//reiniciarPartida();
			}
		});
		btnNewButton_1.setBounds(150, 316, 109, 23);
		contentPane.add(btnNewButton_1);
	}
}
