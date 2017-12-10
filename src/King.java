
public class King extends Piece{
	public King(boolean isWhite, TypePiece type) {
		super(isWhite, type);
		// TODO Auto-generated constructor stub
	}

	private boolean isChecked;
	private boolean isMated;
	
	public boolean isChecked() {
		return isChecked;
	}
	
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	
	public boolean isMated() {
		return isMated;
	}
	
	public void setMated(boolean isMated) {
		this.isMated = isMated;
	}	
}
