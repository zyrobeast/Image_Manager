package IMAGE_MANAGER;

public class LOG {
    public static String n = "**************************************************LOG*****************************************************\n";
    public static java.awt.TextArea t = new java.awt.TextArea(n + "Welcome to IMAGE_MANAGEMENT_SET by AS Softwares And Application\nProgrammed By: Abhisek Singh");

    private LOG() {
    }

    public static void append(String h) {
        t.setEditable(true);
        t.setText(t.getText() + "\n" + h);
        t.setEditable(false);
    }

    public static void reset() {
        t.setEditable(true);
        t.setText(n);
        t.setEditable(false);
    }
}
