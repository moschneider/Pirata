package Dados;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Config.Definicoes;

import javax.swing.JButton;

import javazoom.jl.player.JLayer.MP3Musica;
import IO.ImageLoader;
import IO.Panel;
import IO.WinIO;
import IO.Window;


public class Quadrante {

	Window winQuad;
	Campo campo;
	Jogador player;
	int x, y;
	
	ListaNpc listaNpc;
	
	Panel buttonPanel;

	JButton up, down, right, left, shot;

	JLabel pontosText, objetivoText;
	
	JPanel vidasPanel, tirosPanel;
	
	int locX, locY;
	
	File applauseFile, splashFile, ataqueFile, coletarFile, colisaoFile, explosaoFile, golpeFile, informacaoFile, inicioFile, morteFile, recargaFile, tiroCurtoFile, tiroLongoFile, caindoFile, nooFile, oceanoFile, risadaFile, cashFile, owFile;
	
	Mundo m;
	
	boolean noMar = false;
	
	MP3Musica musica;
	
	Font sidePanelFont;
	
	WinIO winIO;
	
	Resultados resultados;

	/**
	 * Administracao do quadrante definido por "x" e "y"
	 * @param x
	 * @param y
	 */

	public Quadrante(int x, int y, Mundo m, Jogador player, Resultados resultados)
	{
		this.x=x;
		this.y=y;
		this.player = player;
		this.m=m;
		
		this.resultados = resultados;
		
		//listaNpc = new ListaNpc(elemento);
		
		carregaSons();
		
		musica = new MP3Musica();
		
		sidePanelFont = new Font("SansSerif", Font.BOLD, 30);
		
		criarQuadrante();
		
		winIO = new WinIO();
		
		winIO.message("Informa\u00e7\u00e3o", "Preste aten\u00e7\u00e3o nos objetivos no lado direito da tela.\nVeja que, por hora, vc deve fazer " + Definicoes.OBJETIVO_PONTOS_NECESSARIOS + " pontos!");
		
		winIO.message("Informa\u00e7\u00e3o", "Voc\u00ea pode utilizar o mouse para jogar, mas recomendamos as teclas de cursor (para movimentar) e a tecla [Ctrl] (para atirar)!");
		
		winIO.message("Informa\u00e7\u00e3o", "\u00c9 isso! BOA SORTE! :-)");
		
		resultados.inicioDeJogo = new Timestamp(System.currentTimeMillis());
	}
	
	public void carregaSons()
	{
		ataqueFile = new File(Definicoes.ATAQUE_MP3);
		coletarFile = new File(Definicoes.COLETAR_MP3);
		colisaoFile = new File(Definicoes.COLISAO_MP3);
		golpeFile = new File(Definicoes.GOLPE_MP3);
		informacaoFile = new File(Definicoes.INFORMACAO_MP3);
		inicioFile = new File(Definicoes.INICIO_MP3);
		morteFile = new File(Definicoes.MORTE_MP3);
		recargaFile = new File(Definicoes.RECARGA_MP3);
		tiroCurtoFile = new File(Definicoes.TIRO_CURTO_MP3);
		tiroLongoFile = new File(Definicoes.TIRO_LONGO_MP3);
		caindoFile = new File(Definicoes.CAINDO_MP3);
		nooFile = new File(Definicoes.NOO_MP3);
		oceanoFile = new File(Definicoes.OCEANO_MP3);
		risadaFile = new File(Definicoes.RISADA_MP3);
		cashFile = new File(Definicoes.CASH_MP3);
		owFile = new File(Definicoes.OW_MP3);
		splashFile = new File(Definicoes.SPLASH_MP3);
		applauseFile = new File(Definicoes.APPLAUSE_MP3);
	}
	
