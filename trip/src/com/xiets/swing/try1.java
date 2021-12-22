package com.xiets.swing;

import java.awt.*;
import javax.swing.*;
@SuppressWarnings("serial")
public class try1 extends JFrame {
	
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 155;
    //����label ��textField
    private JLabel usernameLabel = new JLabel("�û���");
    private JLabel passWLabel = new JLabel("����");
    private JTextField usernameTextField = new JTextField();
    private JPasswordField passwoTextField = new JPasswordField();
    private JButton loginBtn = new JButton("ȷ��");
    private JButton closeBtn = new JButton("�ر�");
    private JPanel usernamePanel = new JPanel();
    private JPanel passwordPanel = new JPanel();
    private JPanel btnPanel = new JPanel();
    public void Login() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ϵͳ��¼");
        Container c = getContentPane();
        //����������Ϊ���񲼾�
        c.setLayout(new GridLayout(3, 1, 10, 10));
        //�����û���panel
        usernamePanel.setLayout(new FlowLayout());
        FlowLayout usernameLayout = (FlowLayout) usernamePanel.getLayout();
        usernameLayout.setAlignment(FlowLayout.CENTER);

        passwordPanel.setLayout(new FlowLayout());
        FlowLayout passwordLayout = (FlowLayout) usernamePanel.getLayout();
        passwordLayout.setAlignment(FlowLayout.CENTER);

        btnPanel.setLayout(new FlowLayout());
        FlowLayout btnPanelLayout = (FlowLayout) btnPanel.getLayout();
        btnPanelLayout.setAlignment(FlowLayout.CENTER);
        //��Ԫ��
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);
        usernameTextField.setColumns(18);
        passwordPanel.add(passWLabel);
        passwordPanel.add(passwoTextField);
        passwoTextField.setColumns(18);
        btnPanel.add(loginBtn);
        btnPanel.add(closeBtn);
        c.add(usernamePanel);
        c.add(passwordPanel);
        c.add(btnPanel);
        };

    public static void main(String args[]){
        
    }
}