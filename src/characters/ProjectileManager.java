package characters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ProjectileManager {

	
	BufferedImage projectile;
	int x, y, scale, speedx, speedy, duration;
	int t = 0;
	
	public ProjectileManager(BufferedImage sprite, int originX, int originY, int scale, int speedx, int speedy, int durationMS) {
		projectile = sprite;
		x = originX;
		y = originY;
		this.scale = scale;
		this.speedx = speedx;
		this.speedy = speedy;
		duration = durationMS;
	}
	
	public void render(Graphics g) {
		g.drawImage(projectile, x, y, projectile.getWidth() * scale, projectile.getHeight() * scale, null);
	}
	
	public boolean update() {
		if(t++ <= duration) {
			x += speedx;
			y += speedy;
			return true;
		}else
			return false;
	}
}
