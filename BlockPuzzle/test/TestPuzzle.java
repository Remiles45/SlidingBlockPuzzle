import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import project.model.Puzzle;
import project.model.PuzzlePiece;
import project.model.PuzzlePiece.direction;
import project.model.PuzzlePiece.size;

class TestPuzzle {
	int unit = 100;
	Puzzle puzzle = new Puzzle();
	@Test
	void testGetPuzzle() {
		puzzle.defaultModel();
		assertEquals(puzzle.setPuzzle(),puzzle.getPuzzle());
	}
	@Test
	void testPuzzleSize() {
		//check that there are 10 pieces in the default puzzle
		puzzle.defaultModel();
		assertEquals(10,puzzle.getPuzzleSize());
	}
	@Test
	void testGetPieceX() {
		//know that the first piece in the puzzle is: PuzzlePiece(0,0,size.med, true,this.unit)
		puzzle.defaultModel();
		assertEquals(1,puzzle.getPieceX(0));
	}
	@Test
	void testGetPieceY() {
		//know that the first piece in the puzzle is: PuzzlePiece(0,0,size.med, true,this.unit)
		puzzle.defaultModel();
		assertEquals(1,puzzle.getPieceY(0));
	}
	@Test
	void testMovePiece() {
		//load default puzzle, then move a piece and check x / y
		puzzle.defaultModel();
		puzzle.movePiece(0, direction.right);
		assertEquals(unit+1, puzzle.getPieceX(0));
		puzzle.movePiece(0, direction.left);
		assertEquals(1,puzzle.getPieceX(0));
		puzzle.movePiece(0, direction.down);
		assertEquals(unit+1,puzzle.getPieceY(0));
		puzzle.movePiece(0, direction.up);
		assertEquals(1, puzzle.getPieceY(0));
	}
}
