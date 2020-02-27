package scenes;

import java.awt.Color;
import java.awt.Graphics;

import characters.TestCharacter;
import main.KeyManager;
import scenes.SceneManager.Scenes;

public class BattleScene extends Scene{

	TestCharacter player;
	boolean escHeld = true;
	
	
	
	public BattleScene(KeyManager keyM) {
		super(keyM);
		player = new TestCharacter(keyM, true);
	}


	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, 255));
		g.fillRect(0, 650, 1280, 70);
		
		player.render(g);
		
	}

	
	@Override
	public Scenes update() {
		if(!keyM.escape)
			escHeld = false;
		if(keyM.escape && !escHeld){
			escHeld = true;
			return Scenes.pause;
		}
		
		player.update();
		
		return Scenes.NA;
	}
}
