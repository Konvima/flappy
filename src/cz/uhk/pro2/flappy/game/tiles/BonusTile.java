package cz.uhk.pro2.flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappy.game.TickAware;
import cz.uhk.pro2.flappy.game.Tile;

public class BonusTile extends AbstractTile implements Cloneable {	
	
	private boolean active;
	private Tile emptyTile;
		
	public BonusTile(Image image, Tile emptyTile){
		super(image);
		active = true; 
		this.emptyTile = emptyTile;
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
		} else{
			emptyTile.draw(g, x, y);
		}
	}
	
	@Override
	public BonusTile clone(){
		return new BonusTile(image, emptyTile);
	}
	
}
