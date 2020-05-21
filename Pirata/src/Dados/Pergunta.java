package Dados;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;


public class Pergunta {

	
	static final int CONQUISTADOR = 0;
	static final int EXPLORADOR = 1;
	static final int SOCIALIZADOR = 2;
	static final int ASSASSINO = 3;
	
	JLabel texto;
	
	JComboBox<String> identificacao;
	
	int classificacao;
	
	public Pergunta()
	{
		identificacao = new JComboBox<String>();
		
		identificacao.addItem("0 - n\u00e3o entendi/n\u00e3o me identifico");
		identificacao.addItem("1 - me identifico pouco");
		identificacao.addItem("2 - me identifico parcialmente");
		identificacao.addItem("3 - me identifico");
		identificacao.addItem("4 - me identifico totalmente");
		
		texto = new JLabel("");
		texto.setOpaque(true);
	}

	public JComboBox<String> getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(JComboBox<String> identificacao) {
		this.identificacao = identificacao;
	}
	
	public void setSelected(int index)
	{
		identificacao.setSelectedIndex(index);
	}

	public JLabel getTexto() {
		
		return texto;
	}

	public void setTexto(String txt) {
		
		texto.setText(txt);
	}

	public int getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}
	
	public void setCor(Color cor)
	{
		texto.setForeground(cor);
		identificacao.setForeground(cor);
	}
	
}
