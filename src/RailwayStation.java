import java.util.*;

/**
 * Created by shuoshuo on 2017/10/16.
 */
public class RailwayStation {
    public static void main(String[] args) {
        String arr[]  = {"9:00",  "9:40", "9:50",  "11:00", "15:00", "18:00"};
        String depart[]  = {"9:10", "12:00", "11:20", "11:30", "19:00", "20:00"};
        System.out.println(getStation(arr, depart));
    }

    private static int getStation(String[] arr, String[] depart) {
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            intervals.add(new Interval(arr[i], depart[i]));
        }

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (compareTo(o1.arr, o2.arr) == 0) {
                    return compareTo(o1.depart, o2.depart);
                }
                return compareTo(o1.arr, o2.arr);
            }
        });

        int stations = 0, maxStations = 0, i = 0, j = 0;

        while (i < intervals.size() && j < intervals.size()) {
            if (compareTo(intervals.get(i).arr, intervals.get(j).depart) < 0) {
                stations++;
                maxStations = Math.max(maxStations, stations);
                i++;
            } else {
                stations--;
                j++;
            }
        }

        return maxStations;
    }

    private static int getStations(String[] arr, String[] depart) {
        int cnt = 0;
        List<Interval> intervals = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (intervals.size() == 0) {
                intervals.add(new Interval(arr[i], depart[i]));
                cnt++;
            } else {
                boolean isAdd = false;
                for (Interval interval : intervals) {
                    if (compareTo(interval.arr, depart[i]) > 0  || compareTo(interval.depart, arr[i]) < 0) {
                        interval.arr = arr[i];
                        interval.depart = depart[i];
                        isAdd = true;
                        break;
                    }
                }

                if (!isAdd) {
                    intervals.add(new Interval(arr[i], depart[i]));
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static int compareTo(String s1, String s2) {
        String[] arr1 = s1.split(":");
        String[] arr2 = s2.split(":");

        for (int i = 0 ; i < arr1.length; i++) {
            int val1 = Integer.parseInt(arr1[i]), val2 = Integer.parseInt(arr2[i]);
            if (val1 < val2) {
                return -1;
            } else if(val1 > val2) {
                return 1;
            }
        }

        return 0;
    }
}

class Interval {
    String arr, depart;
    Interval(String arr, String depart) {
        this.arr = arr;
        this.depart = depart;
    }
}
