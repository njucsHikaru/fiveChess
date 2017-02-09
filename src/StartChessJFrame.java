import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class StartChessJFrame extends JFrame{
	private ChessBoard chessBoard;
	private JPanel toolbar;
	private JMenuBar menuBar;
	private JMenu funcMenu;
	private JMenuItem startMenuItem,exitMenuItem,backMenuItem;
	private JButton startButton, backButton, exitButton;
	public StartChessJFrame() {
		setTitle("五子棋");
		chessBoard=new ChessBoard();
		Container contentPane=getContentPane();
		contentPane.add(chessBoard);
		chessBoard.setOpaque(true);
		
		menuBar = new JMenuBar();
		funcMenu = new JMenu("Func");
		
		startMenuItem = new JMenuItem("restart");
		exitMenuItem = new JMenuItem("exit");
		backMenuItem = new JMenuItem("back");
		
		funcMenu.add(startMenuItem);
		funcMenu.add(exitMenuItem);
		funcMenu.add(backMenuItem);
		menuBar.add(funcMenu);
		setJMenuBar(menuBar);
		
		toolbar = new JPanel();
		
		startButton = new JButton("restart");
		exitButton = new JButton("exit");
		backButton = new JButton("back");
		
		toolbar.setLayout(new FlowLayout(FlowLayout.TRAILING));
		toolbar.add(startButton);
		toolbar.add(exitButton);
		toolbar.add(backButton);
		
		MyItemListener listener = new MyItemListener();
			
		startButton.addActionListener(listener);
		exitButton.addActionListener(listener);
		backButton.addActionListener(listener);
		
		startMenuItem.addActionListener(listener);
		exitMenuItem.addActionListener(listener);
		backMenuItem.addActionListener(listener);
		
		add(toolbar,BorderLayout.SOUTH); 
		add(chessBoard);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750,700);
		setResizable(false);
		setLocationRelativeTo(null);
		//pack();//自适应大小
	}
	
	private class MyItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj==startMenuItem||obj==startButton) {
				chessBoard.restartGame();
			}
			else if(obj==exitMenuItem||obj==exitButton) {
				System.exit(0);
			}
			else if(obj==backMenuItem||obj==backButton) {
				chessBoard.goBack();
			}
		}
	}
	public static void main(String[] args) {
		StartChessJFrame f = new StartChessJFrame();
		f.setVisible(true);
	}
}
