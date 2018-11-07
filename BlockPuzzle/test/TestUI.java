import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.view.BlockPuzzleUI;

class TestUI {
	BlockPuzzleUI my_ui;
	@Test
	void testBlockPuzzleUI() {
		my_ui = new BlockPuzzleUI();
		//point within the puzzle
		my_ui.pressedX = 200;
		my_ui.pressedY = 200;
		assertEquals(true, my_ui.in_puzzle_board());
		//point outside the puzzle
		my_ui.pressedX = 600;
		my_ui.pressedY = 600;
		assertEquals(false,my_ui.in_puzzle_board());
	}



}
