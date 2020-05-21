package Dados;

//import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Config.Definicoes;
import IO.Cor;
import IO.ImageLoader;

public class Elemento {

	Cor cor;
	
	JLabel pic;
		
	char valor = ' ';
	
	boolean margem = false;
	
	public Elemento(char valor, boolean margem)
	{
		this.valor=valor;
		
		this.margem=margem;
		
		setPic();
	}
	
	public String getPicPath()
	{
		String path = "";
		
		if(margem)	
		{
			
			switch(valor)
			{
		
			case Definicoes.VAZIO : path = Definicoes.VAZIO_CINZA_IMG; break; // ok
			case Definicoes.PEDRA : path = Definicoes.PEDRA_CINZA_IMG; break; // ok
			case Definicoes.COQUEIRO_ESQUERDA : path = Definicoes.COQUEIRO_ESQUERDA_CINZA_IMG; break; // ok
			case Definicoes.COQUEIRO_DIREITA : path = Definicoes.COQUEIRO_DIREITA_CINZA_IMG; break; // ok
			case Definicoes.ARBUSTO : path = Definicoes.ARBUSTO_CINZA_IMG; break; // ok
			case Definicoes.OBJETIVO : path = Definicoes.OBJETIVO_CINZA_IMG; break;
			case Definicoes.ESQUELETO : path = Definicoes.ESQUELETO_FRENTE_CINZA_IMG; break; // ok
			case Definicoes.ESQUELETO_COSTAS : path = Definicoes.ESQUELETO_COSTAS_CINZA_IMG; break;
			case Definicoes.ESQUELETO_DIREITA : path = Definicoes.ESQUELETO_DIREITA_CINZA_IMG; break;
			case Definicoes.ESQUELETO_ESQUERDA : path = Definicoes.ESQUELETO_ESQUERDA_CINZA_IMG; break;
			case Definicoes.TRIFIDE : path = Definicoes.TRIFIDE_CINZA_IMG; break;
			case Definicoes.BURACO : path = Definicoes.BURACO_CINZA_IMG; break;
			case Definicoes.INFORMACAO : path = Definicoes.INFORMACAO_CINZA_IMG; break;
			case Definicoes.BALAS : path = Definicoes.BALAS_CINZA_IMG; break;
			case Definicoes.BUSSOLA : path = Definicoes.BUSSOLA_CINZA_IMG; break;
			case Definicoes.MAR : path = Definicoes.MAR_CINZA_IMG; break;
			case Definicoes.PIRATA_FRENTE : path = Definicoes.PIRATA_FRENTE_CINZA_IMG; break;
			case Definicoes.PIRATA_COSTAS : path = Definicoes.PIRATA_COSTAS_CINZA_IMG; break;
			case Definicoes.PIRATA_ESQUERDA : path = Definicoes.PIRATA_ESQUERDA_CINZA_IMG; break;
			case Definicoes.PIRATA_DIREITA : path = Definicoes.PIRATA_DIREITA_CINZA_IMG; break;
			case Definicoes.DINHEIRO : path = Definicoes.DINHEIRO_CINZA_IMG; break;
			case Definicoes.TESOURO : path = Definicoes.TESOURO_CINZA_IMG; break;
			case Definicoes.ANEL : path = Definicoes.ANEL_CINZA_IMG; break;
			case Definicoes.CORREIO : path = Definicoes.CORREIO_CINZA_IMG; break;
			
			case Definicoes.TUBARAO_FRENTE : path = Definicoes.TUBARAO_CINZA_IMG; break;
			case Definicoes.TUBARAO_ESQUERDA : path = Definicoes.TUBARAO_CINZA_ESQUERDA_IMG; break;
			case Definicoes.TUBARAO_DIREITA : path = Definicoes.TUBARAO_CINZA_DIREITA_IMG; break;
			case Definicoes.BALA_PARA_BAIXO : path = Definicoes.BALA_PARA_BAIXO_CINZA_IMG; break;
			case Definicoes.BALA_PARA_CIMA : path = Definicoes.BALA_PARA_CIMA_CINZA_IMG; break;
			case Definicoes.BALA_PARA_ESQUERDA : path = Definicoes.BALA_PARA_ESQUERDA_CINZA_IMG; break;
			case Definicoes.BALA_PARA_DIREITA : path = Definicoes.BALA_PARA_DIREITA_CINZA_IMG; break;
			
			case Definicoes.OCEANO : path = Definicoes.OCEANO_CINZA_IMG; break;
			
			case Definicoes.EXPLOSAO : path = Definicoes.EXPLOSAO_CINZA_IMG; break;
			
			default: path = Definicoes.VAZIO_CINZA_IMG;
			
			}
			
		} else
		{
		
			switch(valor)
			{
		
			case Definicoes.VAZIO : path = Definicoes.VAZIO_IMG; break; // ok
			case Definicoes.PEDRA : path = Definicoes.PEDRA_IMG; break; // ok
			case Definicoes.COQUEIRO_ESQUERDA : path = Definicoes.COQUEIRO_ESQUERDA_IMG; break; // ok
			case Definicoes.COQUEIRO_DIREITA : path = Definicoes.COQUEIRO_DIREITA_IMG; break; // ok
			case Definicoes.ARBUSTO : path = Definicoes.ARBUSTO_IMG; break; // ok
			case Definicoes.OBJETIVO : path = Definicoes.OBJETIVO_IMG; break;
			case Definicoes.ESQUELETO : path = Definicoes.ESQUELETO_FRENTE_IMG; break; // ok
			case Definicoes.ESQUELETO_COSTAS : path = Definicoes.ESQUELETO_COSTAS_IMG; break;
			case Definicoes.ESQUELETO_DIREITA : path = Definicoes.ESQUELETO_DIREITA_IMG; break;
			case Definicoes.ESQUELETO_ESQUERDA : path = Definicoes.ESQUELETO_ESQUERDA_IMG; break;
			case Definicoes.TRIFIDE : path = Definicoes.TRIFIDE_IMG; break;
			case Definicoes.BURACO : path = Definicoes.BURACO_IMG; break;
			case Definicoes.INFORMACAO : path = Definicoes.INFORMACAO_IMG; break;
			case Definicoes.BALAS : path = Definicoes.BALAS_IMG; break;
			case Definicoes.BUSSOLA : path = Definicoes.BUSSOLA_IMG; break;
			case Definicoes.MAR : path = Definicoes.MAR_IMG; break;
			case Definicoes.PIRATA_FRENTE : path = Definicoes.PIRATA_FRENTE_IMG; break;
			case Definicoes.PIRATA_COSTAS : path = Definicoes.PIRATA_COSTAS_IMG; break;
			case Definicoes.PIRATA_ESQUERDA : path = Definicoes.PIRATA_ESQUERDA_IMG; break;
			case Definicoes.PIRATA_DIREITA : path = Definicoes.PIRATA_DIREITA_IMG; break;
			case Definicoes.BALA_PARA_BAIXO : path = Definicoes.BALA_PARA_BAIXO_IMG; break;
			case Definicoes.BALA_PARA_CIMA : path = Definicoes.BALA_PARA_CIMA_IMG; break;
			case Definicoes.BALA_PARA_ESQUERDA : path = Definicoes.BALA_PARA_ESQUERDA_IMG; break;
			case Definicoes.BALA_PARA_DIREITA : path = Definicoes.BALA_PARA_DIREITA_IMG; break;
			case Definicoes.EXPLOSAO : path = Definicoes.EXPLOSAO_IMG; break;
			case Definicoes.PIRATA_FRENTE_MAR : path = Definicoes.PIRATA_MAR_FRENTE_IMG; break;
			case Definicoes.PIRATA_COSTAS_MAR : path = Definicoes.PIRATA_MAR_COSTAS_IMG; break;
			case Definicoes.PIRATA_ESQUERDA_MAR : path = Definicoes.PIRATA_MAR_ESQUERDA_IMG; break;
			case Definicoes.PIRATA_DIREITA_MAR : path = Definicoes.PIRATA_MAR_DIREITA_IMG; break;
			case Definicoes.OCEANO : path = Definicoes.OCEANO_IMG; break;
			case Definicoes.DINHEIRO : path = Definicoes.DINHEIRO_IMG; break;
			case Definicoes.TESOURO : path = Definicoes.TESOURO_IMG; break;
			case Definicoes.ANEL : path = Definicoes.ANEL_IMG; break;
			case Definicoes.TUBARAO_FRENTE : path = Definicoes.TUBARAO_IMG; break;
			case Definicoes.TUBARAO_ESQUERDA : path = Definicoes.TUBARAO_ESQUERDA_IMG; break;
			case Definicoes.TUBARAO_DIREITA : path = Definicoes.TUBARAO_DIREITA_IMG; break;
			case Definicoes.MAR_ESPECIAL : path = Definicoes.MAR_IMG; break;
			case Definicoes.CORREIO : path = Definicoes.CORREIO_IMG; break;
			default : path = Definicoes.VAZIO_IMG;

			}
		
		}
		
		return path;

	}
	
	public void setPic()
	{
		String path = getPicPath();
		
		pic = ImageLoader.loadLabel(path);
	    
	    cor = new Cor();
	    
	}
	
	public void updatePic()
	{
		String path = getPicPath();
		
	    File file = new File(path);
	    BufferedImage image = null;
	    
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {

			e.printStackTrace(); // &&&
			
			//System.out.println("Arquivo: " + path);
		}
		
		//URL url = Elemento.class.getResource(path);
		
		pic.setIcon(new ImageIcon(image));
		
		//pic.setIcon(new ImageIcon(url));
	}
	
	public void setPic(ImageIcon ii)
	{
		pic.setIcon(ii);
	}
	
	public void setBackground(char bg)
	{
	    switch(bg)
	    {
	    case Definicoes.CENTRO : pic.setBackground(cor.getBkCor()); break;
	    case Definicoes.MARGEM : pic.setBackground(cor.getMargemCor()); break;
	    case Definicoes.MARGEM2 : pic.setBackground(cor.getMargem2Cor()); break;
	    }
	}
	
	public JLabel getLabel()
	{
		return pic;
	}
}
