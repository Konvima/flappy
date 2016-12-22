package cz.uhk.pro2.flappy.game.tiles;

import java.awt.Image;

import cz.uhk.pro2.flappy.game.Bird;
import cz.uhk.pro2.flappy.game.Tile;

public class WallTile extends AbstractTile{
	
	public WallTile(Image image) {
		super(image);
	}
	
	@Override
	public boolean testColisionHasDied(Bird bird, int x, int y) {
		return bird.collidesWithRectangle(x, y, Tile.SIZE, Tile.SIZE);
	}

}
