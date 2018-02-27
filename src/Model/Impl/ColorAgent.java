package Model.Impl;

import java.util.*;

import Model.Abstraction.*;

public class ColorAgent implements Agent{
	
	private Random rand = new Random();
	private int posX,posY ;
	private int maxX, maxY;
	private Particle particle ;
	public ColorAgent() {
		this.maxX= Core.getMaxX();
		this.maxY = Core.getMaxY();
		posX = rand.nextInt(maxX);
		posY = rand.nextInt(maxY);
	}

	public boolean hasPayload() {
		return particle!=null;
	}
	
	public Particle getParticle() {
		return particle;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	@Override
	public void iterate() {
		move();
	}

	@Override
	public void move() {
		int velX = rand.nextInt(2)-1;
		int velY = rand.nextInt(2)-1;
		while(posX+velX<0 || posX+velX>maxX)
			velX = rand.nextInt(2)-1;
		while(posY+velY<0 || posX+velY>maxY)
			velY = rand.nextInt(2)-1;
		posX += velX;
		posY += velY;
	}	
}