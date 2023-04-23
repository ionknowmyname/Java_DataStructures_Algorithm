package com.faithfulolaleru.GreedyAlgorithms;

import com.faithfulolaleru.base.KnapsackItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FractionalKnapsackProblem {


    public static void main(String[] args) {
        List<KnapsackItem> items = new ArrayList<>();
        int[] value = { 100, 120, 60 };
        int weight[] = { 20, 30, 10 };
        int capacity = 50;

        for (int i = 0; i < value.length; i++) {
            items.add(new KnapsackItem(i + 1, value[i], weight[i]));
        }

        knapSack(items, capacity);
    }



    static void knapSack(List<KnapsackItem> items, int capacity) {

        // sort using comparator for ratio
        Comparator<KnapsackItem> comparator = new Comparator<KnapsackItem>() {
            @Override
            public int compare(KnapsackItem o1, KnapsackItem o2) {
                if(o2.getRatio() > o1.getRatio()) return 1;
                else return -1;
            }

            // generally compare method returns -1 if o1 < o2 & 1 if o1 > o2
            // & this is for sorting in ascending order, see how this comparator
            // is opposite coz we sorting in descending order
        };

        Collections.sort(items, comparator);

        int usedCapacity = 0;
        double totalValue = 0;

        // if full consumption possible then do, if not, consume fractionally
        for(KnapsackItem item : items) {
            if(usedCapacity + item.getWeight() <= capacity) {  // full consumption possible
                usedCapacity += item.getWeight();
                System.out.println("Taken: " + item);
                totalValue += item.getValue();
            } else {
                int availableWeight = capacity - usedCapacity;
                double canAddValue = item.getRatio() * availableWeight;
                System.out.println("Taken: item index = " + item.getIndex() + ", obtained value = "
                        + canAddValue + ", available weight = " + availableWeight + ", ratio = " + item.getRatio());

                usedCapacity += availableWeight;
                totalValue += canAddValue;
            }

            if(usedCapacity == capacity) break;  // means its full, no need to continue adding items
        }

        System.out.println("\nTotal value obtained: " + totalValue);
    }
}
