package Dados;

import Config.Definicoes;

public class Avaliacao {
	
	int[][] pontos;
	
	boolean[][] aberto;
	
	int melhorX = Definicoes.SEM_MELHOR_SOLUCAO, melhorY = Definicoes.SEM_MELHOR_SOLUCAO;
	
	Jogador jogador;
	Campo campo;
	Npc npc;
	
	public Avaliacao(Jogador jogador, Campo campo, Npc npc)
	{
		this.jogador=jogador;
		this.campo=campo;
		
		pontos = new int[Definicoes.MAX_AVALIACAO_X][Definicoes.MAX_AVALIACAO_Y];
		aberto = new boolean[Definicoes.MAX_AVALIACAO_X][Definicoes.MAX_AVALIACAO_Y];
		
		for(int i=0;i<Definicoes.MAX_AVALIACAO_X;i++)
		{
			for(int j=0;j<Definicoes.MAX_AVALIACAO_Y;j++)
			{
				pontos[i][j]=Definicoes.SEM_MELHOR_SOLUCAO;
				aberto[i][j]=false;
			}
		}
	}
	
	public void calcularMelhor()
	{
		int melhorPontos = -1, pontosAtuais = -1;
		
		for(int i=0;i<Definicoes.MAX_AVALIACAO_X;i++)
		{
			for(int j=0;j<Definicoes.MAX_AVALIACAO_Y;j++)
			{	
				pontosAtuais = Math.abs(npc.getX()-(jogador.getX()+i-1)) + Math.abs(npc.getY()-(jogador.getY()+j-1));
				
				if(pontosAtuais>melhorPontos)
				{
					melhorPontos=pontosAtuais;
					melhorX = i-1;
					melhorY = j-1;
				}
			}
		}
	}
	
	public int getMelhorX()
	{
		return melhorX;
	}

	public int getMelhorY()
	{
		return melhorY;
	}
}
