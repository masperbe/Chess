
public class Board {
	
	public Square[][] board;
		
	private int moveNumber;
	private boolean whiteMove;
	private boolean gameInProgress;
	private int wKingX;
	private int wKingY;
	private int bKingX;
	private int bKingY;
	private boolean check = false;
	
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
	
	public boolean getCheck() {
		return check;
	}
	public boolean opposingCheck(int x1, int y1, int x2, int y2, Piece movedPiece) {
		if (!whiteMove){
			if(movedPiece.type() == Piece.TypePiece.KNIGHT){
				if(x2 + 1 == bKingX && y2 + 2 == bKingY){
					return true;
				}
				if(x2 + 1 == bKingX && y2 - 2 == bKingY){
					return true;
				}
				if(x2 - 1 == bKingX && y2 + 2 == bKingY){
					return true;
				}
				if(x2 - 1 == bKingX && y2 - 2 == bKingY){
					return true;
				}
				if(x2 + 2 == bKingX && y2 + 1 == bKingY){
					return true;
				}
				if(x2 + 2 == bKingX && y2 - 1 == bKingY){
					return true;
				}
				if(x2 - 2 == bKingX && y2 + 1 == bKingY){
					return true;
				}
				if(x2 - 2 == bKingX && y2 - 1 == bKingY){
					return true;
				}
				else return false;
			}
			if(movedPiece.type() == Piece.TypePiece.PAWN){
				if(x2 + 1 == bKingX && y2 + 1 == bKingY){
					return true;
				}
				if(x2 - 1 == bKingX && y2 + 1 == bKingY){
					return true;
				}
				else return false;
			}
			if(movedPiece.type() == Piece.TypePiece.ROOK || movedPiece.type() == Piece.TypePiece.QUEEN){
				int i = 1;
				int j;
				while(x2 + i < 8 && x2+i != bKingX){
					if(board[x2+i][y2].isOccupied()){
						break;
					}
					i++;
				}
				if (x2 + i < 8){
					if (x2+i == bKingX && y2 == bKingY){
						return true;
					}
				}
				i = -1;
				while(x2 + i > 0 && x2+i != bKingX){
					if(board[x2+i][y2].isOccupied()){
						break;
					}
					i--;
				}
				if (x2 + i > 0){
					if (x2+i == bKingX && y2 == bKingY){
						return true;
					}
				}
				j = 1;
				while(y2 + j < 8 && y2+j != bKingY){
					if(board[x2+i][y2].isOccupied()){
						break;
					}
					j++;
				}
				if (y2 + j < 8){
					if (y2+j == bKingY && x2 == bKingX){
						return true;
					}
				}
				j = -1;
				while(y2 + j > 0 && y2+j != bKingY){
					if(board[x2][y2+j].isOccupied()){
						break;
					}
					j--;
				}
				if (y2 + j > 0){
					if (y2+j == bKingY && x2 == bKingX){
						return true;
					}
				}
				
			}
			if(movedPiece.type() == Piece.TypePiece.BISHOP || movedPiece.type() == Piece.TypePiece.QUEEN){
				int i = 1;
				int j= 1;
				while(x2+i < 8 && y2+j < 8 && x2 != bKingX && y2 != bKingY){
					if(board[x2+i][y2+j].isOccupied()){
						break;
					}
					i++;
					j++;
				}
				if(x2+i <8 && y2+1 <8){
					if (x2+i == bKingX && y2 +j == bKingY){
						return true;
					}
				}
				i = -1;
				while(x2+i > 0 && y2+j < 8 && x2 != bKingX && y2 != bKingY){
					if(board[x2+i][y2+j].isOccupied()){
						break;
					}
					i--;
					j++;
				}
				if(x2+i >0 && y2+1 <8){
					if (x2+i == bKingX && y2 +j == bKingY){
						return true;
					}
				}
				j = -1;
				while(x2+i < 8 && y2+j > 0 && x2 != bKingX && y2 != bKingY){
					if(board[x2+i][y2+j].isOccupied()){
						break;
					}
					i++;
					j--;
				}
				if(x2+i <8 && y2-1 >0){
					if (x2+i == bKingX && y2 +j == bKingY){
						return true;
					}
				}
				i = -1;
				j = -1;
				while(x2+i > 0 && y2+j > 0 && x2 != bKingX && y2 != bKingY){
					if(board[x2+i][y2+j].isOccupied()){
						break;
					}
					i--;
					j--;
				}
				if(x2-i >0 && y2-1 >0){
					if (x2+i == bKingX && y2 +j == bKingY){
						return true;
					}
				}
			}
		}
		else{
			if(movedPiece.type() == Piece.TypePiece.KNIGHT){
				if(x2 + 1 == wKingX && y2 + 2 == wKingY){
					return true;
				}
				if(x2 + 1 == wKingX && y2 - 2 == wKingY){
					return true;
				}
				if(x2 - 1 == wKingX && y2 + 2 == wKingY){
					return true;
				}
				if(x2 - 1 == wKingX && y2 - 2 == wKingY){
					return true;
				}
				if(x2 + 2 == wKingX && y2 + 1 == wKingY){
					return true;
				}
				if(x2 + 2 == wKingX && y2 - 1 == wKingY){
					return true;
				}
				if(x2 - 2 == wKingX && y2 + 1 == wKingY){
					return true;
				}
				if(x2 - 2 == wKingX && y2 - 1 == wKingY){
					return true;
				}
				else return false;
			}
			if(movedPiece.type() == Piece.TypePiece.PAWN){
				if(x2 + 1 == wKingX && y2 - 1 == wKingY){
					return true;
				}
				if(x2 - 1 == wKingX && y2 - 1 == wKingY){
					return true;
				}
				else return false;
			}
			if(movedPiece.type() == Piece.TypePiece.ROOK || movedPiece.type() == Piece.TypePiece.QUEEN){
				int i = 1;
				int j;
				while(x2 + i < 8 && x2+i != wKingX){
					i++;
				}
				if (x2 + i < 8){
					if (x2+i == wKingX && y2 == wKingY){
						return true;
					}
				}
				i = -1;
				while(x2 + i > 0 && x2+i != wKingX){
					i--;
				}
				if (x2 + i > 0){
					if (x2+i == wKingX && y2 == wKingY){
						return true;
					}
				}
				j = 1;
				while(y2 + j < 8 && y2+j != wKingY){
					j++;
				}
				if (y2 + j < 8){
					if (y2+j == wKingY && x2 == wKingX){
						return true;
					}
				}
				j = -1;
				while(y2 + j > 0 && y2+j != wKingY){
					j--;
				}
				if (y2 + j > 0){
					if (y2+j == wKingY  && x2 == wKingX){
						return true;
					}
				}
				
			}
			if(movedPiece.type() == Piece.TypePiece.BISHOP || movedPiece.type() == Piece.TypePiece.QUEEN){
				int i = 1;
				int j= 1;
				while(x2+i < 8 && y2+j < 8 && x2 != wKingX && y2 != wKingY){
					i++;
					j++;
				}
				if(x2+i <8 && y2+j <8){
					if (x2+i == wKingX && y2 +j == wKingY){
						return true;
					}
				}
				i = -1;
				while(x2+i < 0 && y2+j < 8 && x2 != wKingX && y2 != wKingY){
					i--;
					j++;
				}
				if(x2+i <0 && y2+j <8){
					if (x2+i == wKingX && y2 +j == wKingY){
						return true;
					}
				}
				j = -1;
				while(x2+i < 8 && y2+j < 0 && x2 != wKingX && y2 != wKingY){
					i++;
					j--;
				}
				if(x2+i <8 && y2+j <0){
					if (x2+i == wKingX && y2 +j == wKingY){
						return true;
					}
				}
				i = -1;
				j = -1;
				while(x2+i < 0 && y2+j < 0 && x2 != wKingX && y2 != wKingY){
					i--;
					j--;
				}
				if(x2+i <0 && y2+j <0){
					if (x2+i == wKingX && y2 +j == wKingY){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	// The purpose of this method is to check to see whether the selected move would leave the
	// player on the move in check. If so, false is returned. If not, the move is completed and true is returned.
	private boolean executeMove(int x1, int y1, int x2, int y2, Piece movedPiece, Piece.TypePiece promote) {
		Square[][] temp = new Square[8][8];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				temp[i][j] = new Square();
				temp[i][j].setPiece(board[i][j].getPiece());
			}
		}
		
		int tempKingX, tempKingY;
		
		if (whiteMove) {
			tempKingX = wKingX;
			tempKingY = wKingY;
		} else {
			tempKingX = bKingX;
			tempKingY = bKingY;
		}
		
		temp[x1][y1].setPiece(null);
		if (y2 == 7 && movedPiece.isWhite()) {
			movedPiece = new Piece(true,promote);
		} else if (y2 == 0 && !movedPiece.isWhite()) {
			movedPiece = new Piece(false,promote);
		}
		movedPiece.setMoved();
		temp[x2][y2].setPiece(movedPiece);
		if (movedPiece.type == Piece.TypePiece.KING) {
			tempKingX = x2;
			tempKingY = y2;
		}
		
		//to the right
		int i = 1;
		int j;
		while(tempKingX+i < 8 && temp[tempKingX+i][tempKingY].isOccupied() == false){
			i++;
		}
		if(tempKingX+i < 8 && temp[tempKingX+i][tempKingY].getPiece().isWhite != whiteMove){
			Piece.TypePiece type = temp[tempKingX+i][tempKingY].getPiece().type();
			if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && i==1) || type == Piece.TypePiece.QUEEN){
				return false;
			}
		}
		
		//to the left
		i = -1;
		while(tempKingX+i > 0 && temp[tempKingX+i][tempKingY].isOccupied() == false){
			i--;
		}
		if(tempKingX+i > 0 && temp[tempKingX+i][tempKingY].getPiece().isWhite != whiteMove){
			Piece.TypePiece type = temp[tempKingX+i][tempKingY].getPiece().type();
			if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && i==-1) || type == Piece.TypePiece.QUEEN){
				return false;
			}
		}
		
