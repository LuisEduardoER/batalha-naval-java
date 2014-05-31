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
import java.util.Scanner;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;

public class JogoServer extends JFrame {

    JTextArea area;
    JScrollPane scroll;
    JButton btLimparLog;

    PrintWriter p;
    Scanner leitor;

    public class BtLimparLog implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        	//startServer();
        	area.setText("");
        }

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

        setSize(383, 366);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        
        ServerSocket server;
        try {
            server = new ServerSocket(6543);
            area.append("Iniciando Servidor .....\n");
            area.append("Servidor Iniciado com sucesso!\n");
            while (true) {
            	
            	area.append("Aguardando requisições...\n");
                Socket socket = server.accept();
                new Thread(new EscutaMicro(socket)).start();
                p = new PrintWriter(socket.getOutputStream());

            }
        } catch (Exception e) {

        }

        
    }

    public void retornaDadosCliente(String write) {
        p.println(write);
        p.flush();
    }

    public class EscutaMicro implements Runnable {

        String procurarPermisao;
        String codigoDaPessoa;
        String nomeDaPessoa;

        public EscutaMicro(Socket socket) {
            try {
                leitor = new Scanner(socket.getInputStream());

            } catch (IOException e) {
            }
        }

        @Override
        public void run() {

            String l = leitor.nextLine();
            
            area.append(l+"\n");
            
            String[] lsplit = l.split(";");
            procurarPermisao = lsplit[0];
            codigoDaPessoa = lsplit[1];

            switch (codigoDaPessoa) {
            case "001":
                nomeDaPessoa = "Edmilson";
                break;
            case "002":
                nomeDaPessoa = "Dayvson";
                break;
            case "003":
                nomeDaPessoa = "Anderson";
                break;
            case "004":
                nomeDaPessoa = "Wesley";
                break;
            case "005":
                nomeDaPessoa = "Nilo";
                break;
            case "006":
                nomeDaPessoa = "Neto";
                break;
            case "007":
                nomeDaPessoa = "Jeferson";
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
