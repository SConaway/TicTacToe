package steven.conaway.tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TicTacToe extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final int GRIDSIZE = 3;
	private int player = 0;
	private int pressed = 0;
	
	private boolean[][] clicked = new boolean[GRIDSIZE][GRIDSIZE];
	
	private String message;
	
	private JButton resetBtn = new JButton("Reset");
	private JLabel titleLabel = new JLabel();
	private JPanel centerPanel = new JPanel();
	private JPanel btnPanel = new JPanel();
	private Font titleFont = new Font(Font.SERIF, Font.BOLD, 32);
	private TicTacToeButton[][] tttBtn = new TicTacToeButton[GRIDSIZE][GRIDSIZE];
	
	public TicTacToe() {
        initGUI();
        setTitle("Tic Tac Toe");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initGUI() {
		titleLabel.setFont(titleFont);							// Create and add a label to display title to the top of window
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setText("Tic Tac Toe");
		titleLabel.setBackground(Color.BLACK);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setOpaque(true);
		
		add(titleLabel, BorderLayout.PAGE_START);
		
		centerPanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
		add(centerPanel, BorderLayout.CENTER);
		
		for (int row=0; row<GRIDSIZE; row++) {
			for (int col=0; col<GRIDSIZE; col++) {
				tttBtn[row][col] = new TicTacToeButton(row, col);
				tttBtn[row][col].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pressed++;
						TicTacToeButton button = (TicTacToeButton) e.getSource();
						int row = button.getRow();
						int col = button.getCol();
						clicked[row][col] = true;
						reveal(button, clicked[row][col]);
						check(row,col);
					}
				});
				centerPanel.add(tttBtn[row][col]);
			}
		}
		
		btnPanel.setBackground(Color.YELLOW);
		add(btnPanel, BorderLayout.PAGE_END);
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnPanel.add(resetBtn);
	}
	
	private void check(int row, int col) {

		//top left corner
		if (row==0 && col==0) {
			checkTL();
		}
		//top right corner
		if (row==0 && col==2) {
			checkTR();
		}
		//bottom left corner
		if (row==2 && col==0) {
			checkBL();
		}
		//bottom right corner
		if (row==2 && col==2) {
			checkBR();
		}
		//top row middle
		if (row==0 && col==1) {
			checkTM();
		}
		//bottom row middle
		if (row==2 && col==1) {
			checkBM();
		}
		//left side middle
		if (row==1 && col==0) {
			checkLM();
		}
		//right side middle
		if (row==1 && col==2) {
			checkRM();
		}
		//center
		if (row==1 && col==1) {
			checkC();
		}
		
		if (pressed >=9) {
			draw();
		}

	}
	
	private void checkTL() {
		if (clicked[0][1] && clicked[0][2]) { //horizontal
			if (tttBtn[0][1].isO() && tttBtn[0][2].isO()) {
				if (tttBtn[0][0].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[0][1].isX() && tttBtn[0][2].isX()) {
				if (tttBtn[0][0].isX()) {
					displayMessage("X");
				}
			}
		}
		if (clicked[1][0] && clicked[2][0]) { //vertical
			if (tttBtn[1][0].isO() && tttBtn[2][0].isO()) {
				if (tttBtn[0][0].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[1][0].isX() && tttBtn[2][0].isX()) {
				if (tttBtn[0][0].isX()) {
					displayMessage("X");
				}
			}
		}
		if (clicked[1][1] && clicked[2][2]) { //diagonal
			if (tttBtn[1][1].isO() && tttBtn[2][2].isO()) {
				if (tttBtn[0][0].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[1][1].isX() && tttBtn[2][2].isX()) {
				System.out.println("HI");
				if (tttBtn[0][0].isX()) {
					displayMessage("X");
				}
			}
		}
	}
	
	private void checkTR() {
		if (clicked[0][1] && clicked[0][0]) { // horizontal
			if (tttBtn[0][1].isO() && tttBtn[0][0].isO()) {
				if (tttBtn[0][2].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[0][1].isX() && tttBtn[0][0].isX()) {
				if (tttBtn[0][2].isX()) {
					displayMessage("X");
				}
			}
		}
	
  	if (clicked[1][2] && clicked[2][2]) { // vertical
			if (tttBtn[1][2].isO() && tttBtn[2][2].isO()) {
				if (tttBtn[0][2].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[1][2].isX() && tttBtn[2][2].isX()) {
				if (tttBtn[0][2].isX()) {
					displayMessage("X");
				}
			}
		}
		if (clicked[1][1] && clicked[2][0]) { // diagonal
			if (tttBtn[1][1].isO() && tttBtn[2][0].isO()) {
				if (tttBtn[0][2].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[1][1].isX() && tttBtn[2][0].isX()) {
				if (tttBtn[0][2].isX()) {
					displayMessage("X");
				}
			}
		}
		
	}
	
	private void checkBL() {
		if (clicked[2][1] && clicked[2][2]) { //horizontal
			if (tttBtn[2][1].isO() && tttBtn[2][2].isO()) {
				if (tttBtn[2][0].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[2][1].isX() && tttBtn[2][2].isX()) {
				if (tttBtn[2][0].isX()) {
					displayMessage("X");
				}
			}
		}
		if (clicked[1][0] && clicked[0][0]) { // vertical
			if (tttBtn[1][0].isO() && tttBtn[0][0].isO()) {
				if (tttBtn[2][0].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[1][0].isX() && tttBtn[0][0].isX()) {
				if (tttBtn[2][0].isX()) {
					displayMessage("X");
				}
			}
		}
		if (clicked[1][1] && clicked[0][2]) { //diagonal
			if (tttBtn[1][1].isO() && tttBtn[0][2].isO()) {
				if (tttBtn[2][0].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[1][1].isX() && tttBtn[0][2].isX()) {
				if (tttBtn[2][0].isX()) {
					displayMessage("X");
				}
			}
		}
	}
	
	private void checkBR() {
		if (clicked[2][1] && clicked[2][0]) { // horizontal
			if (tttBtn[2][1].isO() && tttBtn[2][0].isO()) {
				if (tttBtn[2][2].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[2][1].isX() && tttBtn[2][0].isX()) {
				if (tttBtn[2][2].isX()) {
					displayMessage("X");
				}
			}
		}
		if (clicked[1][2] && clicked[0][2]) { // vertical
			if (tttBtn[1][2].isO() && tttBtn[0][2].isO()) {
				if (tttBtn[2][2].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[1][2].isX() && tttBtn[0][2].isX()) {
				if (tttBtn[2][2].isX()) {
					displayMessage("X");
				}
			}
		}
		if (clicked[1][1] && clicked[0][0]) { // diagonal
			if (tttBtn[1][1].isO() && tttBtn[0][0].isO()) {
				if (tttBtn[2][2].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[1][1].isX() && tttBtn[0][0].isX()) {
				if (tttBtn[2][2].isX()) {
					displayMessage("X");
				}
			}
		}
	}
	
	private void checkTM() {
		if (clicked[0][0] && clicked[0][2]) { //horizontal
			if (tttBtn[0][0].isO() && tttBtn[0][2].isO()) {
				if (tttBtn[0][1].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[0][0].isX() && tttBtn[0][2].isX()) {
				if (tttBtn[0][1].isX()) {
					displayMessage("X");
				}
			}
		}
		if(clicked[1][1] && clicked[2][1]) { // vertical
			if (tttBtn[1][1].isO() && tttBtn[2][1].isO()) {
				if (tttBtn[0][1].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[1][1].isX() && tttBtn[2][1].isX()) {
				if (tttBtn[0][1].isX()) {
					displayMessage("X");
				}
			}
		}
	}
	
	private void checkBM() {
		if (clicked[2][0] && clicked[2][2]) { //horizontal
			if (tttBtn[2][0].isO() && tttBtn[2][2].isO()) {
				if (tttBtn[2][1].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[2][0].isX() && tttBtn[2][2].isX()) {
				if (tttBtn[2][1].isX()) {
					displayMessage("X");
				}
			}
		}
		if(clicked[1][1] && clicked[0][1]) { // vertical
			if (tttBtn[1][1].isO() && tttBtn[0][1].isO()) {
				if (tttBtn[2][1].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[1][1].isX() && tttBtn[0][1].isX()) {
				if (tttBtn[2][1].isX()) {
					displayMessage("X");
				}
			}
		}
	}
	
	private void checkLM() {
		if (clicked[1][1] && clicked[1][2]) { //horizontal
			if (tttBtn[1][1].isO() && tttBtn[1][2].isO()) {
				if (tttBtn[1][0].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[1][1].isX() && tttBtn[1][2].isX()) {
				if (tttBtn[1][0].isX()) {
					displayMessage("X");
				}
			}
		}
		if(clicked[0][0] && clicked[2][0]) { // vertical
			if (tttBtn[0][0].isO() && tttBtn[2][0].isO()) {
				if (tttBtn[1][0].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[0][0].isX() && tttBtn[2][0].isX()) {
				if (tttBtn[1][0].isX()) {
					displayMessage("X");
				}
			}
		}
	}
	
	private void checkRM() {
		if (clicked[1][1] && clicked[1][0]) { //horizontal
			if (tttBtn[1][1].isO() && tttBtn[1][0].isO()) {
				if (tttBtn[1][2].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[1][1].isX() && tttBtn[1][0].isX()) {
				if (tttBtn[1][2].isX()) {
					displayMessage("X");
				}
			}
		}
		if(clicked[0][2] && clicked[2][2]) { // vertical
			if (tttBtn[0][2].isO() && tttBtn[2][2].isO()) {
				if (tttBtn[1][2].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[0][2].isX() && tttBtn[2][2].isX()) {
				if (tttBtn[1][2].isX()) {
					displayMessage("X");
				}
			}
		}
	}
	
	private void checkC() {
		if (clicked[1][0] && clicked[1][2]) { // horizontal
			if (tttBtn[1][0].isO() && tttBtn[1][2].isO()) {
				if (tttBtn[1][1].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[1][0].isX() && tttBtn[1][2].isX()) {
				if (tttBtn[1][1].isX()) {
					displayMessage("X");
				}
			}
		}
		if (clicked[0][1] && clicked[2][1]) { // vertical
			if (tttBtn[0][1].isO() && tttBtn[2][1].isO()) {
				if (tttBtn[1][1].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[0][1].isX() && tttBtn[2][1].isX()) {
				if (tttBtn[1][1].isX()) {
					displayMessage("X");
				}
			}
		}
		if (clicked[0][0] && clicked[2][2]) { //diagonal top-left to bottom-right
			if (tttBtn[0][0].isO() && tttBtn[2][2].isO()) {
				if (tttBtn[1][1].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[0][0].isX() && tttBtn[2][2].isX()) {
				if (tttBtn[1][1].isX()) {
					displayMessage("X");
				}
			}
		}
		if (clicked[0][2] && clicked[2][0]) { //diagonal top-right to bottom-left
			if (tttBtn[0][2].isO() && tttBtn[2][0].isO()) {
				if (tttBtn[1][1].isO()) {
					displayMessage("O");
				}
			}
			else if (tttBtn[0][2].isX() && tttBtn[2][0].isX()) {
				if (tttBtn[1][1].isX()) {
					displayMessage("X");
				}
			}
		}
	}
	
	private void draw() {
		displayMessage("draw");
	}
	
	private void displayMessage(String str) {
		if (str.equalsIgnoreCase("o")) {
			message = "Congratulations! Player " + 1 + " (O) won!";
		}
		else if (str.equalsIgnoreCase("x")) {
			message = "Congratulations! Player " + 2 + " (X) won!";
		}
		else if (str.equalsIgnoreCase("draw")) {
			message = "Unfortunately, the game was a draw.";
		}
        JOptionPane.showMessageDialog(this, message);
        System.exit(0);
    }
    
    private void reveal(TicTacToeButton btn, boolean clicked) {
    	if (clicked) {
    		if (player == 0) {
    			btn.setO();
    			player = 1;
    		}
    		else if (player == 1) {
    			btn.setX();
    			player = 0;
    		}
    	}
        
    }

    private void reset() {
    	for (int row=0; row<GRIDSIZE; row++) {
			for (int col=0; col<GRIDSIZE; col++) {
				tttBtn[row][col].reset();
			}
		}
    	message = null;
    	player = 0;
    	pressed = 0;
    }
    
	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}
	  	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TicTacToe();
				}
		});

	}

}
