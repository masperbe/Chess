
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
	private void wKingXAndY(int wKingX, int wKingY, int x1, int y1, int x2, int y2){
		if(wKingX == x1 && wKingY == y1){
			wKingX = x2;
			wKingY = y2;
		}
		
	}
	private void bKingXAndY(int bKingX, int bKingY, int x1, int y1, int x2, int y2){
		if(bKingX == x1 && bKingY == y1){
			bKingX = x2;
			bKingY = y2;
		}
		
	}
	
	
	// The purpose of this method is to check to see whether the selected move would leave the
	// player on the move in check. If so, false is returned. If not, the move is completed and true is returned.
	private boolean executeMove(int x1, int y1, int x2, int y2, Piece movedPiece) {
		Square[][] temp = new Square[8][8];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				temp[i][j] = board[i][j];
			}
		}
		
		temp[x1][y1].setPiece(null);
		movedPiece.setMoved();
		temp[x2][y2].setPiece(movedPiece);
		
		if(whiteMove){//white to move - is white in check?
			//to the right
			int i = 1;
			int j;
			while(wKingX+i < 8 && temp[wKingX+i][wKingY].isOccupied() == false){
				i++;
			}
			if(wKingX+i < 8 && temp[wKingX+i][wKingY].getPiece().isWhite == false){
				Piece.TypePiece type = temp[wKingX+i][wKingY].getPiece().type();
				if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && i==1) || type == Piece.TypePiece.QUEEN){
					wKingXAndY(wKingX,wKingY,x1,y1,x2,y2);
					return false;
				}
			}
			
			//to the left
			i = -1;
			while(wKingX+i > 0 && temp[wKingX+i][wKingY].isOccupied() == false){
				i--;
			}
			if(wKingX+i > 0 && temp[wKingX+i][wKingY].getPiece().isWhite == false){
				Piece.TypePiece type = temp[wKingX+i][wKingY].getPiece().type();
				if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && i==-1) || type == Piece.TypePiece.QUEEN){
					wKingXAndY(wKingX,wKingY,x1,y1,x2,y2);
					return false;
				}
			}
			
			//to the up
			j = 1;
			while(wKingY+j < 8 && temp[wKingX][wKingY+j].isOccupied() == false){
				j++;
			}
			if(wKingY+j < 8 && temp[wKingX][wKingY+j].getPiece().isWhite == false){
				Piece.TypePiece type = temp[wKingX][wKingY+j].getPiece().type();
				if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && j==1) || type == Piece.TypePiece.QUEEN){
					wKingXAndY(wKingX,wKingY,x1,y1,x2,y2);
					return false;
				}
			}
			
			//to the down
			j = -1;
			while(wKingY+j > 0 && temp[wKingX][wKingY+j].isOccupied() == false){
				j--;
			}
			if(wKingY+j > 0 && temp[wKingX][wKingY+j].getPiece().isWhite == false){
				Piece.TypePiece type = temp[wKingX][wKingY+j].getPiece().type();
				if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && j==-1) || type == Piece.TypePiece.QUEEN){
					wKingXAndY(wKingX,wKingY,x1,y1,x2,y2);
					return false;
				}
			}
			
			//to the top right
			i = 1;
			j = 1;
			while(wKingX+i < 8 && wKingY+j < 8 && temp[wKingX+i][wKingY+j].isOccupied() == false){
				i++;
				j++;
			}
			if(wKingX+i < 8 && wKingY+j < 8 && temp[wKingX+i][wKingY+j].getPiece().isWhite == false){
				Piece.TypePiece type = temp[wKingX+i][wKingY+j].getPiece().type();
				if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && j==1) || type == Piece.TypePiece.QUEEN || type == Piece.TypePiece.PAWN){
					wKingXAndY(wKingX,wKingY,x1,y1,x2,y2);
					return false;
				}
			}
			
			//to the bottom right
			i = 1;
			j = -1;
			while(wKingX+i < 8 && wKingY+j > 0 && temp[wKingX+i][wKingY+j].isOccupied() == false){
				i++;
				j--;
			}
			if(wKingX+i < 8 && wKingY+j > 0 && temp[wKingX+i][wKingY+j].getPiece().isWhite == false){
				Piece.TypePiece type = temp[wKingX+i][wKingY+j].getPiece().type();
				if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && i==1) || type == Piece.TypePiece.QUEEN){
					wKingXAndY(wKingX,wKingY,x1,y1,x2,y2);
					return false;
				}
			}
			
			//to the bottom left
			i = -1;
			j = -1;
			while(wKingX+i > 0 && wKingY+j > 0 && temp[wKingX+i][wKingY+j].isOccupied() == false){
				i--;
				j--;
			}
			if(wKingX+i > 0 && wKingY+j > 0 && temp[wKingX+i][wKingY+j].getPiece().isWhite == false){
				Piece.TypePiece type = temp[wKingX+i][wKingY+j].getPiece().type();
				if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && j==-1) || type == Piece.TypePiece.QUEEN){
					wKingXAndY(wKingX,wKingY,x1,y1,x2,y2);
					return false;
				}
			}
			
			//to the top left
			i = -1;
			j = 1;
			while(wKingX+i > 0 && wKingY+j < 8 && temp[wKingX+i][wKingY+j].isOccupied() == false){
				i--;
				j++;
			}
			if(wKingX+i > 0 && wKingY+j < 8 && temp[wKingX+i][wKingY+j].getPiece().isWhite == false){
				Piece.TypePiece type = temp[wKingX+i][wKingY+j].getPiece().type();
				if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && j==1) || type == Piece.TypePiece.QUEEN || type == Piece.TypePiece.PAWN){
					wKingXAndY(wKingX,wKingY,x1,y1,x2,y2);
					return false;
				}
			}
			if(wKingX + 1 < 8 && wKingY + 2 < 8)
				if(temp[wKingX + 1][wKingY +2].isOccupied() && temp[wKingX + 1][wKingY + 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[wKingX + 1][wKingY +2].getPiece().isWhite == false){
					wKingXAndY(wKingX,wKingY,x1,y1,x2,y2);
					return false;
				}
			if(wKingX + 1 < 8 && wKingY - 2 > 0)
				if(temp[wKingX + 1][wKingY - 2].isOccupied() && temp[wKingX + 1][wKingY - 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[wKingX + 1][wKingY - 2].getPiece().isWhite == false){
					wKingXAndY(wKingX,wKingY,x1,y1,x2,y2);
					return false;
				}
			if(wKingX - 1 > 0 && wKingY + 2 < 8)
				if(temp[wKingX - 1][wKingY + 2].isOccupied() && temp[wKingX - 1][wKingY + 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[wKingX - 1][wKingY + 2].getPiece().isWhite == false){
					wKingXAndY(wKingX,wKingY,x1,y1,x2,y2);
					return false;
				}
			if(wKingX - 1 > 0 && wKingY - 2 > 0)
				if(temp[wKingX - 1][wKingY - 2].isOccupied() && temp[wKingX - 1][wKingY - 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[wKingX - 1][wKingY - 2].getPiece().isWhite == false){
					wKingXAndY(wKingX,wKingY,x1,y1,x2,y2);
					return false;
				}
			if(wKingX + 2 < 8 && wKingY + 1 < 8)
				if(temp[wKingX + 2][wKingY + 1].isOccupied() && temp[wKingX + 2][wKingY + 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[wKingX + 2][wKingY + 1].getPiece().isWhite == false){
					wKingXAndY(wKingX,wKingY,x1,y1,x2,y2);
					return false;
				}
			if(wKingX + 2 < 8 && wKingY - 1 > 0)
				if(temp[wKingX + 2][wKingY - 1].isOccupied() && temp[wKingX + 2][wKingY - 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[wKingX + 2][wKingY - 1].getPiece().isWhite == false){
					wKingXAndY(wKingX,wKingY,x1,y1,x2,y2);
					return false;
				}
			if(wKingX - 2 > 0 && wKingY + 1 < 8)
				if(temp[wKingX - 2][wKingY + 1].isOccupied() && temp[wKingX - 2][wKingY + 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[wKingX - 2][wKingY + 1].getPiece().isWhite == false){
					wKingXAndY(wKingX,wKingY,x1,y1,x2,y2);
					return false;
				}
			if(wKingX - 2 > 0 && wKingY - 1 > 0)
				if(temp[wKingX - 2][wKingY - 1].isOccupied() && temp[wKingX - 2][wKingY - 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[wKingX - 2][wKingY - 1].getPiece().isWhite == false){
					wKingXAndY(wKingX,wKingY,x1,y1,x2,y2);
					return false;
				}
		} else {//black to move - is black in check?
			//to the right
			int i = 1;
			int j;
			while(bKingX+i < 8 && temp[bKingX+i][bKingY].isOccupied() == false){
				i++;
			}
			if(bKingX+i < 8 && temp[bKingX+i][bKingY].getPiece().isWhite == true){
				Piece.TypePiece type = temp[bKingX+i][bKingY].getPiece().type();
				if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && i==1) || type == Piece.TypePiece.QUEEN){
					bKingXAndY(bKingX,bKingY,x1,y1,x2,y2);
					return false;
				}
			}
			
			//to the left
			i = -1;
			while(bKingX+i > 0 && temp[bKingX+i][bKingY].isOccupied() == false){
				i--;
			}
			if(bKingX+i > 0 && temp[bKingX+i][bKingY].getPiece().isWhite == true){
				Piece.TypePiece type = temp[bKingX+i][bKingY].getPiece().type();
				if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && i==-1) || type == Piece.TypePiece.QUEEN){
					bKingXAndY(bKingX,bKingY,x1,y1,x2,y2);
					return false;
				}
			}
			
			//to the up
			j = 1;
			while(bKingY+j < 8 && temp[bKingX][bKingY+j].isOccupied() == false){
				j++;
			}
			if(bKingY+j < 8 && temp[bKingX][bKingY+j].getPiece().isWhite == true){
				Piece.TypePiece type = temp[bKingX][bKingY+j].getPiece().type();
				if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && j==1) || type == Piece.TypePiece.QUEEN){
					bKingXAndY(bKingX,bKingY,x1,y1,x2,y2);
					return false;
				}
			}
			
			//to the down
			j = -1;
			while(bKingY+j > 0 && temp[bKingX][bKingY+j].isOccupied() == false){
				j--;
			}
			if(bKingY+j > 0 && temp[bKingX][bKingY+j].getPiece().isWhite == true){
				Piece.TypePiece type = temp[bKingX][bKingY+j].getPiece().type();
				if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && j==-1) || type == Piece.TypePiece.QUEEN){
					bKingXAndY(bKingX,bKingY,x1,y1,x2,y2);
					return false;
				}
			}
			
			//to the top right
			i = 1;
			j = 1;
			while(bKingX+i < 8 && bKingY+j < 8 && temp[bKingX+i][bKingY+j].isOccupied() == false){
				i++;
				j++;
			}
			if(bKingX+i < 8 && bKingY+j < 8 && temp[bKingX+i][bKingY+j].getPiece().isWhite == true){
				Piece.TypePiece type = temp[bKingX+i][bKingY+j].getPiece().type();
				if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && j==1) || type == Piece.TypePiece.QUEEN || type == Piece.TypePiece.PAWN){
					bKingXAndY(bKingX,bKingY,x1,y1,x2,y2);
					return false;
				}
			}
			
			//to the bottom right
			i = 1;
			j = -1;
			while(bKingX+i < 8 && bKingY+j > 0 && temp[bKingX+i][bKingY+j].isOccupied() == false){
				i++;
				j--;
			}
			if(bKingX+i < 8 && bKingY+j > 0 && temp[bKingX+i][bKingY+j].getPiece().isWhite == true){
				Piece.TypePiece type = temp[bKingX+i][bKingY+j].getPiece().type();
				if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && i==1) || type == Piece.TypePiece.QUEEN){
					bKingXAndY(bKingX,bKingY,x1,y1,x2,y2);
					return false;
				}
			}
			
			//to the bottom left
			i = -1;
			j = -1;
			while(bKingX+i > 0 && bKingY+j > 0 && temp[bKingX+i][bKingY+j].isOccupied() == false){
				i--;
				j--;
			}
			if(bKingX+i > 0 && bKingY+j > 0 && temp[bKingX+i][bKingY+j].getPiece().isWhite == true){
				Piece.TypePiece type = temp[bKingX+i][bKingY+j].getPiece().type();
				if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && j==-1) || type == Piece.TypePiece.QUEEN){
					bKingXAndY(bKingX,bKingY,x1,y1,x2,y2);
					return false;
				}
			}
			
			//to the top left
			i = -1;
			j = 1;
			while(bKingX+i > 0 && bKingY+j < 8 && temp[bKingX+i][bKingY+j].isOccupied() == false){
				i--;
				j++;
			}
			if(bKingX+i > 0 && bKingY+j < 8 && temp[bKingX+i][bKingY+j].getPiece().isWhite == true){
				Piece.TypePiece type = temp[bKingX+i][bKingY+j].getPiece().type();
				if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && j==1) || type == Piece.TypePiece.QUEEN || type == Piece.TypePiece.PAWN){
					bKingXAndY(bKingX,bKingY,x1,y1,x2,y2);
					return false;
				}
			}
			if(bKingX + 1 < 8 && bKingY + 2 < 8)
				if(temp[bKingX + 1][bKingY + 2].isOccupied() && temp[bKingX + 1][bKingY + 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[bKingX + 1][bKingY +2].getPiece().isWhite == true){
					bKingXAndY(bKingX,bKingY,x1,y1,x2,y2);
					return false;
				}
			if(bKingX + 1 < 8 && bKingY - 2 > 0)
				if(temp[bKingX + 1][bKingY - 2].isOccupied() && temp[bKingX + 1][bKingY - 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[bKingX + 1][bKingY - 2].getPiece().isWhite == true){
					bKingXAndY(bKingX,bKingY,x1,y1,x2,y2);
					return false;
				}
			if(bKingX - 1 > 0 && bKingY + 2 < 8)
				if(temp[bKingX - 1][bKingY + 2].isOccupied() && temp[bKingX - 1][bKingY + 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[bKingX - 1][bKingY + 2].getPiece().isWhite == true){
					bKingXAndY(bKingX,bKingY,x1,y1,x2,y2);
					return false;
				}
			if(bKingX - 1 > 0 && bKingY - 2 > 0)
				if(temp[bKingX - 1][bKingY - 2].isOccupied() && temp[bKingX - 1][bKingY - 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[bKingX - 1][bKingY - 2].getPiece().isWhite == true){
					bKingXAndY(bKingX,bKingY,x1,y1,x2,y2);
					return false;
				}
			if(bKingX + 2 < 8 && bKingY + 1 < 8)
				if(temp[bKingX + 2][bKingY + 1].isOccupied() && temp[bKingX + 2][bKingY + 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[bKingX + 2][bKingY + 1].getPiece().isWhite == true){
					bKingXAndY(bKingX,bKingY,x1,y1,x2,y2);
					return false;
				}
			if(bKingX + 2 < 8 && bKingY - 1 > 0)
				if(temp[bKingX + 2][bKingY - 1].isOccupied() && temp[bKingX + 2][bKingY - 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[bKingX + 2][bKingY - 1].getPiece().isWhite == true){
					bKingXAndY(bKingX,bKingY,x1,y1,x2,y2);
					return false;
				}
			if(bKingX - 2 > 0 && bKingY + 1 < 8)
				if(temp[bKingX - 2][bKingY + 1].isOccupied() && temp[bKingX - 2][bKingY + 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[bKingX - 2][bKingY + 1].getPiece().isWhite == true){
					bKingXAndY(bKingX,bKingY,x1,y1,x2,y2);
					return false;
				}
			if(bKingX - 2 > 0 && bKingY - 1 > 0)
				if(temp[bKingX - 2][bKingY - 1].isOccupied() && temp[bKingX - 2][bKingY - 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[bKingX - 2][bKingY - 1].getPiece().isWhite == true){
					bKingXAndY(bKingX,bKingY,x1,y1,x2,y2);
					return false;
				}
		}
		
		board = temp;
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
		if (movedPiece.isWhite() && whiteMove == true) {
			if (movedPiece.type() == Piece.TypePiece.PAWN) {
				if (destinationOccupied) {
					if (Math.abs(x1-x2) == 1) {
						if (y2-y1 == 1) {
							executeMove(x1,y1,x2,y2,movedPiece);
						}
						else return; //TO-DO throw an exception here (invalid destination square)
					}
					else return; //TO-DO throw an exception here (invalid destination square)
				}
				else if (x1 == x2) {
					if (y2-y1 == 1 || ((y2-y1 == 2) && (movedPiece.hasMoved() == false))) {
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
		else if(movedPiece.isWhite() == false && whiteMove == false){
			if (movedPiece.type() == Piece.TypePiece.PAWN) {
				if(destinationOccupied) {
					if(Math.abs(x1-x2) == 1) {
						if(y1-y2 == 1){
							executeMove(x1,y1,x2,y2,movedPiece);
						}
						else return;
					}
					else return;
				}
				else if (x1 == x2){
					if(y1-y2 == 1 || ((y1-y2 == 2)&& (movedPiece.hasMoved() == false))) {
						executeMove(x1,y1,x2,y2,movedPiece);
					}
					else return;
				}
				else return;
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
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
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
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
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
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
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
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
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
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
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
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
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
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
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
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
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
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
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
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
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
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
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
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
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
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
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
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
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
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
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
							if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == false){
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
