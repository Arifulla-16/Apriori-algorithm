package apriori;

import java.util.*;
import java.io.*;

public class fr {
    String file;

    fr(String q) {
        file = q;
    }

    public ArrayList<ArrayList<String>> listGenerator() {

        BufferedReader read = null;

        String line;

        ArrayList<ArrayList<String>> itemsets = new ArrayList<ArrayList<String>>();

        try {
            read = new BufferedReader(new FileReader(file));
            line = read.readLine();
            while ((line = read.readLine()) != null) {

                String trxns = line.substring(4, line.length() - 1);
                String[] ele = trxns.split(",");
                ArrayList<String> row = new ArrayList<String>();
                for (String tk : ele) {
                    row.add(tk);
                    // System.out.print(tk + " ");
                }
                itemsets.add(row);
                // System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return itemsets;
    }
}