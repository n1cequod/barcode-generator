import javax.swing.*;
import java.io.File;

public class FileChooser {

    static File file;
    static int response;
    static JFileChooser chooser = new JFileChooser(".");

    public static String chooseDir() {
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        response = chooser.showOpenDialog(null);

        if(response == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            return file.getAbsolutePath();
        }
        return "";
    }


}
