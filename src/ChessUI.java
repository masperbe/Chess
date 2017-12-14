import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ChessUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChessUI window = new ChessUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChessUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1500, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewGame = new JButton("New Game?");
		btnNewGame.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 82));
		btnNewGame.setBounds(12, 825, 720, 115);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnDrawAccepted = new JButton("Propose draw?");
		btnDrawAccepted.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 82));
		btnDrawAccepted.setBounds(744, 825, 726, 115);
		frame.getContentPane().add(btnDrawAccepted);
		
		JLabel lblTextOutput = new JLabel("Would you like to play a game of chess?");
		lblTextOutput.setVerticalAlignment(SwingConstants.TOP);
		lblTextOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextOutput.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 38));
		lblTextOutput.setBounds(12, 726, 1458, 86);
		frame.getContentPane().add(lblTextOutput);
		
		JButton btnChessBoardGoes = new JButton("Chess board goes here");
		btnChessBoardGoes.setBounds(286, 50, 953, 663);
		frame.getContentPane().add(btnChessBoardGoes);
		
		JButton btnCapturedWhite = new JButton("Caputred white pieces go here");
		btnCapturedWhite.setBounds(35, 50, 239, 314);
		frame.getContentPane().add(btnCapturedWhite);
		
		JButton btnCapturedBlack = new JButton("Captured black pieces go here");
		btnCapturedBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCapturedBlack.setBounds(35, 369, 239, 344);
		frame.getContentPane().add(btnCapturedBlack);
		
		JButton btnMovePointer = new JButton("Move pointer goes here");
		btnMovePointer.setBounds(1251, 219, 219, 325);
		frame.getContentPane().add(btnMovePointer);
	}
}
