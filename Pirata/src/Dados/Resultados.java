package Dados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Config.Definicoes;
import IO.ImageLoader;
import IO.WinIO;
import IO.Window;
import Principal.Principal;

public class Resultados {
	
	File filePath;
	
	WinIO winIO;
	
	// Resultados do questionario
	
	String tipoJogador;
	
	int codigoTipo;
	
	String descricaoTipo;
	
	int certezaTipo;
	int percentagemConquistador, percentagemExplorador, percentagemSocializador, percentagemAssassino;
	int percentagemIdentificacao;
	
	// Resultados do jogo

	int mainQuests = 0;
	int sideQuests = 0;
	int pontos = 0;
	
	boolean conseguiuDinheiro = false;
	boolean conseguiuAnel = false;
	boolean conseguiuTesouro = false;
	boolean conseguiuTesouroPerdido = false;
	
	int esqueletosMortos = 0;
	int plantasAtacadas = 0;
	int tirosDisparados = 0;
	
	boolean entrouNaAgua = false;
	boolean atirouNaAgua = false;
	
	int recarregamentoArma = 0;
	
	boolean entrouNaFaseEsqueletos = false;
	
	int esqueletosMortosNaFaseDosEsqueletos = 0;

	int mortePorEsqueletos = 0;
	int mortePorPlantas = 0;
	int mortePorOceano = 0;
	int mortePorTubarao = 0;
	int mortePorBuraco = 0;

	int machucados = 0;
	
	int passagensTotais = 0;
	
	int passagensPrimeiroObjetivo = 0;
	int passagensSegundoObjetivo = 0;
	int passagensTerceiroObjetivo = 0;

	Timestamp inicioDeJogo;
	Timestamp finalDeJogo;
	Timestamp finalObjetivo1;
	Timestamp finalObjetivo2;

	boolean entrouCaixaDeCorreio = false;
	boolean enviouMensagem = false;
	
	int bausAbertos = 0;
	int informacoesLidas = 0;
	
	boolean copiouImagemNoClipboard = false;
	
	Window agradecimento;
	
	JButton encerrar, novoTeste;
	
	JLabel imageLabel;
	
	JPanel buttonPanel;
	
	RelatorioFinal rf;
	
	int continues = 0;
	
	public void setRelatorioFinal(RelatorioFinal rf)
	{
		this.rf=rf;
	}
	
	public String simNao(boolean input)
	{
		
		if(input)
			return("... SIM ...");
		
		return("<<< N\u00c3O >>>");
	}
	
	public void adicioneDescricao()
	{
		switch(codigoTipo)
		{
		case Pergunta.CONQUISTADOR:
			descricaoTipo = "Conquistadores s\u00e3o movidos por objetivos no jogo para se destacar dos outros jogadores," + System.getProperty( "line.separator" ) + 
			                "geralmente alguma forma de acumular pontos, sejam eles pontos de experi\u00eancia," + System.getProperty( "line.separator" ) +
			                "n\u00edveis ou mesmo cupons de desconto. Eles ser\u00e3o atra\u00eddos por um invent\u00e1rio de badges" + System.getProperty( "line.separator" ) + 
			                "ou trof\u00e9us, por exemplo.";
			break;
		case Pergunta.EXPLORADOR:
			descricaoTipo = "Exploradores s\u00e3o conduzidos pela vontade de descobrir o m\u00e1ximo poss\u00edvel sobre o jogo, incluindo" + System.getProperty( "line.separator" ) + 
							"desde o mapeamento da \u00e1rea geogr\u00e1fica at\u00e9 a compreens\u00e3o da mecânica. Eles s\u00e3o curiosos" + System.getProperty( "line.separator" ) +
							"e v\u00e3o querer entender o porqu\u00ea e como cumprir um desafio proposto." + System.getProperty( "line.separator" );
			break;
		case Pergunta.SOCIALIZADOR:
			descricaoTipo = "Socializadores est\u00e3o interessados em pessoas, e no que elas t\u00eam a dizer. O jogo \u00e9 apenas" + System.getProperty( "line.separator" ) + 
							"um pano de fundo  para socializarem com outros jogadores. Os socializadores s\u00e3o os maiores" + System.getProperty( "line.separator" ) +
							"comentadores de status e os que motivam os desafios em time." + System.getProperty( "line.separator" );
			break;
		case Pergunta.ASSASSINO:
			descricaoTipo = "Talvez a esp\u00e9cime mais competitiva. Os Assassinos s\u00e3o movidos pela vontade de impor-se e" + System.getProperty( "line.separator" ) + 
							"ficam satisfeitos em proporcionar momentos de agonia e ansiedade nos outros jogadores." + System.getProperty( "line.separator" ) +
							"Para que eles ganhem, algu\u00e9m precisa perder (e muitos \"algu\u00e9ns\"). S\u00e3o presen\u00e7as frequentes" + System.getProperty( "line.separator" ) + 
							"no top do leaderboard." + System.getProperty( "line.separator" );
		}
	}

