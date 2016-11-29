package com.alexnaustin.bullethell.states;

import java.awt.Graphics;

import com.alexnaustin.bullethell.main.Handler;
import com.alexnaustin.bullethell.worlds.World;



public class GameState extends State{
	public World world;
	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler);
		handler.setWorld(world);
		//handler.getCamera().move(player.getX(), player.getY());
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}

}
