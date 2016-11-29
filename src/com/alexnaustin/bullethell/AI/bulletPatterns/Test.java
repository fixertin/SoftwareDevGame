package com.alexnaustin.bullethell.AI.bulletPatterns;

import com.alexnaustin.bullethell.creatures.Bullet;
import com.alexnaustin.bullethell.entities.Enemy;
import com.alexnaustin.bullethell.main.Handler;

public class Test extends Pattern{

	public Test(Handler handler, Enemy shooter) {
		super(handler, shooter);
	}
	
	public void fillBullets(){
		shooter.bullets.add(new Bullet(handler, shooter.getX(), shooter.getY(), 90, shooter));
	}

}
