import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shuoshuo on 2017/10/16.
 */
public class JobSequenceSort {
    public static void main(String[] args) {
        Job[] jobs = { new Job('a', 2, 100), new Job('b', 1, 19), new Job('c', 2, 27),
                new Job('d', 1, 25), new Job('e', 3, 15)};
        maxValue(jobs);
    }

    private static void maxValue(Job[] jobs) {
        //sort job by values desc
        Arrays.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o2.value - o1.value;
            }
        });

        Job[] sortedJobs = new Job[jobs.length];
        Set<Integer> dates = new HashSet<>();

        int len = 0;

        //if deadline is not used, then put the job there
        for (Job job : jobs) {
            if (dates.add(job.deadLine)) {
                if (len == 0 || sortedJobs[len - 1].deadLine < job.deadLine) {
                    sortedJobs[len++] = job;
                } else {
                    int i = len - 1;
                    for (; i >= 0; i--) {
                        if (sortedJobs[i].deadLine > job.deadLine) {
                            sortedJobs[i + 1] = sortedJobs[i];
                        } else {
                            break;
                        }
                    }

                    sortedJobs[i + 1] = job;
                    len++;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            System.out.print(sortedJobs[i].id + "        ");
        }
    }

}

class Job {
    char id;
    int deadLine, value;

    Job(char id, int deadLine, int value) {
        this.id = id;
        this.deadLine = deadLine;
        this.value = value;
    }
}
