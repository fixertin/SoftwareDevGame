package com.alexnaustin.bullethell.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.alexnaustin.bullethell.GFX.Assets;
import com.alexnaustin.bullethell.GFX.GameCamera;
import com.alexnaustin.bullethell.GFX.Window;
import com.alexnaustin.bullethell.input.KeyManager;
import com.alexnaustin.bullethell.states.GameState;
import com.alexnaustin.bullethell.states.State;



public class Game implements Runnable{
	
	private Window window;
	private Thread thread;
	private boolean running = false;
	private BufferStrategy bs;
	private Graphics g;
	private KeyManager keyManager;
	private GameCamera gameCamera;
	private Handler handler;
	
	
	public int width, height;
	public String title;
	
	//states
	private State gameState;
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	private void init(){
		window = new Window(title, width, height);
		window.getFrame().addKeyListener(keyManager);
		Assets.init();
		gameCamera = new GameCamera(this, 0, 0);
		handler = new Handler(this);
		gameState = new GameState(handler);
		State.setState(gameState);
	}
	private void tick(){
		if(State.getState() != null)
			State.getState().tick();
		keyManager.tick();
	}
	private void render(){
		bs = window.getCanvas().getBufferStrategy();
		if(bs == null){
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		//draw
		
		if(State.getState() != null)
			State.getState().render(g);
		
		//don't draw
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() {
		init();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	public GameCamera getCamera(){
		return gameCamera;
	}
	
}
