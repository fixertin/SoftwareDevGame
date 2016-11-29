package com.alexnaustin.bullethell.creatures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.alexnaustin.bullethell.GFX.Assets;
import com.alexnaustin.bullethell.entities.Creature;
import com.alexnaustin.bullethell.entities.Player;
import com.alexnaustin.bullethell.main.Handler;


public class Bullet extends Entity{

	private Creature shooter;
	private double angle;
	private double trueAngle;
	private int speed;
	private boolean velXisNegative, velYisNegative;
	
	private Rectangle hitbox = new Rectangle();


	public Bullet(Handler handler, double x, double y, double angle, Creature shooter) {
		super(handler, x, y);
		this.shooter = shooter;
		this.angle = angle;
		speed = 5;
		trueAngle = angle;
		velXisNegative = checkVelX();
		velYisNegative = checkVelY();
		checkAngle();
		velx = getXSpeed();
		vely = getYSpeed();
		
		hitbox.setBounds((int)x, (int)y, 32, 32);
		
	}
	public Bullet(Handler handler, double x, double y, double angle, Creature shooter, int speed) {
		super(handler, x, y);
		this.shooter = shooter;
		this.angle = angle;
		this.speed = speed;
		trueAngle = angle;
		velXisNegative = checkVelX();
		velYisNegative = checkVelY();
		checkAngle();
		velx = getXSpeed();
		vely = getYSpeed();
		
		hitbox.setBounds((int)x, (int)y, 32, 32);
		
	}
	
	private double getXSpeed(){
		double temp = Math.cos(Math.toRadians(angle)) * speed;
		if(velXisNegative){
			return -temp;
		}else
			return temp;
		
	}
	private double getYSpeed(){
		double temp = Math.sin(Math.toRadians(angle)) * speed;
		if(velYisNegative){
			return -temp;
		}else
			return temp;
	}
	private void checkAngle(){
		if((0<=angle) && (angle<=90)){
			return;
		}
		if((90<=angle) && (angle<=180)){
			angle = 180 - angle;
		}
		if((180<=angle) && (angle<=270)){
			angle = 90 - (270 - angle);
		}
		if((270<=angle) && (angle<=360)){
			angle = 360 - angle;
		}
	}
	
	private boolean checkVelX(){
		if((0<=angle) && (angle<=90)){
			return false;
		}
		if((90<=angle) && (angle<=180)){
			return true;
		}
		if((180<=angle) && (angle<=270)){
			return true;
		}
		if((270<=angle) && (angle<=360)){
			return false;
		}
		
		return false;
	}
	private boolean checkVelY(){
		if((0<=angle) && (angle<=90)){
			return false;
		}
		if((90<=angle) && (angle<=180)){
			return false;
		}
		if((180<=angle) && (angle<=270)){
			return true;
		}
		if((270<=angle) && (angle<=360)){
			return true;
		}
		return false;
	}
	
	public boolean isAtEdge(){
		if((x <= 0) || (x >= handler.getWidth()) || (y <= 0) || (y >= handler.getHeight())){
			return true;
		}
		else
			return false;
	}
	
	public boolean hasIntersectedPlayer(){
		if((hitbox.intersects(handler.getWorld().getP().getHitbox())&&(shooter != handler.getWorld().getP())))
			return true;
		else
			return false;
	}
	
	public boolean hasIntersectedEnemy(int i){
		if((handler.getWorld().getEnemies().get(i) != shooter)&&(hitbox.intersects(handler.getWorld().getEnemies().get(i).getHitbox())))
			return true;
		else
			return false;
	}
	
	public void test(){
		if(hasIntersectedPlayer())
			System.out.println("intersection on player");
		for(int i=0; i<handler.getWorld().getEnemies().size(); i++){
			if(hasIntersectedEnemy(i))
				System.out.println("intersection on an enemy");
		}
	}

	@Override
	public void tick() {
		hitbox.setBounds((int)x+9, (int)y+9, 12, 13);
		test();
		if(!isAtEdge()){
			x += velx;
			y += vely;
		}
	}
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		BufferedImage tempBullet;
		if(shooter instanceof Player) 
			tempBullet = Assets.pBullet;
		else 
			tempBullet = Assets.eBullet;
		g2d.rotate(Math.toRadians(trueAngle), (int)x+16, (int)y+16);
		if(!isAtEdge()){
			g2d.drawImage(tempBullet, (int)x, (int)y, null);
		}
		g2d.rotate(Math.toRadians(-trueAngle), (int)x+16, (int)y+16);
	}

}