	public void criarQuadrante()
	{
		campo = new Campo(m);
		
		listaNpc = new ListaNpc(campo,player,resultados,this);
		
		player.setCampo(campo);
		player.setListaNpc(listaNpc);
		player.setQuadrante(this);
		
		winQuad = new Window("V" + Definicoes.VERSAO,true);
		
		player.setJanela(winQuad);
		
		//define o background e elementos
		for(int i=0;i<Definicoes.VIEW_MAX_X;i++)
			for(int j=0;j<Definicoes.VIEW_MAX_Y;j++)
			{
				locX = x*10+i-1;
				locY = y*10+j-1;

				campo.create(i, j, Definicoes.OCEANO, false);
				
				if(locX>=0 && locX<=Definicoes.MUNDO_MAX_X-1 && locY>=0 && locY<=Definicoes.MUNDO_MAX_Y-1)
					
				{
					if(i==0 || i ==Definicoes.VIEW_MAX_X-1 || j==0 || j==Definicoes.VIEW_MAX_Y-1)

						campo.setValor(i, j, m.e[locX][locY].valor,true); else
					
							campo.setValor(i, j, m.e[locX][locY].valor,false);
					
					if(i==player.locX && j==player.locY)
						campo.setValor(i, i, Definicoes.PIRATA_FRENTE,false);
					
					campo.updatePic(i, j);
					
					/*if(campo.getValor(i, j)==Definicoes.PLANTA || campo.getValor(i, j)==Definicoes.ESQUELETO)
					{
						listaNpc.adicionar(i, j, campo.getValor(i, j));
					}*/

					/*if(i==0 || i ==Definicoes.VIEW_MAX_X-1 || j==0 || j==Definicoes.VIEW_MAX_Y-1)

						elemento[i][j].setBackground(Definicoes.MARGEM);
					
					else
						
						elemento[i][j].setBackground(Definicoes.CENTRO);*/

				}
			}

			initQuadrante();

	}
	
	public void setVidasPanel(int vida)
	{
		
		JLabel[] vidaLabel = new JLabel[vida]; 
	
		for(int i=0;i<vida;i++)
		{
			vidaLabel[i]=new JLabel(ImageLoader.loadIcon(Definicoes.VIDA_IMG));
			vidasPanel.add(vidaLabel[i]);
		}
	}
	
	public void removeLife(int vida)
	{
		vidasPanel.remove(vida);
		
		vidasPanel.updateUI();
	}
	
	public void setTirosPanel(int tiros)
	{
		
		JLabel[] tiroLabel = new JLabel[tiros]; 
	
		for(int i=0;i<tiros;i++)
		{
			tiroLabel[i]=new JLabel(ImageLoader.loadIcon(Definicoes.BALA_IMG));
			tirosPanel.add(tiroLabel[i]);
		}
	}
	
	public void setPontosPanel(int pontos)
	{
		pontosText.setText(pontos + "");
	}
	
	public void recargaTiro()
	{
		//System.out.println("---->");
		
		tirosPanel.removeAll();
		
		setTirosPanel(Definicoes.START_SHOTS);
		
		tirosPanel.updateUI();
	}
	
	public void removeTiro(int tiro)
	{
		tirosPanel.remove(tiro);
		
		tirosPanel.updateUI();
	}

	
	public void carregaQuadrante()
	{
		
		listaNpc = new ListaNpc(campo,player,resultados,this);
		
		player.setListaNpc(listaNpc);
		
		insiraPlayer();
		
		//System.out.println("Proximo quadrante do mundo " + x + "/" + y + ":");
		
		for(int j=0;j<Definicoes.VIEW_MAX_Y;j++)
		{
			for(int i=0;i<Definicoes.VIEW_MAX_X;i++)
			{
				locX = x*10+i-1;
				locY = y*10+j-1;
				
				//if(locX>=0 && locX<=Definicoes.MUNDO_MAX_X-1 && locY>=0 && locY<=Definicoes.MUNDO_MAX_Y-1)
					//System.out.print(m.e[locX][locY].valor);
				
			}
			//System.out.println();
		}
		
		//System.out.println();
		
		//System.out.println("Valores em memória:");
		
		//for(int j=0;j<Definicoes.VIEW_MAX_Y;j++)
		//{
			//for(int i=0;i<Definicoes.VIEW_MAX_X;i++)
			{
				//System.out.print(campo.getValor(i, j));
			}
			
			//System.out.println();
		//}
		
		//System.out.println();
		
		campo.loadMundo(x, y);
		
		campo.showAll();
		
		//System.out.println("Valores após a atribuição:");
		
		//for(int j=0;j<Definicoes.VIEW_MAX_Y;j++)
		//{
			//for(int i=0;i<Definicoes.VIEW_MAX_X;i++)
			//{
				//System.out.print(campo.getValor(i, j));
			//}
			
			//System.out.println();
		//}
		
		//System.out.println();
		
	}
	
