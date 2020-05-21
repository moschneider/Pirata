package Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Config.Definicoes;
import Dados.Jogador;
import Dados.Quadrante;
import Dados.Questionario;
import Dados.Resultados;
import IO.ImageLoader;
import IO.Window;

public class Principal {
	
	// Sound effects - http://www.flashkit.com/
	// Clipart - http://www.clipartpanda.com/
	
	//Campo campo;
	Jogador jogador;
	Quadrante[][] quadrante;
	
	JLabel imageLabel;
	JPanel buttonPanel;
	
	JButton entrar;
	
	Window splash;
	
	Resultados resultados;
	
	/**
	 * Rotina principal com campo e jogador
	 */
	
	public Principal()
	{
		
		resultados = new Resultados();
		
		splash = new Window("V" + Definicoes.VERSAO,true);
		
		entrar = new JButton("Entrar");
				
		buttonPanel = new JPanel();
		
		buttonPanel.add(entrar);
		
		entrar.setFocusable(true);
		entrar.requestFocus();
		
		splash.getFrame().getRootPane().setDefaultButton(entrar);
		entrar.requestFocus();
		
		imageLabel = new JLabel(ImageLoader.loadIcon(Definicoes.PIRATA_SPLASH));
		
		splash.addCenterLn(imageLabel);
		
		splash.addCenterLn(buttonPanel);
		
		splash.pack();
		
		splash.centerPos();
		
		splash.show();
		
		entrar.addActionListener(new EntrarListener());
		
	}
	
	class EntrarListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			
			splash.hide();
			
			new Questionario(resultados);
			
			/*jogador = new Jogador();
			
			Mundo m = new Mundo();
				
			quadrante = new Quadrante[Definicoes.QUADRANTE_MAX_X][Definicoes.QUADRANTE_MAX_Y];
			
			quadrante[Definicoes.QUADRANTE_INICIAL_X][Definicoes.QUADRANTE_INICIAL_Y]=new Quadrante(Definicoes.QUADRANTE_INICIAL_X,Definicoes.QUADRANTE_INICIAL_Y,m,jogador);*/

		}
		
	}

	public static void main(String[] args) {

		new Principal();
	}

}
