package Dados;

import java.awt.Color;

import Config.Definicoes;

import IO.Window;

public class Morte extends Thread {

	Window win;
	
	public Morte(Window win)
	{
		this.win=win;
	}
	
	public void run()
	{
		win.getFrame().getContentPane().setBackground(Color.RED);
		
		try {
			sleep(Definicoes.MORTE_SCREEN_FLASH_DELAY);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		win.getFrame().getContentPane().setBackground(null);
	}
	
}
