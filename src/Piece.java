
public class Piece {
	public enum TypePiece {
		KING, QUEEN, KNIGHT, BISHOP, ROOK, PAWN
	}
	
	protected TypePiece type;
	protected boolean hasMoved;
	protected boolean isTaken;
	protected boolean isWhite;
	
	public Piece(boolean isWhite, TypePiece type){
		 this.isWhite = isWhite;
		 this.type = type;
		 hasMoved = false;
		 isTaken = false;
	}
	
	public boolean hasMoved(){
		return hasMoved;
	}
	
	public boolean isTaken(){
		return isTaken;
	}
	
	public boolean isWhite(){
		return isWhite;
	}
	
	public TypePiece type() {
		return type;
	}
}
