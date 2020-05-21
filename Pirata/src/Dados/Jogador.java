package Dados;

import java.io.File;
import java.sql.Timestamp;

import javax.swing.*;

import javazoom.jl.player.JLayer.MP3Musica;
import Config.Definicoes;
import IO.WinIO;
import IO.Window;

public class Jogador {
	
	int balas, vida, locQuad, locX, locY, pontos;
	
	int objetivoAtual = 1;
	
	int informacaoObtida = 0;
	
	int[] objetivo;

	File fimFile, monsterFile, ataqueFile, coletarFile, colisaoFile, explosaoFile, golpeFile, informacaoFile, inicioFile, morteFile, recargaFile, tiroCurtoFile, tiroLongoFile, caindoFile, nooFile, oceanoFile, risadaFile, cashFile, owFile, clingFile;
	
	Campo campo;
	Quadrante quadrante;
	
	MP3Musica musica;
	
	Window janela;
	
	Tiro tiro;
	Morte morte;
	
	ListaNpc listaNpc;
	
	JLabel[] vidaLabel;
	JPanel panel;
	
	Resultados resultados;
	
	int continues = Definicoes.CONTINUES;
	
	// Dados do relatório
	

	public Jogador(Resultados resultados)
	{
		this.resultados = resultados;
		
		objetivo = new int[Definicoes.NUMERO_OBJETIVOS];
		
		for(int i=0;i<Definicoes.NUMERO_OBJETIVOS;i++)
			objetivo[i]=0;
		
		balas = Definicoes.START_SHOTS;
		vida = Definicoes.START_LIVES;
		locQuad = 0;
		locX = 1;
		locY = 1;
		
		carregaSons();
	}
	
	public void recarga()
	{
		balas = Definicoes.START_SHOTS;
		
		quadrante.recargaTiro();
	}
	
	public void setQuadrante(Quadrante quadrante)
	{
		this.quadrante=quadrante;
	}
	
	public void setJanela(Window janela)
	{
		this.janela=janela;
	}
	
	public void setCampo(Campo campo)
	{
		this.campo = campo;
	}
	
	public void setListaNpc(ListaNpc listaNpc)
	{
		this.listaNpc=listaNpc;
	}

	public boolean isPirata(char valor)
	{
		if(valor==Definicoes.PIRATA_COSTAS || valor==Definicoes.PIRATA_DIREITA || valor==Definicoes.PIRATA_ESQUERDA || valor==Definicoes.PIRATA_FRENTE)
			return true;
		
		return false;
	}
	
	public int getX()
	{
		return locX;
	}
	
	public int getY()
	{
		return locY;
	}
	
	public int getPlayerDirection()
	{
		switch(campo.getValor(locX, locY))
		{
			case Definicoes.PIRATA_FRENTE : return Definicoes.PARA_BAIXO;
			case Definicoes.PIRATA_COSTAS : return Definicoes.PARA_CIMA; 
			case Definicoes.PIRATA_DIREITA : return Definicoes.PARA_DIREITA;
			case Definicoes.PIRATA_ESQUERDA : return Definicoes.PARA_ESQUERDA;
		}
		
		return Definicoes.ERROR_GERAL;
	}
	
	public void tiro()
	{
		if(Definicoes.DISCOUNT_SHOTS)
		{
			if(balas>-1) balas--;
		
			if(balas>-1) quadrante.removeTiro(balas);
		}
		
		//System.out.println("Valor do campo:" + campo.getValor(locX,locY));
		
		if(balas>-1 && Definicoes.isPlayerMar(campo.getValor(locX,locY))==false)
		{
		
			int xPlus = 0, yPlus = 0;
		
			musica = new MP3Musica();
			musica.tocar(tiroLongoFile);
			musica.start();
		
			switch(getPlayerDirection())
			{
				case Definicoes.PARA_BAIXO : xPlus = 0; yPlus = 1; break;
				case Definicoes.PARA_CIMA : xPlus = 0; yPlus = -1; break;
				case Definicoes.PARA_ESQUERDA : xPlus = -1; yPlus = 0; break;
				case Definicoes.PARA_DIREITA : xPlus = 1; yPlus = 0; break;
			}
		
			tiro = new Tiro(xPlus,yPlus,locX,locY,campo,this,listaNpc);
		
			tiro.start();
			
			resultados.tirosDisparados++;
			
		} else
		{
			// Descarregado ou em mar
			
			if(Definicoes.isPlayerMar(campo.getValor(locX,locY)))
					resultados.atirouNaAgua=true;
			
			musica = new MP3Musica();
			musica.tocar(clingFile);
			musica.start();
		
		}
	}
	
