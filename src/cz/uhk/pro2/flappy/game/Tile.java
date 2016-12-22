package cz.uhk.pro2.flappy.game;

import java.awt.Graphics;

/**
 * Spolecny interface pro vsechny druhy dlazdic na herni plose.
 * @author konvima1
 *
 */
public interface Tile {
	/**
	 * Sirka a vyska dlazdice v pixelech.
	 */
	public static final int SIZE = 20;
	/**
	 * Vyklesli dlazdici na platno g.
	 * @param g
	 * @param x x-ova souradnice dlazdice ve viewportu v pixelech
	 * @param y y-ova souradnice dlazdice ve viewportu v pixelech
	 */
	void draw(Graphics g, int x, int y);
	
	/**
	 * Otestuje, zda ptak koliduje s touto dlazdici na souradnicich x, y
	 * a pokud ano a jedna se o zed, vrati true.
	 * @param bird
	 * @param x
	 * @param y
	 * @return
	 */
	boolean testColisionHasDied(Bird bird, int x, int y);
	/**
	 * Otestuje, zda ptak kolicuje s touto dlazdici na souradnicich z, y a pokud ano
	 * a jedna se o bonus, tak vratin pocet bodu, kolik ma hrac za bonus obdrzet
	 * @param bird
	 * @param x
	 * @param y
	 * @return
	 */
	int testColisionOptainBonusPoint(Bird bird, int x, int y);
	/**
	 * Obnovi stav dlazdice pri "protoceni"herniho sveta znovu dokola
	 * Bude zavolana minimalne jednou kdyz se dlazice znovu objevi na pravem okraji herni plochy
	 */
	void refresh();
}
