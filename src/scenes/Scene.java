package scenes;

import java.awt.Graphics;

import main.KeyManager;
import scenes.SceneManager.Scenes;

public abstract class Scene {
	
	KeyManager keyM;
	
	public Scene(KeyManager keyM) {
		this.keyM = keyM;
	}
	
	abstract public void render(Graphics g);

	abstract public Scenes update();
}
