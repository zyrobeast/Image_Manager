package IMAGE_MANAGER;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class IMAGE_CONVERTER {
    private IMAGE_CONVERTER() {
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
            JButton png, jpg;
            frame = new JFrame("Enter the format to convert");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setResizable(false);
            frame.setSize(500, 300);
            frame.setLocation(200, 200);
            frame.setLayout(null);
            jpg = new JButton("JPEG");
            png = new JButton("PNG");
            frame.add(jpg);
            frame.add(png);
            jpg.setBounds(100, 100, 100, 50);
            png.setBounds(300, 100, 100, 50);
            jpg.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    save(f, image2, "JPEG");
                    frame.dispose();
                }
            });
            png.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    save(f, image2, "PNG");
                    frame.dispose();
                }
            });
            frame.setVisible(true);
        } else
            LOG.append("No files selected");
    }

    public static void save(File[] f, BufferedImage[] image2, String type) {
        if (type.equals("JPEG")) {
            image2 = removeTransparency(image2);
        }
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
            if (permission) {
                for (int k = 0; k < image2.length; k++) {
                    try {
                        String name = f[k].getName();
                        name = name.substring(0, name.lastIndexOf("."));
                        ImageIO.write(image2[k], type, new File(save_loc + "\\" + name + "." + type));
                    } catch (Exception e) {
                    }
                }
                LOG.append("Saved files Successfully.");
            }
        } else
            LOG.append("Destination Folder not selected.");
    }

    public static BufferedImage[] removeTransparency(BufferedImage[] image) {
        BufferedImage[] image2 = new BufferedImage[image.length];
        for (int k = 0; k < image2.length; k++) {
            int height = image[k].getHeight();
            int width = image[k].getWidth();
            image2[k] = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            image2[k].createGraphics().drawImage(image[k], 0, 0, java.awt.Color.white, null);
        }
        return image2;
    }
}