package br.com.batalhanaval.server;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.batalhanaval.client.Jogador;
import br.com.batalhanaval.resources.Resource;

import java.awt.Color;

public class JogoServer extends JFrame {

    private JTextArea area;
    private JScrollPane scroll;
    private JButton btLimparLog;
    private PrintWriter p;
    private Scanner leitor;
    
    private static Integer TAM_MATRIZ = Resource.getTAM_MATRIZ(); // Matriz de 10x10	
    private Integer matrizJogador1[][];
    private Integer matrizJogador2[][];
    
    // Informações de cada jogador
    private Jogador jogador1;
    private Jogador jogador2;

    // Listener Botões
    public class BtLimparLog implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        	//startServer();
        	area.setText("");
        }

    }
    
    // Gera os tabuleiros
    public void iniciarPartida(){
    	
    	insereLog("Iniciando partida no servidor...");
    	matrizJogador1 = getMatriz();
    	matrizJogador2 = getMatriz();
    	
    	jogador1 = new Jogador();
    	jogador2 = new Jogador();
    	
    	insereLog("Tabuleiro Gerado!");
    	insereLog("Pronto para iniciar a partida!");
    }
    
    // Gera o tabuleiro
    // Ao gerar o tabuleiro é setado ZERO em todas as celulas
    public Integer[][] getMatriz(){
    	
    	// cria nova matriz de 10x10            
        Integer matriz[][] = new Integer[TAM_MATRIZ][TAM_MATRIZ];

        // Monta o tabuleiro do jogador
        for (int yy = 0; yy < TAM_MATRIZ; yy++)
                for (int xx = 0; xx < TAM_MATRIZ; xx++) {                         
                        matriz[xx][yy] = new Integer(0);
                        
                        //insereLog(Integer.toString(matriz[xx][yy]));
                        
                }
        
        return matriz;
    	
    }

    //Distribui os barcos no tabuleiro
    public void distribuirEmbarcacoes(Integer tabuleiro[][]){
    	// Na matriz caso a celula esteja com ZERO está vazia.
    	
    	// PORTA AVIÕES 	  = 1
    	// FRAGATA			  = 2
    	// TORPEDEIROS		  = 3
    	// CONTRA-TORPEDEIROS = 4
    	// SUBMARIO 		  = 5
    	
    	distribuiSubmarinos(tabuleiro);
    	
    	
    }
    
    // Distribui os submarinos o tabuleiro
    private Boolean distribuiSubmarinos(Integer tabuleiro[][]){
    	
    	// 4 submarinos ocupando uma célula
    	int qtdSubmarinos = 4;
    	
    	do {
			
    		int X = randInt(0, 9);
    		int Y = randInt(0, 9);
    		
    		if(tabuleiro[X][Y] == 0){
    			tabuleiro[X][Y] = 5;
    			qtdSubmarinos --;
    		}
    		
		} while (qtdSubmarinos > 0);
    	
    	insereLog("Submarinos Posicionados");
		
    	return true;
    }
    
	public int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
    
    public JogoServer() {

        super("Servidor: Controle do acesso");
        setTitle("Servidor");
        Font font = new Font("Serif", Font.PLAIN, 18);
        area = new JTextArea();
        area.setBackground(Color.BLACK);
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.BOLD, 14));
        area.setForeground(Color.GREEN);
        scroll = new JScrollPane(area);
        btLimparLog = new JButton("Limpar Log");
        btLimparLog.addActionListener(new BtLimparLog());
        btLimparLog.setFont(font);


        Container principal = getContentPane();
        principal.setLayout(new BorderLayout());

        Container p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p1.add(BorderLayout.EAST, btLimparLog);

        principal.add(BorderLayout.SOUTH, p1);
        principal.add(BorderLayout.CENTER, scroll);

        setSize(462, 366);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        
        ServerSocket server;
        try {
            server = new ServerSocket(6543);
            
            insereLog("Iniciando Servidor....");
            insereLog("Servidor iniciado com sucesso!");
            
            while (true) {
            	
            	insereLog("Aguardando requisições...");
            	
                Socket socket = server.accept();
                new Thread(new EscutaMicro(socket)).start();
                p = new PrintWriter(socket.getOutputStream());

            }
        } catch (Exception e) {

        }

        
    }
    
    // Insere o texto no log
    private void insereLog(String log){
    	area.append(log+"\n");
    }

    // Envia dados para o cliente
    public void retornaDadosCliente(String write) {
        p.println(write);
        p.flush();
    }
    
    private Integer atirar(String posicao){
    	
    	String[] lsplit = posicao.split(";");
        int linha = Integer.parseInt(lsplit[0]);
        int coluna = Integer.parseInt(lsplit[1]);
        
        if(matrizJogador2[coluna][linha] == 1) return 1;
        else if(matrizJogador2[coluna][linha] == 2) return 2;
        else if(matrizJogador2[coluna][linha] == 3) return 3;
        else if(matrizJogador2[coluna][linha] == 4) return 4;
        else if(matrizJogador2[coluna][linha] == 5) return 5; // Acertou submarino
        
        else return 0; // Nesse caso atirou na água
    }

    public class EscutaMicro implements Runnable {

    	//O Servidor recebe 3 parâmetros separados por ponto e vírgula
    	//Ex.: INICIAR;0;0 - TIRO;4;5
        String param1;
        String param2;
        String param3;

        public EscutaMicro(Socket socket) {
            try {
                leitor = new Scanner(socket.getInputStream());

            } catch (IOException e) {
            }
        }

        @Override
        public void run() {

            String l = leitor.nextLine();
            
            insereLog(l);
            
            String[] lsplit = l.split(";");
            param1 = lsplit[0]; // comando
            param2 = lsplit[1];
            param3 = lsplit[2];
            
            //insereLog("Atirou na posição: X:"+procurarPermisao+" - Y:"+codigoDaPessoa);

            switch (param1) {
            
            case "INICIAR":
            	iniciarPartida();
            	distribuirEmbarcacoes(matrizJogador1);
            	distribuirEmbarcacoes(matrizJogador2);
            	
            	retornaDadosCliente("10");
            	
                break;
                
            case "TIRO":
            	
                int resposta = atirar(lsplit[1]+";"+lsplit[2]);
                retornaDadosCliente(Integer.toString(resposta));
                
                break;
            
            default:
                break;
            }

            
            // -------------------------
        }
    }

    public static void main(String[] args) {
        new JogoServer();

    }

}
