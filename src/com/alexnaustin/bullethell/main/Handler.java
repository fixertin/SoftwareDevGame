package com.alexnaustin.bullethell.main;

import com.alexnaustin.bullethell.GFX.GameCamera;
import com.alexnaustin.bullethell.input.KeyManager;
import com.alexnaustin.bullethell.worlds.World;

public class Handler {
	private Game game;
	private World world;
	
	public Handler(Game game){
		this.game = game;
	}
	
	public double checkAngle(double angle){
		if((0<=angle) && (angle<=90))
			return angle;
		else if((90<=angle) && (angle<=180))
			return 180 - angle;
		else if((180<=angle) && (angle<=270))
			return 90 - (270 - angle);
		else if((270<=angle) && (angle<=360))
			return 360 - angle;
		else
			return 0;
	}
	public boolean checkVelX(double angle){
		if((0<=angle) && (angle<=90))
			return false;
		else if((90<=angle) && (angle<=180))
			return true;
		else if((180<=angle) && (angle<=270))
			return true;
		else if((270<=angle) && (angle<=360))
			return false;
		else
			return false;
	}
	public boolean checkVelY(double angle){
		if((0<=angle) && (angle<=90))
			return false;
		else if((90<=angle) && (angle<=180))
			return false;
		else if((180<=angle) && (angle<=270))
			return true;
		else if((270<=angle) && (angle<=360))
			return true;
		else
			return false;
	}
	public double getXSpeed(double angle, double h){
		double temp = Math.cos(Math.toRadians(angle)) * h;
		if(checkVelX(angle)){
			return -temp;
		}else
			return temp;
		
	}
	public double getYSpeed(double angle, double h){
		double temp = Math.sin(Math.toRadians(angle)) * h;
		if(checkVelY(angle)){
			return -temp;
		}else
			return temp;
	}
	
	public int getWidth(){
		return game.width;
	}
	public int getHeight(){
		return game.height;
	}
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	public GameCamera getCamera(){
		return game.getCamera();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}
