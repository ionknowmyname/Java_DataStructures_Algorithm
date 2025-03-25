package com.faithfulolaleru.fairmoney;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Testing {

    static List<Info> db = new ArrayList<>();

    public static void main(String[] args) {

        Info info1 = new Info("111", 100.00, "2024-11-12 17:00:07");
        Info info2 = new Info("222", 200.00, "2024-11-12 17:00:07");
        Info info3 = new Info("222", 300.00, "2024-11-12 17:00:07");
        Info info4 = new Info("222", 400.00, "2024-11-12 17:00:07");
        Info info5 = new Info("222", 500.00, "2024-11-12 17:00:07");
        Info info6 = new Info("222", 700.00, "2024-11-12 17:00:07");
        Info info7 = new Info("222", 800.00, "2024-11-12 17:00:07");
        Info info9 = new Info("222", 900.00, "2024-11-12 17:00:07");
        Info info10 = new Info("222", 1000.00, "2024-11-12 17:00:07");
        Info info11 = new Info("222", 1100.00, "2024-11-12 17:00:07");
        Info info12 = new Info("222", 1200.00, "2024-11-12 17:00:07");
        Info info13 = new Info("222", 1300.00, "2024-11-12 17:00:07");
        populateDB(info1);
        populateDB(info2);
        populateDB(info3);
        populateDB(info4);
        populateDB(info5);
        populateDB(info6);
        populateDB(info7);
        populateDB(info9);
        populateDB(info10);
        populateDB(info11);
        populateDB(info12);
        populateDB(info13);

        // List<Info> toRun = List.of(info1, info2);
        arrange(db);

        System.out.println(db.get(0).getBalance());
        System.out.println(db.size());

    }


    public static void populateDB(Info data) {
//        Info info1 = new Info("111", 300.00, "2024-11-12 17:00:07");
//        Info info2 = new Info("222", 300.00, "2024-11-12 17:00:07");
//        Info info3 = new Info("333", 300.00, "2024-11-12 17:00:07");
        db.add(data);

    }

    public static List<Info> arrange(List<Info> toCompare) {

        // List<Info> toReturn = new ArrayList<>();
        Comparator<Info> c1  = new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                if(o2.getBalance() > o1.getBalance()) return 1;
                else return -1;
            }
        };

        Collections.sort(toCompare, c1);

        return toCompare.subList(0, 9);
    }


    public static class Info {
        String user_id;
        Double balance;

        String timestamp;


        public Info(String userId, Double balance, String timestamp) {
            this.user_id = userId;
            this.balance = balance;
            this.timestamp  = timestamp;
        }

        public String getUser_id() {
            return user_id;
        }

        public Double getBalance() {
            return balance;
        }

        public String getTimestamp() {
            return timestamp;
        }
    }



}
