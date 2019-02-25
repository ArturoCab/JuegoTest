package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Keyboard implements KeyListener{
	
	private final static int numberKeys=120;
	private final boolean[] keys=new boolean[numberKeys];

	public boolean arriba;
	public boolean abajo;
	public boolean izquierda;
	public boolean derecha;
	
	public void update() {
		arriba=keys[KeyEvent.VK_W];
		abajo=keys[KeyEvent.VK_S];
		derecha=keys[KeyEvent.VK_D];
		izquierda=keys[KeyEvent.VK_A];  
		
		System.out.println(arriba+" "+abajo+ " "+ derecha+ " "+izquierda);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()]=true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()]=false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
}
