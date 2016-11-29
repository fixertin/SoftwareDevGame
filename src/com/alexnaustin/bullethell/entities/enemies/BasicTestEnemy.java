package com.alexnaustin.bullethell.entities.enemies;

import java.awt.Graphics;

import com.alexnaustin.bullethell.GFX.Assets;
import com.alexnaustin.bullethell.entities.Enemy;
import com.alexnaustin.bullethell.main.Handler;

public class BasicTestEnemy extends Enemy{
	public int index = 0;

	public BasicTestEnemy(Handler handler, double x, double y) {
		super(handler, x, y);
	}

	@Override
	public void setHitboxBounds() {
		hitbox.setBounds((int)x, (int)y, 32, 32);
	}

	@Override
	public void tick() {
		setHitboxBounds();
		if(movements.get(index).getState() == null)
			movements.get(index).start();
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
		velx = 0;
		vely = 0;
	}

	@Override
	public void render(Graphics g) {
		for(int i=0; i<bullets.size(); i++)
			bullets.get(i).render(g);
		g.drawImage(Assets.enemy, (int)x, (int)y, null);
	}

}
