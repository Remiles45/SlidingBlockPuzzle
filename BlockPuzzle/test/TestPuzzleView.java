import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import project.model.PuzzlePiece;
import project.model.PuzzlePiece.direction;
import project.view.PuzzleView;

class TestPuzzleView {
//	PuzzleView puzzle_view;
	int unit = 100;

	@Test
	void testResetPuzzle() {
		//create random puzzle with default configuration
		PuzzleView puzzle_view = new PuzzleView(0,0,400,500,10);
		ArrayList<PuzzlePiece> original = puzzle_view.getPuzzle();
		puzzle_view.move(0, direction.left);
		puzzle_view.resetPuzzle();
		assertEquals(original,puzzle_view.getPuzzle());
	}
	@Test
	void testGetPuzzleSize() {
		//confirm 10 puzzle pieces in the puzzle
		PuzzleView puzzle_view = new PuzzleView(0,0,400,500,10);
		assertEquals(10,puzzle_view.getPuzzleSize());
	}
	@Test
	void testCoordinate() {
		//ensure that points are being validated as inside of a puzzle piece
		PuzzleView puzzle_view = new PuzzleView(0,0,400,500,10);
		assertTrue(puzzle_view.testCoordinate(0, unit/2, unit/2));
		assertFalse(puzzle_view.testCoordinate(0, 2*unit, 2*unit));
	}
	@Test
	void testInPuzzleBoard() {
		PuzzleView puzzle_view = new PuzzleView(0,0,400,500,10);
		assertTrue(puzzle_view.onPuzzleBoard(200,250,unit,unit));
		assertFalse(puzzle_view.onPuzzleBoard(-10,0,unit,unit));
	}
//	@Test
//	void testCantMove() {
//		PuzzleView puzzle_view = new PuzzleView(0,0,400,500,10);
//		int size = puzzle_view.getPuzzleSize();
//		System.out.println(puzzle_view.cantMove(0, unit, unit*4, unit, unit*2));
//		System.out.println(puzzle_view.cantMove(size-1, unit, unit*4, unit*2, unit));
//		assertTrue(puzzle_view.cantMove(0, unit*3, unit, unit, unit*2));
//		assertFalse(puzzle_view.cantMove(size, unit, unit*4, unit, unit*2));
//	}
	@Test
	void testMove() {
		//test all the movements required to move the large block 1 unit
		PuzzleView puzzle_view = new PuzzleView(0,0,400,500,10);
		int size = puzzle_view.getPuzzleSize();
		puzzle_view.move(size-1, direction.left);
		puzzle_view.move(4, direction.down);
		puzzle_view.move(0, direction.down);
		puzzle_view.move(1, direction.left);
		puzzle_view.move(2, direction.left);
		puzzle_view.move(3, direction.left);
		puzzle_view.move(size-2, direction.down);
		puzzle_view.move(size-3, direction.right);
		puzzle_view.move(5, direction.down);
		puzzle_view.move(2, direction.down);
		puzzle_view.move(3, direction.left);
		puzzle_view.move(3, direction.left);
		puzzle_view.move(6, direction.up);
	}
	@Test 
	void testWin() {
		PuzzleView puzzle_view = new PuzzleView(0,0,400,500,10);
		puzzle_view.changePuzzleToTest();
		//confirm that the puzzle has indeed been changed to 1
		assertEquals(1,puzzle_view.getPuzzleSize());
		//attempt to move large block off of the board
		puzzle_view.move(0, direction.down);
	}
}
