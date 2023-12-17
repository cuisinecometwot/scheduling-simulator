import java.util.List;
public class Main{
    public static void main(String[] args){
        System.out.println("----------------FCFS----------------------");
        fcfs();
        System.out.println("----------------SJF-----------------------");
        sjf();
        System.out.println("----------------SRTF----------------------");
        srtf();
        System.out.println("----------------PNPS----------------------");
        pnps();
        System.out.println("----------------PPS-----------------------");
        pps();
        System.out.println("----------------RR------------------------");
        rr();
        // System.out.println("----------------CFS-----------------------");
        // cfs();
    }
    // Init Test Cases
    public static void fcfs(){
        CPUScheduler fcfs = new FirstComeFirstServe();
        fcfs.add(new Row("P1", 0, 5));
        fcfs.add(new Row("P2", 2, 4));
        fcfs.add(new Row("P3", 4, 3));
        fcfs.add(new Row("P4", 6, 6));
        fcfs.process();
        display(fcfs);
    }
    
    public static void sjf(){
        CPUScheduler sjf = new ShortestJobFirst();
        sjf.add(new Row("P1", 0, 5));
        sjf.add(new Row("P2", 2, 3));
        sjf.add(new Row("P3", 4, 2));
        sjf.add(new Row("P4", 6, 4));
        sjf.add(new Row("P5", 7, 1));
        sjf.process();
        display(sjf);
    }
    
    public static void srtf(){
        CPUScheduler srtf = new ShortestRemainingTimeFirst();
        srtf.add(new Row("P1", 8, 1));
        srtf.add(new Row("P2", 5, 1));
        srtf.add(new Row("P3", 2, 7));
        srtf.add(new Row("P4", 4, 3));
        srtf.add(new Row("P5", 2, 8));
        srtf.add(new Row("P6", 4, 2));
        srtf.add(new Row("P7", 3, 5));
        srtf.process();
        display(srtf);
    }
    
    public static void pnps(){
        CPUScheduler pnps = new PriorityNonPreemptive();
        pnps.add(new Row("P1", 8, 1));
        pnps.add(new Row("P2", 5, 1));
        pnps.add(new Row("P3", 2, 7));
        pnps.add(new Row("P4", 4, 3));
        pnps.add(new Row("P5", 2, 8));
        pnps.add(new Row("P6", 4, 2));
        pnps.add(new Row("P7", 3, 5));
        pnps.process();
        display(pnps);
    }
    
    public static void pps(){
        CPUScheduler pps = new PriorityPreemptive();
        pps.add(new Row("P1", 8, 1));
        pps.add(new Row("P2", 5, 1));
        pps.add(new Row("P3", 2, 7));
        pps.add(new Row("P4", 4, 3));
        pps.add(new Row("P5", 2, 8));
        pps.add(new Row("P6", 4, 2));
        pps.add(new Row("P7", 3, 5));
        pps.process();
        display(pps);
    }
    
    public static void rr(){
        CPUScheduler rr = new RoundRobin();
        rr.setTimeQuantum(2);
        rr.add(new Row("P1", 0, 4));
        rr.add(new Row("P2", 1, 5));
        rr.add(new Row("P3", 2, 6));
        rr.add(new Row("P4", 4, 1));
        rr.add(new Row("P5", 6, 3));
        rr.add(new Row("P6", 7, 2));
        rr.process();
        display(rr);
    }
    
    public static void display(CPUScheduler object){
        System.out.println("Process\tArrived\tBurst\tWaiting\tTurnaround");
        for (Row row : object.getRows()){
            System.out.println(row.getProcessName() + "\t" + row.getArrivalTime() + "\t" + row.getBurstTime() + "\t" + row.getWaitingTime() + "\t" + row.getTurnaroundTime());
        }
        System.out.println();
        for (int i = 0; i < object.getTimeline().size(); i++){
            List<Event> timeline = object.getTimeline();
            System.out.print(timeline.get(i).getStartTime() + "(" + timeline.get(i).getProcessName() + ")");
            if (i == object.getTimeline().size() - 1){
                System.out.print(timeline.get(i).getFinishTime());
            }
        }
        System.out.println("\n\nAverage WT: " + object.getAverageWaitingTime() + "\nAverage TAT: " + object.getAverageTurnAroundTime());
    }
}
