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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class ChessUI {

	private JFrame frame;
	private JTextField textFieldX1;
	private JTextField textFieldY1;
	private JTextField textFieldX2;
	private JTextField textFieldY2;

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
		Board board = new Board();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ImageIcon boardIcon = new ImageIcon("img/cboard.png");
		JLabel boardImg = new JLabel();
		boardImg.setIcon(boardIcon);
		boardImg.setBounds(216,128,568,568);
		frame.getContentPane().add(boardImg);
		
		ImageIcon whitePawnIcon = new ImageIcon("img/wpawn.png");
		ImageIcon whiteKnightIcon = new ImageIcon("img/wknight.png");
		ImageIcon whiteBishopIcon = new ImageIcon("img/wbishop.png");
		ImageIcon whiteRookIcon = new ImageIcon("img/wrook.png");
		ImageIcon whiteQueenIcon = new ImageIcon("img/wqueen.png");
		ImageIcon whiteKingIcon = new ImageIcon("img/wking.png");
		ImageIcon blackPawnIcon = new ImageIcon("img/bpawn.png");
		ImageIcon blackKnightIcon = new ImageIcon("img/bknight.png");
		ImageIcon blackBishopIcon = new ImageIcon("img/bbishop.png");
		ImageIcon blackRookIcon = new ImageIcon("img/brook.png");
		ImageIcon blackQueenIcon = new ImageIcon("img/bqueen.png");
		ImageIcon blackKingIcon = new ImageIcon("img/bking.png");
		JLabel[][] squareIcon = new JLabel[8][8];
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				squareIcon[i][j] = new JLabel();
		
		JButton btnNewGame = new JButton("New Game?");
		btnNewGame.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 82));
		btnNewGame.setBounds(161, 0, 720, 115);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				board.newGame();
				for (int i=0; i<8; i++)
					for (int j=0; j<8; j++)
						if (board.board[i][j].isOccupied())
							if (board.board[i][j].getPiece().isWhite())
								switch (board.board[i][j].getPiece().type()) {
									case PAWN: squareIcon[i][j].setIcon(whitePawnIcon);
									break;
									
									case KNIGHT: squareIcon[i][j].setIcon(whiteKnightIcon);
									break;
									
									case BISHOP: squareIcon[i][j].setIcon(whiteBishopIcon);
									break;
									
									case ROOK: squareIcon[i][j].setIcon(whiteRookIcon);
									break;
									
									case QUEEN: squareIcon[i][j].setIcon(whiteQueenIcon);
									break;
									
									case KING: squareIcon[i][j].setIcon(whiteKingIcon);
									break;
								}
							else 
								switch (board.board[i][j].getPiece().type()) {
								case PAWN: squareIcon[i][j].setIcon(blackPawnIcon);
								break;
								
								case KNIGHT: squareIcon[i][j].setIcon(blackKnightIcon);
								break;
								
								case BISHOP: squareIcon[i][j].setIcon(blackBishopIcon);
								break;
								
								case ROOK: squareIcon[i][j].setIcon(blackRookIcon);
								break;
								
								case QUEEN: squareIcon[i][j].setIcon(blackQueenIcon);
								break;
								
								case KING: squareIcon[i][j].setIcon(blackKingIcon);
								break;
							}
				for (int i=0; i<8; i++) {
					for (int j=0; j<8; j++) {
						squareIcon[i][j].setBounds(300 + (101*i), 800 - (101*j),50,50);
						frame.getContentPane().add(squareIcon[i][j]);
					}
				}
				frame.getContentPane().add(boardImg);
				frame.repaint();
			}
		});
		frame.getContentPane().add(btnNewGame);
		
		JButton btnDrawAccepted = new JButton("Propose draw?");
		btnDrawAccepted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDrawAccepted.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 36));
		btnDrawAccepted.setBounds(993, 660, 177, 80);
		frame.getContentPane().add(btnDrawAccepted);
		
		JLabel lblTextOutput = new JLabel("Would you like to play a game of chess?"); 
		lblTextOutput.setVerticalAlignment(SwingConstants.TOP);
		lblTextOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextOutput.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 38));
		lblTextOutput.setBounds(818, 143, 352, 144);
		frame.getContentPane().add(lblTextOutput);
		
		
		//JButton btnChessBoardGoes = new JButton("Chess board goes here");
		//btnChessBoardGoes.setBounds(286, 50, 953, 663);
		//frame.getContentPane().add(btnChessBoardGoes);
		
		JButton btnCapturedWhite = new JButton("Caputred white pieces");
		btnCapturedWhite.setBounds(12, 165, 192, 249);
		frame.getContentPane().add(btnCapturedWhite);
		
		JButton btnCapturedBlack = new JButton("Captured black pieces");
		btnCapturedBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCapturedBlack.setBounds(12, 427, 192, 249);
		frame.getContentPane().add(btnCapturedBlack);
		
		JButton btnMovePointer = new JButton("Move pointer goes here");
		btnMovePointer.setBounds(1251, 219, 219, 325);
		frame.getContentPane().add(btnMovePointer);
		
		JLabel lblMovePiece = new JLabel("Move your pieces here");
		lblMovePiece.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 36));
		lblMovePiece.setBounds(818, 291, 239, 53);
		frame.getContentPane().add(lblMovePiece);
		
		textFieldX1 = new JTextField();
		textFieldX1.setBounds(818, 357, 116, 22);
		frame.getContentPane().add(textFieldX1);
		textFieldX1.setColumns(10);
		
		textFieldY1 = new JTextField();
		textFieldY1.setBounds(818, 392, 116, 22);
		frame.getContentPane().add(textFieldY1);
		textFieldY1.setColumns(10);
		
		textFieldX2 = new JTextField();
		textFieldX2.setBounds(818, 427, 116, 22);
		frame.getContentPane().add(textFieldX2);
		textFieldX2.setColumns(10);
		
		textFieldY2 = new JTextField();
		textFieldY2.setBounds(818, 462, 116, 22);
		frame.getContentPane().add(textFieldY2);
		textFieldY2.setColumns(10);
		
		JLabel lblCanMove = new JLabel("You cant move that piece there");
		lblCanMove.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 36));
		lblCanMove.setBounds(818, 497, 352, 80);
		frame.getContentPane().add(lblCanMove);
		
		JButton btnForfeit = new JButton("Forfeit?");
		btnForfeit.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 36));
		btnForfeit.setBounds(796, 660, 177, 80);
		frame.getContentPane().add(btnForfeit);
	}
}
