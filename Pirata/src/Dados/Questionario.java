package Dados;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Config.Definicoes;
import IO.ImageLoader;
import IO.Window;

public class Questionario {
	
	final int MAX_PERGUNTAS = 20;
	final int MAX_TIPOS = 4;

	Pergunta pergunta[];
	
	int pontos[];
	
	String versao = "V1.1";
	
	Window perguntasWin, resultadosWin;
	
	JButton cancelar, irParaJogo, fechar;
	
	JPanel buttonPanel, buttonPanel2;
	
	Resultados resultados;
	
	Jogador jogador;
	Quadrante[][] quadrante;
	
	JLabel imageLabel;
		
	JButton entrar;
	
	Window explicacao;
	
	public Questionario(Resultados resultados)
	{
		this.resultados = resultados;
		
		initVariables();
		
		initPerguntas();
		
		mostraPerguntas();
	}
	

	
	public void initVariables()
	{
		pontos = new int[MAX_TIPOS];
		
		for(int i=0;i<MAX_TIPOS;i++)
			pontos[i]=0;
		
		resultados.tipoJogador = "...";
		
		resultados.certezaTipo = 0;
		resultados.percentagemConquistador = 0;
		resultados.percentagemExplorador = 0;
		resultados.percentagemSocializador = 0;
		resultados.percentagemAssassino = 0;
		resultados.percentagemIdentificacao = 0;
		

	}
	
	public void mostraPerguntas()
	{
		cancelar = new JButton("Cancelar");
		irParaJogo = new JButton("Ir ao Jogo");
		
		buttonPanel = new JPanel();
		
		buttonPanel.add(irParaJogo);
		buttonPanel.add(cancelar);
		
		cancelar.addActionListener(new CancelarListener());
		irParaJogo.addActionListener(new JogoListener());
		
		perguntasWin = new Window("V" + Definicoes.VERSAO,true);
		
		perguntasWin.getFrame().getRootPane().setDefaultButton(irParaJogo);
		irParaJogo.requestFocus();
		
		for(int i=0;i<MAX_PERGUNTAS;i++)
		{
			if(i%2==0)
				pergunta[i].setCor(Color.BLUE);
			else
				pergunta[i].setCor(Color.DARK_GRAY);
			
			perguntasWin.addCenter(pergunta[i].getTexto());
			perguntasWin.addCenter(pergunta[i].getIdentificacao());
			perguntasWin.cr();
		}
		
		perguntasWin.addLine(2);
		
		perguntasWin.addRangeCenter(buttonPanel, 0, 2);
		
		perguntasWin.pack();
		
		perguntasWin.centerPos();
		
		perguntasWin.show();
	}
	
