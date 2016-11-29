package com.alexnaustin.bullethell.GFX;

import java.awt.image.BufferedImage;

public class Assets {
	public static BufferedImage player, pBullet, eBullet, asteroid, enemy;
	
	public static final int WIDTH = 32, HEIGHT = 32;
	
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/spritesheet1.png"));
		player = sheet.crop(0, 0, WIDTH, HEIGHT);
		pBullet = sheet.crop(0, 1, WIDTH, HEIGHT);
		eBullet = sheet.crop(0, 2, WIDTH, HEIGHT);
		asteroid = sheet.crop(0, 3, WIDTH, HEIGHT);
		enemy = sheet.crop(0, 4, WIDTH, HEIGHT);
	}
}