		//to the up
		j = 1;
		while(tempKingY+j < 8 && temp[tempKingX][tempKingY+j].isOccupied() == false){
			j++;
		}
		if(tempKingY+j < 8 && temp[tempKingX][tempKingY+j].getPiece().isWhite != whiteMove){
			Piece.TypePiece type = temp[tempKingX][tempKingY+j].getPiece().type();
			if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && j==1) || type == Piece.TypePiece.QUEEN){
				return false;
			}
		}
		
		//to the down
		j = -1;
		while(tempKingY+j > 0 && temp[tempKingX][tempKingY+j].isOccupied() == false){
			j--;
		}
		if(tempKingY+j > 0 && temp[tempKingX][tempKingY+j].getPiece().isWhite != whiteMove){
			Piece.TypePiece type = temp[tempKingX][tempKingY+j].getPiece().type();
			if(type == Piece.TypePiece.ROOK || (type == Piece.TypePiece.KING && j==-1) || type == Piece.TypePiece.QUEEN){
				return false;
			}
		}
		
		//to the top right
		i = 1;
		j = 1;
		while(tempKingX+i < 8 && tempKingY+j < 8 && temp[tempKingX+i][tempKingY+j].isOccupied() == false){
			i++;
			j++;
		}
		if(tempKingX+i < 8 && tempKingY+j < 8 && temp[tempKingX+i][tempKingY+j].getPiece().isWhite != whiteMove){
			Piece.TypePiece type = temp[tempKingX+i][tempKingY+j].getPiece().type();
			if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && j==1) || type == Piece.TypePiece.QUEEN || (type == Piece.TypePiece.PAWN && j==1)){
				return false;
			}
		}
		
		//to the bottom right
		i = 1;
		j = -1;
		while(tempKingX+i < 8 && tempKingY+j > 0 && temp[tempKingX+i][tempKingY+j].isOccupied() == false){
			i++;
			j--;
		}
		if(tempKingX+i < 8 && tempKingY+j > 0 && temp[tempKingX+i][tempKingY+j].getPiece().isWhite != whiteMove){
			Piece.TypePiece type = temp[tempKingX+i][tempKingY+j].getPiece().type();
			if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && i==1) || type == Piece.TypePiece.QUEEN){
				return false;
			}
		}
		
		//to the bottom left
		i = -1;
		j = -1;
		while(tempKingX+i > 0 && tempKingY+j > 0 && temp[tempKingX+i][tempKingY+j].isOccupied() == false){
			i--;
			j--;
		}
		if(tempKingX+i > 0 && tempKingY+j > 0 && temp[tempKingX+i][tempKingY+j].getPiece().isWhite != whiteMove){
			Piece.TypePiece type = temp[tempKingX+i][tempKingY+j].getPiece().type();
			if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && j==-1) || type == Piece.TypePiece.QUEEN){
				return false;
			}
		}
		
		//to the top left
		i = -1;
		j = 1;
		while(tempKingX+i > 0 && tempKingY+j < 8 && temp[tempKingX+i][tempKingY+j].isOccupied() == false){
			i--;
			j++;
		}
		if(tempKingX+i > 0 && tempKingY+j < 8 && temp[tempKingX+i][tempKingY+j].getPiece().isWhite != whiteMove){
			Piece.TypePiece type = temp[tempKingX+i][tempKingY+j].getPiece().type();
			if(type == Piece.TypePiece.BISHOP || (type == Piece.TypePiece.KING && j==1) || type == Piece.TypePiece.QUEEN || (type == Piece.TypePiece.PAWN && j==1)){
				return false;
			}
		}
		if(tempKingX + 1 < 8 && tempKingY + 2 < 8)
			if(temp[tempKingX + 1][tempKingY +2].isOccupied() && temp[tempKingX + 1][tempKingY + 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempKingX + 1][tempKingY +2].getPiece().isWhite != whiteMove){
				return false;
			}
		if(tempKingX + 1 < 8 && tempKingY - 2 > 0)
			if(temp[tempKingX + 1][tempKingY - 2].isOccupied() && temp[tempKingX + 1][tempKingY - 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempKingX + 1][tempKingY - 2].getPiece().isWhite != whiteMove){
				return false;
			}
		if(tempKingX - 1 > 0 && tempKingY + 2 < 8)
			if(temp[tempKingX - 1][tempKingY + 2].isOccupied() && temp[tempKingX - 1][tempKingY + 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempKingX - 1][tempKingY + 2].getPiece().isWhite != whiteMove){
				return false;
			}
		if(tempKingX - 1 > 0 && tempKingY - 2 > 0)
			if(temp[tempKingX - 1][tempKingY - 2].isOccupied() && temp[tempKingX - 1][tempKingY - 2].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempKingX - 1][tempKingY - 2].getPiece().isWhite != whiteMove){
				return false;
			}
		if(tempKingX + 2 < 8 && tempKingY + 1 < 8)
			if(temp[tempKingX + 2][tempKingY + 1].isOccupied() && temp[tempKingX + 2][tempKingY + 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempKingX + 2][tempKingY + 1].getPiece().isWhite != whiteMove){
				return false;
			}
		if(tempKingX + 2 < 8 && tempKingY - 1 > 0)
			if(temp[tempKingX + 2][tempKingY - 1].isOccupied() && temp[tempKingX + 2][tempKingY - 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempKingX + 2][tempKingY - 1].getPiece().isWhite != whiteMove){
				return false;
			}
		if(tempKingX - 2 > 0 && tempKingY + 1 < 8)
			if(temp[tempKingX - 2][tempKingY + 1].isOccupied() && temp[tempKingX - 2][tempKingY + 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempKingX - 2][tempKingY + 1].getPiece().isWhite != whiteMove){
				return false;
			}
		if(tempKingX - 2 > 0 && tempKingY - 1 > 0)
			if(temp[tempKingX - 2][tempKingY - 1].isOccupied() && temp[tempKingX - 2][tempKingY - 1].getPiece().type() == Piece.TypePiece.KNIGHT && temp[tempKingX - 2][tempKingY - 1].getPiece().isWhite != whiteMove){
				return false;
			}
		
		// If we reach this line of code, the move is legal
		board = temp;
		
		if(whiteMove){
			whiteMove = false;
			wKingX = tempKingX;
			wKingY = tempKingY;
		}
		else {
			whiteMove = true;
			bKingX = tempKingX;
			bKingY = tempKingY;
		}
		check = opposingCheck(x1, y1, x2, y2, movedPiece);
		return true;
	}
	
	// The purpose of this method is to check to see whether the piece on the origin square
	// can move to the destination square. If it can, then executeMove is called.
	public void move(int x1, int y1, int x2, int y2, Piece.TypePiece promote){
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
							executeMove(x1,y1,x2,y2,movedPiece,promote);
						}
						else return; //TO-DO throw an exception here (invalid destination square)
					}
					else return; //TO-DO throw an exception here (invalid destination square)
				}
				else if (x1 == x2) {
					if (movedPiece.isWhite() && (y2-y1 == 1 || (y2-y1 == 2 && !movedPiece.hasMoved())) || 
							!movedPiece.isWhite() && (y2-y1 == -1 || (y2-y1 == -2 && !movedPiece.hasMoved()))) {
						executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
					else return; //TO-DO throw an exception here (invalid destination square)
				} else return; //TO-DO throw an exception here (invalid destination square)
			}
			else if (movedPiece.type() == Piece.TypePiece.KNIGHT){
				if (x1-x2 == 1){
					if(y2-y1 == 2){
						executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
					else if (y1-y2 == 2){
						executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
				}
				else if (x1-x2 == 2){
					if(y2-y1 == 1){
						executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
					else if (y1-y2 == 1){
						executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
				}
				else if (x2-x1 == 1){
					if(y2-y1 == 2){
						executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
					else if (y1-y2 == 2){
						executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
				}
				else if (x2-x1 == 2){
					if(y2-y1 == 1){
						executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
					else if (y1-y2 == 1){
						executeMove(x1,y1,x2,y2,movedPiece,promote);
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
						}
							executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
					else if(x2-x1 > 0){
						for(int i = x2-x1;i>1;i--){
							if(board[x1+i-1][y2].isOccupied()){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,promote);
					}	
				}
				if(x1-x2 == 0){
					if(y1-y2 > 0){
						for(int i = y1-y2;i>1;i--){
							if(board[x2][y1-i+1].isOccupied()){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
					else if(y2-y1 > 0){
						for(int i = y2-y1;i>1;i--){
							if(board[x2][y2-i+1].isOccupied()){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,promote);
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
						}
						executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
					else if(x2-x1 > 0 && y2-y1 > 0){//top right
						for(int i = y2-y1;i>1;i--){
							if(board[x2-i+1][y2-i+1].isOccupied()){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
					else if(x2-x1 > 0 && y1-y2 > 0){//bottom right
						for(int i = x2-x1;i>1;i--){
							if(board[x2-i+1][y2+i-1].isOccupied()){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
					else if(x1-x2 > 0 && y2-y1 > 0){//top left
						for(int i = y2-y1;i>1;i--){
							if(board[x2+i-1][y2-i+1].isOccupied()){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,promote);	
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
						}
						executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
					else if(x2-x1 > 0 && y2-y1 > 0){//top right
						for(int i = y2-y1;i>1;i--){
							if(board[x2-i+1][y2-i+1].isOccupied()){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
					else if(x2-x1 > 0 && y1-y2 > 0){//bottom right
						for(int i = x2-x1;i>1;i--){
							if(board[x2-i+1][y2+i-1].isOccupied()){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
					else if(x1-x2 > 0 && y2-y1 > 0){//top left
						for(int i = y2-y1;i>1;i--){
							if(board[x2+i-1][y2-i+1].isOccupied()){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
				}
				if(y1-y2 == 0){
					if(x1-x2 > 0){
						if(board[x2][y2].isOccupied() && board[x2][y2].getPiece().isWhite() == whiteMove){
							return;
						}
						for(int i = x1-x2;i>1;i--){
							if(board[x2-1+i][y2].isOccupied()){
								return;
							}
							
						}
							executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
					else if(x2-x1 > 0){
						for(int i = x2-x1;i>1;i--){
							if(board[x1+i-1][y2].isOccupied()){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
				}
				if(x1-x2 == 0){
					if(y1-y2 > 0){
						for(int i = y1-y2;i>1;i--){
							if(board[x2][y1-i+1].isOccupied()){
								return;
							}
						}
							executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
					else if(y2-y1 > 0){
						for(int i = y2-y1;i>1;i--){
							if(board[x2][y2-i+1].isOccupied()){
								return;
							}
						}
						executeMove(x1,y1,x2,y2,movedPiece,promote);
					}
				}
			}
			else if(movedPiece.type() == Piece.TypePiece.KING){
				if((Math.abs(x2-x1) == 1 && Math.abs(y2-y1) <=  1) || (Math.abs(y2-y1) == 1 && Math.abs(x2-x1) <= 1)){
					executeMove(x1,y1,x2,y2,movedPiece,promote);
				}
				else if(movedPiece.hasMoved == false && x2-x1 == 2 && board[7][0].getPiece().hasMoved() == false && board[5][0].isOccupied() == false){
					executeMove(x1,y1,x2,y2,movedPiece,promote);
					movedPiece = board[7][0].getPiece();
					board[7][0].setPiece(null);
					board[5][0].setPiece(movedPiece);
				}
				else if(movedPiece.hasMoved == false && x1-x2 == 2 && board[0][0].getPiece().hasMoved() == false && board[1][0].isOccupied() == false && board[3][0].isOccupied() == false){
					executeMove(x1,y1,x2,y2,movedPiece,promote);
					movedPiece = board[0][0].getPiece();
					board[0][0].setPiece(null);
					board[3][0].setPiece(movedPiece);
				}
				else if(movedPiece.hasMoved == false && x2-x1 == 2 && board[7][7].getPiece().hasMoved() == false && board[5][7].isOccupied() == false){
					executeMove(x1,y1,x2,y2,movedPiece,promote);
					movedPiece = board[7][7].getPiece();
					board[7][7].setPiece(null);
					board[5][7].setPiece(movedPiece);
				}
				else if(movedPiece.hasMoved == false && x1-x2 == 2 && board[0][7].getPiece().hasMoved() == false && board[1][7].isOccupied() == false && board[3][7].isOccupied() == false){
					executeMove(x1,y1,x2,y2,movedPiece,promote);
					movedPiece = board[0][7].getPiece();
					board[0][7].setPiece(null);
					board[3][7].setPiece(movedPiece);
				}
				else return;
			}
			
		}
	}
}
