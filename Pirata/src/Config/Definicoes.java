package Config;

public class Definicoes {
	
	// Versao
	
	public final static String VERSAO = "1.02";
	
	// Cheat mode
	
	public final static boolean DISCOUNT_SHOTS = true;
	public final static boolean DISCOUNT_LIVES = true;
	
	// Carateristicas iniciais
	
	public final static int START_LIVES = 5;
	public final static int START_SHOTS = 8;
	
	// Dimensoes do mundo
	
	public final static int MUNDO_MAX_X = 50;
	public final static int MUNDO_MAX_Y = 50;
	
	// Fundos para visualizacao
	
	public final static char CENTRO = 'c';
	public final static char MARGEM = 'n';
	public final static char MARGEM2 = '2';
	
	// Direcoes do jogador
	
	public final static int PARA_CIMA = 1;
	public final static int PARA_BAIXO = 2;
	public final static int PARA_ESQUERDA = 3;
	public final static int PARA_DIREITA = 4;
	
	// Erro indefinido
	
	public final static int ERROR_GERAL = 123456;	
	
	// Objetivos
	
	public final static int NUMERO_OBJETIVOS = 3;
	
	public final static int OBJETIVO_PONTOS = 1;
	public final static int OBJETIVO_RECARGAS = 2;
	public final static int OBJETIVO_BAUS = 3;
	
	public final static int OBJETIVO_PONTOS_NECESSARIOS = 1000;
	public final static int OBJETIVO_RECARGAS_NECESSARIAS = 5;
	public final static int OBJETIVO_BAUS_NECESSARIOS = 5;
	
	public final static String OBJETIVO_PONTOS_TEXT = "Alcance " + OBJETIVO_PONTOS_NECESSARIOS + " pontos!";
	public final static String OBJETIVO_RECARGAS_TEXT = "Recarrague sua arma " + OBJETIVO_RECARGAS_NECESSARIAS + " vezes!";
	public final static String OBJETIVO_BAUS_TEXT = "Abre " + OBJETIVO_BAUS_NECESSARIOS + " ba\u00fas!";
	
	// Informacoes
	
	public final static String INFORMACAO_PONTOS_PRINCIPAL = "Apenas matar esqueletos para conseguir pontos pode levar muito tempo.";
	public final static String INFORMACAO_PONTOS_SIDE = "Al\u00e9m de tesouros, h\u00e0 outros itens preciosos na ilha.";
	
	public final static String INFORMACAO_RECARGAS_PRINCIPAL = "Objetivo \u00e9 apenas recarga. Atire se quiser.";
	public final static String INFORMACAO_RECARGAS_SIDE = "H\u00e1 um an\u00e9l precioso do holand\u00eas voador na parte de baixo da ilha.";
	
	public final static String INFORMACAO_BAUS_PRINCIPAL = "Os ba\u00fas se encontram mais na parte inferior direita da ilha";
	public final static String INFORMACAO_BAUS_SIDE = "Falam lendas antigas que h\u00e1 um tesouro perdido no lago.";
	
	// Quadrante dos esqueletos
	
	public final static int QUAD_ESQUELETOS_X = 4;
	public final static int QUAD_ESQUELETOS_Y = 1;
	
	// Pontos
	
	public final static int PONTOS_ELEMENTO_ESCONDIDO 	= 500;
	public final static int PONTOS_BAU					= 250;
	public final static int PONTOS_ESQUELETO			= 150;
	public final static int PONTOS_INFORMACAO			= 50;
	public final static int PONTOS_PICKUP				= 1;
	public final static int PONTOS_TESOURO				= 400;
	public final static int PONTOS_ANEL					= 300;
	public final static int PONTOS_DINHEIRO				= 270;
	
	// Campos disponiveis 
	
	public final static char VAZIO 						= '.';
	public final static char PEDRA 						= '#';
	public final static char COQUEIRO_ESQUERDA 			= '@';
	public final static char COQUEIRO_DIREITA			= 'E';
	public final static char ARBUSTO 					= '%';
	public final static char OBJETIVO 					= 'o';
	public final static char TRIFIDE	 				= '&';
	
	public final static char ESQUELETO 					= '$';
	public final static char ESQUELETO_ESQUERDA			= '4';
	public final static char ESQUELETO_DIREITA			= '5';
	public final static char ESQUELETO_COSTAS			= '6';
	
	public final static char BURACO 					= 'v';
	public final static char INFORMACAO 				= 'i';
	public final static char BALAS 						= 'b';
	public final static char BUSSOLA 					= '*';
	public final static char MAR           				= 'm';
	
