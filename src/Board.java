
public class Board {
	
	public Square[][] board;
		
	private int moveNumber;
	private boolean whiteMove;
	private boolean gameInProgress;
	
	public Board() {
		board = new Square[8][8];
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				board[i][j] = new Square();
		newGame();
	}
	
	public void newGame(){
		whiteMove = true;
		//make it so lblTextOutput says Whites Move or just dissapears
		board[0][0].setPiece(new Piece (true, Piece.TypePiece.ROOK));
		board[1][0].setPiece(new Piece (true, Piece.TypePiece.KNIGHT));
		board[2][0].setPiece(new Piece (true, Piece.TypePiece.BISHOP));
		board[3][0].setPiece(new Piece (true, Piece.TypePiece.QUEEN));
		board[4][0].setPiece(new Piece (true, Piece.TypePiece.KING));
		board[5][0].setPiece(new Piece (true, Piece.TypePiece.BISHOP));
		board[6][0].setPiece(new Piece (true, Piece.TypePiece.KNIGHT));
		board[7][0].setPiece(new Piece (true, Piece.TypePiece.ROOK));
		
		for (int i=0; i<8; i++)
			board[i][1].setPiece(new Piece (true, Piece.TypePiece.PAWN));
		
		for (int i=0; i<8; i++)
			board[i][6].setPiece(new Piece (false, Piece.TypePiece.PAWN));
		
		board[0][7].setPiece(new Piece (false, Piece.TypePiece.ROOK));
		board[1][7].setPiece(new Piece (false, Piece.TypePiece.KNIGHT));
		board[2][7].setPiece(new Piece (false, Piece.TypePiece.BISHOP));
		board[3][7].setPiece(new Piece (false, Piece.TypePiece.QUEEN));
		board[4][7].setPiece(new Piece (false, Piece.TypePiece.KING));
		board[5][7].setPiece(new Piece (false, Piece.TypePiece.BISHOP));
		board[6][7].setPiece(new Piece (false, Piece.TypePiece.KNIGHT));
		board[7][7].setPiece(new Piece (false, Piece.TypePiece.ROOK));
	}

	public boolean isGameInProgress() {
		return gameInProgress;
	}

	public boolean isWhiteMove() {
		return whiteMove;
	}

