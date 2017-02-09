import java.awt.Color;


public class Evaluation extends ChessBoard{
	//确定评价指标，单or双，遍历判断
	//遍历n层情况
	//根据打分确定位置
	public boolean judge(int index) {
		boolean flag=false;
		int ori[][]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
		for(int i=0;i<8;i++) {
			flag = judgeByOrientation(ori[i][0],ori[i][1],index);
			if(flag)
				return true;
		}
		return flag;
		
	}
	public boolean judgeByOrientation(int a,int b,int index) {
		int count=1;
		int x=0,y=0;
		Color c = curPlayer?Color.black:Color.white;
		for(x=xIndex-a,y=yIndex-b;!isBoard(x,y);x=x-a,y=y-b) {
			if(getChess(x,y,c)!=null)	count++;
			else break;
		}
		if(count>=index) return true;
		return false;
	}
}


