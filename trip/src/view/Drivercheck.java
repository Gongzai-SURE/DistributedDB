package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entity.Driver;
import Entity.Driver2;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Drivercheck extends JFrame {

	private JPanel contentPane;
	private String driver_id;
	private JLabel usernametxt;
	private JLabel tripfeetxt;
	private JLabel uploctxt;
	private JLabel destxt;
	private JLabel phonetxt;
	private JLabel lentxt;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Drivercheck frame = new Drivercheck();
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
	public Drivercheck(Driver2 driver) {
		driver_id=driver.getRk();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Drivercheck.class.getResource("/icon/back.png")));
		setTitle("\u8BA2\u5355\u786E\u8BA4");
		setBounds(100, 100, 803, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel username = new JLabel("\u4E58\u5BA2\u59D3\u540D:");
		username.setIcon(new ImageIcon(Drivercheck.class.getResource("/icon/usermanage.png")));
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setFont(new Font("����", Font.BOLD, 30));
		username.setBounds(23, 118, 186, 44);
		contentPane.add(username);
		
		JLabel tripfee = new JLabel("\u884C\u7A0B\u91D1\u989D:");
		tripfee.setIcon(new ImageIcon(Drivercheck.class.getResource("/icon/deal.png")));
		tripfee.setHorizontalAlignment(SwingConstants.CENTER);
		tripfee.setFont(new Font("����", Font.BOLD, 30));
		tripfee.setBounds(23, 192, 186, 44);
		contentPane.add(tripfee);
		
		JButton confirm = new JButton("结束订单");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmperformed(e,driver);
			}
		});
		confirm.setIcon(new ImageIcon(Drivercheck.class.getResource("/icon/confine.png")));
		confirm.setFont(new Font("����", Font.BOLD, 30));
		confirm.setBounds(311, 270, 191, 57);
		contentPane.add(confirm);
		
		usernametxt = new JLabel("\u5305\u5B50");
		usernametxt.setHorizontalAlignment(SwingConstants.CENTER);
		usernametxt.setFont(new Font("����", Font.BOLD, 30));
		usernametxt.setBounds(229, 118, 133, 44);
		contentPane.add(usernametxt);
		
		tripfeetxt = new JLabel("999\u5143");
		tripfeetxt.setHorizontalAlignment(SwingConstants.CENTER);
		tripfeetxt.setFont(new Font("����", Font.BOLD, 30));
		tripfeetxt.setBounds(231, 192, 133, 44);
		contentPane.add(tripfeetxt);
		
		JLabel uploc = new JLabel("上车地点:");
		uploc.setIcon(new ImageIcon(Drivercheck.class.getResource("/icon/location.png")));
		uploc.setHorizontalAlignment(SwingConstants.CENTER);
		uploc.setFont(new Font("Dialog", Font.BOLD, 30));
		uploc.setBounds(23, 34, 186, 44);
		contentPane.add(uploc);
		
		JLabel destination=new JLabel("目的地");
		destination.setIcon(new ImageIcon(Drivercheck.class.getResource("/icon/location.png")));
		destination.setHorizontalAlignment(SwingConstants.CENTER);
		destination.setFont(new Font("Dialog", Font.BOLD, 30));
		destination.setBounds(368, 34, 186, 44);
		contentPane.add(destination);
		
		uploctxt = new JLabel("包子");
		uploctxt.setHorizontalAlignment(SwingConstants.CENTER);
		uploctxt.setFont(new Font("Dialog", Font.BOLD, 30));
		uploctxt.setBounds(229, 34, 133, 44);
		contentPane.add(uploctxt);
		
		destxt = new JLabel("包子");
		destxt.setHorizontalAlignment(SwingConstants.CENTER);
		destxt.setFont(new Font("Dialog", Font.BOLD, 30));
		destxt.setBounds(566, 34, 191, 44);
		contentPane.add(destxt);
		
		JLabel phone = new JLabel("联系方式:");
		phone.setIcon(new ImageIcon(Drivercheck.class.getResource("/icon/changephone.png")));
		phone.setHorizontalAlignment(SwingConstants.CENTER);
		phone.setFont(new Font("Dialog", Font.BOLD, 30));
		phone.setBounds(368, 118, 186, 44);
		contentPane.add(phone);
		
		JLabel triplen = new JLabel("行程距离:");
		triplen.setIcon(new ImageIcon(Drivercheck.class.getResource("/icon/situation.png")));
		triplen.setHorizontalAlignment(SwingConstants.CENTER);
		triplen.setFont(new Font("Dialog", Font.BOLD, 30));
		triplen.setBounds(368, 192, 186, 44);
		contentPane.add(triplen);
		
		phonetxt = new JLabel("包子");
		phonetxt.setHorizontalAlignment(SwingConstants.CENTER);
		phonetxt.setFont(new Font("Dialog", Font.BOLD, 30));
		phonetxt.setBounds(566, 118, 178, 44);
		contentPane.add(phonetxt);
		
	    lentxt = new JLabel("包子");
		lentxt.setHorizontalAlignment(SwingConstants.CENTER);
		lentxt.setFont(new Font("Dialog", Font.BOLD, 30));
		lentxt.setBounds(566, 192, 178, 44);
		contentPane.add(lentxt);
		
		this.settext(driver);
	
		
	}
	private void settext(Driver2 driver) {
		String rowkey2=driver.getRk();
		ArrayList<String> sites = new ArrayList<String>();
		try {
			sites=driver.getorderdata(rowkey2);
			System.out.println(sites);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(sites.get(0));
		
		this.uploctxt.setText(String.valueOf(sites.get(0)));
		this.destxt.setText(String.valueOf(sites.get(1)));
		this.usernametxt.setText(String.valueOf(sites.get(2)));
		this.phonetxt.setText(String.valueOf(sites.get(3)));
		this.tripfeetxt.setText(String.valueOf(sites.get(4)));
		this.lentxt.setText(String.valueOf(sites.get(5)));
		
		
	}
	
	private void confirmperformed(ActionEvent e,Driver2 driver) {
		String rowkey2=driver.getRk();
		try {
			driver.over_trip(rowkey2);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		this.setVisible(false);
			/**
			 * 传回确认信号
			 */
		JOptionPane.showMessageDialog(null, "订单已确认");
		
	}
}