	public final static char MAR_ESPECIAL				= 'F';
	
	public final static char PIRATA_FRENTE				= 'p';
	public final static char PIRATA_COSTAS 				= '1';
	public final static char PIRATA_ESQUERDA 			= '2';
	public final static char PIRATA_DIREITA 			= '3';
	
	public final static char PIRATA_FRENTE_MAR			= '7';
	public final static char PIRATA_COSTAS_MAR 			= '8';
	public final static char PIRATA_ESQUERDA_MAR 		= '9';
	public final static char PIRATA_DIREITA_MAR 		= '0';
	
	public final static char DINHEIRO					= 'D';
	public final static char TESOURO					= 'T';
	public final static char ANEL						= 'A';
	
	public final static char CORREIO					= 'X';
	
	public final static char BALA_PARA_CIMA 			= '?';
	public final static char BALA_PARA_BAIXO 			= '!';
	public final static char BALA_PARA_ESQUERDA 		= '(';
	public final static char BALA_PARA_DIREITA 			= '=';
	
	public final static char EXPLOSAO					= 'G';
	
	public final static char TUBARAO_FRENTE				= 'U';
	public final static char TUBARAO_ESQUERDA			= 'I';
	public final static char TUBARAO_DIREITA			= 'O';
	
	public final static char OCEANO						= 'Q';
	
	// Dimensoes da vista
	
	public final static int VIEW_MAX_X = 12;
	public final static int VIEW_MAX_Y = 12;
	
	// Dimensoes de quadrantes
	
	public final static int QUADRANTE_MAX_X = 5;
	public final static int QUADRANTE_MAX_Y = 5;
	
	// Quadrante inicial do jogo
	
	public final static int QUADRANTE_INICIAL_X = 0;
	public final static int QUADRANTE_INICIAL_Y = 0;
	
	// Npc
	
	public final static int VIDA_INICIAL_NPCS = 5;
	public final static int TIROS_INICIAIS_NPCS = 30;
	
	public final static char INDEFINIDA = ':';
	
	// Sons
	
	public final static String ATAQUE_MP3 = "src/FX/Ataque.mp3";
	public final static String INICIO_MP3 = "src/FX/Welcome.mp3";
	public final static String COLETAR_MP3 = "src/FX/Coletar.mp3";
	public final static String COLISAO_MP3 = "src/FX/Colisao.mp3";
	public final static String EXPLOSAO_MP3 = "src/FX/Explosao.mp3";
	public final static String GOLPE_MP3 = "src/FX/Golpe.mp3";
	public final static String INFORMACAO_MP3 = "src/FX/Informacao.mp3";
	public final static String MORTE_MP3 = "src/FX/Morte.mp3";
	public final static String RECARGA_MP3 = "src/FX/Recarga.mp3";
	public final static String TIRO_CURTO_MP3 = "src/FX/TiroCurto.mp3";
	public final static String TIRO_LONGO_MP3 = "src/FX/TiroLongo.mp3";
	public final static String CAINDO_MP3 = "src/FX/Caindo.mp3";
	public final static String NOO_MP3 = "src/FX/Noo.mp3";
	public final static String OCEANO_MP3 = "src/FX/Oceano.mp3";
	public final static String RISADA_MP3 = "src/FX/Risada.mp3";
	public final static String CASH_MP3 = "src/FX/Cash.mp3";
	public final static String OW_MP3 = "src/FX/Ow.mp3";
	public final static String EXPLOSION_MP3 = "src/FX/Explosao.mp3";
	public final static String CLING_MP3 = "src/FX/Cling.mp3";
	public final static String MONSTER_MP3 =  "src/FX/Monster.mp3";
	public final static String SPLASH_MP3 = "src/FX/Splash.mp3";
	public final static String FIM_MP3 = "src/FX/Fim.mp3";
	public final static String APPLAUSE_MP3 = "src/FX/Applause.mp3";
	public final static String GONG_MP3 = "src/FX/Gong.mp3";
	
	// Imagens
	
