package com.xiets.swing;

import javax.swing.*;
import java.awt.*;

public class flowlayout {

    public static void main(String[] args) {
        JFrame jf = new JFrame("��ʽ���ִ���");
        jf.setSize(300, 250);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        // ����������壬ָ��ʹ�� ��ʽ����
        JPanel panel = new JPanel(new FlowLayout());

        JButton btn01 = new JButton("��ť01");
        JButton btn02 = new JButton("��ť02");
        JButton btn03 = new JButton("��ť03");
        JButton btn04 = new JButton("��ť04");
        JButton btn05 = new JButton("��ť05");

        panel.add(btn01);
        panel.add(btn02);
        panel.add(btn03);
        panel.add(btn04);
        panel.add(btn05);

        jf.setContentPane(panel);
        jf.setVisible(true);        // PS: ���������Ϊ����ʾ(����), ������ӵ�����Ż���ʾ
    }

}
