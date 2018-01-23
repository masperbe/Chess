
public class Board {
	
	public Square[][] board = new Square[8][8];
		
	private int moveNumber;
	private boolean whiteMove;
	private boolean gameInProgress;
	
	public Board() {
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				board[i][j] = new Square();
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
		
		for(int j=2; j<5;j++){
			for (int i=0; i<8; i++){
				board[i][j].setPiece(null);
			}
		}
		//TODO URGENT Get not only the board to recognize there is not a piece on a board but there should also not be an image of the piece
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
	
	public void move(int x1, int y1, int x2, int y2){
		Piece movedPiece;
		boolean destinationOccupied = board[x2][y2].isOccupied();
		
		//Check if the origin square is empty
		if (board[x1][y1].isOccupied())
			movedPiece = board[x1][y1].getPiece();
		else
			return; //TO-DO throw an exception here (original square empty)
		
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
							board[x1][y1].setPiece(null);
							movedPiece.setMoved();
							whiteMove = false;
							board[x2][y2].setPiece(movedPiece);
						}
						else return; //TO-DO throw an exception here (invalid destination square)
					}
					else return; //TO-DO throw an exception here (invalid destination square)
				}
				else if (x1 == x2) {
					if (y2-y1 == 1 || ((y2-y1 == 2) && (movedPiece.hasMoved() == false))) {
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = false;
						board[x2][y2].setPiece(movedPiece);
					}
					else return; //TO-DO throw an exception here (invalid destination square)
				} else return; //TO-DO throw an exception here (invalid destination square)
			}
			else if (movedPiece.type() == Piece.TypePiece.KNIGHT){
				if (x1-x2 == 1){
					if(y2-y1 == 2){
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = false;
						board[x2][y2].setPiece(movedPiece);
					}
					else if (y1-y2 == 2){
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = false;
						board[x2][y2].setPiece(movedPiece);
					}
				}
				else if (x1-x2 == 2){
					if(y2-y1 == 1){
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = false;
						board[x2][y2].setPiece(movedPiece);
					}
					else if (y1-y2 == 1){
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = false;
						board[x2][y2].setPiece(movedPiece);
					}
				}
				else if (x2-x1 == 1){
					if(y2-y1 == 2){
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = false;
						board[x2][y2].setPiece(movedPiece);
					}
					else if (y1-y2 == 2){
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = false;
						board[x2][y2].setPiece(movedPiece);
					}
				}
				else if (x2-x1 == 2){
					if(y2-y1 == 1){
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = false;
						board[x2][y2].setPiece(movedPiece);
					}
					else if (y1-y2 == 1){
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = false;
						board[x2][y2].setPiece(movedPiece);
					}
				}
				else return;
			}
			else if (movedPiece.type() == Piece.TypePiece.ROOK){
				if(x1-x2 != 0 || y1-y2 != 0){//delete later but it was giving me an error on the brackets and i was too lazy to figure it out
					
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
						
							board[x1][y1].setPiece(null);
							movedPiece.setMoved();
							whiteMove = false;
							board[x2][y2].setPiece(movedPiece);	
						
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
				
							board[x1][y1].setPiece(null);
							movedPiece.setMoved();
							whiteMove = false;
							board[x2][y2].setPiece(movedPiece);	
						
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
						
							board[x1][y1].setPiece(null);
							movedPiece.setMoved();
							whiteMove = false;
							board[x2][y2].setPiece(movedPiece);	
						
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
						
							board[x1][y1].setPiece(null);
							movedPiece.setMoved();
							whiteMove = false;
							board[x2][y2].setPiece(movedPiece);	
						
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
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = false;
						board[x2][y2].setPiece(movedPiece);	
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
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = false;
						board[x2][y2].setPiece(movedPiece);	
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
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = false;
						board[x2][y2].setPiece(movedPiece);	
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
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = false;
						board[x2][y2].setPiece(movedPiece);	
					}
				}
				else return;
			}
			
			// else stuff if it's not a pawn
		}
		else if(movedPiece.isWhite() == false && whiteMove == false){
			if (movedPiece.type() == Piece.TypePiece.PAWN) {
				if(destinationOccupied) {
					if(Math.abs(x1-x2) == 1) {
						if(y1-y2 == 1){
							board[x1][y1].setPiece(null);
							movedPiece.setMoved();
							whiteMove = true;
							board[x2][y2].setPiece(movedPiece);
						}
						else return;
					}
					else return;
				}
				else if (x1 == x2){
					if(y1-y2 == 1 || ((y1-y2 == 2)&& (movedPiece.hasMoved() == false))) {
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = true;
						board[x2][y2].setPiece(movedPiece);
					}
					else return;
				}
				else return;
			}
			else if (movedPiece.type() == Piece.TypePiece.KNIGHT){
				if (x1-x2 == 1){
					if(y2-y1 == 2){
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = true;
						board[x2][y2].setPiece(movedPiece);
					}
					else if (y1-y2 == 2){
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = true;
						board[x2][y2].setPiece(movedPiece);
					}
				}
				else if (x1-x2 == 2){
					if(y2-y1 == 1){
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = true;
						board[x2][y2].setPiece(movedPiece);
					}
					else if (y1-y2 == 1){
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = true;
						board[x2][y2].setPiece(movedPiece);
					}
				}
				else if (x2-x1 == 1){
					if(y2-y1 == 2){
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = true;
						board[x2][y2].setPiece(movedPiece);
					}
					else if (y1-y2 == 2){
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = true;
						board[x2][y2].setPiece(movedPiece);
					}
				}
				else if (x2-x1 == 2){
					if(y2-y1 == 1){
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = true;
						board[x2][y2].setPiece(movedPiece);
					}
					else if (y1-y2 == 1){
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = true;
						board[x2][y2].setPiece(movedPiece);
					}
				}
				else return;
			}
			else if (movedPiece.type() == Piece.TypePiece.ROOK){
				if(x1-x2 != 0 || y1-y2 != 0){//delete later but it was giving me an error on the brackets and i was too lazy to figure it out
					
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
						
							board[x1][y1].setPiece(null);
							movedPiece.setMoved();
							whiteMove = true;
							board[x2][y2].setPiece(movedPiece);	
						
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
						
							board[x1][y1].setPiece(null);
							movedPiece.setMoved();
							whiteMove = true;
							board[x2][y2].setPiece(movedPiece);	
						
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
						
							board[x1][y1].setPiece(null);
							movedPiece.setMoved();
							whiteMove = true;
							board[x2][y2].setPiece(movedPiece);	
						
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
						
							board[x1][y1].setPiece(null);
							movedPiece.setMoved();
							whiteMove = true;
							board[x2][y2].setPiece(movedPiece);	
						
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
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = true;
						board[x2][y2].setPiece(movedPiece);	
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
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = true;
						board[x2][y2].setPiece(movedPiece);	
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
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = true;
						board[x2][y2].setPiece(movedPiece);	
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
						board[x1][y1].setPiece(null);
						movedPiece.setMoved();
						whiteMove = true;
						board[x2][y2].setPiece(movedPiece);	
					}
				}
				else return;
			}
			
			
		}
		// else stuff if it's not white
		
	}
}
