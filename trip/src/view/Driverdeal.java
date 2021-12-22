package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Entity.Driver;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;

public class Driverdeal extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	private JList yearlist;
	private JList monthlist;
	private Driver driver;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Driverdeal frame = new Driverdeal(driver);
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
	public Driverdeal(Driver driverinput) {
		driver=driverinput;
		setIconImage(Toolkit.getDefaultToolkit().getImage(Driverdeal.class.getResource("/icon/find.png")));
		setTitle("\u5386\u53F2\u8BA2\u5355");
		setBounds(100, 100, 882, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 852, 326);
		//scrollPane.setLayout(null);
		contentPane.add(scrollPane);
		
		JLabel amounttxt = new JLabel("0.0");
		amounttxt.setFont(new Font("Dialog", Font.BOLD, 30));
		amounttxt.setBounds(139, 403, 138, 36);
		contentPane.add(amounttxt);
		
		
		table = new JTable();
		table.setFont(new Font("Dialog", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u4E58\u5BA2id", "\u4E58\u5BA2\u7535\u8BDD", "\u91D1\u989D", "\u652F\u4ED8\u72B6\u6001", "\u884C\u7A0B\u8DDD\u79BB", "\u4E0B\u8F66\u5730\u70B9", "\u4E0A\u8F66\u5730\u70B9", "\u7C7B\u578B", "\u5F00\u59CB\u65F6\u95F4", "\u7ED3\u675F\u65F6\u95F4"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(145);
		table.getColumnModel().getColumn(5).setPreferredWidth(105);
		table.getColumnModel().getColumn(6).setPreferredWidth(105);
		table.getColumnModel().getColumn(8).setPreferredWidth(150);
		table.getColumnModel().getColumn(9).setPreferredWidth(150);
		table.setAutoCreateRowSorter(true);
		table.setBounds(0, 0, 852, 326);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		this.fillTableInfoid();
		amounttxt.setText(driver.amount+"");
		scrollPane.setViewportView(table);
		
		JScrollPane selectByYear = new JScrollPane();
		selectByYear.setBounds(289, 379, 153, 105);
		contentPane.add(selectByYear);
		
		yearlist = new JList();
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
		selectByMonth.setBounds(478, 379, 153, 105);
		contentPane.add(selectByMonth);
		
		monthlist = new JList();
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
		monthlist.setFont(new Font("Dialog", Font.BOLD, 30));
		selectByMonth.setViewportView(monthlist);
		
		JButton yearbuttun = new JButton("按年查找");
		yearbuttun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectinfobyyear(e);
				amounttxt.setText(driver.amount+"");
			}
		});
		yearbuttun.setFont(new Font("Dialog", Font.BOLD, 25));
		yearbuttun.setBounds(670, 357, 137, 63);
		contentPane.add(yearbuttun);
		
		JButton monyhbuttun = new JButton("按月查找");
		monyhbuttun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectinfobymonth(e);
				amounttxt.setText(driver.amount+"");
			}
		});
		monyhbuttun.setFont(new Font("Dialog", Font.BOLD, 25));
		monyhbuttun.setBounds(670, 432, 137, 68);
		contentPane.add(monyhbuttun);
		
		JLabel amountlabel = new JLabel("总收入");
		amountlabel.setFont(new Font("Dialog", Font.BOLD, 30));
		amountlabel.setBounds(35, 403, 91, 36);
		contentPane.add(amountlabel);
		
	
		
	}

	private void selectinfobymonth(ActionEvent e) {
		int year =yearlist.getSelectedIndex();
		int month = monthlist.getSelectedIndex();
		this.fillTableInfomonth(year,month);
	}

	private void selectinfobyyear(ActionEvent e) {
		int i = yearlist.getSelectedIndex();
		this.fillTableInfoyear(i);
		
	}
	
	
	private void fillTableInfoid() {
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		ArrayList<Vector> sites = null;
		try {
			sites = Driver.getDataTrip("trip",driver.rk);
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
			sites = Driver.getDataTripYear("trip",driver.rk,year+2017);
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
			sites = Driver.getDataTripMonth("trip",driver.rk,year+2017,month+1);
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
