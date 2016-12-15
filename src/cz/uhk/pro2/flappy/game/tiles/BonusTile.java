package cz.uhk.pro2.flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappy.game.TickAware;

public class BonusTile extends AbstractTile implements Cloneable {	
	
	private boolean active;
		
	public BonusTile(Image image){
		super(image);
		active = true; 
	}
	
	public void setActive(boolean active) {
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
	
}
