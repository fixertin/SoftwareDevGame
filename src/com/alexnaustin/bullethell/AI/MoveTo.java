package com.alexnaustin.bullethell.AI;

import java.awt.Rectangle;

import com.alexnaustin.bullethell.entities.Enemy;
import com.alexnaustin.bullethell.main.Handler;

public class MoveTo extends AI {
	protected double destX;
	protected double destY;
	protected double distance;
	
	private int unitX, unitY;
	
	protected double velx, vely;
	
	protected double angle;

	

	public MoveTo(Handler handler, double distance, Enemy e, double angle) {
		super(handler, e);
		this.angle = handler.checkAngle(angle);
		this.distance = distance;
		unitX = getXisNegative(angle);
		unitY = getYisNegative(angle);
		setValues();
	}
	
	private void setValues(){
		destX = (Math.cos(Math.toRadians(this.angle)) * distance*unitX)+ e.getX();
		destY = (Math.sin(Math.toRadians(this.angle)) * distance*unitY)+ e.getY();
		velx = handler.getXSpeed(angle, 3) * unitX;
		vely = handler.getYSpeed(angle, 3) * unitY;
		hitbox = new Rectangle();
		setBounds();
	}
	
	private int getXisNegative(double angle){
		if(handler.checkVelX(angle))
			return -1;
		else
			return 1;
	}
	private int getYisNegative(double angle){
		if(handler.checkVelY(angle))
			return -1;
		else
			return 1;
	}
	
	public void setBounds(){
		hitbox.setBounds((int)destX, (int)destY, 32, 32);
	}

	@Override
	public void reset(Enemy e) {
		this.e = e;
		setValues();
		start();
	}

	@Override
	public void tick() {
		setBounds();
		if(isRunning()){
			if(!isAtDestination())
				moveEnemy();
			else{
				succeed();
				e.setVelx(0);
				e.setVely(0);
			}
		}
	}
	
	private void moveEnemy(){
		if(isAtDestination()){
			e.setVelx(0);
			e.setVely(0);
		} else {
			e.setVelx(velx);
			e.setVely(vely);
		}
	}
	private boolean isAtDestination(){
		if(e.getHitbox().getBounds().intersects(hitbox)){
			return true;
		} else 
			return false;
		
	}

}
