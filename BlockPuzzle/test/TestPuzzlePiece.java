import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.model.PuzzlePiece;
import project.model.PuzzlePiece.direction;
import project.model.PuzzlePiece.size;

class TestPuzzlePiece {
	PuzzlePiece piece; 
	int unit = 100;
	
	@Test
	void testGetX() {
		piece = new PuzzlePiece(0, 0, size.small, false, unit);
		//actual x will be 1 according to the bias for the gap btwn pieces
		assertEquals(1,piece.getX());
	}

	@Test
	void testGetY() {
		piece = new PuzzlePiece(0, 0, size.small, false, unit);
		//actual y will be 1 according to the bias for the gap btwn pieces
		assertEquals(1,piece.getY());
	}

	@Test
	void testGetWidth() {
		piece = new PuzzlePiece(0, 0, size.med, false, unit);
		//width in medium horizontal piece = 2*unit -gap between pieces (2*pose bias)
		//therefore -2
		assertEquals(2*unit-2,piece.getWidth());
		piece = new PuzzlePiece(0,0,size.med,true,unit);
		assertEquals(unit-2, piece.getWidth());
		//test small
		piece = new PuzzlePiece(0, 0, size.small, false, unit);
		assertEquals(unit-2,piece.getWidth());
		//test large
		piece = new PuzzlePiece(0, 0, size.large, false, unit);
		assertEquals(unit*2 - 2, piece.getWidth());
	}

	@Test
	void testGetHeight() {
		piece = new PuzzlePiece(0, 0, size.med, false, unit);
		//height in medium vertical piece = 2*unit -gap between pieces (2*pose bias)
		//therefore -2
		assertEquals(2*unit-2,piece.getWidth());
		piece = new PuzzlePiece(0,0,size.med,true,unit);
		assertEquals(unit-2, piece.getWidth());
		//test small
		piece = new PuzzlePiece(0, 0, size.small, false, unit);
		assertEquals(unit-2,piece.getWidth());
		//test large
		piece = new PuzzlePiece(0, 0, size.large, false, unit);
		assertEquals(unit*2 - 2, piece.getWidth());	}

	@Test
	void testCheckIfLarge() {
		piece = new PuzzlePiece(0, 0, size.small, false, unit);
		assertFalse(piece.checkIfLarge());
		piece = new PuzzlePiece(0, 0, size.med, false, unit);
		assertFalse(piece.checkIfLarge());
		piece = new PuzzlePiece(0, 0, size.large, false, unit);
		assertTrue(piece.checkIfLarge());
	}
	@Test
	void testNextX() {
		piece = new PuzzlePiece(0, 0, size.med, false, unit);
		assertEquals(unit+1,piece.nextX(direction.right));
		
	}

	@Test
	void testNextY() {
		piece = new PuzzlePiece(0, 0, size.med, false, unit);
		assertEquals(unit+1,piece.nextY(direction.down));
	}

	@Test
	void textContained() {
		//choose points known to be inside the piece
		piece = new PuzzlePiece(0,0,size.small,false,unit);
		assertTrue(piece.isContainedIn(unit/2, unit/2));
		piece = new PuzzlePiece(0,0,size.med,false,unit);
		assertTrue(piece.isContainedIn(unit, unit/2));
		piece = new PuzzlePiece(0,0,size.large,false,unit);
		assertTrue(piece.isContainedIn(unit, unit));
		
		//test conditions that are outside of the piece
		piece = new PuzzlePiece(0,0,size.small,false,unit);
		assertFalse(piece.isContainedIn(unit*2, unit*2));
		piece = new PuzzlePiece(0,0,size.med,false,unit);
		assertFalse(piece.isContainedIn(unit*2, unit*2));
		piece = new PuzzlePiece(0,0,size.large,false,unit);
		assertFalse(piece.isContainedIn(3*unit, unit));
	}

	@Test
	void testMove() {
		piece = new PuzzlePiece(0,0,size.small,false,unit);
		piece.move(direction.right);
		assertEquals(unit+1,piece.getX());
		piece.move(direction.left);
		assertEquals(1,piece.getX());
		piece.move(direction.down);
		assertEquals(unit+1,piece.getY());
		piece.move(direction.up);
		assertEquals(1,piece.getY());
	}

}
