package characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import display.Assets;
import main.KeyManager;

public class TestCharacter extends BaseCharacter{

	ProjectileManager projectile;
	
	int scale = 2;
	boolean thrustersOn;
	boolean gunFiring;
	boolean projectileCreated = false;
	
	BufferedImage playerBase; 
	BufferedImage thrusters;
	BufferedImage gunsFire;
	BufferedImage bullets;
	
	public TestCharacter(KeyManager keyM, boolean startPos) {
		super(keyM, startPos);
		width = 42;
		height = 54;
		y = 650 - height;
		fallSpeed = 2;
		
		playerBase = Assets.player.getSubimage(0, 0, width, height);
		thrusters = Assets.player.getSubimage(42, 0, (60 - 42), height);
		gunsFire = Assets.player.getSubimage(60, 0, (65-60), height);
		bullets =  Assets.player.getSubimage(65, 0, 6, height);
		
		inputHistory = new ArrayList<directions>();
		framesHeld = new ArrayList<Integer>();
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(playerBase, x, y, width * scale, height * scale, null);
		
		if(thrustersOn)
			g.drawImage(thrusters, x - (18 * scale), y, (60 - 42) * scale, height * scale, null);
		
		if(projectileCreated && !projectile.equals(null))
			projectile.render(g);
		
		if(gunFiring) {
			g.drawImage(gunsFire, x + (30 * scale), y, gunsFire.getWidth() * scale, gunsFire.getHeight() * scale, null);
			gunFiring = false;
		}
		
		inputRender(g);
		
		g.setColor(new Color(0,0,0));
		g.drawString("vely:" + vely, 0, 20);
		g.drawString("velx:" + velx, 0, 40);
	}

	@Override
	public void update() {
		
		inputUpdate();
		
		if(!keyM.down) {
			if(keyM.left)
				velx -= 2;
			if(keyM.right)
				velx += 2;
		}
		
		if(keyM.up && grounded){
			vely = -30;
			this.grounded = false;
		}
		
		if(keyM.keyJustPressed(KeyEvent.VK_Z)) {
			projectile = new ProjectileManager(bullets, x + (30 * scale), y, scale, 6 + (velx/2), 0, 60);
			gunFiring = true;
			projectileCreated = true;
		}
		if(projectileCreated && !projectile.equals(null)) {
			projectileCreated = projectile.update();
			if(!projectileCreated)
				projectile = null;
		}

		if(velx > 0)
			thrustersOn = true;
		else
			thrustersOn = false;
		
		x+= velx;
		if(x > 1280 - (width * scale))
			x = 1280 - (width * scale);
		else if(x < 0)
			x = 0;
		
		y+= vely;
		if(y > 650 - (height * scale)) {
			y = 650 - (height * scale);
			vely = 0;
		}else if(y < 0)
			y = 0;
		
		if(y == 650 - (height * scale))
			grounded = true;
		if(velx > 0)
			velx /= 1.2;
		else
			velx /= 1.4;
		vely += fallSpeed;
	}

}
