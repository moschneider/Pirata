package Dados;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Config.Definicoes;
import IO.WinIO;

public class Mundo {
	
	String path = "src\\Config\\Mundo.txt", str;
	
	Elemento e[][];
	
	WinIO winIO;
	
	int linha = 0, coluna = 0;

	public Mundo()
	{
		winIO = new WinIO();
		
		FileReader fr = null;
		BufferedReader br;
		
		e = new Elemento[Definicoes.MUNDO_MAX_X][Definicoes.MUNDO_MAX_Y];
		
		try {
			fr = new FileReader(path);
		} catch (FileNotFoundException e) {
			
			winIO.error("Impossivel abrir o arquivo " + path);
			
		}
		
		br = new BufferedReader(fr);
		
		try {
			while((str = br.readLine())!=null)
			{
				////System.out.println(str);
				
				if(str.charAt(0)!='_')
				{
					String str2 = "";
					
					for(int k=0;k<str.length();k++)
					{
						if(str.charAt(k)!='|')
						{
							str2 = str2 + str.charAt(k); 
						}
					}
				
					for(coluna=0;coluna<str2.length() && coluna<Definicoes.MUNDO_MAX_X;coluna++)
					{
						e[coluna][linha] = new Elemento(str2.charAt(coluna),false);
					}
				
					linha++;
				}
			}
		
		} catch (IOException e) {
			
			winIO.error("Impossivel ler arquivo. " + path);
		
		}
	
		// Fechamento do arquivo
	
		try {
			br.close();
		} catch (IOException e) {
		
			winIO.error("Arquivo nao pode ser fechado. " + path);
		
		}
	}
}
