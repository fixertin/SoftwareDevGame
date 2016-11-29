package com.alexnaustin.bullethell.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.alexnaustin.bullethell.GFX.Assets;
import com.alexnaustin.bullethell.clock.Timer;
import com.alexnaustin.bullethell.creatures.Bullet;
import com.alexnaustin.bullethell.main.Handler;

public class Player extends Creature {
	private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	private Timer timer;
	
	
	public Player(Handler handler, double x, double y) {
		super(handler, x, y);
		timer = new Timer(0.05);
		hitbox = new Rectangle();
	}
	
	public void getInput(){
		velx = 0;
		vely = 0;
		if(handler.getKeyManager().up && y > 0)
			vely = -speed;
		if(handler.getKeyManager().down && y+32 < handler.getHeight())
			vely = speed;
		if(handler.getKeyManager().left && x > 0)
			velx = -speed;
		if(handler.getKeyManager().right && x+32 < handler.getWidth())
			velx = speed;
		if(handler.getKeyManager().space)
			shoot();
	}
	
	public void shoot(){
		if(timer.hasReachedTime()){
			bullets.add(new Bullet(handler, x, y, 270, this, 15));
			timer.reset();
		}
	}
	
	@Override
	public void tick() {
		timer.tick();
		setHitboxBounds();
		getInput();
		move();
		for(int i=0; i<bullets.size(); i++){
			if(bullets.get(i).isAtEdge()){
				bullets.remove(i);
			}
		}
		for(int i=0; i<bullets.size(); i++){
			bullets.get(i).tick();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), null);
		for(int i=0; i<bullets.size(); i++){
			bullets.get(i).render(g);
		}
	}

	@Override
	public void setHitboxBounds() {
		hitbox.setBounds((int)x+13, (int)y+9, 5, 4);
	}
	
}
