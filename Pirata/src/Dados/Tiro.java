package Dados;

import Config.Definicoes;

public class Tiro extends Thread {
	
	int dirX, dirY, posX, posY;
	
	Campo campo;
	
	boolean running = true;
	
	Jogador jogador;
	
	ListaNpc listaNpc;
	
	Quadrante quadrante;
	
	public Tiro(int dirX, int dirY, int posX, int posY, Campo campo, Jogador jogador, ListaNpc listaNpc)
	{
		this.dirX = dirX;
		this.dirY = dirY;
		this.posX = posX;
		this.posY = posY;
		this.campo = campo;
		this.jogador = jogador;
		this.listaNpc = listaNpc;
	}
	
	public void run()
	{
		char tipo;

		
		
		if(dirX==1)
			tipo=Definicoes.BALA_PARA_DIREITA;
		else
			if(dirX==-1)
				tipo=Definicoes.BALA_PARA_ESQUERDA;
			else
				if(dirY==1)
					tipo=Definicoes.BALA_PARA_BAIXO;
				else
					tipo=Definicoes.BALA_PARA_CIMA;
	
		while(running)
		{
			if(posX+dirX<1 || posX+dirX>Definicoes.VIEW_MAX_X-2 || posY+dirY<1 || posY+dirY>Definicoes.VIEW_MAX_Y-2)
				running=false;
			else
			{
			
				posX=posX+dirX;
				posY=posY+dirY;
			
				if(campo.isBlank(posX, posY))
				{
					campo.setValor(posX, posY, tipo, false);
					campo.updatePic(posX, posY);
				
					try {
						sleep(50);
					} catch (InterruptedException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(jogador.locX==posX && jogador.locY==posY)
					{
						running=false;
					} else
					{
				
					campo.setValor(posX, posY, Definicoes.VAZIO, false);
					campo.updatePic(posX, posY);
				
					if(posX+dirX<1 || posX+dirX>Definicoes.VIEW_MAX_X-2 || posY+dirY<1 || posY+dirY>Definicoes.VIEW_MAX_Y-2)
						running=false;
					else
						if(campo.isBlank(posX+dirX, posY+dirY)==false)
						{
							running=false;
							listaNpc.stopNpcAt(posX+dirX, posY+dirY);
							//System.out.println("Npc@ " +posX + " " + posY);
						}
					}
				} else
				{
					running=false;
					listaNpc.stopNpcAt(posX, posY);
					//System.out.println("Npc@ " +posX + " " + posY);
				}
			}
		}
	}

}
