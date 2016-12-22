package cz.uhk.pro2.flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappy.game.Bird;
import cz.uhk.pro2.flappy.game.Tile;

public abstract class AbstractTile implements Tile {
	Image image;
	
	public AbstractTile(Image image) {
		this.image = image;
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		//g.drawRect(x, y, Tile.SIZE, Tile.SIZE);
		g.drawImage(image, x, y, null);
	}
	
	@Override
	public boolean testColisionHasDied(Bird bird, int x, int y) {
		return false; //by default se nejedna o smrtelnou dlazdici
	}

	@Override
	public int testColisionOptainBonusPoint(Bird bird, int x, int y) {
		return 0; // by default nedostava hrac zadne extra body
	}
	
	@Override
	public void refresh() {		
	}
}
