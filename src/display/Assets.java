package display;

import java.awt.image.BufferedImage;

import display.ImageLoader;

public class Assets {
	
	//Add images to the "res" folder and initiate a variable on the line below as a BufferedImage
	
	public static BufferedImage player, arrow;
	
	public void init() {
		
		// exampleImage = ImageLoader.loadImage("/textures/exampleImage.png");
		
		//player = ImageLoader.loadImage("/textures/player.png");
		player = ImageLoader.loadImage("/textures/Player.png");
		arrow = ImageLoader.loadImage("/textures/Arrow.png");
	}
}
