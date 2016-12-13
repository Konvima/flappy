package cz.uhk.pro2.flappy.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;

import cz.uhk.pro2.flappy.game.tiles.BonusTile;

public class Bird implements TickAware{
	//fyzika
	static final double koefUp = -5.0;
	static final double koefDown = 2.0;
	static final int ticksFlyingUp = 4;
	
	//souradnice stredu ptaka
	double viewportX;
	double viewportY; //double, aby se dala jemne ladit rychlost padani
	
	//rychlost padani (pozitivni) nebo vzletu (negativni)
	double velocityY = koefDown;
	//kolik ticku jeste zbyva, nez ptak zacne po nakopnuti zase padat
	int ticksToFall = 0;
	
	Image imageOfTheBird;
	
	public Bird(int initialX, int initialY, Image imageOfTheBird) {
		this.viewportX = initialX;
		this.viewportY = initialY;
		this.imageOfTheBird = imageOfTheBird;
	}
	
	public void kick(){
		velocityY = koefUp; // ma zacit letet nahoru
		ticksToFall = ticksFlyingUp;		
	}
	
	public void draw(Graphics g){
		g.drawImage(imageOfTheBird, (int)viewportX - Tile.SIZE/2, (int)viewportY - Tile.SIZE/2, null);
//		g.setColor(Color.GREEN);
//		g.fillOval((int)viewportX - Tile.SIZE/2, (int)viewportY - Tile.SIZE/2, Tile.SIZE, Tile.SIZE);
//		g.setColor(Color.BLACK);
//		g.drawString(viewportX + ", " + viewportY, (int)viewportX, (int)viewportY);
	}
	
	public boolean collidesWithRectangle(int x, int y, int w, int h){
		// vytvorime kruznici (jako objekt) reprezentujici obrys ptaka
		//TODO vytvaret birdBoundery jen kdyz je potreba
		Ellipse2D.Float birdBoundery = new Ellipse2D.Float((int)viewportX - Tile.SIZE/2, (int)viewportY - Tile.SIZE/2, Tile.SIZE, Tile.SIZE);
		// otestujeme, jestli ptak koliduje s obdelnikem zadanym parametry
		return birdBoundery.intersects(x, y, w, h);		
	}	
	
	public Image getImageOfTheBird() {
		return imageOfTheBird;
	}

	@Override
	public void tick(long ticksSinceStart) {
		viewportY += velocityY;	
		if(ticksToFall > 0){
			//ptak letel nahoru
			ticksToFall--;
		}else{
			//ptak ma padat
			velocityY = koefDown;
		}
	}
}
