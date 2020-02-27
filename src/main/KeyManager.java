package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	private boolean[] keys, justPressed, cantPress;
	public boolean down, left, right, up, enter, escape, backspace, z;
	
	public KeyManager(){
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
	}
	
	public void tick(){
	for(int i = 0; i<keys.length;i++){
		if(cantPress[i] && !keys[i]){
			cantPress[i] = false;
		}else if(justPressed[i]){
			cantPress[i] = true;
			justPressed[i] = false;
		}
		if(!cantPress[i] && keys[i]){
			justPressed[i] = true;
		}
	}
		
		
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		enter = keys[KeyEvent.VK_ENTER];
		escape = keys[KeyEvent.VK_ESCAPE];
		backspace = keys[KeyEvent.VK_BACK_SPACE];
		
		z = keys[KeyEvent.VK_Z];
		}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	public boolean keyJustPressed(int keyCode){
		  if(keyCode < 0 || keyCode >= keys.length)
		    return false;
		  return justPressed[keyCode];
		}

}
