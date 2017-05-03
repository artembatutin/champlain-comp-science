package sem2.chap7;

import java.io.*;

public class WaterBills {

    public static void main(String[] arg) throws IOException {
        //reading file.
        BufferedReader in = new BufferedReader(new FileReader("./data/misc/water.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("./data/misc/water.out"));

        //handling.
        in.lines().forEachOrdered(l -> {
            try {
                String[] parts = l.split(" ");
                int num = Integer.parseInt(parts[0]);
                Code code = Code.getCode(parts[1]);
                int consumption;

                if (code != Code.ERROR) {
                    consumption = Integer.parseInt(parts[2]);
                    double cost = code.calc(consumption);
                    double discount = cost > 75 ? (cost / 100D) * 10 : cost > 20 ? (cost / 100D) * 5 : 0;
                    double GST = (cost / 100D) * 7D;
                    double PST = (cost / 100D) * 7.5D;

                    double fcost = cost - discount + GST + PST;

                    String builder =
                            "Number: " + num +
                            " Code: " + code.name() +
                            " Consumption: " + consumption +
                            " Cost: " + cost +
                            " Discount: " + discount +
                            " GST: " + GST +
                            " PST: " + PST +
                            " Final Cost: " + fcost;
                    out.write(builder);
                    out.newLine();
                } else {
                    out.write(num + " " + parts[1] + " ERROR INVALID CODE");
                    out.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        out.flush();
        out.close();
    }

    public enum Code {
        H {
            @Override
            double calc(int gals) {
                return 5 + gals * 0.0005D;
            }
        },
        C {
            @Override
            double calc(int gals) {
                if (gals < 4_000_000)
                    return 1000;
                else
                    return 1000 + (gals - 4_000_000) / 0.00025D;
            }
        },
        I {
            @Override
            double calc(int gals) {
                if (gals < 4_000_000)
                    return 1000;
                else if (gals < 10_000_000)
                    return 2000;
                return 3000;
            }
        },
        ERROR {
            @Override
            double calc(int gals) {
                return 0;
            }
        };

        abstract double calc(int gals);

        public static Code getCode(String s) {
            s = s.toLowerCase();
            for (Code c : Code.values()) {
                if (c.name().toLowerCase().equals(s)) {
                    return c;
                }
            }
            return ERROR;
        }
    }
}
