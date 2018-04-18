public class MultipleQueueScheduler extends Scheduler
{
    private int _quanta;

    public MultipleQueueScheduler(Processor theCPU)
    {
        super(theCPU);
    }

    public String algName() { return "Multiple Queue Scheduler"; }

    private void setQuanta(int priority)
    {
        _quanta = 1 << (5 - priority);
    }

    // This function does the scheduling work!
    public void tick()
    {
        // Note that I added _quanta as an integer.  When a job is placed into the CPU,
        // the quanta for the job should be set based on the priority of the job.  You
        // can call the 'setQuanta' function (just above!), passing the job's priority.
        // This will set _quanta's value.  Then, when you are checking to see if the
        // job is done or blocked, if it is neither of these, decrement _quanta.  If this
        // gets to zero, move the job from the CPU and lower its priority
        // (by calling job.decPriority().
        //
        // Finally, since we are changing the priority of the jobs as they get bumped,
        // don't use job.priority()!  Instead, use job.curPriority().

        // TBD
    }

    public void testCase1(Processor cpu)
    {
        cpu.addJob(1, 1, 50, "10,20,30,40", 1, 128);
        cpu.addJob(2, 1, 30, "7,15,22", 3, 103);
        cpu.addJob(3, 1, 20, "6,14", 2, 74);
        cpu.addJob(4, 1, 20, "6,14", 3, 85);
    }

    public void testCase2(Processor cpu)
    {
        cpu.addJob(1, 0, 30, "9,18,27", 4, 210);
        cpu.addJob(2, 1, 20, "3,6,9,12,15", 5, 104);
        cpu.addJob(3, 2, 30, "5,10,15,20,25", 2, 120);
        cpu.addJob(4, 21, 21, "20", 4, 179);
        cpu.addJob(5, 42, 29, "28", 3, 198);
        cpu.addJob(6, 71, 18, "5,10,15", 3, 135);
        cpu.addJob(7, 89, 13, "4,8,12", 4, 136);
        cpu.addJob(8, 102, 19, "4,8,12,16", 2, 158);
        cpu.addJob(9, 121, 19, "9,18", 1, 217);
        cpu.addJob(10, 140, 26, "6,12,18,24", 1, 234);
    }
}
