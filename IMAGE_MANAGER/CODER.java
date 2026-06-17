package IMAGE_MANAGER;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;

public class CODER {
    private CODER() {
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
                image2[k] = image;
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
                    LOG.append("Some error ocurred while saving Files.");
                }
                if (permission) {
                    for (int k = 0; k < f.length; k++) {
                        try {
                            String name = f[k].getName();
                            name = name.substring(0, name.lastIndexOf("."));
                            FileWriter fw = new FileWriter(save_loc + "\\" + name + ".ima");
                            int height = image2[k].getHeight();
                            int width = image2[k].getWidth();
                            fw.write("" + width + "\r\n");
                            fw.write("" + height + "\r\n");
                            for (int i = 0; i < height; i++) {
                                for (int j = 0; j < width; j++) {
                                    fw.write(image2[k].getRGB(j, i) + "\r\n");
                                }
                            }
                            fw.close();
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