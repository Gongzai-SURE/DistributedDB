package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import Entity.*;
import Entity.Driver2;
//import driver.Drivercheck;

public class DriverUI extends JFrame {

	private JPanel contentPane;
	private Driver driverinput=new Driver();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DriverUI frame = new DriverUI("driv1","zhangsan");
//					frame.setLocationRelativeTo(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public DriverUI(String rk,String un,String psw) {
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(DriverUI.class.getResource("/icon/driver.png")));
		setTitle("PIUPIU\u53F8\u673A\u6700\u68D2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 542);
		Driver driv=new Driver(rk,un);
		driv.psw=psw;
		try {
			driv.getDatainfo();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu my = new JMenu("\u6211\u7684  ");
		my.setIcon(new ImageIcon(DriverUI.class.getResource("/icon/driversmall.png")));
		my.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menuBar.add(my);
		
		JMenuItem myinfo = new JMenuItem("修改信息");
		myinfo.setIcon(new ImageIcon(DriverUI.class.getResource("/icon/myinfosmall.png")));
		myinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TurnTodriverinfo(e,driv);
			}
		});
		my.add(myinfo);
		
		JMenuItem Historydeal = new JMenuItem("\u5386\u53F2\u8BA2\u5355");
		Historydeal.setIcon(new ImageIcon(DriverUI.class.getResource("/icon/dealsmall .png")));
		Historydeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TurnToDriverdeal(e);
			}
		});
		my.add(Historydeal);
		
		JMenuItem dealconfirm = new JMenuItem("\u8BA2\u5355\u786E\u8BA4");
		dealconfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drivercheckperformed(e,rk);
			}
		});
		dealconfirm.setIcon(new ImageIcon(DriverUI.class.getResource("/icon/backsmall1.png")));
		my.add(dealconfirm);
		
		JMenu AboutUS = new JMenu("\u5173\u4E8E\u6211\u4EEC");
		AboutUS.setIcon(new ImageIcon(DriverUI.class.getResource("/icon/aboutussmall.png")));
		AboutUS.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menuBar.add(AboutUS);
		
		JMenuItem egg = new JMenuItem("\u5F69\u86CB");
		egg.setIcon(new ImageIcon(DriverUI.class.getResource("/icon/eggsmall.png")));
		egg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eggperformed(e);
			}
		});
		AboutUS.add(egg);
		
		JMenu back = new JMenu("\u8FD4\u56DE");
		back.setIcon(new ImageIcon(DriverUI.class.getResource("/icon/backsmall1.png")));
		back.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menuBar.add(back);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u8FD4\u56DE\u767B\u5F55\u754C\u9762");
		mntmNewMenuItem_2.setIcon(new ImageIcon(DriverUI.class.getResource("/icon/backsmall2.png")));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backtologin(e);
			}
		});
		back.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel adj = new JLabel("\u5C0A\u656C\u7684");
		adj.setFont(new Font("����", Font.BOLD, 30));
		adj.setBounds(28, 41, 107, 54);
		contentPane.add(adj);
		
		JLabel dirvername = new JLabel(driv.un);
		dirvername.setFont(new Font("����", Font.BOLD, 30));
		dirvername.setBounds(147, 41, 302, 54);
		contentPane.add(dirvername);
		
		JLabel HistoryDealNum = new JLabel(driv.hdtype);
//		HistoryDealNum.setIcon(new ImageIcon(Driver.class.getResource("/icon/deal.png")));
		HistoryDealNum.setFont(new Font("����", Font.BOLD, 30));
		HistoryDealNum.setBounds(28, 136, 489, 54);
		contentPane.add(HistoryDealNum);
		
		JLabel scoretxt = new JLabel("评分：");
//		HistoryDealNum_1.setIcon(new ImageIcon(Driver.class.getResource("/icon/deal.png")));
		scoretxt.setFont(new Font("����", Font.BOLD, 30));
		scoretxt.setBounds(28, 215, 179, 54);
		contentPane.add(scoretxt);
		
		JLabel score = new JLabel(driv.score+"");
		score.setFont(new Font("����", Font.BOLD, 30));
		score.setBounds(122, 215, 135, 54);
		contentPane.add(score);
	}

	private void drivercheckperformed(ActionEvent e,String rk) {
		Driver2 new_driver=new Driver2();
		new_driver.setRk(rk);
		String xx=new_driver.getRk();
		try {
			if(new_driver.is_over_trip(xx)) {
			System.out.println(xx);
			this.setVisible(true);
			Drivercheck frame = new Drivercheck(new_driver);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null,"无进行中的订单");
			}
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void TurnToDriverdeal(ActionEvent e) {
		this.setVisible(true);
		Driverdeal frame = new Driverdeal(driverinput);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void TurnTodriverinfo(ActionEvent evt,Driver driv) {

		
		this.setVisible(true);
		Driverchangeinfo frame = new Driverchangeinfo(driv);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	private void backtologin(ActionEvent evt) {
		dispose();
		main123 frame = new main123();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * �ʵ�
	 * @param e
	 */
	private void eggperformed(ActionEvent e) {
		this.setVisible(true);
		SurpriseEgg frame = new SurpriseEgg();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