	public void esrevaArquivo()
	{
			
		FileWriter fw = null;
		BufferedWriter bw;
		
		JFileChooser fc = new JFileChooser();
		
		int opcao = fc.showSaveDialog(null);
		
		if(opcao==JFileChooser.APPROVE_OPTION)
		{
		
		filePath=fc.getSelectedFile();
	
		try {
			fw = new FileWriter(filePath,true);
		}	 catch (IOException e) {
			// TODO Auto-generated catch block
			winIO.error("Impossivel abrir o arquivo " + filePath);
			e.printStackTrace();
		}
	
		bw = new BufferedWriter(fw);
	
		try {
			
			bw.write("RELAT\u00d3RIO TESTE DO PIRATA " + System.getProperty( "line.separator" ));
			bw.write("------------------------- " + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
			
			bw.write("A. RESULTADOS DO QUESTION\u00e1RIO" + System.getProperty( "line.separator" ));
			bw.write("-----------------------------" + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
						
			bw.write("Tipo de Jogador:     " + tipoJogador + System.getProperty( "line.separator" ) + System.getProperty( "line.separator" ));
			
			adicioneDescricao();
			
			bw.write("Descri\u00e7\u00e3o do tipo:   " + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ) + descricaoTipo + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
			
			bw.write("Certeza do Tipo (%): " + certezaTipo + System.getProperty( "line.separator" ));
			bw.write("Conquistador (%):    " + percentagemConquistador + System.getProperty( "line.separator" ));
			bw.write("Explorador (%):      " + percentagemExplorador + System.getProperty( "line.separator" ));
			bw.write("Socializador (%):    " + percentagemSocializador + System.getProperty( "line.separator" ));
			bw.write("Assassino (%):       " + percentagemAssassino + System.getProperty( "line.separator" ));
			bw.write("Identifica\u00e7\u00e3o (%):   " + percentagemIdentificacao + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
			
			// Resultados do jogo
			

			bw.write("B. COMPORTAMENTO NO JOGO " + System.getProperty( "line.separator" ));
			bw.write("------------------------" + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));

			bw.write("1. DESEMPENHO GERAL     " + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
			
			bw.write("Quests principais terminados: " + mainQuests + System.getProperty( "line.separator" ));
			bw.write("Quests opcionais terminados:  " + sideQuests + System.getProperty( "line.separator" ));
			bw.write("Pontos:                       " + pontos + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
			
			bw.write("2. DETALHAMENTO QUESTS OPCIONAIS     " + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
			
			bw.write("Dinheiro recuperado:        " + simNao(conseguiuDinheiro) + System.getProperty( "line.separator" ));
			bw.write("An\u00e9l recuperado:            " + simNao(conseguiuAnel) + System.getProperty( "line.separator" ));
			bw.write("Tesouro recuperado:         " + simNao(conseguiuTesouro) + System.getProperty( "line.separator" ));
			bw.write("Tesouro perdido recuperado: " + simNao(conseguiuTesouroPerdido) + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" )); 
			
			bw.write("3. AGRESSIVIDADE   "  + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
			
			bw.write("Esqueletos mortos:                        " + esqueletosMortos + System.getProperty( "line.separator" ));
			bw.write("Plantas ou tubarões atacados:             " + plantasAtacadas + System.getProperty( "line.separator" )); 
			bw.write("Tiros disparados:                         " + tirosDisparados + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
			bw.write("Recarregamentos de arma:                  " + recarregamentoArma + System.getProperty( "line.separator" ));
			bw.write("Entrou na fase dos equeletos:             " + simNao(entrouNaFaseEsqueletos) + System.getProperty( "line.separator" )); 
			bw.write("Esqueletos mortos na fase dos esqueletos: " + esqueletosMortosNaFaseDosEsqueletos + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" )); 
			
			bw.write("4. COMPORTAMENTO NA \u00c1GUA " + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
			
			bw.write("Entrou na \u00e1gua: " + simNao(entrouNaAgua) + System.getProperty( "line.separator" )); 
			bw.write("Atirou na \u00e1gua: " + simNao(atirouNaAgua) + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" )); 
			
			bw.write("5. EVENTOS ADVERSOS " + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
			
			bw.write("Mortes por esqueletos: " + mortePorEsqueletos + System.getProperty( "line.separator" )); 
			bw.write("Mortes por plantas:    " + mortePorPlantas + System.getProperty( "line.separator" ));
			bw.write("Mortes por oceano:     " + mortePorOceano + System.getProperty( "line.separator" ));
			bw.write("Mortes por tubar\u00e3o:    " + mortePorTubarao + System.getProperty( "line.separator" ));
			bw.write("Mortes por buraco:     " + mortePorBuraco + System.getProperty( "line.separator" ));
			bw.write("Machucados:            " + machucados + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
			
			bw.write("6. EXPLORA\u00c7\u00c3O " + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
			
			bw.write("Passagens totais:               " + passagensTotais + System.getProperty( "line.separator" ));
			
			bw.write("Passagens no primeiro objetivo: " + passagensPrimeiroObjetivo + System.getProperty( "line.separator" ));
			bw.write("Passagens no segundo objetivo:  " + passagensSegundoObjetivo + System.getProperty( "line.separator" ));
			bw.write("Passagens no terceiro objetivo: " + passagensTerceiroObjetivo + System.getProperty( "line.separator" ));
			
			bw.write("Ba\u00fas abertos:                   " + bausAbertos + System.getProperty( "line.separator" )); 
			bw.write("Informa\u00e7ões lidas:              " + informacoesLidas + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
			
			bw.write("7. SOCIALIZA\u00c7\u00c3O " + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));

			bw.write("Entrou na caixa de correio: " +  simNao(entrouCaixaDeCorreio) + System.getProperty( "line.separator" ));
			bw.write("Enviou mensagem na caixa:   " + simNao(enviouMensagem) + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
			
			bw.write("8. EXPOSI\u00c7\u00c3O DE CONQUISTAS " + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
			
			bw.write("Salvou imagem de resultados: " + simNao(copiouImagemNoClipboard) + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));
			
			bw.write("9. DURA\u00c7\u00c3O DO \u00daLTIMO JOGO" + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));

			bw.write("In\u00edcio do jogo:             " +  inicioDeJogo + System.getProperty( "line.separator" ));
			bw.write("Final do primeiro objetivo: " +  finalObjetivo1 + System.getProperty( "line.separator" ));
			bw.write("Final do segundo objetivo:  " +  finalObjetivo2 + System.getProperty( "line.separator" ));
			bw.write("Fim do jogo:                " +  finalDeJogo + System.getProperty( "line.separator" ) + System.getProperty( "line.separator" ));
			
			bw.write("10. CONTINUES" + System.getProperty( "line.separator" )+ System.getProperty( "line.separator" ));

			bw.write("Continues utilizados: " +  continues + System.getProperty( "line.separator" ));
			
		} catch (IOException e) {
		
			winIO.error("Impossivel ler arquivo. " + filePath);
	
		}

		// Fechamento do arquivo

		try {
			bw.close();
		} catch (IOException e) {
	
			winIO.error("Arquivo nao pode ser fechado. " + filePath);
	
		}
		
			agradecimento = new Window("V" + Definicoes.VERSAO,true);
			
			encerrar = new JButton("Encerrar");
			novoTeste = new JButton("Novo Teste");
			
			buttonPanel = new JPanel();
			
			buttonPanel.add(novoTeste);
			buttonPanel.add(encerrar);
			
			novoTeste.setFocusable(true);
			novoTeste.requestFocus();
			
			agradecimento.getFrame().getRootPane().setDefaultButton(novoTeste);
			novoTeste.requestFocus();
			
			imageLabel = new JLabel(ImageLoader.loadIcon(Definicoes.AGRADECIMENTOS));
			
			agradecimento.addCenterLn(imageLabel);
			
			agradecimento.addCenterLn(buttonPanel);
			
			agradecimento.pack();
			
			agradecimento.centerPos();
			
			agradecimento.show();
			
			novoTeste.addActionListener(new NovoTesteListener());
			encerrar.addActionListener(new EncerrarListener());
		
		} else
			rf.resultadosWin.show();
		
	}
	
	class NovoTesteListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			agradecimento.hide();
			
			new Principal();
		}
		
	}
	
	class EncerrarListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			agradecimento.hide();
			
			System.exit(0);
			
		}
		
	}

}