	public void insiraPlayer()
	{
		campo.setValor(player.locX, player.locY, Definicoes.PIRATA_FRENTE,false);
		
		campo.updatePic(player.locX, player.locY);
		
		//System.out.println("Player: " + player.locX + "/" + player.locY);
	}
	
	public void setObjetivo(int nrObjetivo)
	{
		switch(nrObjetivo)
		{
			case 1: objetivoText.setText(Definicoes.OBJETIVO_PONTOS_TEXT); break;
			case 2: objetivoText.setText(Definicoes.OBJETIVO_RECARGAS_TEXT); break;
			case 3: objetivoText.setText(Definicoes.OBJETIVO_BAUS_TEXT); break;
		}
		
	}
	
	public void objetivoAlcancado()
	{
		player.objetivoAtual++;
		
		resultados.mainQuests++;
		
		player.informacaoObtida=0;
		
		if(player.objetivoAtual>Definicoes.NUMERO_OBJETIVOS)
		{
			listaNpc.stopAll();
			
			musica = new MP3Musica();
			musica.tocar(applauseFile);
			musica.start();
			
			resultados.finalDeJogo=new Timestamp(System.currentTimeMillis());
			
			winIO.message("Parab\u00e9ns, comandante!", "Todos os objetivos alcan\u00e7ados!!!");
			
			/*try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			winQuad.hide();
			
			new RelatorioFinal(resultados);
		} else
		{
			listaNpc.stopAll();
			
			winIO.message("Parab\u00e9ns, comandante!", "Objetivo alcan\u00e7ado!\nO pr\u00f3ximo objetivo est\u00e0 descrito no lado direito da tela.");
			
			if(player.objetivoAtual==2)
				resultados.finalObjetivo1=new Timestamp(System.currentTimeMillis());
			
			if(player.objetivoAtual==3)
				resultados.finalObjetivo2=new Timestamp(System.currentTimeMillis());
			
			wakeUpNpcs();
		}
		
		setObjetivo(player.objetivoAtual);
	}
	
	public void wakeUpNpcs()
	{
		listaNpc=new ListaNpc(campo,player,resultados,this);
		
		listaNpc.addAll();
		listaNpc.startAll();
		
		player.setCampo(campo);
		player.setListaNpc(listaNpc);
		player.setQuadrante(this);
		
		player.setListaNpc(listaNpc);
	}
	
	public void setListaNpc(ListaNpc listaNpc)
	{
		this.listaNpc = listaNpc;
	}
	
	public void setJogador(Jogador jogador)
	{
		this.player=jogador;
	}

	/**
	 * Desenhar o quadrante na tela conforme suas configuracoes na matriz
	 */

	public void initQuadrante()
	{
		

		for(int i=0;i<Definicoes.VIEW_MAX_Y;i++)
		{
			for(int j=0;j<Definicoes.VIEW_MAX_X;j++)
			{
				winQuad.addCenter(campo.getLabel(j, i));
			}

			winQuad.cr();
		}



		pontosText = new JLabel("0");
		pontosText.setFont(sidePanelFont);
		pontosText.setForeground(Color.BLUE);

		vidasPanel = new JPanel();
		setVidasPanel(Definicoes.START_LIVES);
		
		tirosPanel = new JPanel();
		setTirosPanel(Definicoes.START_SHOTS);
		
		objetivoText = new JLabel("--");
		objetivoText.setForeground(Color.DARK_GRAY);

		//Cor cor = new Cor();

		//winQuad.getFrame().getContentPane().setBackground(cor.getBkCor());

		/*up = new JButton("\u2191");
		down = new JButton("\u2193");
		right = new JButton("\u2192");
		left = new JButton("\u2190");*/
		
		up = new JButton(ImageLoader.loadIcon(Definicoes.SETA_PARA_CIMA_IMG));
		down = new JButton(ImageLoader.loadIcon(Definicoes.SETA_PARA_BAIXO_IMG));
		right = new JButton(ImageLoader.loadIcon(Definicoes.SETA_PARA_DIREITA_IMG));
		left = new JButton(ImageLoader.loadIcon(Definicoes.SETA_PARA_ESQUERDA_IMG));
		
		shot = new JButton(ImageLoader.loadIcon(Definicoes.PISTOLA_IMG));
		
		up.setBackground(Color.WHITE);
		down.setBackground(Color.WHITE);
		right.setBackground(Color.WHITE);
		left.setBackground(Color.WHITE);
		
		shot.setBackground(Color.WHITE);
		
		shot.addActionListener(new ShotListener());
		shot.addKeyListener(new TecladoListener());

		up.addActionListener(new UpListener());
		up.addKeyListener(new TecladoListener());
		
		down.addActionListener(new DownListener());
		down.addKeyListener(new TecladoListener());
		
		right.addActionListener(new RightListener());
		right.addKeyListener(new TecladoListener());
		
		left.addActionListener(new LeftListener());
		left.addKeyListener(new TecladoListener());

		buttonPanel = new Panel();

		buttonPanel.addCenter(new JLabel());
		buttonPanel.addCenter(up);
		buttonPanel.addCenter(new JLabel());

		buttonPanel.cr();

		buttonPanel.addCenter(left);
		buttonPanel.addCenter(shot);
		buttonPanel.addCenter(right);

		buttonPanel.cr();

		buttonPanel.addCenter(new JLabel());
		buttonPanel.addCenter(down);
		buttonPanel.addCenter(new JLabel());

		buttonPanel.pack();
		
		vidasPanel.setPreferredSize(new Dimension(300,50));

		winQuad.setPos(13, 0);

		winQuad.addRangeCenter(loadImageToJLabel(Definicoes.INFO_PONTOS_IMG), 13, 3);

		winQuad.setPos(13, 1);

		winQuad.addRangeCenter(pontosText, 13, 3);

		winQuad.setPos(13, 2);

		winQuad.addRangeCenter(loadImageToJLabel(Definicoes.INFO_VIDAS_IMG), 13, 3);

		winQuad.setPos(13, 3);

		winQuad.addRangeCenter(vidasPanel, 13, 3);

		winQuad.setPos(13, 4);

		winQuad.addRangeCenter(loadImageToJLabel(Definicoes.INFO_TIROS_IMG), 13, 3);

		winQuad.setPos(13, 5);

		winQuad.addRangeCenter(tirosPanel, 13, 3);

		winQuad.setPos(13, 6);

		winQuad.addRangeCenter(loadImageToJLabel(Definicoes.INFO_OBJETIVO_IMG), 13, 3);

		winQuad.setPos(13, 7);

		winQuad.addRangeCenter(objetivoText, 13, 3);
		
		setObjetivo(player.objetivoAtual);
		
		winQuad.add(buttonPanel.getPanel(), 13, 8, 1, 5);

		winQuad.pack();

		winQuad.centerPos();

		winQuad.show();
		
		//System.out.println("Tela X:" + winQuad.getFrame().getContentPane().getWidth() + " - Y:" + winQuad.getFrame().getContentPane().getHeight());
		
		//System.out.println("Botão X:" + up.getWidth() + " - Y:" + up.getHeight());
		
		musica.tocar(inicioFile);
		musica.start();
	
		winQuad.getFrame().addKeyListener(new TecladoListener());
		winQuad.getFrame().setFocusable(true);
		winQuad.getFrame().setResizable(false);
		
		loadNpcs();
		
	}
	
	public void loadNpcs()
	{
		listaNpc = new ListaNpc(campo,player,resultados,this);
		
		listaNpc.addAll();
		
		listaNpc.dumpAll();
		
		listaNpc.startAll();
		
		player.setListaNpc(listaNpc);
	}

	class TecladoListener implements KeyListener 
	{
		@Override
		public void keyPressed(KeyEvent e) {
			
			////System.out.println("Entrou no listener " + e.getKeyCode());

			switch (e.getKeyCode()){
			
			case 17: // Control
				player.tiro();
				break;
			case 37: //Esquerda
				//moverParaEsquerda();
				mover(-1,0);
				break;
			case 38:// Cima
				//moverParaCima();
				mover(0,-1);
				break;

			case 39: // Direita
				//moverParaDireita();
				mover(1,0);
				break;

			case 40: // Baixo
				//moverParaBaixo();
				mover(0,1);
				break;
			default:
				break;
			}
			////System.out.println("Saiu do listener");
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			////System.out.println("Entrou no listener 2");
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			////System.out.println("Entrou no listener 3");
		
		}
	}
	
	class UpListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//moverParaCima();
			mover(0,-1);
		}
	}

