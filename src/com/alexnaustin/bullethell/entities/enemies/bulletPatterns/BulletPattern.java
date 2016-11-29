package com.alexnaustin.bullethell.entities.enemies.bulletPatterns;

import java.util.LinkedList;

import com.alexnaustin.bullethell.creatures.Bullet;

public abstract class BulletPattern {
	protected LinkedList<Bullet> bullets = new LinkedList<Bullet>();
}