	public void adicionarPontos(int adicionar)
	{
		if(objetivoAtual==1)
		{
			objetivo[0]=objetivo[0]+adicionar;
			
			if(objetivo[0]>Definicoes.OBJETIVO_PONTOS_NECESSARIOS)
			{
				quadrante.objetivoAlcancado();
			} 
		}
		
		pontos = pontos + adicionar;
		
		quadrante.setPontosPanel(pontos);
		
		resultados.pontos=pontos;
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
		clingFile = new File(Definicoes.CLING_MP3);
		monsterFile = new File(Definicoes.MONSTER_MP3);
		fimFile = new File(Definicoes.FIM_MP3);
	}
	
	public void morreu(char causa, boolean mar)
	{
		
		if(Definicoes.isEsqueleto(causa))
			causa = Definicoes.ESQUELETO;
		
		if(Definicoes.isTubarao(causa))
			causa = Definicoes.TUBARAO_FRENTE;
		
		switch(causa)
		{
		case Definicoes.ESQUELETO : 	musica = new MP3Musica();
										musica.tocar(risadaFile);
										musica.start();
										
										resultados.mortePorEsqueletos++;
										
										break;
										
		case Definicoes.BURACO :		musica = new MP3Musica();
										musica.tocar(caindoFile);
										musica.start();
										
										resultados.mortePorBuraco++;
										
										break;
										
		case Definicoes.TRIFIDE :		musica = new MP3Musica();
										musica.tocar(nooFile);
										musica.start();
										
										resultados.mortePorPlantas++;
										
										break;
										
		case Definicoes.OCEANO :		musica = new MP3Musica();
										musica.tocar(oceanoFile);
										musica.start();
										
										resultados.mortePorOceano++;
										
										break;
										
		case Definicoes.TUBARAO_FRENTE : musica = new MP3Musica();
										musica.tocar(monsterFile);
										musica.start();
										
										resultados.mortePorTubarao++;
										
										quadrante.noMar=false;
										
										break;
										
		}
		
		if(!mar)
		{
			campo.setBlank(locX, locY);
		} else
		{
			campo.setBlankMar(locX, locY);
		}
		
		campo.updatePic(locX, locY);
						
		locX = 1;
		locY = 1;
		
		WinIO io = new WinIO();
		
		listaNpc.stopNpcAt(1, 1);
		
		campo.setValor(locX, locY, Definicoes.PIRATA_FRENTE,false);
		
		for(int i=1;i<Definicoes.VIEW_MAX_X-1;i++)
			for(int j=1;j<Definicoes.VIEW_MAX_Y-1;j++)
			{
				campo.updatePic(i, j);
			}
		
		//Color c = janela.getFrame().getContentPane().getBackground();
		
		morte = new Morte(janela);
		
		morte.start();
		
		if(Definicoes.DISCOUNT_LIVES)
		{
			
			if(vida>0)
			{
			
				vida--;
			
				quadrante.removeLife(vida);
			
				if(vida==0)
				{

					listaNpc.stopAll();
					
					/*MP3Musica musica = new MP3Musica();
					musica.tocar(fimFile);
					musica.start();*/
				
					if(continues==0)
					{
					
						io.message("Acabou", "G A M E   O V E R !!");
					
						resultados.finalDeJogo=new Timestamp(System.currentTimeMillis());
				
						quadrante.winQuad.hide();
					
						new RelatorioFinal(resultados);
					} else
					{
						String continuacao;
						
						if(continues>1)
							continuacao="continua\u00e7\u00f5es"; else
								continuacao="continua\u00e7\u00e3o";
						
						if(io.yesNoQuestion("G A M E   O V E R !! Voc\u00ea ainda tem " + continues + " " + continuacao + ". Continuar?","Acabou")==0)
						{
							continues--;
							
							listaNpc=new ListaNpc(campo,this,resultados,quadrante);
							
							listaNpc.addAll();
							listaNpc.startAll();
							
							quadrante.setListaNpc(listaNpc);
							
							vida=Definicoes.START_LIVES;
							balas=Definicoes.START_SHOTS;
							
							quadrante.setVidasPanel(Definicoes.START_LIVES);
							quadrante.recargaTiro();
							
							resultados.continues++;
							
							//quadrante.setJogador(this);
							
							/*
							
							player.setCampo(campo);
							player.setListaNpc(listaNpc);
							player.setQuadrante(this);
							
							player.setListaNpc(listaNpc);
							
							*/
						} else
						{
							resultados.finalDeJogo=new Timestamp(System.currentTimeMillis());
							
							quadrante.winQuad.hide();
						
							new RelatorioFinal(resultados);
						}
					}
				}
		
			}
			
		}


	}
}
