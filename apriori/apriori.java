package apriori;

import java.util.*;

public class apriori {
    public static void algo(String fn, int support, double confidence) {
        setgen gen = new setgen();
        fr reader = new fr(fn);
        associator ass = new associator();
        ArrayList<ArrayList<String>> itemsets = reader.listGenerator();

        // int support = 2;
        // double confidence = 0.6;

        ArrayList<ArrayList<String>> delItems = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> prevItems = new ArrayList<ArrayList<String>>();

        HashMap<String, Integer> map1 = new HashMap<String, Integer>();

        for (int i = 0; i < itemsets.size(); i++) {
            for (int j = 0; j < itemsets.get(i).size(); j++) {
                map1.put(itemsets.get(i).get(j), map1.getOrDefault(itemsets.get(i).get(j), 0) + 1);
            }
        }

        System.out.println("c1");
        System.out.println(map1);
        System.out.println();
        System.out.println();

        for (Map.Entry<String, Integer> e : map1.entrySet()) {
            ArrayList<String> temp = new ArrayList<String>();
            if (e.getValue() >= support) {
                temp.add(e.getKey());
            }
            if (temp.size() != 0) {
                delItems.add(temp);
            }
        }
        System.out.println("l1");
        System.out.println(delItems);
        System.out.println();
        System.out.println();
        prevItems = delItems;

        int set = 2;
        while (true) {

            ArrayList<ArrayList<String>> modsets = gen.setGenerator(delItems, set);
            if (modsets.size() == 0) {
                break;
            }

            set++;

            if (modsets.size() == 1) {
                break;
            }

            HashMap<ArrayList<String>, Integer> mapn = new HashMap<ArrayList<String>, Integer>();

            int validator = 0;

            for (int i = 0; i < modsets.size(); i++) {
                for (int j = 0; j < itemsets.size(); j++) {
                    validator = 0;
                    for (int k = 0; k < modsets.get(i).size(); k++) {
                        if (itemsets.get(j).contains(modsets.get(i).get(k))) {
                            validator++;
                        }
                    }
                    if (validator == modsets.get(i).size()) {
                        mapn.put(modsets.get(i), mapn.getOrDefault(modsets.get(i), 0) + 1);
                    }
                }
            }
            System.out.println("c" + (set - 1));
            System.out.println(mapn);
            System.out.println();
            System.out.println();

            prevItems.clear();
            prevItems = delItems;
            delItems = new ArrayList<ArrayList<String>>();
            for (Map.Entry<ArrayList<String>, Integer> e : mapn.entrySet()) {
                if (e.getValue() >= support) {
                    delItems.add(e.getKey());
                }
            }
            System.out.println("l" + (set - 1));
            System.out.println(delItems);
            System.out.println();
            System.out.println();
        }

        if (delItems.size() == 0) {
            // System.out.println(prevItems);
            ass.associationGen(prevItems, itemsets, confidence);
        } else {
            // System.out.println(delItems);
            ass.associationGen(delItems, itemsets, confidence);
        }
    }

    public static void main(String[] args) {
        algo("s1.csv", 2, 0.6);
    }
}