package steven.conaway.tictactoe;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

public class TicTacToeButton extends JButton {

	private static final long serialVersionUID = 1L;
	private static final int MAXSIZE = 100;
	
	private static final String oimage = "images/o.jpg";
	private static final String ximage = "images/x.jpg";
	
	private Boolean unlocked = true;
	private Boolean o;
	private Boolean x;
	private Boolean clicked = false;
	
	private int row = 0;
	private int col = 0;
	
	private final ClassLoader classloader = this.getClass().getClassLoader(); // used to retrieve images
	
	public TicTacToeButton (int row, int col) {
		this.row = row;
		this.col = col;
		setBackground(Color.WHITE);
		Dimension size = new Dimension(MAXSIZE,MAXSIZE);
		setPreferredSize(size);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setO() {
		if (unlocked) {
			setIcon(new ImageIcon(classloader.getResource(oimage)));
		}
		clicked = true;
		unlocked = false;
		o = true;
		x = false;
	}
	
	public void setX() {
		if (unlocked) {
			setIcon(new ImageIcon(classloader.getResource(ximage)));
		}
		clicked = true;
		unlocked = false;
		o = false;
		x = true;
	}
	
	public void reset() {
		setBackground(Color.WHITE);
		clicked = false;
		unlocked = true;
		o = false;
		x = false;
	}
	
	public Boolean isO() {
		return o;
	}
	
	public Boolean isX() {
		return x;
	}
	
	public Boolean isClicked() {
		return clicked;
	}
}
