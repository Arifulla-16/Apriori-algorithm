package apriori;

import java.util.*;

public class setgen {
    public ArrayList<ArrayList<String>> setGenerator(ArrayList<ArrayList<String>> delItems, int k) {

        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

        int com = 0;

        for (int i = 0; i < delItems.size() - 1; i++) {
            for (int j = i + 1; j < delItems.size(); j++) {
                com = 0;
                for (int l = 0; l < delItems.get(j).size(); l++) {
                    if (delItems.get(i).contains(delItems.get(j).get(l))) {
                        com++;
                    }
                }
                // System.out.println(com);
                if (com == k - 2) {
                    ArrayList<String> temp = new ArrayList<String>();
                    for (int m = 0; m < delItems.get(i).size(); m++) {
                        if (!temp.contains(delItems.get(i).get(m))) {
                            temp.add(delItems.get(i).get(m));
                        }
                    }
                    for (int n = 0; n < delItems.get(j).size(); n++) {
                        if (!temp.contains(delItems.get(j).get(n))) {
                            temp.add(delItems.get(j).get(n));
                        }
                    }
                    int size = 0;
                    int s1 = 0;
                    for (int q = 0; q < list.size(); q++) {
                        size = 0;
                        for (int w = 0; w < temp.size(); w++) {
                            if (list.get(q).contains(temp.get(w))) {
                                size++;
                            }
                        }
                        if (size == temp.size()) {
                            s1 = 1;
                        }
                    }
                    if (!list.contains(temp) && s1 == 0) {
                        list.add(temp);
                    }
                }
            }
        }

        return list;
    }
}
