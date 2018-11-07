package project.model;

public class PuzzlePiece {

	int x_pos, y_pos, height, width, unit, pose_bias;
	boolean is_vertical, is_large;
	public enum direction{
		up,
		down,
		left,
		right
	}
	public enum size{
		small,
		med,
		large
	}
	public int getX() {
		return this.x_pos;
	}
	public int getY() {
		return this.y_pos;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public boolean checkIfLarge() {
		return this.is_large;
	}
	public PuzzlePiece(int x_pos, int y_pos, size s, boolean is_virtical, int unit) {
		int gap_btwn_pieces;
		//bias the x and y positions by 1 pixel to make gaps between puzzle pieces
		this.pose_bias = 1;
		gap_btwn_pieces = this.pose_bias * 2;
		this.x_pos = x_pos + 1;
		this.y_pos = y_pos + 1;
		this.unit = unit;
		//set width and height depending on what size piece
		if(s == size.med) {
			//medium size piece cares about virticle or horizontal orientation
			this.is_large = false;
			if( is_virtical) {
				this.height = 2 * this.unit - gap_btwn_pieces;
				this.width = this.unit - gap_btwn_pieces;
			}else {
				this.height = this.unit - gap_btwn_pieces;
				this.width = 2*this.unit - gap_btwn_pieces;
			}
		} 
		else if(s == size.large) {
			this.is_large = true;
			this.height = 2 * this.unit - gap_btwn_pieces;
			this.width = 2 * this.unit - gap_btwn_pieces;
		} 
		else if(s == size.small) {
			this.is_large = false;
			this.height = this.unit - gap_btwn_pieces;
			this.width = this.unit - gap_btwn_pieces;
		}
	}
	public void move(direction d) {
		if(d == direction.up) {
			this.y_pos = this.y_pos - (this.unit);
		}else if(d == direction.down) {
			this.y_pos = this.y_pos + (this.unit);
		} 
		if(d == direction.left) {
			this.x_pos = this.x_pos - (this.unit);
		}else if(d == direction.right) {
			this.x_pos = this.x_pos + (this.unit);
		}
	}
	public int nextX(direction d) {
		if((d == direction.up) || (d == direction.down)) {
			return this.x_pos;
		}
		else if(d == direction.left) {
			return this.x_pos - this.unit;
		}
		else
			return this.x_pos + this.unit;
	}
	public int nextY(direction d) {
		if((d == direction.left) || (d == direction.right)) {
			return this.y_pos;
		}
		else if(d == direction.up) {
			return this.y_pos - this.unit;
		}
		else
			return this.y_pos + this.unit;
	}
	public boolean isContainedIn(int questionable_x, int questionable_y) {
		return (checkX(questionable_x) && checkY(questionable_y));		
	}
	boolean checkX(int q_x) {
		return (q_x < (this.x_pos + this.width)) && (q_x > this.x_pos);
	}
	boolean checkY(int q_y) {
		return (q_y < (this.y_pos + this.height)) && (q_y > this.y_pos);
	}
}
