package cz.uhk.pro2.flappy.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import cz.uhk.pro2.flappy.game.GameBoard;
import cz.uhk.pro2.flappy.game.Tile;
import cz.uhk.pro2.flappy.game.service.BoardLoader;
import cz.uhk.pro2.flappy.game.service.CsvBoardLoader;

public class MainWindow extends JFrame { 
	//TODO implementovat dlazdici BonusTile, kdyz narazi do ptaka, zmizi, promenna active typu boolean
	
	BoardPanel pnl = new BoardPanel();
	GameBoard gameBoard;
	long x = 0;
	
	class BoardPanel extends JPanel{
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			gameBoard.drawAndDetectCollisions(g);
		}
	}
	
	public MainWindow(){
		super("Flappy bird");
		try (InputStream is = new FileInputStream("level.csv")){
			BoardLoader loader = new CsvBoardLoader(is);
			gameBoard = loader.getGameBoard();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			gameBoard = new GameBoard();
		} catch (IOException e1) {
			e1.printStackTrace();
			gameBoard = new GameBoard();
		}
		pnl.setPreferredSize(new Dimension(300, Tile.SIZE*20)); //TODO 
		setAlwaysOnTop(true);
		add(pnl, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		gameBoard.setWidthPix(pnl.getWidth());
		
		Timer t = new Timer(20, e ->{
			gameBoard.tick(x++);
			pnl.repaint();
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1){
					//kdyz jeste nebezi timer, tak ho nastartovat
					if(!t.isRunning()) t.start();
					else
					gameBoard.kickTheBird();
				}
				else if (e.getButton() == MouseEvent.BUTTON3){
					if (gameBoard.isGameOver()){
					gameBoard.reset();
					x = 0; //posunout svet na zacatek;
					gameBoard.tick(0);
					// prekreslit
					pnl.repaint();
					// zastavit casovac az do dalsiho kliknuti mysi
					t.stop();
					}
				}
				}
			});	
	}	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MainWindow w = new MainWindow();
			w.setVisible(true);
		});

	}

}
