package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Juego extends Canvas implements Runnable {
	private static final long serialVersionUID = 214687697799453950L;
	
	private static final int WIDTH=800;
	private static final int HEIGHT=600;
	
	private static volatile boolean enFuncionamiento=false;

	private static JFrame ventana;
	private static final String NOMBRE = "Juego";
	
	private static Thread thread;
	
	
	private Juego() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		ventana = new JFrame(NOMBRE);
		
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setLayout(new BorderLayout());
		ventana.add(this,BorderLayout.CENTER);
		ventana.pack();
		
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	
	public static void main(String[] args) {
		Juego juego=new Juego();
		juego.init();
	}
	
	private synchronized void init() {
		
		enFuncionamiento=true;
		thread = new Thread(this,"graphics");
		thread.start();
	}
	
	private synchronized void stop() {
		enFuncionamiento=false;
		
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(enFuncionamiento) {
			
		}
	}
}
