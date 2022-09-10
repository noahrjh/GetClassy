import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String[] rec;
        Product p;
        int i = 0;
        ArrayList<Product> products = new ArrayList<>();

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));


                int line = 0;
                while (reader.ready()) {
                    rec = reader.readLine().split(", ");
                    p = new Product(rec[0], rec[1], rec[2], Double.parseDouble(rec[3]));
                    products.add(p);
                    line++;

                    System.out.printf("\nLine %4d %-60s ", line, products.get(i));
                    i++;
                }
                reader.close();
                System.out.println("\n\nData file read!");

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
