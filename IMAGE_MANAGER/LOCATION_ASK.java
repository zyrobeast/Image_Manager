package IMAGE_MANAGER;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

class LOCATION_ASK {
    private LOCATION_ASK() {
    }

    public static File[] browseFile(String[] filetype, String des) {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Choose your files.");
        jfc.setMultiSelectionEnabled(true);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setFileHidingEnabled(true);
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.addChoosableFileFilter(new FileFilter() {
            public boolean accept(File f) {
                if (f.isDirectory())
                    return true;
                for (int i = 0; i < filetype.length; i++)
                    if (f.getName().toLowerCase().endsWith(filetype[i]))
                        return true;
                return false;
            }

            public String getDescription() {
                return des;
            }
        });
        jfc.setFileView(new javax.swing.filechooser.FileView() {
            public javax.swing.Icon getIcon(java.io.File f) {
                return javax.swing.filechooser.FileSystemView.getFileSystemView().getSystemIcon(f);
            }
        });
        int r = 0;
        ;
        r = jfc.showOpenDialog(null);
        File[] loc = null;
        if (r == JFileChooser.APPROVE_OPTION)
            loc = jfc.getSelectedFiles();
        return loc;
    }

    public static String browseFolder() {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Please choose a folder");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setFileHidingEnabled(true);
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setFileView(new javax.swing.filechooser.FileView() {
            public javax.swing.Icon getIcon(java.io.File f) {
                return javax.swing.filechooser.FileSystemView.getFileSystemView().getSystemIcon(f);
            }
        });
        int r = 0;
        ;
        r = jfc.showSaveDialog(null);
        String loc = "";
        if (r == JFileChooser.APPROVE_OPTION)
            loc = jfc.getSelectedFile().getAbsolutePath();
        return loc;
    }
}