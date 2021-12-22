package com.xiets.swing;

import javax.swing.*;

public class Grouplayout {

    public static void main(String[] args) {
        JFrame jf = new JFrame("���Դ���");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // ���������������
        JPanel panel = new JPanel();
        // �������鲼�֣�����������
        GroupLayout layout = new GroupLayout(panel);
        // ���������Ĳ���
        panel.setLayout(layout);

        // �������
        JButton btn01 = new JButton("Button01");
        JButton btn02 = new JButton("Button02");
        JButton btn03 = new JButton("Button03");
        JButton btn04 = new JButton("Button04");
        JButton btn05 = new JButton("Button05");

        // �Զ��������֮��ļ�϶
        layout.setAutoCreateGaps(true);
        // �Զ����������봥�������߿�����֮��ļ�϶
        layout.setAutoCreateContainerGaps(true);

        /*
         * ˮƽ�飨��ȷ�� X �᷽�������/���з�ʽ��
         *
         * ˮƽ����: ˮƽ���У��������У�
         * ˮƽ����: ��ֱ���У��������У�
         */
        // ˮƽ���У����£� btn01 �� btn02
        GroupLayout.ParallelGroup hParalGroup01 = layout.createParallelGroup().addComponent(btn01).addComponent(btn02);

        // ˮƽ���У����£�btn03 �� btn04
        GroupLayout.ParallelGroup hParalGroup02 = layout.createParallelGroup().addComponent(btn03).addComponent(btn04);

        // ˮƽ���У����ң�hParalGroup01 �� hParalGroup02
        GroupLayout.SequentialGroup hSeqGroup = layout.createSequentialGroup().addGroup(hParalGroup01).addGroup(hParalGroup02);

        // ˮƽ���У����£�hSeqGroup �� btn05
        GroupLayout.ParallelGroup hParalGroup = layout.createParallelGroup().addGroup(hSeqGroup).addComponent(btn05, GroupLayout.Alignment.CENTER);

        layout.setHorizontalGroup(hParalGroup);  // ָ�����ֵ� ˮƽ�飨ˮƽ���꣩

        /*
         * ��ֱ�飨��ȷ�� Y �᷽�������/���з�ʽ��
         *
         * ��ֱ����: ��ֱ���У��������У�
         * ��ֱ����: ˮƽ���У��������У�
         */
        // ��ֱ���У����ң�btn01 �� btn03
        GroupLayout.ParallelGroup vParalGroup01 = layout.createParallelGroup().addComponent(btn01).addComponent(btn03);

        // ��ֱ���У����ң�btn02 �� btn04
        GroupLayout.ParallelGroup vParalGroup02 = layout.createParallelGroup().addComponent(btn02).addComponent(btn04);

        // ��ֱ���У����£�vParalGroup01, vParalGroup02 �� btn05
        GroupLayout.SequentialGroup vSeqGroup = layout.createSequentialGroup().addGroup(vParalGroup01).addGroup(vParalGroup02).addComponent(btn05);

        layout.setVerticalGroup(vSeqGroup);    // ָ�����ֵ� ��ֱ�飨��ֱ���꣩

        jf.setContentPane(panel);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

}

