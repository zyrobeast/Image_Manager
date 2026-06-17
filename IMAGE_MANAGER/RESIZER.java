package IMAGE_MANAGER;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class RESIZER {
    private RESIZER() {
    }

    public static void start() {
        String[] types = {".png", ".jpeg", ".bmp", ".jpg"};
        File[] f = LOCATION_ASK.browseFile(types, "\".png\",\".jpeg\",\".bmp\",\".jpg\"");
        BufferedImage image = null;
        if (f != null) {
            BufferedImage[] image2 = new BufferedImage[f.length];
            LOG.append("Reading Images.");
            for (int k = 0; k < f.length; k++) {
                try {
                    image = ImageIO.read(f[k]);
                } catch (Exception e) {
                }
                int height = image.getHeight();
                int width = image.getWidth();
                image2[k] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        int p = image.getRGB(j, i);
                        image2[k].setRGB(j, i, p);
                    }
                }
            }
            LOG.append("Completed Reading Images.");
            JFrame frame;
            JLabel x, y;
            JButton b;
            JTextField ex, ey;
            frame = new JFrame("Enter the dimensions.");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setResizable(false);
            frame.setSize(500, 250);
            frame.setLocation(200, 200);
            frame.setLayout(null);
            b = new JButton("SAVE");
            x = new JLabel("Enter width.");
            y = new JLabel("Enter height.");
            ex = new JTextField();
            ey = new JTextField();
            frame.add(x);
            frame.add(ex);
            frame.add(y);
            frame.add(ey);
            frame.add(b);
            x.setBounds(20, 50, 150, 20);
            ex.setBounds(200, 50, 50, 20);
            y.setBounds(20, 100, 150, 20);
            ey.setBounds(200, 100, 50, 20);
            b.setBounds(300, 150, 100, 20);
            b.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    save(f, image2, ex.getText().trim(), ey.getText().trim());
                    frame.dispose();
                }
            });
            frame.setVisible(true);
        } else
            LOG.append("No files selected");
    }

    public static void save(File[] f, BufferedImage[] image2, String x, String y) {
        String save_loc = "";
        if (f.length > 0)
            save_loc = LOCATION_ASK.browseFolder();
        if (save_loc.length() > 0) {
            long ran = (long) (Math.random() * 100000000);
            boolean permission = true;
            try {
                new File(save_loc + "\\" + ran + ".test").createNewFile();
                new File(save_loc + "\\" + ran + ".test").delete();
            } catch (Exception e) {
                permission = false;
                LOG.append("Some error ocurred while saving Images.");
            }
            block:
            {
                if (permission) {
                    for (int k = 0; k < f.length; k++) {
                        try {
                            String name = f[k].getName();
                            name = name.substring(0, name.lastIndexOf("."));
                            BufferedImage imag = ImageIO.read(f[k]);
                            int width = imag.getWidth();
                            int height = imag.getHeight();
                            BufferedImage imagei = new BufferedImage(Integer.parseInt(x), Integer.parseInt(y), BufferedImage.TYPE_INT_ARGB);
                            java.awt.Graphics g = imagei.createGraphics();
                            g.drawImage(imag, 0, 0, Integer.parseInt(x), Integer.parseInt(y), null);
                            ImageIO.write(imagei, "PNG", new File(save_loc + "\\" + name + ".png"));
                        } catch (Exception e) {
                            LOG.append("Some error ocurred while saving Images.");
                            break block;
                        }
                        LOG.append("Saved files Successfully.");
                    }
                }
            }
        } else
            LOG.append("Destination Folder not selected.");
    }
}
