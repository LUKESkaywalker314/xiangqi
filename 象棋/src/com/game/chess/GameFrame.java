package com.game.chess;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * 功能：游戏窗口<br>
 * 作者：我是小木鱼（Lag）<br>
 */
public class GameFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -3812892829859080331L;

	/** 游戏面板 */
	private GamePanel gamePanel;

	/**
	 * 功能：构造函数<br>
	 */
	public GameFrame()
	{
		try {
			// 菜单
			JMenuBar jmb_gobang = new JMenuBar();
			JMenu jm_game = new JMenu("游戏");
			jm_game.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			JMenuItem jmi_game_new = jm_game.add("新游戏");
			jmi_game_new.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			jmi_game_new.addActionListener(this);
			jmi_game_new.setActionCommand("new");
			JMenuItem jmi_game_undo = jm_game.add("悔棋");
			jmi_game_undo.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			jmi_game_undo.addActionListener(this);
			jmi_game_undo.setActionCommand("undo");
			JMenuItem jmi_surrender = jm_game.add("认输");
			jmi_surrender.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			jmi_surrender.addActionListener(this);
			jmi_surrender.setActionCommand("surrender");
			jm_game.addSeparator();

			// 添加“保存游戏”菜单项
			JMenuItem jmi_game_save = jm_game.add("保存游戏");
			jmi_game_save.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			jmi_game_save.addActionListener(this);
			jmi_game_save.setActionCommand("save");

			JMenuItem jmi_game_exit = jm_game.add("退出");
			jmi_game_exit.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			jmi_game_exit.addActionListener(this);
			jmi_game_exit.setActionCommand("exit");
			jmb_gobang.add(jm_game);
			JMenu jm_help = new JMenu("帮助");
			jm_help.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			JMenuItem jmi_help_about = jm_help.add("关于");
			jmi_help_about.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			jmi_help_about.addActionListener(this);
			jmi_help_about.setActionCommand("about");
			jmb_gobang.add(jm_help);

			this.setJMenuBar(jmb_gobang);
			// 面板
			this.gamePanel = new GamePanel();
			this.add(this.gamePanel);
			// 显示
			this.setTitle("中国象棋");
			this.setLayout(null);
			this.setSize(666, 620);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);

			JMenu jm_difficulty = new JMenu("难度选择");
			jm_difficulty.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			JMenuItem jmi_difficulty_easy = jm_difficulty.add("初级");
			jmi_difficulty_easy.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			jmi_difficulty_easy.addActionListener(this);
			jmi_difficulty_easy.setActionCommand("easy");
			JMenuItem jmi_difficulty_hard = jm_difficulty.add("高级");
			jmi_difficulty_hard.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			jmi_difficulty_hard.addActionListener(this);
			jmi_difficulty_hard.setActionCommand("hard");
			jmb_gobang.add(jm_difficulty);

			// 添加窗口关闭事件处理
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					int result = JOptionPane.showConfirmDialog(null, "是否保存当前游戏进度？", "确认", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						gamePanel.saveGame();
					} else {
						gamePanel.clearSavedGame();
					}
					System.exit(0);
				}
			});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "程序出现异常错误，即将退出！\r\n\r\n" + e.toString(), "提示", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	public GamePanel getGamePanel() {
		return this.gamePanel;
	}
	/**
	 * 功能：事件监听<br>
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		if("new".equals(command)) {
			this.gamePanel.newGame();
		} else if("undo".equals(command)) {
			this.gamePanel.undo();
		} else if("surrender".equals(command)) {
			this.gamePanel.surrender();
		} else if ("easy".equals(command)) {
			this.gamePanel.setDifficulty("初级");
		} else if ("hard".equals(command)) {
			this.gamePanel.setDifficulty("高级");
		} else if("save".equals(command)) {
			this.gamePanel.saveGame();
		} else if("exit".equals(command)) {
			System.exit(0);
		} else if("about".equals(command)) {
			JOptionPane.showMessageDialog(this,"欢迎使用中国象棋","提示",JOptionPane.INFORMATION_MESSAGE);
		}
	}

}