	public final static String VAZIO_IMG = "src/Imagens/Blank.png"; 
	public final static String PEDRA_IMG = "src/Imagens/Pedra.png";
	public final static String COQUEIRO_ESQUERDA_IMG= "src/Imagens/CoqueiroE.png";
	public final static String COQUEIRO_DIREITA_IMG= "src/Imagens/CoqueiroD.png"; 
	public final static String ARBUSTO_IMG = "src/Imagens/Arbusto.png"; 
	public final static String OBJETIVO_IMG = "src/Imagens/Bau.png"; 
	public final static String ESQUELETO_FRENTE_IMG = "src/Imagens/EsqueletoFrente.png"; 
	public final static String ESQUELETO_COSTAS_IMG = "src/Imagens/EsqueletoCostas.png";
	public final static String ESQUELETO_DIREITA_IMG = "src/Imagens/EsqueletoPerfilD.png";
	public final static String ESQUELETO_ESQUERDA_IMG = "src/Imagens/EsqueletoPerfilE.png";
	public final static String TRIFIDE_IMG = "src/Imagens/PlantaCarnivora.png"; 
	public final static String BURACO_IMG = "src/Imagens/Buraco.png"; 
	public final static String INFORMACAO_IMG = "src/Imagens/Informacao.png"; 
	public final static String BALAS_IMG = "src/Imagens/Balas.png"; 
	public final static String BUSSOLA_IMG = "src/Imagens/Bussola.png";
	public final static String OCEANO_IMG = "src/Imagens/Oceano.png";
	public final static String MAR_IMG = "src/Imagens/Mar.png"; 
	
	public final static String MAR_ESPECIAL_IMG = "src/Imagens/Bussola.png";
	
	public final static String PIRATA_FRENTE_IMG = "src/Imagens/PirataFrente.png"; 
	public final static String PIRATA_COSTAS_IMG = "src/Imagens/PirataCostas.png";
	public final static String PIRATA_ESQUERDA_IMG = "src/Imagens/PirataPerfilE.png";
	public final static String PIRATA_DIREITA_IMG = "src/Imagens/PirataPerfilD.png";
	
	public final static String INFO_TIROS_IMG = "src/Imagens/InfoTiros.png"; 
	public final static String INFO_VIDAS_IMG = "src/Imagens/InfoVidas.png";
	public final static String INFO_OBJETIVO_IMG = "src/Imagens/InfoObjetivo.png";
	public final static String INFO_PONTOS_IMG  = "src/Imagens/InfoPontos.png";
	
	public final static String BALA_PARA_CIMA_IMG = "src/Imagens/MoveBalaC.png";
	public final static String BALA_PARA_BAIXO_IMG = "src/Imagens/MoveBalaB.png";
	public final static String BALA_PARA_ESQUERDA_IMG = "src/Imagens/MoveBalaE.png";
	public final static String BALA_PARA_DIREITA_IMG = "src/Imagens/MoveBalaD.png";
	
	public final static String TESOURO_IMG = "src/Imagens/TesouroAreia.png";
	public final static String DINHEIRO_IMG = "src/Imagens/DinheiroAreia.png";
	public final static String ANEL_IMG = "src/Imagens/AnelAreia.png";
	
	public final static String CORREIO_IMG = "src/Imagens/CorreioAreia.png"; 
	
	public final static String EXPLOSAO_IMG = "src/Imagens/Explosao.png";
	
	public final static String PIRATA_SPLASH = "src/Imagens/PirataSplash.png";
	public final static String EXPLICACAO = "src/Imagens/Explicacoes.png";
	public final static String AGRADECIMENTOS = "src/Imagens/Agradecimentos.jpg";
	
	// Imagens no mar/lago
	
	public final static String PIRATA_MAR_FRENTE_IMG = "src/Imagens/PirataMarF.png";
	public final static String PIRATA_MAR_COSTAS_IMG = "src/Imagens/PirataMarC.png";
	public final static String PIRATA_MAR_ESQUERDA_IMG = "src/Imagens/PirataMarE.png";
	public final static String PIRATA_MAR_DIREITA_IMG = "src/Imagens/PirataMarD.png";
	public final static String TUBARAO_IMG = "src/Imagens/Tubarao.png";
	public final static String TUBARAO_ESQUERDA_IMG = "src/Imagens/TubaraoE.png";
	public final static String TUBARAO_DIREITA_IMG = "src/Imagens/TubaraoD.png";

	// Imagens Cinzas
	
	public final static String VAZIO_CINZA_IMG = "src/ImagensCinza/Blank.png"; 
	public final static String PEDRA_CINZA_IMG = "src/ImagensCinza/Pedra.png";
	public final static String COQUEIRO_ESQUERDA_CINZA_IMG = "src/ImagensCinza/CoqueiroE.png";
	public final static String COQUEIRO_DIREITA_CINZA_IMG = "src/ImagensCinza/CoqueiroD.png"; 
	public final static String ARBUSTO_CINZA_IMG = "src/ImagensCinza/Arbusto.png"; 
	public final static String OBJETIVO_CINZA_IMG = "src/ImagensCinza/Bau.png"; 
	
