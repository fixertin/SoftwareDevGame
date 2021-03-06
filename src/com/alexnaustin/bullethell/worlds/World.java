package com.alexnaustin.bullethell.worlds;

import java.awt.Graphics;
import java.util.LinkedList;

import com.alexnaustin.bullethell.entities.Enemy;
import com.alexnaustin.bullethell.entities.Player;
import com.alexnaustin.bullethell.entities.enemies.BasicTestEnemy;
import com.alexnaustin.bullethell.main.Handler;


public class World {
	private Handler handler;
	
	private Player p;
	
	public LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	
	public World(Handler handler){
		this.handler = handler;
		loadWorld();
	}
	

	
	private void loadWorld(){
		p = new Player(handler, 100, 400);
		addEnemies();
		addCommands();
	}
	
	private void addEnemies(){
		enemies.add(new BasicTestEnemy(handler, -32, -32));
	}
	private void addCommands(){
		fillCommands1();
	}
	
	//this is where the enemies will all have individual
	//methods to fill their commands
	private void fillCommands1(){
		enemies.get(0).addMovement(270, 45);
		enemies.get(0).addShootPatternCircle(10);
		enemies.get(0).addMovement(230, 0);
		enemies.get(0).addShootPatternCircle(5);
		enemies.get(0).addMoveToEdge(315);
	}
	
	public void tick(){
		p.tick();
		for(int i=0; i<enemies.size(); i++){
			if(enemies.get(i).isRemoved() && enemies.get(i).getBullets().size() == 0){
				enemies.remove(i);
				System.out.println("removed");
			}else{
				enemies.get(i).tick();
			}
		}
	}
	public void render(Graphics g){
		p.render(g);
		for(int i=0; i<enemies.size(); i++)
			enemies.get(i).render(g);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}

	public LinkedList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(LinkedList<Enemy> enemies) {
		this.enemies = enemies;
	}
}
