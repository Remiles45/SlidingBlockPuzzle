package project.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import project.model.PuzzlePiece.direction;

public class BlockPuzzleUI extends JFrame {

    public int pressedX, pressedY;//gets manipulated in test case
    int min_x, min_y; 
    int puzzle_width = 400;
    int puzzle_height = 500;
    int edge_width = 100;
    int border_width = 10;
    JLabel move_text;
	String moves_msg;
    PuzzleView this_puzzle;
    public BlockPuzzleUI() {
        initComponents();
    }

    private void initComponents() {
    	//put together the UI window
    	setTitle("Block Puzzle");
        JPanel panel = new JPanel();
        setUpPuzzle();
    	panel.add(this_puzzle);
        panel.setBackground(new java.awt.Color(198, 140, 83));
        Dimension panelSize = new Dimension(puzzle_width + edge_width,puzzle_height + 100);
        panel.setPreferredSize(panelSize );
        //listen to mouse movements
     	panel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
			    MousePressed(evt);
			}
     	});
        panel.addMouseMotionListener(new MouseMotionAdapter(){           
            public void mouseDragged(MouseEvent evt) {
            	MouseDragged(evt);
            }
        });
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        //create reset button
	    JButton button = new JButton("Reset Puzzle");
	    button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			    this_puzzle.resetPuzzle();
			    updateMoveMsg();
		    }
	   });
	   panel.add(button);
	   moves_msg = "Moves: " + this_puzzle.moves;
	   move_text = new JLabel(moves_msg);
	   panel.add(move_text);
	   panel.setVisible(true);
    }
    private void MouseDragged(MouseEvent evt) {
       	int sensitivity = 20;
    	int x_diff = this.pressedX - evt.getX();
    	int y_diff = this.pressedY - evt.getY();
    	//check threshold for how far the mouse had to move 
    	if(in_puzzle_board()) {
	 	    updateMoveMsg();
        	int num_pieces = this_puzzle.getPuzzleSize();
        	int scaled_x = this.pressedX - this_puzzle.getX() - border_width;
        	int scaled_y = this.pressedY - this_puzzle.getY() - border_width;
        	for(int i = 0; i < num_pieces; i++) {
        		if(this_puzzle.testCoordinate(i, scaled_x, scaled_y)) {
        			if(Math.abs(x_diff) > sensitivity) {
                		this.pressedX = evt.getX();
    	            	if(x_diff > 0) {//moved left
                			this_puzzle.move(i, direction.left);
    	            	}
    	            	else { //moved right
                			this_puzzle.move(i, direction.right);
    	            	}
                	} else if(Math.abs(y_diff) > sensitivity) {
                		this.pressedY = evt.getY();
                		if(y_diff > 0) {//moved up
                			this_puzzle.move(i, direction.up);
                		}
                		else {//moved down
                			this_puzzle.move(i, direction.down);
                		}
                	}
        			repaint();
        			break;
        		}
        	}
        }
    }
    private void MousePressed(MouseEvent evt) {
        this.pressedX = evt.getX();
        this.pressedY = evt.getY();
    }
    public boolean in_puzzle_board() {
    	boolean valid_x = (this.pressedX > this.min_x) && (this.pressedX < (this.min_x + puzzle_width));
    	boolean valid_y = (this.pressedY > this.min_y) && (this.pressedY < (this.min_y + puzzle_height));
    	return (valid_x && valid_y);
    }
    public void setUpPuzzle() {
    	this_puzzle = new PuzzleView(0,0,puzzle_width,puzzle_height,border_width);
     	this.min_x = this_puzzle.getX()+edge_width/2;
     	this.min_y = this_puzzle.getY()+border_width;
    }
    private void updateMoveMsg() {
    	moves_msg = "Moves: " + this_puzzle.moves;
	    move_text.setText(moves_msg);
	    repaint();
    }
}
