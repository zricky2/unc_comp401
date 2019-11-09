package a7;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FramePuzzleWidget extends JPanel implements MouseListener, KeyListener {
	private PictureView picture_view;
	private SubPicture[] subPictures;
	private JPanel panel;
	private PictureView[][] puzzle;
	private Picture picture;
	private Picture blankTile;
	// blank position
	private int blank_row;
	private int blank_col;

	public FramePuzzleWidget(Picture picture) {
		setLayout(new BorderLayout());

		picture_view = new PictureView(picture.createObservable());
	
		this.picture = picture;
		JLabel title_label = new JLabel(picture.getCaption());
		add(title_label, BorderLayout.SOUTH);

		panel = new JPanel(new GridLayout(5, 5));
		

		ArrayList<SubPicture> subPictures = new ArrayList<SubPicture>();

		Iterator<SubPicture> tiles = ((PictureImpl) picture).tile(picture.getWidth() / 5, picture.getHeight() / 5);
		for (int i = 0; i < 25; i++) {
			if (tiles.hasNext()) {
				subPictures.add(tiles.next());
			}
		}
		blankTile = subPictures.get(24).paint(0, 0, picture.getWidth() / 5, picture.getHeight() / 5,
				new ColorPixel(0.0, 0.0, 0.0));
		subPictures.get(24).paint(0, 0, picture.getWidth() / 5, picture.getHeight() / 5, new ColorPixel(1.0, 1.0, 1.0));

		puzzle = new PictureView[5][5];

		int counter = 0;

		for (int x = 0; x < puzzle.length; x++) {
			for (int y = 0; y < puzzle[0].length; y++) {

				PictureView panelPicture = new PictureView(subPictures.get(counter).createObservable());
				panelPicture.addMouseListener(this);
				panelPicture.setFocusable(false);
				/*if (x == 4 && y == 4) {
					panelPicture.addKeyListener(this);
				}
				*/
				puzzle[x][y] = panelPicture;
				counter++;

				panel.add(puzzle[x][y]);
			}
		}
		blank_row = 4;
		blank_col = 4;

		add(panel);
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("You pressed on the frame at: " + e.getX() + "," + e.getY());

		for (int x = 0; x < puzzle.length; x++) {
			for (int y = 0; y < puzzle[0].length; y++) {
				if (puzzle[x][y] == e.getSource()) {
					exchangeMousePictures(x, y);
					
				}
				
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
				panel.setFocusable(true);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			exchangeKeyPictures(blank_row - 1, blank_col);
			System.out.println("You pressed the up key");
			break;
		case KeyEvent.VK_LEFT:
			exchangeKeyPictures(blank_row, blank_col - 1);
			System.out.println("You pressed the left key");
			break;
		case KeyEvent.VK_RIGHT:
			exchangeKeyPictures(blank_row, blank_col + 1);
			System.out.println("You pressed the right key");
			break;
		case KeyEvent.VK_DOWN:
			exchangeKeyPictures(blank_row + 1, blank_col);
			System.out.println("You pressed the down key");
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	// helper methods

	private void exchangeKeyPictures(int row, int col) {
		if (row < 0 || col < 0 || row > puzzle.length - 1 || col > puzzle[0].length - 1) {
			// change nothing
		} else if (row == blank_row && col == blank_col) {
			// change nothing
		} else if (row == blank_row && col != blank_col) {

			puzzle[blank_row][blank_col].setPicture(puzzle[row][col].getPicture());
			puzzle[row][col].setPicture(new PictureView(blankTile.createObservable()).getPicture());
			blank_row = row;
			blank_col = col;
		} else if (row != blank_row && col == blank_col) {

			puzzle[blank_row][blank_col].setPicture(puzzle[row][col].getPicture());
			puzzle[row][col].setPicture(new PictureView(blankTile.createObservable()).getPicture());
			blank_row = row;
			blank_col = col;
		}
	}

	private void exchangeMousePictures(int row, int col) {
		if (row < 0 || col < 0 || row > puzzle.length - 1 || col > puzzle[0].length - 1) {
			// change nothing
		} else if (row == blank_row && col == blank_col) {
			// change nothing
		} else if (row == blank_row && col != blank_col) {
			int newCol;

			if (blank_col < col) {
				newCol = blank_col + 1;
			} else {
				newCol = blank_col - 1;
			}
			while (blank_col != col) {
				if (blank_col < col) {

					puzzle[blank_row][blank_col].setPicture(puzzle[row][newCol].getPicture());
					puzzle[row][newCol].setPicture(new PictureView(blankTile.createObservable()).getPicture());
					newCol++;
					blank_col++;

				} else {

					puzzle[blank_row][blank_col].setPicture(puzzle[row][newCol].getPicture());
					puzzle[row][newCol].setPicture(new PictureView(blankTile.createObservable()).getPicture());
					newCol--;
					blank_col--;

				}
			}
			blank_row = row;
			blank_col = col;
		} else if (row != blank_row && col == blank_col) {
			int newRow;
			if (blank_row < row) {
				newRow = blank_row + 1;
			} else {
				newRow = blank_row - 1;
			}

			while (blank_row != row) {

				if (blank_row < row) {

					puzzle[blank_row][blank_col].setPicture(puzzle[newRow][col].getPicture());
					puzzle[newRow][col].setPicture(new PictureView(blankTile.createObservable()).getPicture());
					newRow++;
					blank_row++;
				} else {

					puzzle[blank_row][blank_col].setPicture(puzzle[newRow][col].getPicture());
					puzzle[newRow][col].setPicture(new PictureView(blankTile.createObservable()).getPicture());
					newRow--;
					blank_row--;
				}
			}
			blank_row = row;
			blank_col = col;
		}

	}

}
