import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TTT extends Frame implements ActionListener {
	
	private JButton[][] buttons = new JButton[3][3];
	private JButton reset;
	private JLabel l1;
	private Random r = new Random();
	
	public TTT(){
		
		JFrame frame = new JFrame("Tic-Tac-Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		
		l1 = new JLabel("Tic Tac Toe. You are X. Computer is O.");
		l1.setPreferredSize(new Dimension(310, 100));
		panel.add(l1);
		
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[0].length; j++){
				buttons[i][j] = new JButton("-");
				buttons[i][j].setPreferredSize(new Dimension(100, 100));
				buttons[i][j].setBackground(Color.YELLOW);
				panel.add(buttons[i][j]);
				buttons[i][j].addActionListener(this);
			}
		}

		reset = new JButton("Reset");
		reset.setPreferredSize(new Dimension(310, 100));
		panel.add(reset);
		reset.addActionListener(this);
		
		panel.setPreferredSize(new Dimension(320, 530));
		
		Container con = frame.getContentPane();
		con.add(panel, BorderLayout.LINE_START);
		
		frame.pack();
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == reset){
		  for(int i = 0; i < buttons.length; i++){
		    for(int j = 0; j < buttons[0].length; j++){
					buttons[i][j].setEnabled(true);
					buttons[i][j].setBackground(Color.YELLOW);
		      buttons[i][j].setText("-");
		    }
		  }
		} else {
  		for(int i = 0; i < buttons.length; i++) {
  			for(int j = 0; j < buttons[0].length; j++) {
  				if(e.getSource() == buttons[i][j]) {
  					buttons[i][j].setText("X");
  					buttons[i][j].setBackground(Color.GREEN);
  					buttons[i][j].setEnabled(false);
  					if(checkWin() == 1) {
  						for(int k = 0; k < buttons.length; k ++) {
  							for(int l = 0; l < buttons[0].length; l++) {
  								buttons[k][l].setEnabled(false);
  							}
  						}
  						JOptionPane.showMessageDialog(null, "You Win!");
  					} else {
  						comp();
  						if(checkWin() == 2) {
  							for(int k = 0; k < buttons.length; k ++) {
  								for(int l = 0; l < buttons[0].length; l++) {
  									buttons[k][l].setEnabled(false);
  								}
  							}
  							JOptionPane.showMessageDialog(null, "You Lose!");
  						} else if(checkWin() == 3) {
  						  JOptionPane.showMessageDialog(null, "Tie!");
  						}
  					}
				  }
  			}
			}
		}
	}
	
	public void comp() {
		boolean pickAnother = true;
		while(pickAnother) {
			int x = r.nextInt(3), y = r.nextInt(3);
			if(buttons[x][y].isEnabled() == true) {
				buttons[x][y].setText("O");
				buttons[x][y].setBackground(Color.RED);
				buttons[x][y].setEnabled(false);
				pickAnother = false;
			}
		}
	}
	
	public int checkWin() {
	  int disabled = 0;
		for(int i = 0; i < buttons.length; i++) {
			if(buttons[i][0].getText().equals(buttons[i][1].getText()) && buttons[i][0].getText().equals(buttons[i][2].getText())) {
				if(buttons[i][0].getText().equals("X")) {
					return 1;
				} else if(buttons[i][0].getText().equals("O")) {
					return 2;
				} else {
					return 0;
				}
			}
			for(int j = 0; j < buttons[0].length; j++) {
				if(buttons[0][j].getText().equals(buttons[1][j].getText()) && buttons[0][j].getText().equals(buttons[2][j].getText())) {
					if(buttons[0][j].getText().equals("X")) {
						return 1;
					} else if(buttons[0][j].getText().equals("O")) {
						return 2;
					} else {
						return 0;
					}
				}
				disabled = disabled + (buttons[i][j].isEnabled() == false ? 1 : 0);
			}
		}
		if(buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[0][0].getText().equals(buttons[2][2].getText())){
			if(buttons[0][0].getText().equals("X")) {
				return 1;
			} else if(buttons[0][0].getText().equals("O")) {
				return 2;
			} else {
				return 0;
			}
		} else if(buttons[2][0].getText().equals(buttons[1][1].getText()) && buttons[2][0].getText().equals(buttons[0][2].getText())){
			if(buttons[2][0].getText().equals("X")) {
				return 1;
			} else if(buttons[2][0].getText().equals("O")) {
				return 2;
			} else {
				return 0;
			}
		} else if (disabled == 9){
		  return 3;
		} else {
			return 0;
		}
		//TODO: tie ends in endless loop
	}
}
