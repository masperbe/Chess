
public class Board {
	
	public Square[][] board;
		
	private int moveNumber;
	private boolean whiteMove;
	private boolean gameInProgress;
	private int wKingX;
	private int wKingY;
	private int bKingX;
	private int bKingY;
	
	public Board() {
		board = new Square[8][8];
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				board[i][j] = new Square();
		newGame();
	}
	
	public void newGame(){
		whiteMove = true;
		//make it so lblTextOutput says Whites Move or just disappears
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
		
		wKingX = 4;
		wKingY = 0;
		bKingX = 4;
		bKingY = 7;
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
	
	// The purpose of this method is to check to see whether the selected move would leave the
	// player on the move in check. If so, false is returned. If not, the move is completed and true is returned.
	private boolean executeMove(int x1, int y1, int x2, int y2, Piece movedPiece) {
		Square[][] temp = new Square[8][8];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				temp[i][j] = new Square();
				temp[i][j].setPiece(board[i][j].getPiece());
			}
		}
		
		int tempWKingX = wKingX;
		int tempWKingY = wKingY;
		int tempBKingX = bKingX;
		int tempBKingY = bKingY;
		
		temp[x1][y1].setPiece(null);
		movedPiece.setMoved();
		temp[x2][y2].setPiece(movedPiece);
		if (movedPiece.type == Piece.TypePiece.KING) {
			if (movedPiece.isWhite()) {
				tempWKingX = x2;
				tempWKingY = y2;
			} else {
				tempBKingX = x2;
				tempBKingY = y2;
			}
		}
		
