package com.xiets.swing;

import javax.swing.*;
import java.awt.*;

public class Gridlayout {

    public static void main(String[] args) {
        JFrame jf = new JFrame("���񻯴���");
        jf.setSize(200, 250);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        // ���� 3 �� 3 �� �����񲼾�,ˮƽ �� ��ֱ ��϶��Ϊ10������ͼ
        GridLayout layout = new GridLayout(2, 4,10,10);
        
        // ���� ˮƽ �� ��ֱ ��϶
         //layout.setHgap(10);
         //layout.setVgap(10);
        
        JPanel panel = new JPanel(layout);

        JButton btn01 = new JButton("��ť01");
        JButton btn02 = new JButton("��ť02");
        JButton btn03 = new JButton("��ť03");
        JButton btn04 = new JButton("��ť04");
        JButton btn05 = new JButton("��ť05");
        JButton btn06 = new JButton("��ť06");
        JButton btn07 = new JButton("��ť07");
        JButton btn08 = new JButton("��ť08");

        panel.add(btn01);
        panel.add(btn02);
        panel.add(btn03);
        panel.add(btn04);
        panel.add(btn05);
        panel.add(btn06);
        panel.add(btn07);
        panel.add(btn08);

        jf.setContentPane(panel);
        jf.setVisible(true);
    }

}