	public int getMoveNumber() {
		return moveNumber;
	}
	private void executeMove(int x1, int y1, int x2, int y2, Piece movedPiece, boolean whiteMove) {
		int wKingPosX = 0;
		int wKingPosY = 0;
		int bKingPosX = 0;
		int bKingPosY = 0;
		
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if(board[i][j].getPiece().type == Piece.TypePiece.KING && board[i][j].getPiece().isWhite() == true){//for the white king
					wKingPosX = i;
					wKingPosY = j;
					
				}
				if(board[i][j].getPiece().type == Piece.TypePiece.KING && board[i][j].getPiece().isWhite() == false){//for the black king
					bKingPosX = i;
					bKingPosY = j;
				}
			}
		}
		/*
		Knight
		if(board[Math.abs(wKingPosX-1)][Math.abs(wKingPosY-2)].getPiece().type == Piece.TypePiece.KNIGHT && board[Math.abs(wKingPosX-1)][Math.abs(wKingPosY-2)].getPiece().isWhite == false){
			return;
		}
		if(board[Math.abs(wKingPosX-2)][Math.abs(wKingPosY-1)].getPiece().type == Piece.TypePiece.KNIGHT && board[Math.abs(wKingPosX-1)][Math.abs(wKingPosY-2)].getPiece().isWhite == false){
			return;
		}
		if(board[Math.abs(bKingPosX-1)][Math.abs(bKingPosY-2)].getPiece().type == Piece.TypePiece.KNIGHT && board[Math.abs(bKingPosX-1)][Math.abs(bKingPosY-2)].getPiece().isWhite == false){
			return;
		}
		if(board[Math.abs(bKingPosX-2)][Math.abs(bKingPosY-1)].getPiece().type == Piece.TypePiece.KNIGHT && board[Math.abs(bKingPosX-1)][Math.abs(bKingPosY-2)].getPiece().isWhite == false){
			return;
		}
		Knight*/
		
		board[x1][y1].setPiece(null);
		movedPiece.setMoved();
		this.whiteMove = whiteMove;
		board[x2][y2].setPiece(movedPiece);
	}
	
	public void move(int x1, int y1, int x2, int y2){
		Piece movedPiece;
		boolean destinationOccupied = board[x2][y2].isOccupied();
		
		//Check if the origin square is empty
		if (board[x1][y1].isOccupied())
			movedPiece = board[x1][y1].getPiece();
		else
			return; //TO-DO throw an exception here (original square empty)
			// throw new IllegalMoveException(); <- requires writing a class called IllegalMoveException
		
		//Check if the piece is trying to capture a friendly piece
		if (destinationOccupied)
			if (movedPiece.isWhite() == board[x2][y2].getPiece().isWhite())
				return; //TO-DO throw an exception here (capturing own piece)
		
		//Check the color and type of the piece to see if the move is legal
		if (movedPiece.isWhite() && whiteMove == true) {
			if (movedPiece.type() == Piece.TypePiece.PAWN) {
				if (destinationOccupied) {
					if (Math.abs(x1-x2) == 1) {
						if (y2-y1 == 1) {
							executeMove(x1,y1,x2,y2,movedPiece,false);
						}
						else return; //TO-DO throw an exception here (invalid destination square)
					}
					else return; //TO-DO throw an exception here (invalid destination square)
				}
				else if (x1 == x2) {
					if (y2-y1 == 1 || ((y2-y1 == 2) && (movedPiece.hasMoved() == false))) {
						executeMove(x1,y1,x2,y2,movedPiece,false);
					}
					else return; //TO-DO throw an exception here (invalid destination square)
				} else return; //TO-DO throw an exception here (invalid destination square)
			}
			else if (movedPiece.type() == Piece.TypePiece.KNIGHT){
				if (x1-x2 == 1){
					if(y2-y1 == 2){
						executeMove(x1,y1,x2,y2,movedPiece,false);
					}
					else if (y1-y2 == 2){
						executeMove(x1,y1,x2,y2,movedPiece,false);
					}
				}
				else if (x1-x2 == 2){
					if(y2-y1 == 1){
						executeMove(x1,y1,x2,y2,movedPiece,false);
					}
					else if (y1-y2 == 1){
						executeMove(x1,y1,x2,y2,movedPiece,false);
					}
				}
				else if (x2-x1 == 1){
					if(y2-y1 == 2){
						executeMove(x1,y1,x2,y2,movedPiece,false);
					}
					else if (y1-y2 == 2){
						executeMove(x1,y1,x2,y2,movedPiece,false);
					}
				}
				else if (x2-x1 == 2){
					if(y2-y1 == 1){
						executeMove(x1,y1,x2,y2,movedPiece,false);
					}
					else if (y1-y2 == 1){
						executeMove(x1,y1,x2,y2,movedPiece,false);
					}
				}
				else return;
			}
			else if (movedPiece.type() == Piece.TypePiece.ROOK){
				
				if(y1-y2 == 0){
					if(x1-x2 > 0){
			
						for(int i = x1-x2;i>1;i--){
							
							if(board[x2-1+i][y2].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,false);
					}
					else if(x2-x1 > 0){
						for(int i = x2-x1;i>1;i--){
							if(board[x1+i-1][y2].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,false);
					}	
				}
				if(x1-x2 == 0){
					if(y1-y2 > 0){
						
						for(int i = y1-y2;i>1;i--){
							
							if(board[x2][y1-i+1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,false);
					}
					else if(y2-y1 > 0){
						for(int i = y2-y1;i>1;i--){
							if(board[x2][y2-i+1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,false);
					}
				}
			}
			else if (movedPiece.type() == Piece.TypePiece.BISHOP){
				if(Math.abs(x1-x2) == Math.abs(y1-y2)){
					if(x1-x2 > 0 && y1-y2 > 0){//bottom left
						for(int i = x1-x2;i>1;i--){
							if(board[x1+i-1][y1+i-1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,false);
					}
					else if(x2-x1 > 0 && y2-y1 > 0){//top right
						for(int i = y2-y1;i>1;i--){
							if(board[x2-i+1][y2-i+1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,false);
					}
					else if(x2-x1 > 0 && y1-y2 > 0){//bottom right
						for(int i = x2-x1;i>1;i--){
							if(board[x2-i+1][y2+i-1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,false);
					}
					else if(x1-x2 > 0 && y2-y1 > 0){//top left
						for(int i = y2-y1;i>1;i--){
							if(board[x2+i-1][y2-i+1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,false);	
					}
				}
				else return;
			}
			else if (movedPiece.type() == Piece.TypePiece.QUEEN){
				if(Math.abs(x1-x2) == Math.abs(y1-y2)){
					if(x1-x2 > 0 && y1-y2 > 0){//bottom left
						for(int i = x1-x2;i>1;i--){
							if(board[x1+i-1][y1+i-1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,false);
					}
					else if(x2-x1 > 0 && y2-y1 > 0){//top right
						for(int i = y2-y1;i>1;i--){
							if(board[x2-i+1][y2-i+1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,false);
					}
					else if(x2-x1 > 0 && y1-y2 > 0){//bottom right
						for(int i = x2-x1;i>1;i--){
							if(board[x2-i+1][y2+i-1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,false);
					}
					else if(x1-x2 > 0 && y2-y1 > 0){//top left
						for(int i = y2-y1;i>1;i--){
							if(board[x2+i-1][y2-i+1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,false);
					}
				}
				if(y1-y2 == 0){
					if(x1-x2 > 0){
			
						for(int i = x1-x2;i>1;i--){
							
							if(board[x2-1+i][y2].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,false);
					}
					else if(x2-x1 > 0){
						for(int i = x2-x1;i>1;i--){
							if(board[x1+i-1][y2].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,false);
					}
				}
				if(x1-x2 == 0){
					if(y1-y2 > 0){
						
						for(int i = y1-y2;i>1;i--){
							
							if(board[x2][y1-i+1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,false);
					}
					else if(y2-y1 > 0){
						for(int i = y2-y1;i>1;i--){
							if(board[x2][y2-i+1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,false);
					}
				}
			}
			else if(movedPiece.type() == Piece.TypePiece.KING){
				if(Math.abs(x2-x1) == 1 || Math.abs(y2-y1) == 1){
					executeMove(x1,y1,x2,y2,movedPiece,false);
				}
				else return;
			}
			
		}
		else if(movedPiece.isWhite() == false && whiteMove == false){
			if (movedPiece.type() == Piece.TypePiece.PAWN) {
				if(destinationOccupied) {
					if(Math.abs(x1-x2) == 1) {
						if(y1-y2 == 1){
							executeMove(x1,y1,x2,y2,movedPiece,true);
						}
						else return;
					}
					else return;
				}
				else if (x1 == x2){
					if(y1-y2 == 1 || ((y1-y2 == 2)&& (movedPiece.hasMoved() == false))) {
						executeMove(x1,y1,x2,y2,movedPiece,true);
					}
					else return;
				}
				else return;
			}
			else if (movedPiece.type() == Piece.TypePiece.KNIGHT){
				if (x1-x2 == 1){
					if(y2-y1 == 2){
						executeMove(x1,y1,x2,y2,movedPiece,true);
					}
					else if (y1-y2 == 2){
						executeMove(x1,y1,x2,y2,movedPiece,true);
					}
				}
				else if (x1-x2 == 2){
					if(y2-y1 == 1){
						executeMove(x1,y1,x2,y2,movedPiece,true);
					}
					else if (y1-y2 == 1){
						executeMove(x1,y1,x2,y2,movedPiece,true);
					}
				}
				else if (x2-x1 == 1){
					if(y2-y1 == 2){
						executeMove(x1,y1,x2,y2,movedPiece,true);
					}
					else if (y1-y2 == 2){
						executeMove(x1,y1,x2,y2,movedPiece,true);
					}
				}
				else if (x2-x1 == 2){
					if(y2-y1 == 1){
						executeMove(x1,y1,x2,y2,movedPiece,true);
					}
					else if (y1-y2 == 1){
						executeMove(x1,y1,x2,y2,movedPiece,true);
					}
				}
				else return;
			}
			else if (movedPiece.type() == Piece.TypePiece.ROOK){
				
				if(y1-y2 == 0){
					if(x1-x2 > 0){
			
						for(int i = x1-x2;i>1;i--){
							
							if(board[x2-1+i][y2].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,true);
					}
					else if(x2-x1 > 0){
						for(int i = x2-x1;i>1;i--){
							if(board[x1+i-1][y2].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,true);
					}		
				}
				if(x1-x2 == 0){
					if(y1-y2 > 0){
						
						for(int i = y1-y2;i>1;i--){
							
							if(board[x2][y1-i+1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,true);
					}
					else if(y2-y1 > 0){
						for(int i = y2-y1;i>1;i--){
							if(board[x2][y2-i+1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,true);	
					}
				}
			}
			else if (movedPiece.type() == Piece.TypePiece.BISHOP){
				if(Math.abs(x1-x2) == Math.abs(y1-y2)){
					if(x1-x2 > 0 && y1-y2 > 0){//bottom left
						for(int i = x1-x2;i>1;i--){
							if(board[x1+i-1][y1+i-1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,true);
					}
					else if(x2-x1 > 0 && y2-y1 > 0){//top right
						for(int i = y2-y1;i>1;i--){
							if(board[x2-i+1][y2-i+1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,true);
					}
					else if(x2-x1 > 0 && y1-y2 > 0){//bottom right
						for(int i = x2-x1;i>1;i--){
							if(board[x2-i+1][y2+i-1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,true);
					}
					else if(x1-x2 > 0 && y2-y1 > 0){//top left
						for(int i = y2-y1;i>1;i--){
							if(board[x2+i-1][y2-i+1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,true);
					}
				}
				else return;
			}
			else if (movedPiece.type() == Piece.TypePiece.QUEEN){
				if(Math.abs(x1-x2) == Math.abs(y1-y2)){
					if(x1-x2 > 0 && y1-y2 > 0){//bottom left
						for(int i = x1-x2;i>1;i--){
							if(board[x1+i-1][y1+i-1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,true);
					}
					else if(x2-x1 > 0 && y2-y1 > 0){//top right
						for(int i = y2-y1;i>1;i--){
							if(board[x2-i+1][y2-i+1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,true);
					}
					else if(x2-x1 > 0 && y1-y2 > 0){//bottom right
						for(int i = x2-x1;i>1;i--){
							if(board[x2-i+1][y2+i-1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,true);	
					}
					else if(x1-x2 > 0 && y2-y1 > 0){//top left
						for(int i = y2-y1;i>1;i--){
							if(board[x2+i-1][y2-i+1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,true);
					}
				}
				
				if(y1-y2 == 0){
					if(x1-x2 > 0){
			
						for(int i = x1-x2;i>1;i--){
							
							if(board[x2-1+i][y2].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
								return;
							}
						}						
							executeMove(x1,y1,x2,y2,movedPiece,true);
					}
					else if(x2-x1 > 0){
						for(int i = x2-x1;i>1;i--){
							if(board[x1+i-1][y2].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,true);	
					}	
				}
				if(x1-x2 == 0){
					if(y1-y2 > 0){
						
						for(int i = y1-y2;i>1;i--){
							
							if(board[x2][y1-i+1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,true);	
					}
					else if(y2-y1 > 0){
						for(int i = y2-y1;i>1;i--){
							if(board[x2][y2-i+1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,true);
					}
				}
			}
			else if(movedPiece.type() == Piece.TypePiece.KING){
				if(Math.abs(x2-x1) == 1 || Math.abs(y2-y1) == 1){
					executeMove(x1,y1,x2,y2,movedPiece,true);
				}
				else return;
			}	
		}
	}
}