		if(whiteMove){//white to move - is white in check?
			//to the right
			int i = 1;
			int j;
			while(tempWKingX+i < 8 && temp[tempWKingX+i][tempWKingY].isOccupied() == false){
				i++;
			}
			if(tempWKingX+i < 8 && temp[tempWKingX+i][tempWKingY].getPiece().isWhite == false){
				Piece.TypePiece type = temp[tempWKingX+i][tempWKingY].getPiece().type();
				if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && i==1) || type == Piece.TypePiece.QUEEN){
					return false;
				}
			}
			
			//to the left
			i = -1;
			while(tempWKingX+i > 0 && temp[tempWKingX+i][tempWKingY].isOccupied() == false){
				i--;
			}
			if(tempWKingX+i > 0 && temp[tempWKingX+i][tempWKingY].getPiece().isWhite == false){
				Piece.TypePiece type = temp[tempWKingX+i][tempWKingY].getPiece().type();
				if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && i==-1) || type == Piece.TypePiece.QUEEN){
					return false;
				}
			}
			
			//to the up
			j = 1;
			while(tempWKingY+j < 8 && temp[tempWKingX][tempWKingY+j].isOccupied() == false){
				j++;
			}
			if(tempWKingY+j < 8 && temp[tempWKingX][tempWKingY+j].getPiece().isWhite == false){
				Piece.TypePiece type = temp[tempWKingX][tempWKingY+j].getPiece().type();
				if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && j==1) || type == Piece.TypePiece.QUEEN){
					return false;
				}
			}
			
			//to the down
			j = -1;
			while(tempWKingY+j > 0 && temp[tempWKingX][tempWKingY+j].isOccupied() == false){
				j--;
			}
			if(tempWKingY+j > 0 && temp[tempWKingX][tempWKingY+j].getPiece().isWhite == false){
				Piece.TypePiece type = temp[tempWKingX][tempWKingY+j].getPiece().type();
				if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && j==-1) || type == Piece.TypePiece.QUEEN){
					return false;
				}
			}
			
			//to the top right
			i = 1;
			j = 1;
			while(tempWKingX+i < 8 && tempWKingY+j < 8 && temp[tempWKingX+i][tempWKingY+j].isOccupied() == false){
				i++;
				j++;
			}
			if(tempWKingX+i < 8 && tempWKingY+j < 8 && temp[tempWKingX+i][tempWKingY+j].getPiece().isWhite == false){
				Piece.TypePiece type = temp[tempWKingX+i][tempWKingY+j].getPiece().type();
				if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && j==1) || type == Piece.TypePiece.QUEEN || (type == Piece.TypePiece.PAWN && j==1)){
					return false;
				}
			}
			
			//to the bottom right
			i = 1;
			j = -1;
			while(tempWKingX+i < 8 && tempWKingY+j > 0 && temp[tempWKingX+i][tempWKingY+j].isOccupied() == false){
				i++;
				j--;
			}
			if(tempWKingX+i < 8 && tempWKingY+j > 0 && temp[tempWKingX+i][tempWKingY+j].getPiece().isWhite == false){
				Piece.TypePiece type = temp[tempWKingX+i][tempWKingY+j].getPiece().type();
				if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && i==1) || type == Piece.TypePiece.QUEEN){
					return false;
				}
			}
			
			//to the bottom left
			i = -1;
			j = -1;
			while(tempWKingX+i > 0 && tempWKingY+j > 0 && temp[tempWKingX+i][tempWKingY+j].isOccupied() == false){
				i--;
				j--;
			}
			if(tempWKingX+i > 0 && tempWKingY+j > 0 && temp[tempWKingX+i][tempWKingY+j].getPiece().isWhite == false){
				Piece.TypePiece type = temp[tempWKingX+i][tempWKingY+j].getPiece().type();
				if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && j==-1) || type == Piece.TypePiece.QUEEN){
					return false;
				}
			}
			
			//to the top left
			i = -1;
			j = 1;
			while(tempWKingX+i > 0 && tempWKingY+j < 8 && temp[tempWKingX+i][tempWKingY+j].isOccupied() == false){
				i--;
				j++;
			}
			if(tempWKingX+i > 0 && tempWKingY+j < 8 && temp[tempWKingX+i][tempWKingY+j].getPiece().isWhite == false){
				Piece.TypePiece type = temp[tempWKingX+i][tempWKingY+j].getPiece().type();
				if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && j==1) || type == Piece.TypePiece.QUEEN || (type == Piece.TypePiece.PAWN && j==1)){
					return false;
				}
			}
			if(tempWKingX + 1 < 8 && tempWKingY + 2 < 8)
				if(temp[tempWKingX + 1][tempWKingY +2].isOccupied() && temp[tempWKingX + 1][tempWKingY + 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempWKingX + 1][tempWKingY +2].getPiece().isWhite == false){
					return false;
				}
			if(tempWKingX + 1 < 8 && tempWKingY - 2 > 0)
				if(temp[tempWKingX + 1][tempWKingY - 2].isOccupied() && temp[tempWKingX + 1][tempWKingY - 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempWKingX + 1][tempWKingY - 2].getPiece().isWhite == false){
					return false;
				}
			if(tempWKingX - 1 > 0 && tempWKingY + 2 < 8)
				if(temp[tempWKingX - 1][tempWKingY + 2].isOccupied() && temp[tempWKingX - 1][tempWKingY + 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempWKingX - 1][tempWKingY + 2].getPiece().isWhite == false){
					return false;
				}
			if(tempWKingX - 1 > 0 && tempWKingY - 2 > 0)
				if(temp[tempWKingX - 1][tempWKingY - 2].isOccupied() && temp[tempWKingX - 1][tempWKingY - 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempWKingX - 1][tempWKingY - 2].getPiece().isWhite == false){
					return false;
				}
			if(tempWKingX + 2 < 8 && tempWKingY + 1 < 8)
				if(temp[tempWKingX + 2][tempWKingY + 1].isOccupied() && temp[tempWKingX + 2][tempWKingY + 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempWKingX + 2][tempWKingY + 1].getPiece().isWhite == false){
					return false;
				}
			if(tempWKingX + 2 < 8 && tempWKingY - 1 > 0)
				if(temp[tempWKingX + 2][tempWKingY - 1].isOccupied() && temp[tempWKingX + 2][tempWKingY - 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempWKingX + 2][tempWKingY - 1].getPiece().isWhite == false){
					return false;
				}
			if(tempWKingX - 2 > 0 && tempWKingY + 1 < 8)
				if(temp[tempWKingX - 2][tempWKingY + 1].isOccupied() && temp[tempWKingX - 2][tempWKingY + 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempWKingX - 2][tempWKingY + 1].getPiece().isWhite == false){
					return false;
				}
			if(tempWKingX - 2 > 0 && tempWKingY - 1 > 0)
				if(temp[tempWKingX - 2][tempWKingY - 1].isOccupied() && temp[tempWKingX - 2][tempWKingY - 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempWKingX - 2][tempWKingY - 1].getPiece().isWhite == false){
					return false;
				}
		} else {//black to move - is black in check?
			//to the right
			int i = 1;
			int j;
			while(tempBKingX+i < 8 && temp[tempBKingX+i][tempBKingY].isOccupied() == false){
				i++;
			}
			if(tempBKingX+i < 8 && temp[tempBKingX+i][tempBKingY].getPiece().isWhite == true){
				Piece.TypePiece type = temp[tempBKingX+i][tempBKingY].getPiece().type();
				if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && i==1) || type == Piece.TypePiece.QUEEN){
					return false;
				}
			}
			
			//to the left
			i = -1;
			while(tempBKingX+i > 0 && temp[tempBKingX+i][tempBKingY].isOccupied() == false){
				i--;
			}
			if(tempBKingX+i > 0 && temp[tempBKingX+i][tempBKingY].getPiece().isWhite == true){
				Piece.TypePiece type = temp[tempBKingX+i][tempBKingY].getPiece().type();
				if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && i==-1) || type == Piece.TypePiece.QUEEN){
					return false;
				}
			}
			
			//to the up
			j = 1;
			while(tempBKingY+j < 8 && temp[tempBKingX][tempBKingY+j].isOccupied() == false){
				j++;
			}
			if(tempBKingY+j < 8 && temp[tempBKingX][tempBKingY+j].getPiece().isWhite == true){
				Piece.TypePiece type = temp[tempBKingX][tempBKingY+j].getPiece().type();
				if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && j==1) || type == Piece.TypePiece.QUEEN){
					return false;
				}
			}
			
			//to the down
			j = -1;
			while(tempBKingY+j > 0 && temp[tempBKingX][tempBKingY+j].isOccupied() == false){
				j--;
			}
			if(tempBKingY+j > 0 && temp[tempBKingX][tempBKingY+j].getPiece().isWhite == true){
				Piece.TypePiece type = temp[tempBKingX][tempBKingY+j].getPiece().type();
				if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && j==-1) || type == Piece.TypePiece.QUEEN){
					return false;
				}
			}
			
			//to the top right
			i = 1;
			j = 1;
			while(tempBKingX+i < 8 && tempBKingY+j < 8 && temp[tempBKingX+i][tempBKingY+j].isOccupied() == false){
				i++;
				j++;
			}
			if(tempBKingX+i < 8 && tempBKingY+j < 8 && temp[tempBKingX+i][tempBKingY+j].getPiece().isWhite == true){
				Piece.TypePiece type = temp[tempBKingX+i][tempBKingY+j].getPiece().type();
				if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && j==1) || type == Piece.TypePiece.QUEEN || (type == Piece.TypePiece.PAWN && j==1)){
					return false;
				}
			}
			
			//to the bottom right
			i = 1;
			j = -1;
			while(tempBKingX+i < 8 && tempBKingY+j > 0 && temp[tempBKingX+i][tempBKingY+j].isOccupied() == false){
				i++;
				j--;
			}
			if(tempBKingX+i < 8 && tempBKingY+j > 0 && temp[tempBKingX+i][tempBKingY+j].getPiece().isWhite == true){
				Piece.TypePiece type = temp[tempBKingX+i][tempBKingY+j].getPiece().type();
				if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && i==1) || type == Piece.TypePiece.QUEEN){
					return false;
				}
			}
			
			//to the bottom left
			i = -1;
			j = -1;
			while(tempBKingX+i > 0 && tempBKingY+j > 0 && temp[tempBKingX+i][tempBKingY+j].isOccupied() == false){
				i--;
				j--;
			}
			if(tempBKingX+i > 0 && tempBKingY+j > 0 && temp[tempBKingX+i][tempBKingY+j].getPiece().isWhite == true){
				Piece.TypePiece type = temp[tempBKingX+i][tempBKingY+j].getPiece().type();
				if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && j==-1) || type == Piece.TypePiece.QUEEN){
					return false;
				}
			}
			
			//to the top left
			i = -1;
			j = 1;
			while(tempBKingX+i > 0 && tempBKingY+j < 8 && temp[tempBKingX+i][tempBKingY+j].isOccupied() == false){
				i--;
				j++;
			}
			if(tempBKingX+i > 0 && tempBKingY+j < 8 && temp[tempBKingX+i][tempBKingY+j].getPiece().isWhite == true){
				Piece.TypePiece type = temp[tempBKingX+i][tempBKingY+j].getPiece().type();
				if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && j==1) || type == Piece.TypePiece.QUEEN || (type == Piece.TypePiece.PAWN && j==1)){
					return false;
				}
			}
			if(tempBKingX + 1 < 8 && tempBKingY + 2 < 8)
				if(temp[tempBKingX + 1][tempBKingY + 2].isOccupied() && temp[tempBKingX + 1][tempBKingY + 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempBKingX + 1][tempBKingY +2].getPiece().isWhite == true){
					return false;
				}
			if(tempBKingX + 1 < 8 && tempBKingY - 2 > 0)
				if(temp[tempBKingX + 1][tempBKingY - 2].isOccupied() && temp[tempBKingX + 1][tempBKingY - 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempBKingX + 1][tempBKingY - 2].getPiece().isWhite == true){
					return false;
				}
			if(tempBKingX - 1 > 0 && tempBKingY + 2 < 8)
				if(temp[tempBKingX - 1][tempBKingY + 2].isOccupied() && temp[tempBKingX - 1][tempBKingY + 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempBKingX - 1][tempBKingY + 2].getPiece().isWhite == true){
					return false;
				}
			if(tempBKingX - 1 > 0 && tempBKingY - 2 > 0)
				if(temp[tempBKingX - 1][tempBKingY - 2].isOccupied() && temp[tempBKingX - 1][tempBKingY - 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempBKingX - 1][tempBKingY - 2].getPiece().isWhite == true){
					return false;
				}
			if(tempBKingX + 2 < 8 && tempBKingY + 1 < 8)
				if(temp[tempBKingX + 2][tempBKingY + 1].isOccupied() && temp[tempBKingX + 2][tempBKingY + 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempBKingX + 2][tempBKingY + 1].getPiece().isWhite == true){
					return false;
				}
			if(tempBKingX + 2 < 8 && tempBKingY - 1 > 0)
				if(temp[tempBKingX + 2][tempBKingY - 1].isOccupied() && temp[tempBKingX + 2][tempBKingY - 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempBKingX + 2][tempBKingY - 1].getPiece().isWhite == true){
					return false;
				}
			if(tempBKingX - 2 > 0 && tempBKingY + 1 < 8)
				if(temp[tempBKingX - 2][tempBKingY + 1].isOccupied() && temp[tempBKingX - 2][tempBKingY + 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempBKingX - 2][tempBKingY + 1].getPiece().isWhite == true){
					return false;
				}
			if(tempBKingX - 2 > 0 && tempBKingY - 1 > 0)
				if(temp[tempBKingX - 2][tempBKingY - 1].isOccupied() && temp[tempBKingX - 2][tempBKingY - 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempBKingX - 2][tempBKingY - 1].getPiece().isWhite == true){
					return false;
				}
		}
		
		board = temp;
		wKingX = tempWKingX;
		wKingY = tempWKingY;
		bKingX = tempBKingX;
		bKingY = tempBKingY;
		if(whiteMove){
			whiteMove = false;
		}
		else whiteMove = true;
		return true;
	}
	
	// The purpose of this method is to check to see whether the piece on the origin square
	// can move to the destination square. If it can, then executeMove is called.
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
		if (movedPiece.isWhite() == whiteMove) {
			if (movedPiece.type() == Piece.TypePiece.PAWN) {
				if (destinationOccupied) {
					if (Math.abs(x1-x2) == 1) {
						if (y2-y1 == 1 && movedPiece.isWhite() || y2-y1 == -1 && !movedPiece.isWhite()) {
							executeMove(x1,y1,x2,y2,movedPiece);
						}
						else return; //TO-DO throw an exception here (invalid destination square)
					}
					else return; //TO-DO throw an exception here (invalid destination square)
				}
				else if (x1 == x2) {
					if (movedPiece.isWhite() && (y2-y1 == 1 || (y2-y1 == 2 && !movedPiece.hasMoved())) || 
							!movedPiece.isWhite() && (y2-y1 == -1 || (y2-y1 == -2 && !movedPiece.hasMoved()))) {
						executeMove(x1,y1,x2,y2,movedPiece);
					}
					else return; //TO-DO throw an exception here (invalid destination square)
				} else return; //TO-DO throw an exception here (invalid destination square)
			}
			else if (movedPiece.type() == Piece.TypePiece.KNIGHT){
				if (x1-x2 == 1){
					if(y2-y1 == 2){
						executeMove(x1,y1,x2,y2,movedPiece);
					}
					else if (y1-y2 == 2){
						executeMove(x1,y1,x2,y2,movedPiece);
					}
				}
				else if (x1-x2 == 2){
					if(y2-y1 == 1){
						executeMove(x1,y1,x2,y2,movedPiece);
					}
					else if (y1-y2 == 1){
						executeMove(x1,y1,x2,y2,movedPiece);
					}
				}
				else if (x2-x1 == 1){
					if(y2-y1 == 2){
						executeMove(x1,y1,x2,y2,movedPiece);
					}
					else if (y1-y2 == 2){
						executeMove(x1,y1,x2,y2,movedPiece);
					}
				}
				else if (x2-x1 == 2){
					if(y2-y1 == 1){
						executeMove(x1,y1,x2,y2,movedPiece);
					}
					else if (y1-y2 == 1){
						executeMove(x1,y1,x2,y2,movedPiece);
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
							executeMove(x1,y1,x2,y2,movedPiece);
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
							executeMove(x1,y1,x2,y2,movedPiece);
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
							executeMove(x1,y1,x2,y2,movedPiece);
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
							executeMove(x1,y1,x2,y2,movedPiece);
					}
				}
			}
			else if (movedPiece.type() == Piece.TypePiece.BISHOP){
				if(Math.abs(x1-x2) == Math.abs(y1-y2)){
					if(x1-x2 > 0 && y1-y2 > 0){//bottom left
						for(int i = x1-x2;i>1;i--){
							if(board[x2+i-1][y2+i-1].isOccupied()){
								return;
							}
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == true){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece);
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
						executeMove(x1,y1,x2,y2,movedPiece);
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
						executeMove(x1,y1,x2,y2,movedPiece);
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
						executeMove(x1,y1,x2,y2,movedPiece);	
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
						executeMove(x1,y1,x2,y2,movedPiece);
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
						executeMove(x1,y1,x2,y2,movedPiece);
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
						executeMove(x1,y1,x2,y2,movedPiece);
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
						executeMove(x1,y1,x2,y2,movedPiece);
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
							executeMove(x1,y1,x2,y2,movedPiece);
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
							executeMove(x1,y1,x2,y2,movedPiece);
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
							executeMove(x1,y1,x2,y2,movedPiece);
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
							executeMove(x1,y1,x2,y2,movedPiece);
					}
				}
			}
			else if(movedPiece.type() == Piece.TypePiece.KING){
				if(Math.abs(x2-x1) == 1 || Math.abs(y2-y1) == 1){
					executeMove(x1,y1,x2,y2,movedPiece);
				}
				else return;
			}
			
		}
	}
}
