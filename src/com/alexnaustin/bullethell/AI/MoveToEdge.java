package com.alexnaustin.bullethell.AI;

import com.alexnaustin.bullethell.entities.Enemy;
import com.alexnaustin.bullethell.main.Handler;

public class MoveToEdge extends AI{
	private double angle;
	private int unitX, unitY;
	private double velX, velY;

	public MoveToEdge(Handler handler, Enemy e, double angle) {
		super(handler, e);
		this.angle = angle;
		unitX = getXisNegative(angle);
		unitY = getYisNegative(angle);
		init();
	}
	
	private void init(){
		velX = handler.getXSpeed(angle, 3) * unitX;
		velY = handler.getYSpeed(angle, 3) * unitY;
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
	
	@Override
	public void reset(Enemy e) {
		start();
	}

	@Override
	public void tick() {
		if(isRunning()){
			if(!isAtDestination())
				moveEnemy();
			else{
				succeed();
				e.setRemoved(true);
			}
		}
	}
	
	private void moveEnemy(){
		e.setVelx(velX);
		e.setVely(velY);
	}
	
	private boolean isAtDestination(){
		if((e.getX() <= -32 || e.getX() >= handler.getWidth()) || (e.getY() <= -32 || e.getY() >= handler.getHeight()))
			return true;
		else
			return false;
	}

}
