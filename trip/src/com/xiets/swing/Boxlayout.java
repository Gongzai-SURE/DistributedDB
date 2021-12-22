package com.xiets.swing;

import javax.swing.*;

public class Boxlayout {

    public static void main(String[] args) {
        JFrame jf = new JFrame("���ʹ���");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton btn01 = new JButton("Button01");
        JButton btn02 = new JButton("Button02");
        JButton btn03 = new JButton("Button03");
        JButton btn04 = new JButton("Button04");
        JButton btn05 = new JButton("Button05");

        // ������һ��ˮƽ������
        Box hBox01 = Box.createHorizontalBox();
        hBox01.add(btn01);
        hBox01.add(btn02);
        hBox01.add(btn03);

        // �����ڶ�ˮƽ������
        Box hBox02 = Box.createHorizontalBox();
        hBox02.add(btn04);
        hBox02.add(Box.createHorizontalGlue()); // ���һ��ˮƽ����״�Ĳ��ɼ����������ʣ��ˮƽ�ռ�
        hBox02.add(btn05);

        // ����һ����ֱ��������������������ˮƽ�䣨Box���Ƕ�ף�
        Box vBox = Box.createVerticalBox();
        vBox.add(hBox01);
        vBox.add(hBox02);

        // �Ѵ�ֱ��������Ϊ����������õ�����
        jf.setContentPane(vBox);

        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

}

