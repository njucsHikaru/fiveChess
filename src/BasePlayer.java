
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public abstract class BasePlayer implements IPlayer {
	//我已下的棋子
	protected List<Point> myPoints = new ArrayList<Point>(200);
	//棋盘
	protected ChessBoard chessboard;
	
	public boolean hasWin(){return false;}

	@Override
	public final List<Point> getMyPoints() {
		return myPoints;
	}

	@Override
	public void setChessboard(ChessBoard chessboard) {
		this.chessboard = chessboard;
		
		myPoints.clear();
	}
	
	private final Point temp = new Point(0, 0, Color.white);
	
}

