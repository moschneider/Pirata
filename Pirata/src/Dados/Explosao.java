package Dados;

import java.io.File;

import Config.Definicoes;
import javazoom.jl.player.JLayer.MP3Musica;

public class Explosao extends Thread {

	Campo campo;
	int locX, locY;
	Jogador jogador;
	MP3Musica musica;
	
	File explosionFile;
	
	public Explosao(int locX, int locY, Campo campo, Jogador jogador)
	{
		this.locX=locX;
		this.locY=locY;
		this.campo=campo;
		this.jogador=jogador;
		
		carregaSons();
	}
	
	public void carregaSons()
	{
		explosionFile = new File(Definicoes.EXPLOSION_MP3);
	}
	
	public void run()
	{
		if(locX!=jogador.locX || locY!=jogador.locY)
		{
			campo.setValor(locX, locY, Definicoes.EXPLOSAO, false);
			campo.updatePic(locX, locY);
				
			musica = new MP3Musica();
			musica.tocar(explosionFile);
			musica.start();
				
			try {
				sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			jogador.adicionarPontos(Definicoes.PONTOS_ESQUELETO);
				
			campo.setValor(locX, locY, Definicoes.VAZIO, false);
			campo.updatePic(locX, locY);
				
		}
	}
	
}
