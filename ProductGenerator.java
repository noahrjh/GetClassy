import java.io.BufferedOutputStream;
import java.io .*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.ArrayList;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerator
{
    public static void main(String[] args)
    {
        ArrayList <Product> recs = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        Product p;
        boolean done = false;

        String name;
        String description;
        String ID;
        Double cost;

        do
        {
            name = SafeInput.getNonZeroLenString(in, "Enter the Product Name");
            description = SafeInput.getNonZeroLenString(in, "Enter the Product Description");
            ID = SafeInput.getRegExString(in, "Enter the Product ID", "00000\\d");
            cost = SafeInput.getDouble(in, "Enter the Product Cost");

            p = new Product(name, description, ID, cost);
            recs.add(p);

            done = SafeInput.getYNConfirm(in, "Are you done? ");
        } while(!done);


        File workingDirectory = new File(SafeInput.getNonZeroLenString(in, "Please enter your files name"));
        Path file = Paths.get(workingDirectory.getPath() + ".txt");

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));


            String s;
            for(Product rec : recs)
            {
                s = rec.toCSVDataRecord();
                System.out.println("Record " + s);
                writer.write(s, 0, s.length());
                writer.newLine();

            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