	class CancelarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
			
		}
		
	}
	
	class JogoListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			perguntasWin.hide();
				
			computarResultados();
			
			explicacao = new Window("V" + Definicoes.VERSAO,true);
			
			entrar = new JButton("Iniciar o Jogo");
			
			explicacao.getFrame().getRootPane().setDefaultButton(entrar);
			entrar.requestFocus();
			
			buttonPanel = new JPanel();
			
			buttonPanel.add(entrar);
			
			imageLabel = new JLabel(ImageLoader.loadIcon(Definicoes.EXPLICACAO));
			
			explicacao.addCenterLn(imageLabel);
			
			explicacao.addCenterLn(buttonPanel);
			
			explicacao.pack();
			
			explicacao.centerPos();
			
			explicacao.show();
			
			entrar.addActionListener(new EntrarListener());
			

		}
		
	}
	
	class EntrarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			explicacao.hide();
			
			jogador = new Jogador(resultados);
			
			Mundo m = new Mundo();
				
			quadrante = new Quadrante[Definicoes.QUADRANTE_MAX_X][Definicoes.QUADRANTE_MAX_Y];
			
			quadrante[Definicoes.QUADRANTE_INICIAL_X][Definicoes.QUADRANTE_INICIAL_Y]=new Quadrante(Definicoes.QUADRANTE_INICIAL_X,Definicoes.QUADRANTE_INICIAL_Y,m,jogador,resultados);

		}
		
	}
	
	public void computarResultados()
	{
		for(int i=0;i<MAX_PERGUNTAS;i++)
		{
			pontos[pergunta[i].getClassificacao()]=pontos[pergunta[i].getClassificacao()]+pergunta[i].getIdentificacao().getSelectedIndex();
		}
		
		//for(int i=0;i<MAX_TIPOS;i++)
			//System.out.println(pontos[i]);
		
		int pontosTotais = 0;
		
		for(int i=0;i<MAX_TIPOS;i++)
			pontosTotais=pontosTotais+pontos[i];
		
		if(pontosTotais!=0)
		{
			resultados.percentagemConquistador = (pontos[Pergunta.CONQUISTADOR] * 100)/pontosTotais;
			resultados.percentagemExplorador = (pontos[Pergunta.EXPLORADOR] * 100)/pontosTotais;
			resultados.percentagemSocializador = (pontos[Pergunta.SOCIALIZADOR] * 100)/pontosTotais;
			resultados.percentagemAssassino = (pontos[Pergunta.ASSASSINO] * 100)/pontosTotais;
			
		}
		
		resultados.codigoTipo = -1; 
		
		int maiorPonto = -1;
		
		for(int i=0;i<MAX_TIPOS;i++)
		{
			if(pontos[i]>maiorPonto)
			{
				maiorPonto=pontos[i];
				resultados.codigoTipo=i;
			}
		}
		
		switch(resultados.codigoTipo)
		{
		case Pergunta.CONQUISTADOR : resultados.tipoJogador = "CONQUISTADOR"; break;
		case Pergunta.EXPLORADOR : resultados.tipoJogador = "EXPLORADOR"; break;
		case Pergunta.SOCIALIZADOR : resultados.tipoJogador = "SOCIALIZADOR"; break;
		case Pergunta.ASSASSINO : resultados.tipoJogador = "ASSASSINO"; break;
		}
		
		resultados.percentagemIdentificacao = pontosTotais * 100 / 80;
		
		resultados.certezaTipo = pontos[resultados.codigoTipo] * 100 / 20;
		
	}
	
	public void initPerguntas()
	{
		pergunta = new Pergunta[MAX_PERGUNTAS];
		
		for(int i=0;i<MAX_PERGUNTAS;i++)
		{
			pergunta[i] = new Pergunta();
		}
		
		pergunta[0].setTexto("Eu gosto de conquistar novos pr\u00eamios em jogos");
		pergunta[0].setClassificacao(Pergunta.CONQUISTADOR);
		
		pergunta[1].setTexto("Gosto de experi\u00eancias muito variadas em jogos e explorar lados novos");
		pergunta[1].setClassificacao(Pergunta.EXPLORADOR);
		
		pergunta[2].setTexto("Eu gosto de expor minhas conquistas (por exemplo, no Facebook)");
		pergunta[2].setClassificacao(Pergunta.CONQUISTADOR);
		
		pergunta[3].setTexto("Adoro jogos no estilo de World of Warcraft ou Dota");
		pergunta[3].setClassificacao(Pergunta.SOCIALIZADOR);
		
		pergunta[4].setTexto("Fico impressionado/a com jogadores que conquistaram altos pr\u00eamios");
		pergunta[4].setClassificacao(Pergunta.CONQUISTADOR);
		
		pergunta[5].setTexto("Gosto de jogos de explora\u00e7\u00e3o como Myst, Zelda ou Monkey Island");
		pergunta[5].setClassificacao(Pergunta.EXPLORADOR);
		
		pergunta[6].setTexto("Sou muito competitivo/a em jogos");
		pergunta[6].setClassificacao(Pergunta.ASSASSINO);
		
		pergunta[7].setTexto("Adoro saber que estou jogando contra outras pessoas reais");
		pergunta[7].setClassificacao(Pergunta.SOCIALIZADOR);
		
		pergunta[8].setTexto("Eu gosto de explodir coisas em jogos");
		pergunta[8].setClassificacao(Pergunta.ASSASSINO);
		
		pergunta[9].setTexto("Adoro novos itens e medalhas");
		pergunta[9].setClassificacao(Pergunta.CONQUISTADOR);
		
		pergunta[10].setTexto("Gosto de encontrar itens escondidos em jogos eletr\u00f4nicos");
		pergunta[10].setClassificacao(Pergunta.EXPLORADOR);
		
		pergunta[11].setTexto("Gosto de compartilhar minha experi\u00eancia de jogos com outros");
		pergunta[11].setClassificacao(Pergunta.SOCIALIZADOR);
		
		pergunta[12].setTexto("Meus jogos favoritos s\u00e3o de tiro em primeira pessoa.");
		pergunta[12].setClassificacao(Pergunta.ASSASSINO);
		
		pergunta[13].setTexto("Adoro descobrir novos bugs em jogos");
		pergunta[13].setClassificacao(Pergunta.EXPLORADOR);
		
		pergunta[14].setTexto("Gosto de jogos de mundo aberto e posso ficar horas explorando");
		pergunta[14].setClassificacao(Pergunta.EXPLORADOR);
		
		pergunta[15].setTexto("Jogo jogos eletr\u00f4nicos at\u00e9 o final conseguindo 100% das conquistas");
		pergunta[15].setClassificacao(Pergunta.CONQUISTADOR);
		
		pergunta[16].setTexto("Tenho v\u00e1rios amigos que conheci em jogos online");
		pergunta[16].setClassificacao(Pergunta.SOCIALIZADOR);
		
		pergunta[17].setTexto("Sou conhecido/a por minhas a\u00e7\u00f5es agressivas dentro de jogos");
		pergunta[17].setClassificacao(Pergunta.ASSASSINO);
		
		pergunta[18].setTexto("Adoro jogos multi jogador");
		pergunta[18].setClassificacao(Pergunta.SOCIALIZADOR);
		
		pergunta[19].setTexto("Eu n\u00e3o gosto de me comunicar em jogos, gosto mesmo de atirar");
		pergunta[19].setClassificacao(Pergunta.ASSASSINO);
	}
	

}
