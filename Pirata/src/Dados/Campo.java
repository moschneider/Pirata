package Dados;

import javax.swing.JLabel;

import Config.Definicoes;

public class Campo {

	Elemento[][] elemento;
	
	Mundo m;
	
	public Campo(Mundo m)
	{
		elemento = new Elemento[Definicoes.VIEW_MAX_X][Definicoes.VIEW_MAX_Y];
		
		this.m = m;
	}
	
	public boolean isMargem(int x, int y)
	{
		if(x==0) return true;
		
		if(y==0) return true;
		
		if(x==Definicoes.VIEW_MAX_X-1) return true;
		
		if(y==Definicoes.VIEW_MAX_Y-1) return true;
		
		return false;
	}

	public Elemento[][] getElemento() {
		return elemento;
	}

	public void setElemento(Elemento[][] elemento) {
		this.elemento = elemento;
	}
	
	public char getValor(int x, int y)
	{
		if(x<0 || y<0 || x>Definicoes.MUNDO_MAX_X-1 || y>Definicoes.MUNDO_MAX_Y-1)
			return Definicoes.OCEANO;
		
		return elemento[x][y].valor;
	}
	
	public boolean isBlank(int x, int y)
	{
		if(elemento[x][y].valor==Definicoes.VAZIO)
			return true;
		
		return false;
	}
	
	public boolean isBlankMar(int x, int y)
	{
		if(elemento[x][y].valor==Definicoes.MAR)
			return true;
		
		return false;
	}
	
	public boolean isPlayer(int x, int y)
	{
		return Definicoes.isPlayer(elemento[x][y].valor);
	}
	
	public boolean isPlayerMar(int x, int y)
	{
		return Definicoes.isPlayerMar(elemento[x][y].valor);
	}
	
	public void setValor(int x, int y, char c, boolean margem)
	{
		if(x>-1 && y>-1 && x<Definicoes.MUNDO_MAX_X && y<Definicoes.MUNDO_MAX_Y)
		{
			elemento[x][y].valor=c;
		
			elemento[x][y].margem=margem;
		}
	}
	
	public void updatePic(int x, int y)
	{
		if(x>-1 && y>-1 && x<Definicoes.MUNDO_MAX_X && y<Definicoes.MUNDO_MAX_Y)
		{
			elemento[x][y].updatePic();
		}
	}
	
	public void create(int x, int y, char c, boolean margem)
	{
		elemento[x][y]=new Elemento(c,margem);
	}
	
	public JLabel getLabel(int x, int y)
	{
		return elemento[x][y].getLabel();
	}
	
	public void setBlank(int x, int y)
	{
		elemento[x][y].valor=Definicoes.VAZIO;
	}
	
	public void setBlankMar(int x, int y)
	{
		elemento[x][y].valor=Definicoes.MAR;
	}
	
	public void showAll()
	{
		for(int i=0;i<Definicoes.VIEW_MAX_X;i++)
			for(int j=0;j<Definicoes.VIEW_MAX_Y;j++)
			{
				elemento[i][j].updatePic();
			}
	}
	
	public void loadMundo(int x, int y)
	{
		int locX, locY;
		
		for(int i=0;i<Definicoes.VIEW_MAX_X;i++)
			for(int j=0;j<Definicoes.VIEW_MAX_Y;j++)
			{
				locX = x*10+i-1;
				locY = y*10+j-1;
				
				if(locX>=0 && locX<=Definicoes.MUNDO_MAX_X-1 && locY>=0 && locY<=Definicoes.MUNDO_MAX_Y-1)
				{
					
					if(i==0 || i==Definicoes.VIEW_MAX_X-1 || j==0 || j==Definicoes.VIEW_MAX_Y-1)
					
						setValor(i, j, m.e[locX][locY].valor,true); else
							setValor(i, j, m.e[locX][locY].valor,false);
					
				}
				
				else setValor(i, j, Definicoes.OCEANO,false);
				
			}
	}

	public boolean isDanger(int x, int y)
	{
		char elementoAFrente = getValor(x,y);
		
		for(int i=0;i<Definicoes.dangerValues.length;i++)
			if(elementoAFrente==Definicoes.dangerValues[i])
				return true; 
		
		return false;
	
	}

}
