package Dados;

import Config.Definicoes;

public class Npc extends Thread {

	boolean isRunning = true;
	
	int vida = Definicoes.VIDA_INICIAL_NPCS;
	int tiros = Definicoes.TIROS_INICIAIS_NPCS;
	
	char logica = Definicoes.INDEFINIDA;
	
	boolean morto = false;
	
	int x, y;
	
	Npc proximo, anterior;
	
	Campo campo;
	
	Jogador player;
	
	public Npc(int x, int y, char logica, Campo campo, Jogador player)
	{
		this.x=x;
		this.y=y;
		this.logica=logica;
		this.campo=campo;
		this.player=player;
		
	}
	
	public Npc getProximo() {
		return proximo;
	}

	public void setProximo(Npc proximo) {
		this.proximo = proximo;
	}

	public Npc getAnterior() {
		return anterior;
	}

	public void setAnterior(Npc anterior) {
		this.anterior = anterior;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getTiros() {
		return tiros;
	}

	public void setTiros(int tiros) {
		this.tiros = tiros;
	}

	public int getLogica() {
		return logica;
	}

	public void setLogica(char logica) {
		this.logica = logica;
	}

	public boolean isMorto() {
		return morto;
	}

	public void setMorto(boolean morto) {
		this.morto = morto;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void kill()
	{
		isRunning = false;
	}
	
	public char getEsqueleto(int x, int y)
	{
		if(y==-1)
			return Definicoes.ESQUELETO_COSTAS;
		
		if(x==-1)
			return Definicoes.ESQUELETO_ESQUERDA;
		
		if(x==1)
			return Definicoes.ESQUELETO_DIREITA;
		
		return Definicoes.ESQUELETO;
	}
	
	public char getTubarao(int x, int y)
	{
		
		if(x==-1)
			return Definicoes.TUBARAO_ESQUERDA;
		
		if(x==1)
			return Definicoes.TUBARAO_DIREITA;
		
		return Definicoes.TUBARAO_FRENTE;
	}
	
	public void run()
	{
		int xPlus = 0, yPlus = 0, newX = 0, newY = 0;
		
		if(Definicoes.isEsqueleto(logica))
		{
			int[][] pontos;
			
			logica=Definicoes.ESQUELETO;
			
			pontos = new int[3][3];
			
			int checkX, checkY;
						
			while(isRunning)
			{
				for(int k=0;k<3;k++)
					for(int l=0;l<3;l++)
					{
						checkX=x+k-1;
						checkY=y+l-1;
						
						if((k==1 && l==1) || (checkX<1 || checkX > Definicoes.VIEW_MAX_X-1 || checkY<1 || checkY>Definicoes.VIEW_MAX_Y-1))
							pontos[k][l]=999; else
						if(Definicoes.isPlayer(campo.getValor(checkX, checkY)))
							pontos[k][l]=-999; else
								if(campo.getValor(checkX, checkY)!=Definicoes.VAZIO)
									pontos[k][l]=999; else
									{
										pontos[k][l] = Math.abs(checkX-player.locX) + Math.abs(checkY-player.locY);
									}
					}
				
				int menorX = -1, menorY = -1, menorValor = 998;
				
				for(int k=0;k<3;k++)
					for(int l=0;l<3;l++)
					{
						////System.out.print(pontos[k][l] + " -o- ");
						
						if(pontos[k][l]<menorValor)
						{
							menorX = k;
							menorY = l;
							menorValor = pontos[k][l];
						}
					}
				
				////System.out.println();
				
				menorX = menorX - 1;
				menorY = menorY - 1;
				
				campo.setBlank(x, y);
				
				campo.updatePic(x, y);
				
				if(menorValor<998)
				if(x+menorX>0 && x+menorX<Definicoes.VIEW_MAX_X-1 && y+menorY>0 && y+menorY<Definicoes.VIEW_MAX_Y-1)
					if(campo.isBlank(x+menorX, y+menorY) || campo.isPlayer(x+menorX, y+menorY))
					{
						x=x+menorX;
						y=y+menorY;
					}
				
				campo.setValor(x, y, getEsqueleto(menorX,menorY), false);
				
				campo.updatePic(x, y);
				
				if(x==player.locX && y==player.locY)
				{
					campo.setBlank(x, y);
					campo.updatePic(x, y);
					player.morreu(Definicoes.ESQUELETO,false);
					isRunning=false;
				}
				
				try {
					sleep(Definicoes.ESQUELETO_DELAY);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if(Definicoes.isTubarao(logica))
		{
			//System.out.println("TUTU");
			
			int[][] pontos;
			
			pontos = new int[3][3];
			
			int checkX, checkY;
						
			while(isRunning)
			{
				for(int k=0;k<3;k++)
					for(int l=0;l<3;l++)
					{
						checkX=x+k-1;
						checkY=y+l-1;
						
						if((k==1 && l==1) || (checkX<1 || checkX > Definicoes.VIEW_MAX_X-1 || checkY<1 || checkY>Definicoes.VIEW_MAX_Y-1))
							pontos[k][l]=999; else
						if(Definicoes.isPlayerMar(campo.getValor(checkX, checkY)))
							pontos[k][l]=-999; else
								if(campo.getValor(checkX, checkY)!=Definicoes.MAR)
									pontos[k][l]=999; else
									{
										pontos[k][l] = Math.abs(checkX-player.locX) + Math.abs(checkY-player.locY);
									}
					}
				
				int menorX = -1, menorY = -1, menorValor = 998;
				
				for(int k=0;k<3;k++)
					for(int l=0;l<3;l++)
					{
						////System.out.print(pontos[k][l] + " -o- ");
						
						if(pontos[k][l]<menorValor)
						{
							menorX = k;
							menorY = l;
							menorValor = pontos[k][l];
						}
					}
				
				////System.out.println();
				
				menorX = menorX - 1;
				menorY = menorY - 1;
				
				campo.setBlankMar(x, y);
				
				campo.updatePic(x, y);
				
				if(x+menorX>0 && x+menorX<Definicoes.VIEW_MAX_X-1 && y+menorY>0 && y+menorY<Definicoes.VIEW_MAX_Y-1)
					if(campo.isBlankMar(x+menorX, y+menorY)  || campo.isPlayerMar(x+menorX, y+menorY))
					{
						x=x+menorX;
						y=y+menorY;
					}
				
				campo.setValor(x, y, getTubarao(menorX,menorY), false);
				
				campo.updatePic(x, y);
				
				if(x==player.locX && y==player.locY)
				{
					//campo.setBlankMar(x, y);
					//campo.updatePic(x, y);
					player.morreu(Definicoes.TUBARAO_FRENTE,true);
					//isRunning=false;
				}
				
				try {
					sleep(Definicoes.TUBARAO_DELAY);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if(logica==Definicoes.TRIFIDE)
		{
			
			if((campo.getValor(x+1, y)==Definicoes.VAZIO||Definicoes.isPlayer(campo.getValor(x+1, y))) && campo.isMargem(x+1,y)==false)
				xPlus = 1; 
					else
						if((campo.getValor(x-1, y)==Definicoes.VAZIO||Definicoes.isPlayer(campo.getValor(x-1, y))) && campo.isMargem(x-1,y)==false)
							xPlus = -1; else
								if((campo.getValor(x, y+1)==Definicoes.VAZIO||Definicoes.isPlayer(campo.getValor(x, y +1))) && campo.isMargem(x,y+1)==false)
									yPlus = 1; else
										if((campo.getValor(x, y-1)==Definicoes.VAZIO||Definicoes.isPlayer(campo.getValor(x, y -1))) && campo.isMargem(x,y-1)==false)
											yPlus = -1;
			
			newX = x+xPlus;
			newY = y+yPlus;
			
			if(newX>0 && newX<Definicoes.VIEW_MAX_X && newY>0 && newY<Definicoes.VIEW_MAX_Y)
			{
				int tick = 0;
				
				while(isRunning)
				{
					campo.setBlank(x, y);
					
					campo.updatePic(x, y);
					
					if(campo.getValor(newX, newY)==Definicoes.VAZIO || Definicoes.isPlayer(campo.getValor(newX, newY)))
					{
				
						x = newX;
						y = newY;
						
					} else tick++;
					
					if(Definicoes.isPlayer(campo.getValor(x, y)))
						
						player.morreu(Definicoes.TRIFIDE,false);
						
				
					if(campo.getValor(x, y)==Definicoes.VAZIO) 
						
						campo.setValor(x, y, Definicoes.TRIFIDE,false);
				
					campo.updatePic(x, y);
				
					try {
						sleep(Definicoes.TRIFIDE_DELAY);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					tick++;
				
					if(tick%2==0)
					{
						newX = x+xPlus;
						newY = y+yPlus;
					} else
					{
						newX = x-xPlus;
						newY = y-yPlus;
					}
				
				}
				
			}
		}									
	}

}
