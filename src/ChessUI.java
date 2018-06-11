
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
	
	Board board;
	private JFrame frame;
	private JTextField textFieldOrigin;
	private JTextField textFieldDestination;

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
		btnNewGame.setBounds(216, 0, 568, 115);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				board = new Board();
				for (int i=0; i<8; i++)
					for (int j=0; j<8; j++)
						if (board.board[i][j].isOccupied()) {
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
						}  else {
							squareIcon[i][j].setIcon(null);
						}
							
				for (int i=0; i<8; i++) {
					for (int j=0; j<8; j++) {
						squareIcon[i][j].setBounds(225 + (71*i), 632 - (71*j),50,50);
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
		btnDrawAccepted.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 30));
		btnDrawAccepted.setBounds(1005, 660, 165, 80);
		frame.getContentPane().add(btnDrawAccepted);
		
		JLabel lblTextOutput = new JLabel("<html>Would you like to play a game of chess?</html>"); 
		lblTextOutput.setVerticalAlignment(SwingConstants.TOP);
		lblTextOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextOutput.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 38));
		lblTextOutput.setBounds(818, 143, 352, 144);
		frame.getContentPane().add(lblTextOutput);
		
		JButton btnCapturedWhite = new JButton("Captured white pieces");
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
		lblMovePiece.setBounds(818, 280, 239, 44);
		frame.getContentPane().add(lblMovePiece);
		
		textFieldOrigin = new JTextField();
		textFieldOrigin.setBounds(818, 357, 116, 22);
		frame.getContentPane().add(textFieldOrigin);
		textFieldOrigin.setColumns(10);
		
		textFieldDestination = new JTextField();
		textFieldDestination.setBounds(818, 427, 116, 22);
		frame.getContentPane().add(textFieldDestination);
		textFieldDestination.setColumns(10);
		
		JLabel lblCanMove = new JLabel("");
		lblCanMove.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 36));
		lblCanMove.setBounds(818, 567, 352, 80);
		frame.getContentPane().add(lblCanMove);
		
		JButton btnForfeit = new JButton("Forfeit?");
		btnForfeit.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 30));
		btnForfeit.setBounds(828, 660, 165, 80);
		frame.getContentPane().add(btnForfeit);
		
		JButton btnSubmitMove = new JButton("Submit Move?");
		btnSubmitMove.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 36));
		btnSubmitMove.setBounds(818, 497, 352, 80);
		btnSubmitMove.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent ae) {
				String origin = textFieldOrigin.getText();
				String destination = textFieldDestination.getText();
				int x1;
				int y1;
				int x2;
				int y2;
				Piece.TypePiece promote = Piece.TypePiece.PAWN;
				try {
					switch (origin.charAt(0)) {
						case 'a':
							x1 = 0;
							break;
						case 'b':
							x1 = 1;
							break;
						case 'c':
							x1 = 2;
							break;
						case 'd':
							x1 = 3;
							break;
						case 'e':
							x1 = 4;
							break;
						case 'f':
							x1 = 5;
							break;
						case 'g':
							x1 = 6;
							break;
						case 'h':
							x1 = 7;
							break;
						default:
							throw new Exception(); // Exception("explanation")
					}
					switch (origin.charAt(1)) {
						case '1':
							y1 = 0;
							break;
						case '2':
							y1 = 1;
							break;
						case '3':
							y1 = 2;
							break;
						case '4':
							y1 = 3;
							break;
						case '5':
							y1 = 4;
							break;
						case '6':
							y1 = 5;
							break;
						case '7':
							y1 = 6;
							break;
						case '8':
							y1 = 7;
							break;
						default:
							throw new Exception();
					}
					switch (destination.charAt(0)) {
						case 'a':
							x2 = 0;
							break;
						case 'b':
							x2 = 1;
							break;
						case 'c':
							x2 = 2;
							break;
						case 'd':
							x2 = 3;
							break;
						case 'e':
							x2 = 4;
							break;
						case 'f':
							x2 = 5;
							break;
						case 'g':
							x2 = 6;
							break;
						case 'h':
							x2 = 7;
							break;
						default:
							throw new Exception();
					}
					switch (destination.charAt(1)) {
						case '1':
							y2 = 0;
							break;
						case '2':
							y2 = 1;
							break;
						case '3':
							y2 = 2;
							break;
						case '4':
							y2 = 3;
							break;
						case '5':
							y2 = 4;
							break;
						case '6':
							y2 = 5;
							break;
						case '7':
							y2 = 6;
							break;
						case '8':
							y2 = 7;
							break;
						default:
							throw new Exception();
					}
					
					if ((y2 == 7 || y2 == 0) && board.board[x1][y1].getPiece().type() == Piece.TypePiece.PAWN) {
						if (destination.length() == 4) {
							switch (destination.charAt(3)) {
								case 'Q':
									promote = Piece.TypePiece.QUEEN;
									break;
								case 'R':
									promote = Piece.TypePiece.ROOK;
									break;
								case 'B':
									promote = Piece.TypePiece.BISHOP;
									break;
								case 'N':
									promote = Piece.TypePiece.KNIGHT;
									break;
								default:
									throw new Exception();
							}
						} else {
							throw new Exception();
						}
					}
					
					board.move(x1, y1, x2, y2, promote);

					for (int i=0; i<8; i++)
						for (int j=0; j<8; j++)
							if (board.board[i][j].isOccupied()) {
								if (board.board[i][j].getPiece().isWhite()) {
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
								} else {
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
								}
							} else {
								squareIcon[i][j].setIcon(null);
							}
					for (int i=0; i<8; i++) {
						for (int j=0; j<8; j++) {
							squareIcon[i][j].setBounds(225 + (71*i), 632 - (71*j),50,50);
							frame.getContentPane().add(squareIcon[i][j]);
						}
					}
					if(board.getCheck()){
						lblCanMove.setText("You are in check!");
					}
					else{
						lblCanMove.setText("");
					}
					frame.getContentPane().add(boardImg);
					frame.repaint();
				}
				catch (Exception e) {
					lblCanMove.setText("Invalid entry");
				}
			}
		});
		frame.getContentPane().add(btnSubmitMove);
		
		JLabel lblOriginSquare = new JLabel("Origin Square");
		lblOriginSquare.setBounds(821, 330, 101, 14);
		frame.getContentPane().add(lblOriginSquare);
		
		JLabel lblDestinationSquare = new JLabel("Destination Square");
		lblDestinationSquare.setBounds(818, 392, 133, 14);
		frame.getContentPane().add(lblDestinationSquare);
		
		JLabel lblA = new JLabel("A");
		lblA.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 24));
		lblA.setBounds(245, 715, 28, 16);
		frame.getContentPane().add(lblA);
		
		JLabel lblB = new JLabel("B");
		lblB.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 24));
		lblB.setBounds(316, 715, 28, 16);
		frame.getContentPane().add(lblB);
		
		JLabel lblC = new JLabel("C");
		lblC.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 24));
		lblC.setBounds(387, 715, 28, 16);
		frame.getContentPane().add(lblC);
		
		JLabel lblD = new JLabel("D");
		lblD.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 24));
		lblD.setBounds(458, 715, 28, 16);
		frame.getContentPane().add(lblD);
		
		JLabel lblE = new JLabel("E");
		lblE.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 24));
		lblE.setBounds(529, 715, 28, 16);
		frame.getContentPane().add(lblE);
		
		JLabel lblF = new JLabel("F");
		lblF.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 24));
		lblF.setBounds(600, 715, 28, 16);
		frame.getContentPane().add(lblF);
		
		JLabel lblG = new JLabel("G");
		lblG.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 24));
		lblG.setBounds(671, 715, 28, 16);
		frame.getContentPane().add(lblG);
		
		JLabel lblH = new JLabel("H");
		lblH.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 24));
		lblH.setBounds(742, 715, 28, 16);
		frame.getContentPane().add(lblH);
		
		JLabel lbl8 = new JLabel("8");
		lbl8.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 24));
		lbl8.setBounds(796, 155, 20, 16);
		frame.getContentPane().add(lbl8);
		
		JLabel lbl7 = new JLabel("7");
		lbl7.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 24));
		lbl7.setBounds(796, 226, 20, 16);
		frame.getContentPane().add(lbl7);
		
		JLabel lbl6 = new JLabel("6");
		lbl6.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 24));
		lbl6.setBounds(796, 297, 20, 16);
		frame.getContentPane().add(lbl6);
		
		JLabel lbl5 = new JLabel("5");
		lbl5.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 24));
		lbl5.setBounds(796, 368, 20, 16);
		frame.getContentPane().add(lbl5);
		
		JLabel lbl4 = new JLabel("4");
		lbl4.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 24));
		lbl4.setBounds(796, 439, 20, 16);
		frame.getContentPane().add(lbl4);
		
		JLabel lbl3 = new JLabel("3");
		lbl3.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 24));
		lbl3.setBounds(796, 510, 20, 16);
		frame.getContentPane().add(lbl3);
		
		JLabel lbl2 = new JLabel("2");
		lbl2.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 24));
		lbl2.setBounds(796, 581, 20, 16);
		frame.getContentPane().add(lbl2);
		
		JLabel lbl1 = new JLabel("1");
		lbl1.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 24));
		lbl1.setBounds(796, 652, 20, 16);
		frame.getContentPane().add(lbl1);
	}
}
