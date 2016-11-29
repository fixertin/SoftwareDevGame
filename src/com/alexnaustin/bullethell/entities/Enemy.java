package com.alexnaustin.bullethell.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.alexnaustin.bullethell.AI.AI;
import com.alexnaustin.bullethell.AI.MoveTo;
import com.alexnaustin.bullethell.AI.ShootCircle;
import com.alexnaustin.bullethell.AI.Wait;
import com.alexnaustin.bullethell.GFX.Assets;
import com.alexnaustin.bullethell.clock.Timer;
import com.alexnaustin.bullethell.creatures.Bullet;
import com.alexnaustin.bullethell.main.Handler;

public class Enemy extends Creature{
	public Timer timer;
	public LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	public LinkedList<AI> movements = new LinkedList<AI>();
	private int index = 0;

	public Enemy(Handler handler, double x, double y) {
		super(handler, x, y);
		hitbox = new Rectangle();
		init();
		
	}
	
	private void init(){
		timer = new Timer(0.01);
	}
	
	public void addMovement(double distance, double angle){
		movements.add(new MoveTo(handler, distance, this, angle));
	}
	public void addWait(double time){
		movements.add(new Wait(handler, this, new Timer(time)));
	}
	public void addShootPatternCircle(double shotGap){
		movements.add(new ShootCircle(handler, this, shotGap));
	}

	@Override
	public void tick() {
		timer.tick();
		setHitboxBounds();
		
		if(movements.get(index).getState() == null){
			movements.get(index).start();
		}
		if(movements.get(index).isSuccess() && index+1 < movements.size()){
			index+=1;
			movements.get(index).reset(this);
			
		}
		
		movements.get(index).tick();
		
		x += velx;
		y += vely;
		for(int i=0; i<bullets.size(); i++)
			bullets.get(i).tick();
		for(int i=0; i<bullets.size(); i++){
			if(bullets.get(i).isAtEdge())
				bullets.remove(i);
		}
		
		
		
		//angle += 10;
		velx = 0;
		vely = 0;
	}
	
	public void moveInDirection(double angle){
		int unitx = 1;
		int unity = 1;
		double trueAngle = handler.checkAngle(angle);
		if(handler.checkVelX(angle)) unitx = -unitx;
		if(handler.checkVelY(angle))unity = -unity;
		velx = Math.cos(Math.toRadians(trueAngle)) * 7 * unitx;
		vely = Math.sin(Math.toRadians(trueAngle)) * 7 * unity;
	}

	@Override
	public void render(Graphics g) {
		for(int i=0; i<bullets.size(); i++)
			bullets.get(i).render(g);
		g.drawImage(Assets.enemy, (int)x, (int)y, null);
		
	}

	@Override
	public void setHitboxBounds() {
		hitbox.setBounds((int)x, (int)y, 32, 32);
	}

}
