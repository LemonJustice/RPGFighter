package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import display.Assets;
import main.KeyManager;
import main.Main;
import scenes.BattleScene;

public class SceneManager {
	KeyManager keyM;
	BattleScene battle;
	
	boolean inTitle = false;
	boolean inPause = false;
	boolean escHeld = false;
	int time = 0;
	Font myFont;
	
	enum Scenes{
		NA,
		title,
		battle,
		pause
	};
	
	public SceneManager(KeyManager keyM) {
		this.keyM = keyM;
		myFont = new Font("Serif", Font.BOLD, 36);
	}
	
	public void render(Graphics g) {
		if(inTitle) {
			//TitleScreen
			
		} else if(!inTitle && !inPause) {
			//Game
			battle.render(g);
		} else {
			//Pause menu
			battle.render(g);
			g.setColor(new Color(0,0,0,100));
			g.fillRect(0, 0, 1280, 720);
			g.setColor(new Color(0,0,0,255));
			g.drawString("This is the pause screen, ya dip!", 0, 620);
		}
	}
	
	public void update() {
		time++;
		if(inTitle) {
			//TitleScreen
		} else if(!inTitle && !inPause) {
			//Game
			if(battle == null)
				battle = new BattleScene(keyM);
			
			Scenes newScene = battle.update();
			
			if(newScene != Scenes.NA) {
				changeScene(newScene);
			}
		} else {
			//Pause menu
			if(!keyM.escape)
				escHeld = false;
			if(keyM.escape && !escHeld) {
				escHeld = true;
				changeScene(Scenes.battle);
			}
		}
	}
	
	private void changeScene(Scenes scene) {
		switch(scene) {
		case title:
			battle = null;
			break;
		case battle:
			//title = null;
			inPause = false;
			break;
		case pause:
			inPause = true;
			break;
		}
	}
}
