package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.AbstractListModel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import Entity.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class usertrip extends JFrame {
	private JPanel contentPane;
	private JTable table;
	public Driver driver=new Driver();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					usertrip frame = new usertrip();
//					frame.setLocationRelativeTo(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public usertrip(User user) {
		driver.rk=user.rk;
		setIconImage(Toolkit.getDefaultToolkit().getImage(Driverdeal.class.getResource("/icon/find.png")));
		setTitle("\u5386\u53F2\u8BA2\u5355");
		setBounds(100, 100, 882, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 852, 326);
		contentPane.add(scrollPane);
		
		JLabel amounttxt = new JLabel("");
		amounttxt.setFont(new Font("Dialog", Font.BOLD, 30));
		amounttxt.setBounds(141, 418, 138, 36);
		contentPane.add(amounttxt);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u53F8\u673Aid", "\u53F8\u673A\u7535\u8BDD", "\u91D1\u989D", "\u652F\u4ED8\u72B6\u6001", "\u8BC4\u5206", "\u884C\u7A0B\u8DDD\u79BB", "\u4E0B\u8F66\u5730\u70B9", "\u4E0A\u8F66\u5730\u70B9", "\u7C7B\u578B", "\u5F00\u59CB\u65F6\u95F4", "\u7ED3\u675F\u65F6\u95F4"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(145);
		table.getColumnModel().getColumn(6).setPreferredWidth(105);
		table.getColumnModel().getColumn(7).setPreferredWidth(105);
		table.getColumnModel().getColumn(9).setPreferredWidth(150);
		table.getColumnModel().getColumn(10).setPreferredWidth(150);
		table.setFont(new Font("Dialog", Font.PLAIN, 15));
//		table.setAutoCreateRowSorter(true);
		table.setBounds(0, 0, 852, 326);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		this.fillTableInfoid();
		amounttxt.setText(driver.amount+"");
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		JScrollPane selectByYear = new JScrollPane();
		selectByYear.setBounds(297, 381, 153, 105);
		contentPane.add(selectByYear);
		
		JList yearlist = new JList();
		yearlist.setFont(new Font("Dialog", Font.BOLD, 30));
		yearlist.setModel(new AbstractListModel() {
			String[] values = new String[] {"2017", "2018", "2019", "2020", "2021"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		yearlist.setSelectedIndex(0);
		selectByYear.setViewportView(yearlist);
		
		JScrollPane selectByMonth = new JScrollPane();
		selectByMonth.setBounds(492, 381, 153, 105);
		contentPane.add(selectByMonth);
		
		JList monthlist = new JList();
		monthlist.setFont(new Font("Dialog", Font.BOLD, 30));
		monthlist.setModel(new AbstractListModel() {
			String[] values = new String[] {"Jan 01", "Feb 02", "Mar 03", "Apr 04", "May 05", "Jun 06", "Jul 07", "Aug 08", "Spet 09", "Oct 10", "Nov 11", "Dec 12"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		monthlist.setSelectedIndex(0);
		selectByMonth.setViewportView(monthlist);
		
		JButton yearbuttun = new JButton("按年查找");
		yearbuttun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = yearlist.getSelectedIndex();
				fillTableInfoyear(i);
				amounttxt.setText(driver.amount+"");
			}
		});
		yearbuttun.setFont(new Font("CESI黑体-GB2312", Font.BOLD, 25));
		yearbuttun.setBounds(684, 361, 163, 63);
		contentPane.add(yearbuttun);
		
		JButton monyhbuttun = new JButton("按月查找");
		monyhbuttun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year =yearlist.getSelectedIndex();
				int month = monthlist.getSelectedIndex();
				fillTableInfomonth(year,month);
				amounttxt.setText(driver.amount+"");
			}
		});
		monyhbuttun.setFont(new Font("CESI黑体-GB2312", Font.BOLD, 25));
		monyhbuttun.setBounds(684, 436, 163, 68);
		contentPane.add(monyhbuttun);
		
		JLabel amountlabel = new JLabel("总支出");
		amountlabel.setFont(new Font("Dialog", Font.BOLD, 30));
		amountlabel.setBounds(37, 418, 91, 36);
		contentPane.add(amountlabel);
		
		
	}
	
	private void fillTableInfoid() {
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		ArrayList<Vector> sites = null;
		try {
			sites = driver.getDataTripUser("trip",driver.rk);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//JTable t=new JTable();
		
		for (Vector v:sites) { 
			dtm.addRow(v);
			System.out.println(v);
		}					
	}
	
	private void fillTableInfoyear(int year) {
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		ArrayList<Vector> sites = null;
		try {
			sites = driver.getDataTripYearUser("trip",driver.rk,year+2017);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//JTable t=new JTable();
		
		for (Vector v:sites) { 
			dtm.addRow(v);
			System.out.println(v);
		}					
	}

	private void fillTableInfomonth(int year,int month) {
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		ArrayList<Vector> sites = null;
		try {
			sites = driver.getDataTripMonthUser("trip",driver.rk,year+2017,month+1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//JTable t=new JTable();
		
		for (Vector v:sites) { 
			dtm.addRow(v);
			System.out.println(v);
		}					
	}
}
