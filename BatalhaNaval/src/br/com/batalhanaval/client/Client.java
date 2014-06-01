package br.com.batalhanaval.client;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import br.com.batalhanaval.resources.Resource;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Client extends JFrame {

	private JPanel contentPane = new JPanel();
	private static Integer TAM_MATRIZ = Resource.getTAM_MATRIZ(); // Matriz de 10x10		
	MyLabel matrizJogador1[][];
	MyLabel matrizJogador2[][];

	JPanel tabuleiroJogador1 = new JPanel();
	JPanel tabuleiroJogador2 = new JPanel();
	
	Socket s;
	BufferedReader entrada;
	PrintWriter saida;
	
	JLabel qtdTiro = new JLabel("0");
	
	// Conecta no servidor via socket
	private Boolean iniciarConexao(){
		// Conecta no servidor
		try {
			
			s = new Socket("localhost", 6543);
			entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
			saida = new PrintWriter(s.getOutputStream());
			
			return true;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}

	private Integer enviaDados(String dados){
		
		iniciarConexao();
		// Verifica se está conectado ao servidor
		//if (!s.isConnected()) return false;
		
		try {
			
			// Envia dados atraves do Stream
			saida.println(dados);
			saida.flush();
			
			// Recebe o retorno do servidor
			return retornaDados();

			
			//return true;
			
		} catch (Exception e) {
			//return false;
		}
		return null;
		
	}
	
	private Integer retornaDados(){
		// Imprime retorno
		String linha = null;
		try {
			linha = entrada.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(linha);
		
		return Integer.parseInt(linha);
	}
	
	public MyLabel[][] atualizaTabuleiro(JPanel tabuleiro){
		// cria nova matriz de 10x10		
		MyLabel matriz[][] = new MyLabel[TAM_MATRIZ][TAM_MATRIZ];
						 
		tabuleiro.removeAll(); // limpa da tela a matriz anterior				
		tabuleiro.setLayout(new GridLayout(TAM_MATRIZ, TAM_MATRIZ)); // configura a tela

		// monta a matriz e joga na tela
		for (int linha = 0; linha < TAM_MATRIZ; linha++) {
			for (int coluna = 0; coluna < TAM_MATRIZ; coluna++) {			  
			  matriz[coluna][linha] = new MyLabel(); //popula a matriz para mostrar na tela
					  
			  matriz[coluna][linha].setBounds(20,20,90,90);  
					  
			  // Seta a imagem de fundo
			  matriz[coluna][linha].setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/batalhanaval/resources/agua.jpg")));
			  
			  // Muda o cursor
			  matriz[coluna][linha].setCursor(new Cursor(Cursor.HAND_CURSOR));  
			  
			  //Adiciona a posição ao componente
			  matriz[coluna][linha].setcoluna(coluna);
			  matriz[coluna][linha].setlinha(linha);
			  
			  //Adiciona o evento de clique
			  matriz[coluna][linha].addMouseListener(new MouseAdapter() {
				    
			        public void mouseClicked(MouseEvent e) {
			          
			        	MyLabel matriz = new MyLabel();
			            matriz	= (MyLabel)e.getSource();
			            
			            //Envia para o servidor a posição do tiro
			            // Seta a imagem de fundo
						int linha = matriz.getlinha();
						int coluna = matriz.getcoluna();
						
						int tiro = Integer.parseInt(qtdTiro.getText()) + 1;
						qtdTiro.setText(Integer.toString(tiro)); 
						
						if(enviaDados("TIRO;"+linha+";"+coluna) == 0){
							matrizJogador1[coluna][linha].setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/batalhanaval/resources/errou.jpg")));
							
						}
						else {
							matrizJogador1[coluna][linha].setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/batalhanaval/resources/explosao3.jpg")));
						}
			        
			          System.out.println("ATIRANDO NA POSICAO LINHA:"+ linha+" - COLUNA:"+coluna);
			        }
			    });
					  
			  tabuleiro.add(matriz[coluna][linha]); // adiciona na tela
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

		contentPane.validate();
		contentPane.repaint();
		
		//Zera as estatísticas
		qtdTiro.setText("0"); 
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
					Client frame = new Client();
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
	public Client() {
		setTitle("Batalha Naval");

		reiniciarPartida();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 510);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		tabuleiroJogador1.setBounds(10, 30, 300, 300);
		contentPane.add(tabuleiroJogador1);
		
		
		tabuleiroJogador2.setBounds(320, 30, 300, 300);
		contentPane.add(tabuleiroJogador2);
		
		JLabel lblTabuleiroOponente = new JLabel("Seu Tabuleiro");
		lblTabuleiroOponente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTabuleiroOponente.setBounds(321, 11, 150, 14);
		contentPane.add(lblTabuleiroOponente);
		
		JLabel label = new JLabel("Tabuleiro Oponente");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 11, 150, 14);
		contentPane.add(label);

		JLabel lblNewLabel = new JLabel("Estat\u00EDsticas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 330, 80, 14);
		contentPane.add(lblNewLabel);
		
		JLabel label_2 = new JLabel("Porta Avi\u00F5es:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(343, 352, 80, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Fragata:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(343, 367, 80, 14);
		contentPane.add(label_3);
		
		JButton btnNewButton_1 = new JButton("Reiniciar Partida");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//reiniciarPartida();
				
				enviaDados("INICIAR;0;0");//Inicia a partida no servidor
				reiniciarPartida();
				
			}
		});
		btnNewButton_1.setBounds(0, 439, 137, 33);
		contentPane.add(btnNewButton_1);
		
		JLabel lblEstatsticas = new JLabel("Estat\u00EDsticas:");
		lblEstatsticas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstatsticas.setBounds(320, 330, 80, 14);
		contentPane.add(lblEstatsticas);
		
		JLabel label_1 = new JLabel("Fragata:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(38, 367, 80, 14);
		contentPane.add(label_1);
		
		JLabel lblQtdeTiros = new JLabel("Qtde Tiros:");
		lblQtdeTiros.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQtdeTiros.setBounds(38, 352, 80, 14);
		contentPane.add(lblQtdeTiros);
		
		
		qtdTiro.setFont(new Font("Tahoma", Font.BOLD, 12));
		qtdTiro.setBounds(128, 352, 36, 14);
		contentPane.add(qtdTiro);
	}
}
