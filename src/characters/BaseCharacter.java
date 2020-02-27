package characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.ArrayList;

import display.Assets;
import main.KeyManager;

public abstract class BaseCharacter {
	
	KeyManager keyM;

	int x, y, velx, vely, height, width;
	int hp, mp, str, def, mag, res;
	int moveSpeed, fallSpeed, jumpHeight;
	boolean facingRight, grounded;
	boolean directionHeld = false;
	
	enum directions{
		none,
		left,
		downleft,
		down,
		downright,
		right,
		upright,
		up,
		upleft
	};
	public static final int LEFT = 1;  // 0001
	public static final int DOWN = 2;  // 0010
	public static final int RIGHT = 4;  // 0100
	public static final int UP = 8;  // 1000
	ArrayList<directions> inputHistory;
	
	ArrayList<Integer> framesHeld;
	
	public BaseCharacter(KeyManager keyM, boolean startPos) {
		this.keyM = keyM;
		if(startPos) {
			facingRight = true;
			x = 300;
		}
		grounded = false;
	}
	
	public abstract void render(Graphics g);
	
	public abstract void update();
	
	protected void inputRender(Graphics g) {
		
		for(int i = 0; i < inputHistory.size(); i++) {
			double rotationRequired = 0;
			directionHeld = true;
			switch(inputHistory.get(i)) {
			case left:
				rotationRequired = Math.toRadians (-90);
				break;
			case downleft:
				rotationRequired = Math.toRadians (-135);
				break;
			case down:
				rotationRequired = Math.toRadians (180);
				break;
			case downright:
				rotationRequired = Math.toRadians (-225);
				break;
			case right:
				rotationRequired = Math.toRadians (-270);
				break;
			case upright:
				rotationRequired = Math.toRadians (-315);
				break;
			case up:
				rotationRequired = Math.toRadians (0);
				break;
			case upleft:
				rotationRequired = Math.toRadians (-45);
				break;
			case none:
				directionHeld = false;
				break;
			}
			if(directionHeld) {
				AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, 16, 16);
				AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
				Graphics2D g2d = (Graphics2D) g;
				g2d.drawImage(op.filter(Assets.arrow, null), 20, 80 + (40*i), null);
			}
			else {
				g.setColor(new Color(0, 38, 255));
				g.fillOval(28, 88 + (40*i), 16, 16);
			}
			g.setColor(new Color(0, 38, 255));
			g.drawString(framesHeld.get(i).toString(), 60, 100 + (40*i));
		}
	}
	
	protected void inputUpdate() {
		
		if(inputHistory.isEmpty()) {
			inputHistory.add(0, currentInput());
			framesHeld.add(0, 0);
		}else{
			if(currentInput() != inputHistory.get(0)) {
				inputHistory.add(0, currentInput());
				framesHeld.add(0, 1);
				if(inputHistory.size() > 14)
					inputHistory.remove(14);
				if(framesHeld.size() > 14)
					framesHeld.remove(14);
			}
			else {
				if(framesHeld.get(0) < 99)
					framesHeld.set(0, framesHeld.get(0) + 1);
			}
		}
	}
	
	private directions currentInput() {
		int cur = 0;
	
		if(keyM.left && !keyM.right)
			cur += LEFT; 
		else if(keyM.right && !keyM.left)
			cur += RIGHT;
		
		if(keyM.down && !keyM.up)
			cur += DOWN;
		else if(keyM.up && !keyM.down)
			cur += UP;
		
		switch(cur) {
		default:
			return directions.none;
		case 1:
			return directions.left;
		case 3:
			return directions.downleft;
		case 2:
			return directions.down;
		case 6:
			return directions.downright;
		case 4:
			return directions.right;
		case 12:
			return directions.upright;
		case 8:
			return directions.up;
		case 9:
			return directions.upleft;
		}
	}
	
}
