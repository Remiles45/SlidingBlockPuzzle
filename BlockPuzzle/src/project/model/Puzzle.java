package project.model;
import java.util.ArrayList;

import project.model.PuzzlePiece.direction;
import project.model.PuzzlePiece.size;
public class Puzzle{

	static ArrayList<PuzzlePiece> pieces = new ArrayList<PuzzlePiece>();
	int unit;
	/** Returns a reasonable default. */
	public void defaultModel() {
		this.unit = 100;
		Puzzle.pieces = setPuzzle();
		}
	public ArrayList<PuzzlePiece> setPuzzle(){
		pieces.removeAll(pieces);
		pieces.add(new PuzzlePiece(0,0,size.med, true,this.unit));
		pieces.add(new PuzzlePiece(this.unit,0,size.small, false,this.unit));
		pieces.add(new PuzzlePiece(this.unit*2,0,size.small, false,this.unit));
		pieces.add(new PuzzlePiece(this.unit*3,0,size.small, false,this.unit));
		pieces.add(new PuzzlePiece(0,this.unit*2,size.med, true,this.unit));
		pieces.add(new PuzzlePiece(this.unit,this.unit,size.med, true,this.unit));
		pieces.add(new PuzzlePiece(this.unit*2,this.unit,size.large, false,this.unit));
		pieces.add(new PuzzlePiece(this.unit,this.unit*3,size.med, false,this.unit));
		pieces.add(new PuzzlePiece(this.unit*3,this.unit*3,size.small, false,this.unit));
		pieces.add(new PuzzlePiece(this.unit*2,this.unit*4,size.med, false,this.unit));
		return pieces;
	}
	public ArrayList<PuzzlePiece> testPuzzle(){
		pieces.removeAll(pieces);
		pieces.add(new PuzzlePiece(this.unit,this.unit*3,size.large, false,this.unit));
		return pieces;
	}
	public ArrayList<PuzzlePiece> getPuzzle() {
		return pieces;
	}
	public int getPieceX(int i) {
		PuzzlePiece this_piece = pieces.get(i);
		return this_piece.getX();
	}
	public int getPieceY(int i) {
		PuzzlePiece this_piece = pieces.get(i);
		return this_piece.getY();
	}
	public int getPieceHeight(int i) {
		PuzzlePiece this_piece = pieces.get(i);
		return this_piece.getHeight();
	}
	public int getPieceWidth(int i) {
		PuzzlePiece this_piece = pieces.get(i);
		return this_piece.getWidth();
	}	
	public int getPuzzleSize() {
		return pieces.size();
	}
	public void movePiece(int i, direction d) {
		PuzzlePiece this_piece = pieces.get(i);
		this_piece.move(d);
	}
	public int nextX(int i,direction d) {
		PuzzlePiece this_piece = pieces.get(i);
		return this_piece.nextX(d);
	}
	public int nextY(int i,direction d) {
		PuzzlePiece this_piece = pieces.get(i);
		return this_piece.nextY(d);
	}
	public int getUnit() {
		return this.unit;
	}
	public boolean checkLarge(int i) {
		PuzzlePiece this_piece = pieces.get(i);
		boolean is_large = this_piece.checkIfLarge();
		return is_large;
	}
}
