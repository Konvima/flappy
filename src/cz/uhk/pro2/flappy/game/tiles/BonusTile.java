package cz.uhk.pro2.flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappy.game.TickAware;

public class BonusTile extends AbstractTile implements Cloneable, TickAware {	
	
	private boolean active;
	private int timeToSleep;
		
	public BonusTile(Image image){
		super(image);
		active = true; 
	}
	
	public void setActive(boolean active) {
		if(!active){
			timeToSleep = 100;
		}
		this.active = active;
	}
	
	public boolean isActive(){
		return active;
	}
	
	@Override
	public void draw(Graphics g, int x, int y) {
		if(active){
			super.draw(g, x, y);
		}
	}
	
	@Override
	public BonusTile clone(){
		return new BonusTile(image);
	}

	@Override
	public void tick(long ticksSinceStart) {
		if(!active){
			timeToSleep--;
		}
		if(timeToSleep <= 0){
			active = true;
		}
	}
}
