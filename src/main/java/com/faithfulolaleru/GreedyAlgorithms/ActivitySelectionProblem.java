package com.faithfulolaleru.GreedyAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ActivitySelectionProblem {

    /*
    *   QUESTION: Find the max number of activities an individual can attend given start & end times
    *   of different activities; he can only attend an activity at a time, and he cant join activities
    *   midway
    *
    *
    *   SOLUTION: Sort all activities by end time, pick first activity, and check if next activity start
    *   time is greater than current activity end time
    *
    *
    * */

    public static class Activity {
        private String name;
        private int startTime;
        private int finishTime;

        public Activity(String name, int startTime, int finishTime) {
            this.name = name;
            this.startTime = startTime;
            this.finishTime = finishTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(int finishTime) {
            this.finishTime = finishTime;
        }

        @Override
        public String toString() {
            return "Activity: " + name + ", startTime = " + startTime + " finishTime = " + finishTime;
        }
    }


    public static void main(String[] args) {
        List<Activity> activityList = new ArrayList<>();
        activityList.add(new Activity("A1", 0, 6));
        activityList.add(new Activity("A2", 3, 4));
        activityList.add(new Activity("A3", 1, 2));
        activityList.add(new Activity("A4", 5, 8));
        activityList.add(new Activity("A5", 5, 7));
        activityList.add(new Activity("A6", 8, 9));

        activitySelection(activityList);
    }


    static void activitySelection(List<Activity> activityList) {

        Comparator<Activity> finishTimeComparator = new Comparator<Activity>() {
            @Override
            public int compare(Activity o1, Activity o2) {
                return o1.getFinishTime() - o2.getFinishTime();
            }
        };

        // sort activityList using comparator
        Collections.sort(activityList, finishTimeComparator);

        Activity prevActivity = activityList.get(0);

        System.out.println("\n\nRecommended Schedule: \n" + activityList.get(0));

        for (int i = 1; i < activityList.size(); i++) {
            Activity currActivity = activityList.get(i);

            // current activity start time is after previous activity end time, add to recommended schedule
            if(currActivity.getStartTime() >= prevActivity.getFinishTime()) {
                System.out.println(currActivity);
                prevActivity = currActivity;  // move pointer forward, set previous activity to current for next loop
            }
        }
    }

}
