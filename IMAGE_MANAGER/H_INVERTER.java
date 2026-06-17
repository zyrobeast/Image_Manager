package IMAGE_MANAGER;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class H_INVERTER {
    private H_INVERTER() {
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
                        int p = image.getRGB(width - 1 - j, i);
                        image2[k].setRGB(j, i, p);
                    }
                }
            }
            LOG.append("Completed Reading Images.");
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
                            String ex = name.substring(name.lastIndexOf("."));
                            name = name.substring(0, name.lastIndexOf("."));
                            ImageIO.write(image2[k], "PNG", new File(save_loc + "\\" + name + ".png"));
                        } catch (Exception e) {
                        }
                    }
                    LOG.append("Saved files Successfully.");
                }
            } else
                LOG.append("Destination Folder not selected.");
        } else
            LOG.append("No files selected");
    }
}