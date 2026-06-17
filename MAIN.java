//Author = Abhisek_Singh
//Project = Image_Management_Set

import IMAGE_MANAGER.*;

import javax.swing.*;

class MAIN {
    private MAIN() {
    }

    public static void main(String[] args) {
        LOG.t.setEditable(false);

        JFrame f = new JFrame("IMAGE_MANAGEMENT_SET");
        f.setSize(1350, 300);
        f.setLocation(0, 0);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.getContentPane().setBackground(new java.awt.Color(0, 162, 232));
        f.setLayout(null);

        JButton vr, hr, tm, de, co, ic, re;
        vr = new JButton("Vertical Inverter");
        hr = new JButton("Horizontal Inverter");
        tm = new JButton("Make_Transparent");
        de = new JButton("Decode_Image");
        co = new JButton("Encrypt_Image");
        ic = new JButton("Format_Converter");
        re = new JButton("Resizer");

        f.add(vr);
        f.add(hr);
        f.add(tm);
        f.add(de);
        f.add(co);
        f.add(ic);
        f.add(re);
        f.add(LOG.t);

        vr.setBounds(10, 50, 140, 50);
        hr.setBounds(200, 50, 140, 50);
        tm.setBounds(390, 50, 140, 50);
        de.setBounds(580, 50, 140, 50);
        co.setBounds(770, 50, 140, 50);
        ic.setBounds(960, 50, 140, 50);
        re.setBounds(1150, 50, 140, 50);

        vr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                LOG.reset();
                V_INVERTER.start();
            }
        });

        hr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                LOG.reset();
                H_INVERTER.start();
            }
        });

        tm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                LOG.reset();
                TRANSPARENT_MAKER.start();
            }
        });

        de.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                LOG.reset();
                DECODER.start();
            }
        });

        co.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                LOG.reset();
                CODER.start();
            }
        });

        ic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                LOG.reset();
                IMAGE_CONVERTER.start();
            }
        });

        re.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                LOG.reset();
                RESIZER.start();
            }
        });

        LOG.t.setBounds(20, 150, 1100, 100);
        f.setVisible(true);
    }
}