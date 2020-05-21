package Dados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import IO.WinIO;
import IO.Window;

public class MailBox {

	Window mbWindow;
	WinIO winIO;
	
	JLabel escrevaAlgo, mensagemNaCaixa;
	JButton ok, cancelar;
	JTextArea escrevaArea, mensagemArea;
	JScrollPane escrevaScroll, mensagemScroll;
	JPanel buttonPanel;
	
	String path = "src\\Config\\Correio.txt", str;
	
	public final int yPane = 10, xPane = 50;
	
	Resultados resultados;
	
	public MailBox(Resultados resultados)
	{
		this.resultados=resultados;
		
		winIO = new WinIO();
		
		mbWindow = new Window("Caixa de correio",false);
		
		escrevaAlgo = new JLabel("Escreva arrrrrrgo:");
		mensagemNaCaixa = new JLabel("Mensagens na caixa:");
		
		escrevaArea = new JTextArea(yPane/2,xPane);
		mensagemArea = new JTextArea(yPane,xPane);
		
		escrevaScroll = new JScrollPane(escrevaArea);
		mensagemScroll = new JScrollPane(mensagemArea);
		
		ok = new JButton("OK");
		cancelar = new JButton("Cancelar");
		
		ok.addActionListener(new OkListener());
		cancelar.addActionListener(new CancelarListener());
		
		buttonPanel = new JPanel();
		
		buttonPanel.add(ok);
		buttonPanel.add(cancelar);
		
		mbWindow.addCenterLn(escrevaAlgo);
		mbWindow.addCenterLn(escrevaScroll);
		mbWindow.addCenterLn(buttonPanel);
		mbWindow.addLine(1);
		mbWindow.addCenterLn(mensagemNaCaixa);
		mbWindow.addCenterLn(mensagemScroll);
		
		mbWindow.standardPack();
		
		mbWindow.centerPos();
		
		FileReader fr = null;
		BufferedReader br;
		
		try {
			fr = new FileReader(path);
		} catch (FileNotFoundException e) {
			
			winIO.error("Impossivel abrir o arquivo " + path);
			
		}
		
		br = new BufferedReader(fr);
		
		try {
			while((str = br.readLine())!=null)
			{
				mensagemArea.append(str + "\n");
		
			}
			
		} catch (IOException e) {
			
			winIO.error("Impossivel ler arquivo. " + path);
		
		}
	
		// Fechamento do arquivo
	
		try {
			br.close();
		} catch (IOException e) {
		
			winIO.error("Arquivo nao pode ser fechado. " + path);
		
		}
		mbWindow.show();
	}
	
	class OkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			resultados.enviouMensagem=true;
			
			FileWriter fw = null;
			BufferedWriter bw;
			
			try {
				fw = new FileWriter(path,true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				winIO.error("Impossivel abrir o arquivo " + path);
				e.printStackTrace();
			}
			
			bw = new BufferedWriter(fw);
			
			try {
			
				bw.write("---------------------------------------------");
				bw.write(System.getProperty( "line.separator" ));
				
				bw.write(escrevaArea.getText());
				bw.write(System.getProperty( "line.separator" ));
				
			} catch (IOException e) {
				
				winIO.error("Impossivel ler arquivo. " + path);
			
			}
		
			// Fechamento do arquivo
		
			try {
				bw.close();
			} catch (IOException e) {
			
				winIO.error("Arquivo nao pode ser fechado. " + path);
			
			}
			
			mbWindow.hide();
			
		}
		
		
		
	}
	
	class CancelarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mbWindow.hide();
			
		}
	
	}
}
