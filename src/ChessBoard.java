import java.awt.*;
import java.awt.Image.*;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

//棋盘类
public class ChessBoard extends JPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int MARGIN_W = 110;//宽边距
	public static final int MARGIN_H = 50;//高边距
	public static final int GRID_SPAN = 35;//网格间距
	public static final int ROWS = 15;//行数
	public static final int COLS = 15;//列数
	
	
	Point[] chessList = new Point[(ROWS+1)*(COLS+1)];//点坐标
	boolean isBlack = true;//黑先
	boolean over = false;
	
	int chessCount = 0;//棋子数
	boolean curPlayer;//当前玩家,true:黑;false:白
	int xIndex,yIndex;//当前落子
	Color colortemp;
	Image img;
	
	public ChessBoard() {
		img = Toolkit.getDefaultToolkit().getImage("board.jpg");
		addMouseListener(this);
		//处理鼠标动作事件
		addMouseMotionListener(new MouseMotionListener(){
			//按下按钮、重画视图区域repaint()并在鼠标点处显示消息
			public void mouseDragged(MouseEvent e){
				
			}
			//不按按钮、移动鼠标
			public void mouseMoved(MouseEvent e){
				int x1 = (e.getX()-MARGIN_W+GRID_SPAN/2)/GRID_SPAN;
				int y1 = (e.getY()-MARGIN_H+GRID_SPAN/2)/GRID_SPAN;
				if(isBoard(x1,y1) || over || findChess(x1,y1))
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//不能下
				else
					setCursor(new Cursor(Cursor.HAND_CURSOR));//默认状态
			}
		});
	}
	
	//绘制棋盘
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int imgWidth = img.getWidth(this);
		int imgHeight = img.getHeight(this);
		int FWidth = getWidth();
		int FHeight = getHeight();
		int x = (FWidth-imgWidth)/2;
		int y = (FHeight-imgHeight)/2;
		g.drawImage(img, x, y, null);
		
		for(int i=0;i<ROWS+1;i++){
			g.drawLine(MARGIN_W, MARGIN_H+i*GRID_SPAN, MARGIN_W+COLS*GRID_SPAN, MARGIN_H+i*GRID_SPAN);	
		}
		for(int i=0;i<COLS+1;i++){
			g.drawLine(MARGIN_W+i*GRID_SPAN, MARGIN_H, MARGIN_W+i*GRID_SPAN, MARGIN_H+ROWS*GRID_SPAN);	
		}
		for(int i=0;i<chessCount;i++) {
			int xPos=(int) (chessList[i].getX()*GRID_SPAN+MARGIN_W);
			int yPos=(int) (chessList[i].getY()*GRID_SPAN+MARGIN_H);
			g.setColor(chessList[i].getColor());
			 colortemp=chessList[i].getColor();  
	           if(colortemp==Color.black){  //下白棋
	        	   //确定下棋位置w_xPos,w_yPos
	        	   
	               RadialGradientPaint paint = new RadialGradientPaint(xPos-Point.DIAMETER/2+25, yPos-Point.DIAMETER/2+10, 20, new float[]{0f, 1f}  
	               , new Color[]{Color.WHITE, Color.BLACK});  
	               ((Graphics2D) g).setPaint(paint);  
	               ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
	               ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);  
	  
	           }  
	           else if(colortemp==Color.white){  //下黑棋
	               RadialGradientPaint paint = new RadialGradientPaint(xPos-Point.DIAMETER/2+25, yPos-Point.DIAMETER/2+10, 70, new float[]{0f, 1f}  
	               , new Color[]{Color.WHITE, Color.BLACK});  
	               ((Graphics2D) g).setPaint(paint);  
	               ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
	               ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);  
	  
	           }  
	           
	           Ellipse2D e = new Ellipse2D.Float(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, 34, 35);  
	           ((Graphics2D) g).fill(e);  
	           //标记最后一个棋子的红矩形框  
	             
	           if(i==chessCount-1){//如果是最后一个棋子  
	               g.setColor(Color.red);  
	               g.drawRect(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2,  
	                           34, 35);  
	           }  
		}
	}
	
	//在棋子数组中查找是否有索引为x，y的棋子存在  
	   private boolean findChess(int x,int y){  
	       for(Point c:chessList){  
	           if(c!=null&&c.getX()==x&&c.getY()==y)  
	               return true;  
	       }  
	       return false;  
	   }  
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(over) return;
		xIndex=(e.getX()-MARGIN_W+GRID_SPAN/2)/GRID_SPAN;  
	    yIndex=(e.getY()-MARGIN_H+GRID_SPAN/2)/GRID_SPAN; 
	    if(isBoard(xIndex,yIndex)||findChess(xIndex,yIndex))  
	    	return;  
	    Point ch = new Point(xIndex,yIndex,curPlayer?Color.black:Color.white);
	    chessList[chessCount++]=ch;
	    repaint();
	    if(isOver()) {
	    	String msg=String.format("Congratulations, %s win!", curPlayer?"player black":"player white");
	    	JOptionPane.showMessageDialog(this, msg);
	    }
	    curPlayer=!curPlayer;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isOver() {
		boolean flag=false;
		int ori[][]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
		for(int i=0;i<8;i++) {
			flag = judgeByOrientation(ori[i][0],ori[i][1],5);
			if(flag)
				return true;
		}
		return flag;
		
	}
	public boolean judgeByOrientation(int a,int b, int c) {
		int count=1;
		int x=0,y=0;
		Color color = curPlayer?Color.black:Color.white;
		for(x=xIndex-a,y=yIndex-b;!isBoard(x,y);x=x-a,y=y-b) {
			if(getChess(x,y,color)!=null)	count++;
			else break;
		}
		if(count>=c) return true;
		return false;
	}
	public boolean isBoard(int xIndex, int yIndex) {
		return (xIndex<0||xIndex>ROWS||yIndex<0||yIndex>COLS);
	}
	protected Point getChess(int xIndex,int yIndex,Color color){  
		for(Point p:chessList){  
	           if(p!=null&&p.getX()==xIndex&&p.getY()==yIndex  
	                   &&p.getColor()==color)  
	               return p;  
	       }  
	    return null; 
	}  
	
	public void restartGame() {
		for(int i=0;i<chessList.length-1;i++)
			chessList[i]=null;
		chessCount=0;
		curPlayer = true;
		over = false;
		repaint();
	}
	
	public void goBack() {
		if(chessCount==0)
			return;
		chessList[chessCount-1]=null;
		chessCount--;
		if(chessCount>0) {
			xIndex=chessList[chessCount-1].getX();
			yIndex=chessList[chessCount-1].getY();
		}
		curPlayer = true;
		repaint();
	}
}
