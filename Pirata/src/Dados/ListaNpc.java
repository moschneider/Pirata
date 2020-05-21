package Dados;

import Config.Definicoes;

public class ListaNpc {
	
	/**
	 * Classe lista de sorteios
	 */

	Npc inicio, fim, atual, novo;
	
	Campo campo;
	
	Jogador player;
	
	Explosao explosao;
	
	Resultados resultados;
	Quadrante quadrante;
	
	public ListaNpc(Campo campo, Jogador player, Resultados resultados, Quadrante quadrante)
	{
		this.resultados=resultados;
		this.quadrante=quadrante;
		
		this.campo=campo;
		
		this.player=player;
		
		inicio = new Npc(0,0,Definicoes.INDEFINIDA,campo,player);
		
		atual = inicio;
		
		fim = new Npc(0,0,Definicoes.INDEFINIDA,campo,player);
		
		inicio.setProximo(fim);
		
	}
	
	public void startAll()
	{
		Npc auxiliar;
		
		auxiliar = atual;
		
		atual = inicio;
		
		while(atual!=fim)
		{
			atual.start();
			dumpAtual();
			next();
		}
		
		atual = auxiliar;
	}
	
	public void addAll()
	{
		for(int i=0;i<Definicoes.VIEW_MAX_X;i++)
			for(int j=0;j<Definicoes.VIEW_MAX_Y;j++)
			{
				if(campo.getValor(i, j)==Definicoes.TRIFIDE || Definicoes.isEsqueleto(campo.getValor(i, j)) || Definicoes.isTubarao(campo.getValor(i, j)))
				{
					adicionar(i, j, campo.getValor(i, j));
				}
			}
		
	}
	
	public void stopAll()
	{
		Npc auxiliar;
		
		auxiliar = atual;
		
		atual = inicio;
		
		while(atual!=fim)
		{
			atual.kill();
			dumpAtual();
			next();
			//System.out.println("Killed.");
		}
		
		atual = auxiliar;
	}
	
	public void stopNpcAt(int x, int y)
	{
		Npc auxiliar;
		
		auxiliar = atual;
		
		atual = inicio;
		
		while(atual!=fim)
		{
			//System.out.println("@ " + atual.getX() + " " + atual.getY());
			
			if(atual.getX()==x && atual.getY()==y && atual.isRunning) 
		
				if(atual.logica==Definicoes.ESQUELETO)
				{
					atual.kill();
					if(player.locX!=x || player.locY!=y)
					{
						campo.setValor(x, y, Definicoes.VAZIO, false);
						campo.updatePic(x, y);
					}

					////System.out.println("====== gotya ======");
				
					explosao = new Explosao(x,y,campo,player);
					explosao.start();
					
					resultados.esqueletosMortos++;
					
					if(quadrante.x==Definicoes.QUAD_ESQUELETOS_X && quadrante.y==Definicoes.QUAD_ESQUELETOS_Y)
						resultados.esqueletosMortosNaFaseDosEsqueletos++;
					
				} else
				{
					//System.out.println(resultados.plantasAtacadas);
					
					resultados.plantasAtacadas++;
				}
			
			//else //System.out.println("@ " + atual.getX() + " " + atual.getY() + "Nothing found.");
			//dumpAtual();
			next();
		}
		
		atual = auxiliar;
	}
	
	public void adicionar(int x, int y, char logica)
	{
		if(campo.isMargem(x,y)==false)
		{
		atual.setVida(Definicoes.VIDA_INICIAL_NPCS);
		atual.setTiros(Definicoes.TIROS_INICIAIS_NPCS);
		atual.setX(x);
		atual.setY(y);
		atual.setLogica(logica);
		
		novo = new Npc(0,0,Definicoes.INDEFINIDA,campo,player);
		
		novo.setProximo(atual.getProximo());
		
		atual.setProximo(novo);
		
		atual = novo;
		
		atual.getProximo().setAnterior(atual);
		}
	}
	
	public void atualParaInicio()
	{
		atual = inicio;
	}
	
	public void atualParaFinal()
	{
		atual = fim;
	}
	
	public void next()
	{
		if(atual!=fim)
			atual=atual.getProximo();
	}
	
	public void dumpAll()
	{
		Npc auxiliar;
		
		auxiliar = atual;
		
		atual = inicio;
		
		while(atual!=fim)
		{
			dumpAtual();
			next();
		}
		
		atual = auxiliar;
	}
	
	public void dumpAtual()
	{
		//System.out.println("--------------------------");
		//System.out.println("Lógica: " + atual.logica);
		//System.out.println("X:      " + atual.x);
		//System.out.println("Y:      " + atual.y);
		//System.out.println("Vida:   " + atual.vida);
		//System.out.println("Tiros:  " + atual.tiros);
		//System.out.println("Morto:  " + atual.morto);
		//System.out.println("--------------------------\n");
	}
}
