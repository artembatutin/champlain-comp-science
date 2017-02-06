package sem2.chap7;

import java.io.*;

/**
 * Removing 4 length words.
 */
public class Profanity {

    public static void main(String[] ag) throws IOException {

        //reading file.
        BufferedReader in = new BufferedReader(new FileReader("./data/misc/censor.in"));
        int lines = 0;
        String line;
        String[] data = new String[5];
        while ((line = in.readLine()) != null) {
            data[lines] = line;
            lines++;
        }

        //changing 4 digits to asterisks.
        for (int l = 0; l < data.length; l++) {
            //line
            StringBuilder builder = new StringBuilder();
            String[] words = data[l].split(" ");
            for (String w : words) {
                if (w.length() == 4)
                    builder.append("**** ");
                else
                    builder.append(w).append(" ");
            }
            data[l] = builder.toString();
        }

        //writing result.
        BufferedWriter out = new BufferedWriter(new FileWriter("./data/misc/censor.out"));
        for (String d : data) {
            out.write(d);
            out.newLine();
        }
        out.flush();
        out.close();
    }

}