	class DownListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//moverParaBaixo();
			mover(0,1);
		}
	}

	class LeftListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//moverParaEsquerda();
			mover(-1,0);
		}
	}

	class RightListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//moverParaDireita();
			mover(1,0);
		}
	}
	
	class ShotListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			player.tiro();
		}
		
	}
	
	public boolean isBarrier(int xPlus, int yPlus)
	{
		char elementoAFrente = campo.getValor(player.locX + xPlus, player.locY + yPlus);
		
		////System.out.println("EAF" + elementoAFrente);
		
		for(int i=0;i<Definicoes.barrierValues.length;i++)
			if(elementoAFrente==Definicoes.barrierValues[i])
				return true; 
		
		return false;
	
	}
	
	public boolean isDanger(int xPlus, int yPlus)
	{
		char elementoAFrente = campo.getValor(player.locX + xPlus, player.locY + yPlus);
		
		////System.out.println("EAF" + elementoAFrente);
		
		for(int i=0;i<Definicoes.dangerValues.length;i++)
			if(elementoAFrente==Definicoes.dangerValues[i])
				return true; 
		
		return false;
	
	}
	
	public char getElement(int xPlus, int yPlus)
	{
		return campo.getValor(player.locX + xPlus, player.locY + yPlus);
	}
	
	public char getPirata(int xPlus, int yPlus)
	{
		switch(xPlus)
		{
		case 1: return Definicoes.PIRATA_DIREITA;
		case -1: return Definicoes.PIRATA_ESQUERDA;
		}
		
		switch(yPlus)
		{
		case 1: return Definicoes.PIRATA_FRENTE;
		case -1: return Definicoes.PIRATA_COSTAS;
		}
		
		return Definicoes.VAZIO;
	}
	
	public char getPirataMar(int xPlus, int yPlus)
	{
		switch(xPlus)
		{
		case 1: return Definicoes.PIRATA_DIREITA_MAR;
		case -1: return Definicoes.PIRATA_ESQUERDA_MAR;
		}
		
		switch(yPlus)
		{
		case 1: return Definicoes.PIRATA_FRENTE_MAR;
		case -1: return Definicoes.PIRATA_COSTAS_MAR;
		}
		
		return Definicoes.VAZIO;

	}
	
	public void movePirata(int xPlus, int yPlus)
	{
		if(noMar)
			campo.setValor(player.locX, player.locY, Definicoes.MAR, false); else
				campo.setBlank(player.locX, player.locY);
		campo.updatePic(player.locX, player.locY);
		
		player.locX=player.locX+xPlus;
		player.locY=player.locY+yPlus;
		
		if(campo.getValor(player.locX, player.locY)==Definicoes.MAR || campo.getValor(player.locX, player.locY)==Definicoes.MAR_ESPECIAL)
			noMar=true; else noMar=false;
		
		////System.out.println(player.locX + " ------- " + player.locY);
		
		if(noMar)
		{
			resultados.entrouNaAgua=true;
			
			campo.setValor(player.locX, player.locY, getPirataMar(xPlus,yPlus),false);
			
			musica = new MP3Musica();
			musica.tocar(splashFile);
			musica.start();
			
		} else
			campo.setValor(player.locX, player.locY, getPirata(xPlus,yPlus),false);
		campo.updatePic(player.locX, player.locY);
	}
	
	public void mover(int xPlus, int yPlus)
	{
		int newX, newY;
		
		boolean scroll = false, morto = false;
		
		if(isBarrier(xPlus,yPlus)==false){ 
			
			char currentElement = getElement(xPlus,yPlus);
			
			switch(currentElement)
			{
											
			case Definicoes.BALAS :			musica = new MP3Musica();
											musica.tocar(recargaFile);
											musica.start();
											
											player.adicionarPontos(Definicoes.PONTOS_PICKUP);
											
											player.recarga();
											
											if(player.objetivoAtual==2)
											{
												player.objetivo[1]++;
												if(player.objetivo[1]>=Definicoes.OBJETIVO_RECARGAS_NECESSARIAS)
													objetivoAlcancado();
											}
											
											resultados.recarregamentoArma++;
											
											break;
											
					
			/*case Definicoes.BUSSOLA :		musica = new MP3Musica();
											musica.tocar(coletarFile);
											musica.start();
											
											player.adicionarPontos(Definicoes.PONTOS_PICKUP);
				
											break;*/
											
			case Definicoes.INFORMACAO :	listaNpc.stopAll();
											
											musica = new MP3Musica();
											musica.tocar(informacaoFile);
											musica.start();
											
														
											switch(player.objetivoAtual)
											{
											case 1: if(player.informacaoObtida==0)
													{
														player.informacaoObtida++;
														
														winIO.message("Dica", Definicoes.INFORMACAO_PONTOS_PRINCIPAL);
													} else
														winIO.message("Dica", Definicoes.INFORMACAO_PONTOS_SIDE);
											
													wakeUpNpcs();
											
													break;
											case 2: if(player.informacaoObtida==0)
													{
														player.informacaoObtida++;
												
														winIO.message("Dica", Definicoes.INFORMACAO_RECARGAS_PRINCIPAL);
													} else
														winIO.message("Dica", Definicoes.INFORMACAO_RECARGAS_SIDE);
											
													wakeUpNpcs();
									
													break;
													
											case 3: if(player.informacaoObtida==0)
													{
														player.informacaoObtida++;
										
														winIO.message("Dica", Definicoes.INFORMACAO_BAUS_PRINCIPAL);
													} else
														winIO.message("Dica", Definicoes.INFORMACAO_BAUS_SIDE);
											
													wakeUpNpcs();
							
													break;
											}
											
											player.adicionarPontos(Definicoes.PONTOS_INFORMACAO);
											
											resultados.informacoesLidas++;
											
											break;
											
			case Definicoes.TESOURO :		musica = new MP3Musica();
											musica.tocar(informacaoFile);
											musica.start();
											
											player.adicionarPontos(Definicoes.PONTOS_PICKUP);
											
											player.adicionarPontos(Definicoes.PONTOS_TESOURO);
											
											resultados.conseguiuTesouro=true;
											resultados.sideQuests++;
			
											break;
			
			case Definicoes.ANEL :			musica = new MP3Musica();
											musica.tocar(informacaoFile);
											musica.start();
			
											player.adicionarPontos(Definicoes.PONTOS_ANEL);
											
											resultados.conseguiuAnel=true;
											resultados.sideQuests++;
			
											break;
			
			case Definicoes.DINHEIRO :		musica = new MP3Musica();
											musica.tocar(informacaoFile);
											musica.start();
			
											player.adicionarPontos(Definicoes.PONTOS_DINHEIRO);
											
											resultados.conseguiuDinheiro=true;
											resultados.sideQuests++;
			
											break;
											
			case Definicoes.CORREIO :		listaNpc.stopAll();
											
											musica = new MP3Musica();
											musica.tocar(informacaoFile);
											musica.start();
			
											player.adicionarPontos(Definicoes.PONTOS_INFORMACAO);
											
											if(winIO.yesNoQuestion("Gostaria de enviar uma mensagem aos outros jogadores?", "Correio")==0)
											{
												resultados.entrouCaixaDeCorreio=true;
												
												new MailBox(resultados);
											}
											
											wakeUpNpcs();
											
											break;
											
			case Definicoes.MAR_ESPECIAL :	musica = new MP3Musica();
											musica.tocar(cashFile);
											musica.start();
											
											player.adicionarPontos(Definicoes.PONTOS_ELEMENTO_ESCONDIDO);
											
											break;

											
			case Definicoes.OBJETIVO :		musica = new MP3Musica();
											musica.tocar(cashFile);
											musica.start();
											
											player.adicionarPontos(Definicoes.PONTOS_BAU);
											
											if(player.objetivoAtual==3)
											{
												player.objetivo[2]++;
												if(player.objetivo[2]>=Definicoes.OBJETIVO_BAUS_NECESSARIOS)
													objetivoAlcancado();
											}
											
											resultados.bausAbertos++;
				
											break;
											
			} 
			
			if(Definicoes.isDanger(currentElement))
			{
				/*winQuad.getFrame().getContentPane().setBackground(Color.RED);
				
				player.setJanela(winQuad);*/
				
				player.morreu(currentElement,noMar);
				
				/*if(Definicoes.isEsqueleto(currentElement))
				{
					listaNpc.stopNpcAt(player.locX, player.locY);
					listaNpc.stopNpcAt(player.locX+xPlus, player.locY+yPlus);
				}*/
				
				//System.out.println(currentElement + " QUI!");
				
				noMar=false;
				
				newX = 1;
				newY = 1;
				
				morto = true;
				
				
			} else
			{
			
				newX = player.locX+xPlus;
				newY = player.locY+yPlus;
			}
		
			if(newX==0 && x>0)
			{
				escrevaParaMundo();
				
				x--;
				
				newX=Definicoes.VIEW_MAX_X-2;
				
				scroll=true;
			}
			
			if(newX==Definicoes.VIEW_MAX_X-1 && x<Definicoes.QUADRANTE_MAX_X-1)
			{
				escrevaParaMundo();
				
				x++;
				
				newX=1;
				
				scroll=true;
			}
			
			if(newY==0 && y>0)
			{
				escrevaParaMundo();
				
				y--;
				
				newY=Definicoes.VIEW_MAX_Y-2;
				
				scroll=true;
			}
			
			if(newY==Definicoes.VIEW_MAX_Y-1 && y<Definicoes.QUADRANTE_MAX_Y-1)
			{
				escrevaParaMundo();
				
				y++;
				
				newY=1;
				
				scroll=true;
			}
			
			if(scroll)
			{
				listaNpc.stopAll();
				
				player.locX=newX;
				player.locY=newY;
				
				if(x==Definicoes.QUAD_ESQUELETOS_X && y==Definicoes.QUAD_ESQUELETOS_Y)
					resultados.entrouNaFaseEsqueletos=true;
				
				resultados.passagensTotais++;
				
				switch(player.objetivoAtual)
				{
				case 1: resultados.passagensPrimeiroObjetivo++; break;
				case 2: resultados.passagensSegundoObjetivo++; break;
				case 3: resultados.passagensTerceiroObjetivo++; break;
				}
				
				carregaQuadrante();
				
				if(noMar)
					campo.setValor(player.locX, player.locY,getPirataMar(xPlus,yPlus),false);
				else campo.setValor(player.locX, player.locY,getPirata(xPlus,yPlus),false);
				
				campo.updatePic(player.locX, player.locY);
				
				loadNpcs();
				
			} else
			{
				if(morto==false)
					movePirata(xPlus,yPlus);
			}
		} else
		{
			musica = new MP3Musica();
			musica.tocar(owFile);
			musica.start();
			
			resultados.machucados++;
		}
		
		
	}
	
		
	public void escrevaParaMundo()
	{
		campo.setBlank(player.locX, player.locY);
				
		for(int j=0;j<Definicoes.VIEW_MAX_Y;j++)
		{
			for(int i=0;i<Definicoes.VIEW_MAX_X;i++)
			{
				locX = x*10+i-1;
				locY = y*10+j-1;
				
				if(locX>=0 && locX<=Definicoes.MUNDO_MAX_X-1 && locY>=0 && locY<=Definicoes.MUNDO_MAX_Y-1
						&& player.isPirata(campo.getValor(i, j))==false && Definicoes.isTrifide(campo.getValor(i, j))==false
						&& Definicoes.isTrifide(m.e[locX][locY].valor)==false && campo.getValor(i,j)!=Definicoes.EXPLOSAO
						&& Definicoes.isTiro(campo.getValor(i, j))==false)
					m.e[locX][locY].valor= campo.getValor(i, j);
			}
		}
	}
	
	public JLabel loadImageToJLabel(String path)
	{
		JLabel label = new JLabel();
		
		File file = new File(path);
	    BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {

			e.printStackTrace(); // &&&
		}
			
		label.setIcon(new ImageIcon(image));
		
		return label;
	}
}
