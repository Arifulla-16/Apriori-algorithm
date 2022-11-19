package apriori;

import java.util.*;

public class associator {
    public void confidenceAnalysis(ArrayList<String> la, ArrayList<String> lb, ArrayList<ArrayList<String>> itemsets,
            double confidence) {
        int nla = 0, nlb = 0, nlab = 0;

        int validator = 0;
        int abc = 0;
        for (int i = 0; i < itemsets.size(); i++) {
            validator = 0;
            abc = 0;
            if (itemsets.get(i).contains(la.get(0))) {
                nla++;
                abc++;
            }
            for (int j = 0; j < lb.size(); j++) {
                if (itemsets.get(i).contains(lb.get(j))) {
                    validator++;
                }
            }
            if (validator == lb.size()) {
                nlb++;
                abc++;
            }
            if (abc == 2) {
                nlab++;
            }
        }

        if ((double) nlab / nla >= confidence) {
            System.out.print("RULE: ");
            System.out.println(la + "->" + lb);

            System.out.print("CONFIDENCE: ");
            System.out.println((double) nlab / nla);
            System.out.println("==================================================");

        }

        if ((double) nlab / nlb >= confidence) {
            System.out.print("RULE: ");
            System.out.println(lb + "->" + la);

            System.out.print("CONFIDENCE: ");
            System.out.println((double) nlab / nlb);
            System.out.println("==================================================");

        }

    }

    public void associationGen(ArrayList<ArrayList<String>> list, ArrayList<ArrayList<String>> itemsets,
            double confidence) {
        ArrayList<String> la = new ArrayList<String>();
        ArrayList<String> lb = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                if (list.get(0).size() == 2 && j == 1) {
                    break;
                }
                for (int k = 0; k < list.get(i).size(); k++) {
                    if (k == j) {
                        la.add(list.get(i).get(k));
                    } else {
                        lb.add(list.get(i).get(k));
                    }
                }
                confidenceAnalysis(la, lb, itemsets, confidence);
                la.clear();
                lb.clear();
            }
        }
    }
}