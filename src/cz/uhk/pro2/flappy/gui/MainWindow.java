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
		add(pnl, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		gameBoard.setWidthPix(pnl.getWidth());
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)
				gameBoard.kickTheBird();
				else if (e.getButton() == MouseEvent.BUTTON3){
					if (gameBoard.isGameOver()){
					gameBoard.reset();
					x = 0;
					}
				}
				}
			});
		
		
		Timer t = new Timer(20, e ->{
			gameBoard.tick(x++);
			pnl.repaint();
		});
		
		t.start();
	}	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MainWindow w = new MainWindow();
			w.setVisible(true);
		});

	}

}
