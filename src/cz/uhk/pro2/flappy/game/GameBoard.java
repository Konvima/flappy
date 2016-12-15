package cz.uhk.pro2.flappy.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappy.game.tiles.BonusTile;
import cz.uhk.pro2.flappy.game.tiles.EmptyTile;
import cz.uhk.pro2.flappy.game.tiles.WallTile;

public class GameBoard implements TickAware{
	Tile[][] tiles; //matice dlazdic na herni plose
	int shiftX; //o kolik pixelu svet ubehl doleva
	int widthPix; //sirka hraci plochy v pixelech
	Bird bird;
	int points;
	boolean gameOver; // true pokud doslo ke kolizi a hra ma skoncit
	
	public GameBoard(Tile[][] tiles, Image imageOfTheBird){
		this.tiles = tiles;
		bird = new Bird(30, 100, imageOfTheBird);
		//TODO poresit imageOfTheBird
		reset();
	}
	
	public GameBoard(){
	}
	
	public void setWidthPix(int widthPix) {
		this.widthPix = widthPix;
	}
	
	public void kickTheBird(){
		bird.kick();
	}
	
	/**
	 * Vykresli celou herni plochu(sloupy, bonusy, ptaka) na platno g.
	 * @param g
	 */
	public void drawAndDetectCollisions(Graphics g){
		int minJ = shiftX/Tile.SIZE; //j-souradnice prvni dlazdice vlevo kterou je nutne kreslit
		//pocet dlazdic (na sirku), kolik je nutne kreslit (do viewportu)
		// + 2 protoze muze chybet cast bunky vlevo a vpravo kvuli obema celociselnym delenim
		int countJ = widthPix/Tile.SIZE + 2;
		for(int i = 0; i < tiles.length; i++){
			for(int j = minJ; j <= minJ+countJ; j++){
				//chceme aby level bezel porad dokola, takze modJ se 
				//na konci pole vraci zase na 0; tile[0].length je pocet sloupcu
				int modJ = j % tiles[0].length; 
				Tile t = tiles[i][modJ];
				if(t != null){
					//v bunce je nejaka dlazdice
					//vykreslime ji
					int viewportX = j * Tile.SIZE - shiftX;
					int viewportY = i * Tile.SIZE;
					if(j == minJ + countJ){
						if(t instanceof BonusTile){
							((BonusTile) t).setActive(true);
						}
					}
					t.draw(g, viewportX, viewportY);
					// otestujeme moznou kolizi dlazdice s ptakem
					if (t instanceof WallTile){
						// t je zed
						// otestujeme, jestli dlazdice t koliduje s ptakem
						if (bird.collidesWithRectangle(viewportX, viewportY, Tile.SIZE, Tile.SIZE)){
							gameOver = true; // doslo ke kolizi, hra ma skoncit
						}
					} else if(t instanceof BonusTile){
						if (bird.collidesWithRectangle(viewportX, viewportY, Tile.SIZE, Tile.SIZE)){
							if(((BonusTile) t).isActive()) points++;
							((BonusTile) t).setActive(false);
						}
					}
				}
			}
		}
		g.setColor(new Color(0xff0000));
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		g.drawString("" + points, 5, 20);
		bird.draw(g);
	}
	
	public void reset(){
		gameOver = false;
		bird = new Bird(30, 100, bird.getImageOfTheBird());
		for(int i = 0; i < tiles.length; i++){
			for(int k = 0; k < tiles[0].length; k++){
				if(tiles[i][k] instanceof BonusTile){
					((BonusTile)tiles[i][k]).setActive(true);
				}
			}
		}
		points = 0;
	}
	
	public boolean isGameOver(){
		return gameOver;
	}
	
	@Override
	public void tick(long ticksSinceStart) {
		if(!gameOver){
			//s kazdym tickem ve hre posuneme hru o jeden pixel
			//tj. pocet ticku a pixelu se rovnaji
			shiftX = (int)ticksSinceStart;		
			// dame vedet ptakovi, ze hodiny tickly
			bird.tick(ticksSinceStart);
		} // else pro gameOver hra stoji na miste			
	}
	

}