	public final static String ESQUELETO_FRENTE_CINZA_IMG = "src/ImagensCinza/EsqueletoFrente.png";
	public final static String ESQUELETO_COSTAS_CINZA_IMG = "src/ImagensCinza/EsqueletoCostas.png";
	public final static String ESQUELETO_DIREITA_CINZA_IMG = "src/ImagensCinza/EsqueletoPerfilD.png";
	public final static String ESQUELETO_ESQUERDA_CINZA_IMG = "src/ImagensCinza/EsqueletoPerfilE.png";
	
	public final static String TRIFIDE_CINZA_IMG = "src/ImagensCinza/PlantaCarnivora-01.png";
	
	public final static String BURACO_CINZA_IMG = "src/ImagensCinza/Buraco.png"; 
	public final static String INFORMACAO_CINZA_IMG = "src/ImagensCinza/Informacao.png"; 
	public final static String BALAS_CINZA_IMG = "src/ImagensCinza/Balas.png"; 
	public final static String BUSSOLA_CINZA_IMG = "src/ImagensCinza/Bussola.png"; 
	public final static String MAR_CINZA_IMG = "src/ImagensCinza/Mar.png"; 
	
	public final static String PIRATA_FRENTE_CINZA_IMG = "src/ImagensCinza/PirataFrente.png"; 
	public final static String PIRATA_COSTAS_CINZA_IMG = "src/ImagensCinza/PirataCostas.png";
	public final static String PIRATA_ESQUERDA_CINZA_IMG = "src/ImagensCinza/PirataPerfilE.png";
	public final static String PIRATA_DIREITA_CINZA_IMG = "src/ImagensCinza/PirataPerfilD.png";
	
	public final static String TESOURO_CINZA_IMG = "src/ImagensCinza/TesouroPB.png";
	public final static String DINHEIRO_CINZA_IMG = "src/ImagensCinza/DinheiroPB.png";
	public final static String ANEL_CINZA_IMG = "src/ImagensCinza/AnelPB.png";
	
	public final static String CORREIO_CINZA_IMG = "src/ImagensCinza/CorreioPB.png";
	
	public final static String TUBARAO_CINZA_IMG = "src/ImagensCinza/Tubarao.png";
	public final static String TUBARAO_CINZA_ESQUERDA_IMG = "src/ImagensCinza/TubaraoE.png";
	public final static String TUBARAO_CINZA_DIREITA_IMG = "src/ImagensCinza/TubaraoD.png";
	public final static String BALA_PARA_BAIXO_CINZA_IMG = "src/ImagensCinza/MoveBalaB.png";
	public final static String BALA_PARA_CIMA_CINZA_IMG = "src/ImagensCinza/MoveBalaC.png";
	public final static String BALA_PARA_ESQUERDA_CINZA_IMG = "src/ImagensCinza/MoveBalaE.png";
	public final static String BALA_PARA_DIREITA_CINZA_IMG = "src/ImagensCinza/MoveBalaD.png";

	public final static String OCEANO_CINZA_IMG = "src/ImagensCinza/CorreioPB.png";

	public final static String EXPLOSAO_CINZA_IMG = "src/ImagensCinza/CorreioPB.png";
	
	// Painel ao lado
	
	public final static String INFO_TIROS_CINZA_IMG = "src/ImagensCinza/InfoTiros.png"; 
	public final static String INFO_VIDAS_CINZA_IMG = "src/ImagensCinza/InfoVidas.png";
	public final static String INFO_OBJETIVO_CINZA_IMG = "src/ImagensCinza/InfoObjetivo.png";
	public final static String INFO_PONTOS_CINZA_IMG  = "src/ImagensCinza/InfoPontos.png";
	
	public final static String SETA_PARA_CIMA_IMG = "src/Imagens/SetaCima.png";
	public final static String SETA_PARA_BAIXO_IMG = "src/Imagens/SetaBaixo.png";
	public final static String SETA_PARA_ESQUERDA_IMG = "src/Imagens/SetaEsquerda.png";
	public final static String SETA_PARA_DIREITA_IMG = "src/Imagens/SetaDireita.png";
	
	public final static String PISTOLA_IMG = "src/Imagens/Pistola.png";
	
	public final static String VIDA_IMG = "src/Imagens/Vida.png";
	
	public final static String BALA_IMG = "src/Imagens/BalaCarga.png";
	
	// Npcs - Avaliação de possibilidades de movimento
	
	public final static int MAX_AVALIACAO_X = 3;
	public final static int MAX_AVALIACAO_Y = 3;
	
