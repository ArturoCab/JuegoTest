package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import control.Keyboard;

public class Juego extends Canvas implements Runnable {
	private static final long serialVersionUID = 214687697799453950L;
	
	private static final int WIDTH=800;
	private static final int HEIGHT=600;
	
	private static volatile boolean enFuncionamiento=false;

	private static JFrame ventana;
	private static final String NOMBRE = "Juego";
	
	private static int aps=0;
	private static int fps=0;
	
	private static Thread thread;
	private static Keyboard keyboard;
	
	
	private Juego() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		ventana = new JFrame(NOMBRE);
		
		keyboard=new Keyboard();
		addKeyListener(keyboard);
		
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
	private void actualizar() {
		aps++;
		keyboard.update();
	}
	
	private void mostrar() {
		fps++;
	}

	@Override
	public void run() {
		final int NS_POR_SEGUNDO=1000000000;
		final byte APS_OBJETIVO=60;
		final double NS_POR_ACTUALIZACION=NS_POR_SEGUNDO/APS_OBJETIVO;
		
		long referenciaContador=System.nanoTime();
		long referenciaActualizacion=System.nanoTime();
		double tiempoTranscurrido;
		double delta=0;
		while(enFuncionamiento) {
			final long iniciobucle=System.nanoTime();
			tiempoTranscurrido=iniciobucle-referenciaActualizacion;
			referenciaActualizacion=iniciobucle;
			
			delta+=tiempoTranscurrido/NS_POR_ACTUALIZACION;
			while(delta>=1) {				
				actualizar();
				delta--;
			}
			mostrar();
			if(System.nanoTime()-referenciaContador>NS_POR_SEGUNDO) {
				ventana.setTitle(NOMBRE+" || APS: "+aps+" || FPS: "+fps);
				aps=0;
				fps=0;
				referenciaContador=System.nanoTime();
			}
		}
	}
}
