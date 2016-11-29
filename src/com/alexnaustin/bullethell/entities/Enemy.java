package com.alexnaustin.bullethell.entities;

import java.awt.Rectangle;
import java.util.LinkedList;

import com.alexnaustin.bullethell.AI.AI;
import com.alexnaustin.bullethell.AI.MoveTo;
import com.alexnaustin.bullethell.AI.MoveToEdge;
import com.alexnaustin.bullethell.AI.ShootCircle;
import com.alexnaustin.bullethell.AI.Wait;
import com.alexnaustin.bullethell.clock.Timer;
import com.alexnaustin.bullethell.creatures.Bullet;
import com.alexnaustin.bullethell.main.Handler;

public abstract class Enemy extends Creature{
	protected LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	protected LinkedList<AI> movements = new LinkedList<AI>();
	protected boolean isRemoved;

	

	public Enemy(Handler handler, double x, double y) {
		super(handler, x, y);
		hitbox = new Rectangle();
	}
	public boolean isRemoved() {
		return isRemoved;
	}

	public void setRemoved(boolean isRemoved) {
		this.isRemoved = isRemoved;
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
	public void addMoveToEdge(double angle){
		movements.add(new MoveToEdge(handler, this, angle));
	}

	public LinkedList<Bullet> getBullets() {
		return bullets;
	}
	public void setBullets(LinkedList<Bullet> bullets) {
		this.bullets = bullets;
	}
	public LinkedList<AI> getMovements() {
		return movements;
	}
	public void setMovements(LinkedList<AI> movements) {
		this.movements = movements;
	}
}
