
public class Square {
	private Piece piece;
	private boolean occupied;
	
	public Square(){
		piece = null;
		occupied = false;
	}
	
	public Square(Piece piece){
		this.piece = piece;
		if(piece == null){
			occupied = false;
		}
		else{
			occupied = true;
		}
	}
		
	public Piece getPiece() {
		return piece;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
		if (piece == null)
			occupied = false;
		else
			occupied = true;
	}
	
	public boolean isOccupied() {
		return occupied;
	}	
}
