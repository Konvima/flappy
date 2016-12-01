package cz.uhk.pro2.flappy.game.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cz.uhk.pro2.flappy.game.GameBoard;
import cz.uhk.pro2.flappy.game.Tile;
import cz.uhk.pro2.flappy.game.tiles.WallTile;

public class CsvBoardLoader implements BoardLoader {
	// pomocky object pro zapisovani hlasek o prubehu programu
	static final Logger logger = Logger.getLogger(CsvBoardLoader.class.getName());
	InputStream is; // stream ze ktereho nacitame level

	public CsvBoardLoader(InputStream is) {
		this.is = is;
	}

	@Override
	public GameBoard getGameBoard() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
			// radek s poctem typu dlazdic
			String[] line = br.readLine().split(";");
			int numberOfTypes = Integer.parseInt(line[0]);
			System.out.println("Number of types: " + numberOfTypes);
			logger.log(Level.FINE, "Number of types: " + numberOfTypes);
			// zatim preskocime radky s typy dlazdic
			for (int i = 0; i < numberOfTypes; i++) {
				line = br.readLine().split(";");
				String type = line[0];
				String clazz = line[1];
				int spriteX = Integer.parseInt(line[2]);
				int spriteY = Integer.parseInt(line[3]);
				int spriteWidth = Integer.parseInt(line[4]);
				int spriteHeight = Integer.parseInt(line[5]);
				String url = line[6];
				// TODO
			}
			// radek s pocty radku a sloupcu v matici herni plochy
			line = br.readLine().split(";");
			int rows = Integer.parseInt(line[0]);
			int columns = Integer.parseInt(line[1]);
			System.out.println("Number of rows: " + rows);
			System.out.println("Number of columns: " + columns);
			// vyrobime matici dlazdic
			Tile[][] tiles = new Tile[rows][columns];
			// projdeme radky s matici
			for (int i = 0; i < rows; i++) {
				line = br.readLine().split(";");
				for (int j = 0; j < columns; j++) {
					String t;
					// osetrime pripad, ze by v CSV byly prazdne bunky na konci
					// radku
					if (j < line.length) {
						// v poradky, bunku mame v CSV
						t = line[j];
					} else {
						// bunka v CSV chybi, povazujeme za prazdnou
						t = "";
					}
					if (!"".equals(t)) {
						// v bunce je neprazdny
						tiles[i][j] = new WallTile();
					}
				}
			}
			GameBoard gb = new GameBoard(tiles);
			return gb;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Nepodporovane kodovani souboru");
		} catch (IOException e) {
			throw new RuntimeException("Chyba pri cteni souboru s levelem");
		}
	}
}