	public final static int SEM_MELHOR_SOLUCAO = -9;
	
	// Delays
	
	public final static int MORTE_SCREEN_FLASH_DELAY = 80;
	public final static int ESQUELETO_DELAY = 800;
	public final static int TRIFIDE_DELAY = 300;
	public final static int TUBARAO_DELAY = 1000;
	
	// Continuacao
	
	public final static int CONTINUES = 2;
	
	// Barriers
	
	public final static char[] barrierValues = {Definicoes.EXPLOSAO,Definicoes.PEDRA,Definicoes.COQUEIRO_ESQUERDA,Definicoes.COQUEIRO_DIREITA,Definicoes.ARBUSTO};
	
	// Dangers
	
	public final static char[] dangerValues = {Definicoes.OCEANO,Definicoes.ESQUELETO,Definicoes.TRIFIDE,Definicoes.BURACO,Definicoes.ESQUELETO_COSTAS,Definicoes.ESQUELETO_ESQUERDA,Definicoes.ESQUELETO_DIREITA,Definicoes.TUBARAO_DIREITA,Definicoes.TUBARAO_ESQUERDA,Definicoes.TUBARAO_FRENTE};
	
	// Player
	
	public final static char[] playerValues = {Definicoes.PIRATA_DIREITA,Definicoes.PIRATA_COSTAS,Definicoes.PIRATA_FRENTE,Definicoes.PIRATA_ESQUERDA};
	
	// Equeleto
	
	public final static char[] esqueletoValues = {Definicoes.ESQUELETO,Definicoes.ESQUELETO_COSTAS,Definicoes.ESQUELETO_DIREITA,Definicoes.ESQUELETO_ESQUERDA};
	
	// Tubarao
	
	public final static char[] tubaraoValues = {Definicoes.TUBARAO_DIREITA,Definicoes.TUBARAO_ESQUERDA,Definicoes.TUBARAO_FRENTE};
	
	// Player
	
	public final static char[] playerMarValues = {Definicoes.PIRATA_DIREITA_MAR,Definicoes.PIRATA_COSTAS_MAR,Definicoes.PIRATA_FRENTE_MAR,Definicoes.PIRATA_ESQUERDA_MAR};
	
	// Tiro
	
	public final static char[] tiroValues = {Definicoes.BALA_PARA_BAIXO,Definicoes.BALA_PARA_CIMA,Definicoes.BALA_PARA_DIREITA,Definicoes.BALA_PARA_ESQUERDA};	
	
	// Checar por valores explícitos de perigo
	
	public static boolean isTiro(char valor)
	{		
		for(int i=0;i<Definicoes.tiroValues.length;i++)
			if(valor==Definicoes.tiroValues[i])
				return true; 
			
		return false;
	}
	
	// Checar por valores explícitos de perigo
	
	public static boolean isDanger(char valor)
	{		
		for(int i=0;i<Definicoes.dangerValues.length;i++)
			if(valor==Definicoes.dangerValues[i])
				return true; 
		
		return false;
	}
	
	// Checar por valores explícitos de barreira
	
	public static boolean isBarrier(char valor)
	{		
		for(int i=0;i<Definicoes.barrierValues.length;i++)
			if(valor==Definicoes.barrierValues[i])
				return true; 
		
		return false;
	}
	
	// Checar por valores explícitos de jogador
	
	public static boolean isPlayer(char valor)
	{		
		for(int i=0;i<Definicoes.playerValues.length;i++)
			if(valor==Definicoes.playerValues[i])
				return true; 
		
		return false;
	}
	
	// Checar por valores explícitos de jogador
	
	public static boolean isPlayerMar(char valor)
	{		
		for(int i=0;i<Definicoes.playerMarValues.length;i++)
			if(valor==Definicoes.playerMarValues[i])
				return true; 
		
		return false;
	}
	
	// Checar por valores explícitos de esqueleto
	
	public static boolean isEsqueleto(char valor)
	{		
		for(int i=0;i<Definicoes.esqueletoValues.length;i++)
			if(valor==Definicoes.esqueletoValues[i])
				return true; 
			
		return false;
	}
	
	// Checar por valores explícitos de tubarão
	
	public static boolean isTubarao(char valor)
	{		
		for(int i=0;i<Definicoes.tubaraoValues.length;i++)
			if(valor==Definicoes.tubaraoValues[i])
				return true; 
			
		return false;
	}
	
	// Checar se eh planta carnivora
	
	public static boolean isTrifide(char valor)
	{
		if(valor==Definicoes.TRIFIDE) return true;
		
		return false;
	}
	
}
