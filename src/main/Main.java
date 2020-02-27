package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import display.Assets;
import display.Display;
import main.KeyManager;
import scenes.SceneManager;


public class Main implements Runnable {
	
	Thread thread;
	KeyManager keyManager;
	Display display;
	Assets assets;
	SceneManager scenes;
	
	boolean running;
	private int width;
	private int height;
	private String title;
	private Graphics g;
	private Graphics2D g2d;
	private BufferStrategy buffer;
	
	Color skyBox = new Color(204, 204, 204, 255);

	public Main(String string, int i, int j) {
		this.width = i;
		this.height = j;
		this.title = string;
		keyManager = new KeyManager();
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		scenes = new SceneManager(keyManager);
		Assets assets = new Assets();
		assets.init();
	}
	
	private void render() {
		buffer = display.getCanvas().getBufferStrategy();
		if(buffer == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = buffer.getDrawGraphics();
		g2d = (Graphics2D)g;
		g.setColor(skyBox);
		g.fillRect(0, 0, width, height);
		
		scenes.render(g);
		
		buffer.show();
		g.dispose();
		
	}

	private void update() {
		keyManager.tick();
		scenes.update();
	}
	
	@Override
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if(delta >= 1){
				update();
				render();
				delta--;
			}
		}
		stop();
		
	}
	
	public void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public KeyManager getKM() {
		return keyManager;
	}
}
