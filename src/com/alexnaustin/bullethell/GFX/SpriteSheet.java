package com.alexnaustin.bullethell.GFX;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int w, int h){
		return sheet.getSubimage(x*32, y*32, w, h);
	}
}
