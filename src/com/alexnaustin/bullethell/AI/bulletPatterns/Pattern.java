package com.alexnaustin.bullethell.AI.bulletPatterns;

import java.awt.Graphics;
import java.util.LinkedList;

import com.alexnaustin.bullethell.creatures.Bullet;
import com.alexnaustin.bullethell.entities.Enemy;
import com.alexnaustin.bullethell.main.Handler;

public abstract class Pattern {
	protected LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	protected Enemy shooter;
	protected Handler handler;
	
	public Pattern(Handler handler, Enemy shooter){
		this.handler = handler;
		this.shooter = shooter;
	}

	public LinkedList<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(LinkedList<Bullet> bullets) {
		this.bullets = bullets;
	}

	public Enemy getShooter() {
		return shooter;
	}

	public void setShooter(Enemy shooter) {
		this.shooter = shooter;
	}
	
}
