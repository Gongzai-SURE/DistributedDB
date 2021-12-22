package com.xiets.swing;

import javax.swing.*;

public class Spinglayout {

    public static void main(String[] args) {
        // ��������
        JFrame jf = new JFrame("���Դ���");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(300, 200);
        jf.setLocationRelativeTo(null);

        // ����������壬ʹ�� ���Բ���
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel(layout);
        jf.setContentPane(panel);

        // �������
        JLabel label = new JLabel("Test JLabel: ");
        JButton btn = new JButton("Btn");
        JTextField textField = new JTextField("Text Field");

        // ���������������
        panel.add(label);
        panel.add(btn);
        panel.add(textField);

        /*
         * �����Լ�����ã����Բ������õĹؼ���
         */

        // ��ǩ���Լ��: ���ñ�ǩ�����Ͻ�����Ϊ (5, 5)
        SpringLayout.Constraints labelCons = layout.getConstraints(label);  // �Ӳ����л�ȡָ�������Լ���������û�У����Զ�������
        labelCons.setX(Spring.constant(5));
        labelCons.setY(Spring.constant(5));

        // ��ť���Լ��: �������Ͻ� ˮƽ����Ϊ5, ��ֱ����Ϊ ��ǩ���ϱ����ꣻ���ö�������Ϊ ��ǩ�Ķ�������
        SpringLayout.Constraints btnCons = layout.getConstraints(btn);
        btnCons.setX(Spring.constant(5));
        btnCons.setY(labelCons.getConstraint(SpringLayout.SOUTH));
        btnCons.setConstraint(SpringLayout.EAST, labelCons.getConstraint(SpringLayout.EAST));

        // �ı���Լ��: �������Ͻ� ˮƽ����Ϊ ��ǩ�Ķ������� + 5, ��ֱ����Ϊ 5
        SpringLayout.Constraints textFieldCons = layout.getConstraints(textField);
        textFieldCons.setX(
                Spring.sum(
                        labelCons.getConstraint(SpringLayout.EAST),
                        Spring.constant(5)
                )
        );
        textFieldCons.setY(Spring.constant(5));

        /*
         * ������壨��������Լ�����ã���ȷ�� ��� �� �������ұߺ͵ױ� ֮��ļ�϶��С
         */
        SpringLayout.Constraints panelCons = layout.getConstraints(panel);  // ��ȡ������Լ������

        // ���������� �������� Ϊ �ı���Ķ������� + 5
        panelCons.setConstraint(
                SpringLayout.EAST,
                Spring.sum(
                        textFieldCons.getConstraint(SpringLayout.EAST),
                        Spring.constant(5)
                )
        );

        // ����� ��ť �� �ı��� �� �ϱ����� �� ֵ�ϴ���
        Spring maxHeightSpring = Spring.max(
                btnCons.getConstraint(SpringLayout.SOUTH),
                textFieldCons.getConstraint(SpringLayout.SOUTH)
        );

        // ���������� �ϱ����� Ϊ maxHeightSpring + 5
        panelCons.setConstraint(
                SpringLayout.SOUTH,
                Spring.sum(
                        maxHeightSpring,
                        Spring.constant(5)
                )
        );

        // ��ʾ����
        jf.setVisible(true);
    }

}
