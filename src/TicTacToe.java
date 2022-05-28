import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class TicTacToe extends Applet implements ActionListener {

	Random random = new Random();
	Frame frame1 = new Frame();
	Panel title_panel = new Panel();
	Panel button_panel = new Panel();
	Label textfield = new Label();
	Button[] buttons = new Button[9];
	boolean player1_turn;
	boolean gameStarted = false;

	public TicTacToe() {
		frame1.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame1.dispose();
			}
		});

	}

	public void init() { // Applet initialized

		frame1.setSize(512,512);
		frame1.setBackground(Color.BLACK);
		frame1.setLayout(new BorderLayout());
		frame1.setVisible(true);

		textfield.setBackground(new Color(25, 25, 25));
		textfield.setForeground(new Color(25, 255, 0));
		textfield.setFont(new Font("Arial", Font.BOLD, 75));
		textfield.setAlignment(Label.CENTER);
		textfield.setText("Tic-Tac-Toe");

		// textfield.setOpaque(true);

		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0, 0, 800, 100);

		button_panel.setLayout(new GridLayout(3, 3));
		button_panel.setBackground(new Color(204, 202, 204));

		for (int i = 0; i < 9; i++) {
			buttons[i] = new Button();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("Arial", Font.BOLD, 75));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);

		}
		title_panel.add(textfield);
		frame1.add(title_panel, BorderLayout.NORTH);
		frame1.add(button_panel);

		firstTurn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (gameStarted == false)
			return;

		for (int i = 0; i < 9; i++) {
			if (e.getSource() == buttons[i]) {
				if (player1_turn) {
					if (buttons[i].getLabel() == "") {
						buttons[i].setForeground(new Color(255, 0, 0));
						buttons[i].setLabel("X");
						if (!checkWinner()) {
							if (isStalemate()) {
								textfield.setText("Stalemate!");
								disableButtons();
								return;
							}
							player1_turn = false;
							textfield.setText("O Turn");
						}
					}
				} else {
					if (buttons[i].getLabel() == "") {
						buttons[i].setForeground(new Color(0, 0, 255));
						buttons[i].setLabel("O");
						if (!checkWinner()) {
							if (isStalemate()) {
								textfield.setText("Stalemate!");
								disableButtons();
								return;
							}
							player1_turn = true;
							textfield.setText("X Turn");
						}
					}
				}
			}
		}
	}

	public void disableButtons() {
		for (int j = 0; j < 9; j++) {
			buttons[j].setEnabled(false);
		}
	}

	public boolean isStalemate() {
		for (int i = 0; i < 9; i++) {
			if (buttons[i].getLabel() == "")
				return false;
		}
		return true;
	}

	public void firstTurn() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (random.nextInt(2) == 0) {
			player1_turn = true;
			textfield.setText("X Turn");
		} else {
			player1_turn = false;
			textfield.setText("O Turn");
		}
		gameStarted = true;
	}

	public boolean checkWinner() {
		int checkPositions[][] = new int[][]{new int[]{0, 1, 2},
				new int[]{3, 4, 5}, new int[]{6, 7, 8}, new int[]{0, 3, 6},
				new int[]{1, 4, 7}, new int[]{2, 5, 8}, new int[]{0, 4, 8},
				new int[]{2, 4, 6},};

		for (int i = 0; i < checkPositions.length; i++) {
			if ((buttons[checkPositions[i][0]]).getLabel() == "X"
					&& (buttons[checkPositions[i][1]]).getLabel() == "X"
					&& (buttons[checkPositions[i][2]]).getLabel() == "X") {
				xWins(checkPositions[i][0], checkPositions[i][1],
						checkPositions[i][2]);
				return true;
			} else if ((buttons[checkPositions[i][0]]).getLabel() == "O"
					&& (buttons[checkPositions[i][1]]).getLabel() == "O"
					&& (buttons[checkPositions[i][2]]).getLabel() == "O") {
				oWins(checkPositions[i][0], checkPositions[i][1],
						checkPositions[i][2]);
				return true;
			}
		}
		return false;
	}

	public void xWins(int a, int b, int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);

		disableButtons();
		textfield.setText("X wins!");
	}

	public void oWins(int a, int b, int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);

		disableButtons();
		textfield.setText("O wins!");
	}

}