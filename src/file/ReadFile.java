package file;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadFile <T> {

    public ArrayList<T> inputStream(String path) {
        ArrayList<T> list = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(path);
            if (fis.available() > 0) {
                ObjectInputStream ois = new ObjectInputStream(fis);
                list = (ArrayList<T>) ois.readObject();
                fis.close();
                ois.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
