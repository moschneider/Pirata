package Dados;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.TransferHandler;

import Config.Definicoes;
import IO.WinIO;
import IO.Window;

public class RelatorioFinal {

	Window resultadosWin;
	
	Resultados resultados;
	
	JButton finalizar, copiarImagem;
	
	JPanel buttonPanel;
	
	JTextArea descricao;
	
	JScrollPane scroller;
	
	WinIO winIO;
	
	public RelatorioFinal(Resultados resultados)
	{
		resultados.setRelatorioFinal(this);
		
		this.resultados = resultados;
		
		descricao = new JTextArea(5,50);
		
		descricao.setEditable(false);
		
		scroller = new JScrollPane(descricao);
		
		montarJanelaResultados();
		
		winIO = new WinIO();
		
		winIO.message("Aviso", "Caso queira publicar o resultado no Facebook, basta salvar a imagem para posterior publica\u00e7\u00e3o");
	}
	
	public void adicioneDescricao(int tipo)
	{
		switch(tipo)
		{
		case Pergunta.CONQUISTADOR:
			descricao.append("Conquistadores s\u00e3o movidos por objetivos no jogo para se destacar dos outros jogadores,\n"); 
			descricao.append("geralmente alguma forma de acumular pontos – sejam eles pontos de experi\u00eancia, \n");
			descricao.append("n\u00edveis ou mesmo cupons de desconto. Eles ser\u00e3o atra\u00eddos por um invent\u00e1rio de badges\n"); 
			descricao.append("ou trof\u00e9us, por exemplo.\n");
			break;
		case Pergunta.EXPLORADOR:
			descricao.append("Exploradores s\u00e3o conduzidos pela vontade de descobrir o m\u00e1ximo poss\u00edvel sobre o jogo, incluindo\n"); 
			descricao.append("desde o mapeamento da \u00e1rea geogr\u00e1fica at\u00e9 a compreens\u00e3o da mec\u00e2nica. Eles s\u00e3o curiosos \n");
			descricao.append("e v\u00e3o querer entender o porqu\u00ea e como cumprir um desafio proposto.\n");
			break;
		case Pergunta.SOCIALIZADOR:
			descricao.append("Socializadores est\u00e3o interessados em pessoas, e no que elas t\u00eam a dizer. O jogo \u00e9 apenas\n"); 
			descricao.append("um pano de fundo  para socializarem com outros jogadores. Os socializadores s\u00e3o os maiores\n");
			descricao.append("comentadores de status e os que motivam os desafios em time.\n");
			break;
		case Pergunta.ASSASSINO:
			descricao.append("Talvez a esp\u00e9cime mais competitiva. Os Assassinos s\u00e3o movidos pela vontade de impor-se e\n"); 
			descricao.append("ficam satisfeitos em proporcionar momentos de agonia e ansiedade nos outros jogadores. \n");
			descricao.append("Para que eles ganhem, algu\u00e9m precisa perder (e muitos “algu\u00e9ns”). S\u00e3o presen\u00e7as frequentes\n"); 
			descricao.append("no top do leaderboard.\n");
		}
	}
	
	public void montarJanelaResultados()
	{
		
		resultadosWin = new Window("V" + Definicoes.VERSAO,true);
		
		buttonPanel = new JPanel();
		
		adicioneDescricao(resultados.codigoTipo);
		
		finalizar = new JButton("Finalizar");
		copiarImagem = new JButton("Salvar como Imagem");
		
		finalizar.addActionListener(new FinalizarListener());
		copiarImagem.addActionListener(new CopiarListener());
		
		resultadosWin.addCenter(new JLabel("Tipo do Jogador"));
		resultadosWin.addCenterLn(new JLabel(resultados.tipoJogador));
		
		resultadosWin.addCenter(new JLabel("Certeza do Tipo"));
		resultadosWin.addCenterLn(new JLabel("" + resultados.certezaTipo + "%"));
		
		resultadosWin.addLine(2);
		
		resultadosWin.addRangeCenterLn(new JLabel("Descri\u00e7\u00e3o do Tipo"), 0, 2);
		
		resultadosWin.addRangeCenterLn(scroller, 0, 2);
		
		resultadosWin.addCenter(new JLabel("Fonte e maiores informa\u00e7\u00f5es"));
		resultadosWin.addCenterLn(new JLabel("http://opusphere.com/os-4-perfis-de-jogadores-segundo-richard-bartle/"));
		
		
		resultadosWin.addLine(2);
		
		resultadosWin.addRangeCenterLn(new JLabel("Valores por Tipo"), 0, 2);
		
		resultadosWin.addLine(2);
		
		resultadosWin.addCenter(new JLabel("Conquistador"));
		resultadosWin.addCenterLn(new JLabel("" + resultados.percentagemConquistador + "%"));
		
		resultadosWin.addCenter(new JLabel("Explorador"));
		resultadosWin.addCenterLn(new JLabel("" + resultados.percentagemExplorador + "%"));
		
		resultadosWin.addCenter(new JLabel("Socializador"));
		resultadosWin.addCenterLn(new JLabel("" + resultados.percentagemSocializador + "%"));
		
		resultadosWin.addCenter(new JLabel("Assassino"));
		resultadosWin.addCenterLn(new JLabel("" + resultados.percentagemAssassino + "%"));
		
		resultadosWin.addLine(2);
		
		resultadosWin.addCenter(new JLabel("Identifica\u00e7\u00e3o geral"));
		resultadosWin.addCenterLn(new JLabel("" + resultados.percentagemIdentificacao + "%"));
		 
		resultadosWin.addLine(2);
		
		resultadosWin.addRangeCenterLn(new JLabel("Desempenho no Jogo"), 0, 2);
		
		resultadosWin.addLine(2);
		
		resultadosWin.addCenter(new JLabel("Pontua\u00e7\u00e3o Final"));
		resultadosWin.addCenterLn(new JLabel("" + resultados.pontos));
		
		resultadosWin.addCenter(new JLabel("Quests Principais Conclu\u00eddos"));
		resultadosWin.addCenterLn(new JLabel("" + resultados.mainQuests + " / 3"));
		
		resultadosWin.addCenter(new JLabel("Quests Opcionais Conclu\u00eddos"));
		resultadosWin.addCenterLn(new JLabel("" + resultados.sideQuests + " / 4"));
		
		resultadosWin.addLine(2);
		
		buttonPanel = new JPanel();
		
		buttonPanel.add(copiarImagem);
		buttonPanel.add(finalizar);
		
		resultadosWin.addRangeCenterLn(buttonPanel, 0, 2);
		
		resultadosWin.standardPack();
		
		resultadosWin.centerPos();
		
		resultadosWin.show();
	}
	
	class FinalizarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			resultadosWin.hide();
			
			resultados.esrevaArquivo();
			
			// Janela de agradecimento
		}
		
	}
	
	class CopiarListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			JFileChooser fc = new JFileChooser("Salvar");
			
			int returnVal = fc.showSaveDialog(fc);
			
			if(returnVal==JFileChooser.APPROVE_OPTION)
			{
			
				resultados.copiouImagemNoClipboard=true;
				
				BufferedImage bufImage = new BufferedImage(resultadosWin.getFrame().getSize().width, resultadosWin.getFrame().getSize().height,BufferedImage.TYPE_INT_RGB);
				resultadosWin.getFrame().paint(bufImage.createGraphics());
					File imageFile = fc.getSelectedFile();
					try{
						imageFile.createNewFile();
						ImageIO.write(bufImage, "jpeg", imageFile);
					}catch(Exception ex){
		    	
						ex.printStackTrace();
					}
			}
		}
		
	}

}
