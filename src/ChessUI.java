
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
	private int endGameConsent = 0;

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
		ImageIcon wPawnCapPNG = new ImageIcon("img/wpawnCap.png");
		ImageIcon wKnightCapPNG = new ImageIcon("img/wknightCap.png");
		ImageIcon wBishopCapPNG = new ImageIcon("img/wbishopCap.png");
		ImageIcon wRookCapPNG = new ImageIcon("img/wrookCap.png");
		ImageIcon wQueenCapPNG = new ImageIcon("img/wqueenCap.png");
		ImageIcon bPawnCapPNG = new ImageIcon("img/bpawnCap.png");
		ImageIcon bKnightCapPNG = new ImageIcon("img/bknightCap.png");
		ImageIcon bBishopCapPNG = new ImageIcon("img/bbishopCap.png");
		ImageIcon bRookCapPNG = new ImageIcon("img/brookCap.png");
		ImageIcon bQueenCapPNG = new ImageIcon("img/bqueenCap.png");
		JLabel[][] squareIcon = new JLabel[8][8];
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				squareIcon[i][j] = new JLabel();
		JLabel[] wPawnLbl = new JLabel[8];
		for (int i=0; i<8; i++)
			wPawnLbl[i] = new JLabel();
		JLabel[] wKnightLbl = new JLabel[10];
		JLabel[] wBishopLbl = new JLabel[10];
		JLabel[] wRookLbl = new JLabel[10];
		for (int i=0; i<10; i++){
			wKnightLbl[i] = new JLabel();
			wBishopLbl[i] = new JLabel();
			wRookLbl[i] = new JLabel();
		}
		JLabel[] wQueenLbl = new JLabel[9];
		for (int i=0; i<9; i++)
			wQueenLbl[i] = new JLabel();
		
		JLabel[] bPawnLbl = new JLabel[8];
		for (int i=0; i<8; i++)
			bPawnLbl[i] = new JLabel();
		JLabel[] bKnightLbl = new JLabel[10];
		JLabel[] bBishopLbl = new JLabel[10];
		JLabel[] bRookLbl = new JLabel[10];
		for (int i=0; i<10; i++){
			bKnightLbl[i] = new JLabel();
			bBishopLbl[i] = new JLabel();
			bRookLbl[i] = new JLabel();
		}
		JLabel[] bQueenLbl = new JLabel[9];
		for (int i=0; i<9; i++)
			bQueenLbl[i] = new JLabel();
		
		JLabel lblTextOutput = new JLabel("<html>Would you like to play a game of chess?</html>"); 
		textFieldDestination = new JTextField();
		JButton btnDrawAccepted = new JButton("Propose draw?");
		JButton btnForfeit = new JButton("Forfeit?");
		
		
		JButton btnNewGame = new JButton("New Game?");
		btnNewGame.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 82));
		btnNewGame.setBounds(216, 0, 568, 115);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				lblTextOutput.setText("White's move");
				textFieldDestination.setEnabled(true);
				btnDrawAccepted.setEnabled(true);
				btnForfeit.setEnabled(true);
				//TODO: make the forfeit button and draw button appear so you have to start a game to draw or forfeit
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
		
		lblTextOutput.setVerticalAlignment(SwingConstants.TOP);
		lblTextOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextOutput.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 38));
		lblTextOutput.setBounds(818, 143, 352, 144);
		frame.getContentPane().add(lblTextOutput);
		
		textFieldOrigin = new JTextField();
		textFieldOrigin.setBounds(818, 357, 116, 22);
		frame.getContentPane().add(textFieldOrigin);
		textFieldOrigin.setColumns(10);
		
		textFieldDestination.setBounds(818, 427, 116, 22);
		frame.getContentPane().add(textFieldDestination);
		textFieldDestination.setColumns(10);
		
		
		btnDrawAccepted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(endGameConsent == 0){
					lblTextOutput.setText("<html>To accept, press draw. To deny, press forfeit.</html>");
					endGameConsent = 1;
				}else if(endGameConsent == 2){
					lblTextOutput.setText("The forfeit has been cancled.");
					endGameConsent = 0;
					if(board.isWhiteMove()){
						lblTextOutput.setText("White's move");
					}else lblTextOutput.setText("Black's move");
				}else if(endGameConsent == 1){
					lblTextOutput.setText("The game is drawn.");
					btnDrawAccepted.setEnabled(false);
					btnForfeit.setEnabled(false);
					textFieldDestination.setEnabled(false);
					endGameConsent = 0;
					//TODO: If a score box is added, add half to each person
				}
				
				
			}
		});
		btnDrawAccepted.setEnabled(false);
		btnDrawAccepted.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 30));
		btnDrawAccepted.setBounds(1005, 660, 165, 80);
		frame.getContentPane().add(btnDrawAccepted);
		
		JLabel lblMovePiece = new JLabel("Move your pieces here");
		lblMovePiece.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 36));
		lblMovePiece.setBounds(818, 280, 239, 44);
		frame.getContentPane().add(lblMovePiece);
		
		JLabel lblCanMove = new JLabel("");
		lblCanMove.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 36));
		lblCanMove.setBounds(818, 567, 352, 80);
		frame.getContentPane().add(lblCanMove);
		
		
		btnForfeit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(endGameConsent == 0){
					lblTextOutput.setText("<html>Are you sure you want to forfeit? Press again for yes, press draw for no.</html>");
					endGameConsent = 2;
				}else if(endGameConsent == 1){
					lblTextOutput.setText("The draw has been cancled.");
					endGameConsent = 0;
					if(board.isWhiteMove()){
						lblTextOutput.setText("White's move");
					}else lblTextOutput.setText("Black's move");
				}else if(endGameConsent == 2){
					if(board.isWhiteMove()){
						lblTextOutput.setText("<html>The game is forfeit. Black has won.</html>");
					}else{lblTextOutput.setText("<html>The game is forfeit. White has won.</html>");}
					btnDrawAccepted.setEnabled(false);
					btnForfeit.setEnabled(false);
					textFieldDestination.setEnabled(false);
					endGameConsent = 0;
					//TODO: Make the game end without closing the program.
					//TODO: If a score box is added, add one point to the person who one
				}
				
				
			}
		});
		btnForfeit.setEnabled(false);
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
				int x1,y1,x2,y2;
				int capturedWPawns = 8, capturedBPawns = 8;
				int capturedWKnights = 2, capturedBKnights = 2;
				int capturedWBishops = 2, capturedBBishops = 2;
				int capturedWRooks = 2, capturedBRooks = 2;
				int capturedWQueens = 1, capturedBQueens = 1;
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
						if (destination.length() == 3) {
							switch (destination.charAt(2)) {
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
					
					//this is where the move is resolved
					board.move(x1, y1, x2, y2, promote);
					textFieldOrigin.setText("");
					textFieldDestination.setText("");
					if(board.isWhiteMove()){
						lblTextOutput.setText("White's move");
					}else lblTextOutput.setText("Black's move");
					
					for (int i=0; i<8; i++)
						for (int j=0; j<8; j++)
							if (board.board[i][j].isOccupied()) {
								if (board.board[i][j].getPiece().isWhite()) {
									switch (board.board[i][j].getPiece().type()) {
										case PAWN: squareIcon[i][j].setIcon(whitePawnIcon);
										capturedWPawns--;
										break;
										
										case KNIGHT: squareIcon[i][j].setIcon(whiteKnightIcon);
										capturedWKnights--;
										break;
										
										case BISHOP: squareIcon[i][j].setIcon(whiteBishopIcon);
										capturedWBishops--;
										break;
										
										case ROOK: squareIcon[i][j].setIcon(whiteRookIcon);
										capturedWRooks--;
										break;
										
										case QUEEN: squareIcon[i][j].setIcon(whiteQueenIcon);
										capturedWQueens--;
										break;
										
										case KING: squareIcon[i][j].setIcon(whiteKingIcon);
										break;
									}
								} else {
									switch (board.board[i][j].getPiece().type()) {
										case PAWN: squareIcon[i][j].setIcon(blackPawnIcon);
										capturedBPawns--;
										break;
										
										case KNIGHT: squareIcon[i][j].setIcon(blackKnightIcon);
										capturedBKnights--;
										break;
										
										case BISHOP: squareIcon[i][j].setIcon(blackBishopIcon);
										capturedBBishops--;
										break;
										
										case ROOK: squareIcon[i][j].setIcon(blackRookIcon);
										capturedBRooks--;
										break;
										
										case QUEEN: squareIcon[i][j].setIcon(blackQueenIcon);
										capturedBQueens--;
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
					for (int i=0; i<capturedWPawns; i++) {
						wPawnLbl[i].setIcon(wPawnCapPNG);
						wPawnLbl[i].setBounds(12, 135 + (30*i), 25, 25);
						frame.getContentPane().add(wPawnLbl[i]);
					}
					
					for (int i=0; i<capturedWKnights; i++) {
						wKnightLbl[i].setIcon(wKnightCapPNG);
						wKnightLbl[i].setBounds(42, 135 + (30*i), 25, 25);
						frame.getContentPane().add(wKnightLbl[i]);
					}
					
					for (int i=0; i<capturedWBishops; i++) {
						wBishopLbl[i].setIcon(wBishopCapPNG);
						wBishopLbl[i].setBounds(72, 135 + (30*i), 25, 25);
						frame.getContentPane().add(wBishopLbl[i]);
					}
					
					for (int i=0; i<capturedWRooks; i++) {
						wRookLbl[i].setIcon(wRookCapPNG);
						wRookLbl[i].setBounds(102, 135 + (30*i), 25, 25);
						frame.getContentPane().add(wRookLbl[i]);
					}
					
					for (int i=0; i<capturedWQueens; i++) {
						wQueenLbl[i].setIcon(wQueenCapPNG);
						wQueenLbl[i].setBounds(132, 135 + (30*i), 25, 25);
						frame.getContentPane().add(wQueenLbl[i]);
					}
					
					for (int i=0; i<capturedBPawns; i++) {
						bPawnLbl[i].setIcon(bPawnCapPNG);
						bPawnLbl[i].setBounds(12, 405 + (30*i), 25, 25);
						frame.getContentPane().add(bPawnLbl[i]);
					}
					for (int i=0; i<capturedBKnights; i++) {
						bKnightLbl[i].setIcon(bKnightCapPNG);
						bKnightLbl[i].setBounds(42, 405 + (30*i), 25, 25);
						frame.getContentPane().add(bKnightLbl[i]);
					}
					for (int i=0; i<capturedBBishops; i++) {
						bBishopLbl[i].setIcon(bBishopCapPNG);
						bBishopLbl[i].setBounds(72, 405 + (30*i), 25, 25);
						frame.getContentPane().add(bBishopLbl[i]);
					}
					for (int i=0; i<capturedBRooks; i++) {
						bRookLbl[i].setIcon(bRookCapPNG);
						bRookLbl[i].setBounds(102, 405 + (30*i), 25, 25);
						frame.getContentPane().add(bRookLbl[i]);
					}
					for (int i=0; i<capturedBQueens; i++) {
						bQueenLbl[i].setIcon(bQueenCapPNG);
						bQueenLbl[i].setBounds(132, 405 + (30*i), 25, 25);
						frame.getContentPane().add(bQueenLbl[i]);
					}
					
					
					if(board.getCheck()) {
						lblCanMove.setText("You are in check!");
					}
					else{
						lblCanMove.setText("");
					}
					if(board.valid == false){
						lblCanMove.setText("That move is illegal.");
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
