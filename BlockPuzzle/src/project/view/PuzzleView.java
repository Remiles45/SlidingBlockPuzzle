package project.view;
import project.model.PuzzlePiece.direction;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import project.model.Puzzle;
import project.model.PuzzlePiece;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PuzzleView extends JPanel{
	int x, y, width, height, moves, border_width, unit, last_width, last_height, num_pieces;
	private static final long serialVersionUID = 1L;
	Puzzle puzzle;
	public PuzzleView(int x,int y,int width,int height,int border) {
		Dimension puzzleSize = new Dimension(width + border*2, height + border*2);
		this.setPreferredSize(puzzleSize);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.border_width = border;
		puzzle = new Puzzle(); 
		puzzle.defaultModel();   
		this.unit = puzzle.getUnit();
	}
	public void changePuzzleToTest() {
		puzzle.testPuzzle();
	}
	@Override
	public void paintComponent(Graphics g) { 
		int x, y;
		super.paintComponent(g);
		g.setColor(Color.blue);
		g.fillRect(this.x, this.y, width+border_width*2, height+border_width*2);
		g.clearRect(this.x+border_width, this.y+border_width, width, height);
		g.setColor(new java.awt.Color(198, 140, 83));
		g.fillRect(this.x+unit, this.y+5*unit + border_width, unit*2, border_width);
		this.num_pieces = puzzle.getPuzzleSize();
		for(int j = 0; j < num_pieces; j++) {
			if(puzzle.checkLarge(j)) {
				g.setColor(Color.red);
			} else
				g.setColor(Color.LIGHT_GRAY);
			x = puzzle.getPieceX(j)+this.border_width;
			y = puzzle.getPieceY(j)+this.border_width;
			g.fillRect(x, y, puzzle.getPieceWidth(j), puzzle.getPieceHeight(j));

		}
	}
	public int getPuzzleSize() {
		return puzzle.getPuzzleSize();
	}
	public void resetPuzzle() {
		puzzle.defaultModel();
		this.moves = 0;
	}
	public ArrayList<PuzzlePiece> getPuzzle(){
		return puzzle.getPuzzle();
	}
	public boolean testCoordinate(int pose, int x, int y) {
		int piece_x = puzzle.getPieceX(pose);
		int piece_y = puzzle.getPieceY(pose);
		int x_max = puzzle.getPieceWidth(pose) + piece_x;
		int y_max = puzzle.getPieceHeight(pose) + piece_y;
		int x_min = piece_x;
		int y_min = piece_y;
		boolean validx = (x < x_max) && (x > x_min);
		boolean validy = (y < y_max) && (y > y_min);
		return validx && validy;
	}
	public void move(int pose, direction direction) {
		//check for collision with puzzle board wall or with another piece, only move if possible
		int future_x = puzzle.nextX(pose, direction);
		int future_y = puzzle.nextY(pose, direction);
		int f_w = puzzle.getPieceWidth(pose);
		int f_h = puzzle.getPieceHeight(pose);
		if (puzzle.checkLarge(pose)) {
			if((future_x >= this.x+this.unit) && (future_y >= this.y+this.unit*4)) {
				this.puzzle.movePiece(pose, direction);
				this.puzzle.movePiece(pose, direction);
				repaint();
				this.moves += 1;
				JFrame frame = new JFrame();
				JDialog d = new JDialog(frame, "Congratulations!", true);
				JLabel winner = new JLabel("Congratulations! You won!");
				d.add(winner);
				d.setSize(300, 200);
				d.setVisible(true);
				return;
			}
		}
		if(onPuzzleBoard(future_x, future_y,f_w, f_h)){
			for(int i = 0; i < puzzle.getPuzzleSize(); i++) {
				if(i != pose) {
					if(cantMove(i,future_x,future_y,f_w,f_h)) {
						return;
					}
				}
			}
			this.puzzle.movePiece(pose, direction);
			this.moves += 1;
		}
	}
	public boolean testCollision(int x1, int y1, int w1, int h1, int x2, int y2) {
		//tests if a point is INSIDE a box
		boolean x_test = (x1 <= x2) && (x2 <= (x1 + w1));
		boolean y_test = (y1 <= y2) && (y2 <= (y1 + h1));
		return x_test && y_test;
	}
	public boolean onPuzzleBoard(int x, int y, int w, int h) {
		//check if a point is within the puzzle
		boolean check_x = (x > this.x) && ((x + w) < (this.x + this.width));
		boolean check_y = (y > this.y) && ((y + h) < (this.y + this.height));
		return (check_x && check_y);
	}
	public boolean cantMove(int i, int future_x, int future_y, int f_w, int f_h) {
		//check each corner of a piece to see if it will move to a valid position on the board
		//i.e. check if a piece cannot move, return true if cant move
		int test_x, test_y, test_w, test_h;
		boolean check_c1,check_c2, check_c3, check_c4;//check every corner of future position
		test_x = puzzle.getPieceX(i);
		test_y = puzzle.getPieceY(i);
		test_w = puzzle.getPieceWidth(i);
		test_h = puzzle.getPieceHeight(i);
		check_c1 = testCollision(test_x,test_y,test_w, test_h, future_x, future_y);
		check_c2 = testCollision(test_x,test_y,test_w, test_h, future_x+f_w, future_y);
		check_c3 = testCollision(test_x,test_y,test_w, test_h, future_x, future_y+f_h);
		check_c4 = testCollision(test_x,test_y,test_w, test_h, future_x+f_w, future_y+f_h);
		
		return check_c1 || check_c2 || check_c3 || check_c4;
	}
}
