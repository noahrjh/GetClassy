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

public class PersonGenerator1 {


    public static void main(String[] args) {

        ArrayList <Person> recs = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        Person p;
        boolean done = false;

        String ID;
        String firstName;
        String lastName;
        String title;
        int YOB;

        do
        {
            ID = SafeInput.getRegExString(in, "Enter your ID number ", "00000\\d");
            firstName = SafeInput.getNonZeroLenString(in, "Enter your First Name ");
            lastName = SafeInput.getNonZeroLenString(in, "Enter your Last Name ");
            title = SafeInput.getNonZeroLenString(in, "Enter your Title ");
            YOB = SafeInput.getRangedInt(in, "Enter your Year of Birth ", 1940, 2000);

            p = new Person(ID, firstName, lastName, title, YOB);
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


            String q;
            for(Person rec : recs)
            {
                q = rec.toCSVDataRecord();
                System.out.println("Record " + q);
                writer.write(q, 0, q.length());
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

