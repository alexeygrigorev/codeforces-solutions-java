package coursera.algo2.week1;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import notsandbox.Problem;

import com.google.common.collect.Lists;

/**
 * In this programming problem and the next you'll code up the greedy algorithms from lecture for minimizing the
 * weighted sum of completion times.. Download the text file here. This file describes a set of jobs with positive and
 * integral weights and lengths. It has the format<br>
 * 
 * <pre>
 * [number_of_jobs]
 * [job_1_weight] [job_1_length]
 * [job_2_weight] [job_2_length]
 * ...
 * </pre>
 * 
 * For example, the third line of the file is "74 59", indicating that the second job has weight 74 and length 59. You
 * should NOT assume that edge weights or lengths are distinct.<br>
 * <br>
 * 
 * First task: Your task in this problem is to run the greedy algorithm that schedules jobs in decreasing order of the
 * difference (weight - length). Recall from lecture that this algorithm is not always optimal. IMPORTANT: if two jobs
 * have equal difference (weight - length), you should schedule the job with higher weight first. Beware: if you break
 * ties in a different way, you are likely to get the wrong answer. You should report the sum of weighted completion
 * times of the resulting schedule --- a positive integer --- in the box below.<br>
 * <br>
 * 
 * ADVICE: If you get the wrong answer, try out some small test cases to debug your algorithm (and post your test cases
 * to the discussion forum)!<br>
 * <br>
 * 
 * Second task: For this problem, use the same data set as in the previous problem. Your task now is to run the greedy
 * algorithm that schedules jobs (optimally) in decreasing order of the ratio (weight/length). In this algorithm, it
 * does not matter how you break ties. You should report the sum of weighted completion times of the resulting schedule
 * - a positive integer - in the box below.<br>
 * <br>
 * 
 * @author Grigorev Alexey
 * 
 */
public class JobSchedulingProblem extends Problem {

    @Override
    public void run() {
        List<Job> data = readData();

        long problem1 = problem1(Lists.newArrayList(data));
        out.print(problem1);
        out.print(' ');

        long problem2 = problem2(Lists.newArrayList(data));
        out.println(problem2);

        out.flush();
    }

    public long problem1(List<Job> data) {
        problem1Sort(data);
        return sumOfWeightedCompletionTimes(data);
    }

    public List<Job> problem1Sort(List<Job> data) {
        Collections.sort(data, new DifferenceJobComparator());
        return data;
    }

    public long problem2(List<Job> data) {
        problem2Sort(data);
        return sumOfWeightedCompletionTimes(data);
    }

    public List<Job> problem2Sort(List<Job> data) {
        Collections.sort(data, new RatioJobComparator());
        return data;
    }

    public long sumOfWeightedCompletionTimes(List<Job> data) {
        long completionTime = 0;
        long score = 0;
        for (Job job : data) {
            completionTime = completionTime + job.length;
            score = score + job.weight * completionTime;
        }
        return score;
    }

    public List<Job> readData() {
        int n = scanner.nextInt();
        List<Job> jobs = Lists.newArrayListWithCapacity(n);
        while (n > 0) {
            int weight = scanner.nextInt();
            int length = scanner.nextInt();
            jobs.add(new Job(weight, length));
            n--;
        }
        return jobs;
    }

    public static class Job {
        private final int weight;
        private final int length;

        public Job(int weight, int length) {
            this.weight = weight;
            this.length = length;
        }

        public int difference() {
            return weight - length;
        }

        public double ratio() {
            return 1.0 * weight / length;
        }

        @Override
        public String toString() {
            return "Job [weight=" + weight + ", length=" + length + ", diff=" + difference() + ", ratio=" + ratio() + "]";
        }

        @Override
        public boolean equals(Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }
    }

    public static class DifferenceJobComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            int diff1 = o1.difference();
            int diff2 = o2.difference();
            if (diff1 == diff2) {
                return -Utils.compareInt(o1.weight, o2.weight);
            }

            return -Utils.compareInt(diff1, diff2);
        }
    }

    public static class RatioJobComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            double ratio1 = o1.ratio();
            double ratio2 = o2.ratio();
            return -Double.compare(ratio1, ratio2);
        }
    }

 

}
