//Test case 1: Perfect!
//Test case 2: 5/10, kinda close? 

public class ShortestJobFirstScheduler extends Scheduler
{
    public ShortestJobFirstScheduler(Processor theCPU)
    {
        super(theCPU);
    }

    public String algName() { return "Shortest Job First Scheduler"; }

    // This function does the scheduling work!
    public void tick()
    {
        // You can call job.timeLeft() to see how much time is left for this job

    	//NOTE: for some reason job 9 has blocks at 10 and 1, instead of 18 and 9 which is what is listed when the job is added.
    	// I do not understand why this is different, as I assumed blocks were handled in the framework code, as I don't touch them.
    	
    	
        //*************************************************************************
    	//**************************** BASIC SETUP START **************************
        //*************************************************************************
    	
    	Job jobChecker;
    	Job job;
    	
    	// Moves UNBLOCKED jobs into READYQUEUE
        for (job = cpu.unblockedQueue.first(); job != null; job = cpu.unblockedQueue.first())
        {
//        	System.out.println(job.timeLeft());
            job.enqueueEnd(cpu.readyQueue);
        }
        
        // Moves any NEW jobs into READYQUEUE
        for (job = cpu.newJobQueue.first(); job != null; job = cpu.newJobQueue.first())
        {
        	System.out.println(job.timeLeft());
            job.enqueueEnd(cpu.readyQueue);
        }
        
        // Check CURRENT job, if DONE move to DONEQUEUE, if BLOCKED move to BLOCKEDQUEUE
        job = cpu.currentJob.first();
        if (job != null)
        {
            if (job.done())
            {
                job.enqueueEnd(cpu.doneQueue);
            }
            else if (job.blocked())
            {
//            	System.out.println("WE'VE BEEN BLOCKEDDDD : );
                job.enqueueEnd(cpu.blockedQueue);
            }
        }

        
        


        //*************************************************************************
        //**************************** BASIC SETUP END **************************** 
        //*************************************************************************
        
    	//Make the job with the SHORTEST TIME the current job.
        job = cpu.currentJob.first();
        if(job == null) {
	    	for(jobChecker = cpu.readyQueue.first(); jobChecker != null; jobChecker = cpu.readyQueue.first()) {
	    		if(cpu.currentJob.first() == null) {
	    			job = cpu.readyQueue.first();
	    			job.enqueueStart(cpu.currentJob);
	    		}
	    		
	    		job = cpu.currentJob.first();
	    		jobChecker = cpu.readyQueue.first();
	    		if(job == jobChecker) {
	    			break;
	    		}
	    		
	    		if(jobChecker != null && job != null) {
	//    			System.out.println("job time: " + job.timeLeft() + "    -----   jobChecker time: " + jobChecker.timeLeft());
		    		if(job.timeLeft() > jobChecker.timeLeft()) {
		    			job.enqueueEnd(cpu.unblockedQueue);
		    			jobChecker.enqueueStart(cpu.currentJob);
		    		}
		    		else {
		    			jobChecker.enqueueEnd(cpu.unblockedQueue);
		    		}
	    		}
	    	}
        }
  	
    	job = cpu.currentJob.first();
//    	if(job != null)
//    		System.out.println("Current job id: " + job.id() + ", remaining time: " + job.timeLeft() + ", time: " + cpu.time);
//    	job.enqueueStart(cpu.currentJob);
    }

    public void testCase1(Processor cpu)
    {
    	System.out.println("In test case one");
        cpu.addJob(1, 1, 50, "10,20,30,40", 1, 126);
        cpu.addJob(2, 1, 30, "7,15,22", 1, 101);
        cpu.addJob(3, 1, 20, "6,14", 1, 35);
        cpu.addJob(4, 1, 20, "6,14", 1, 41);
    }

    public void testCase2(Processor cpu)
    {
        cpu.addJob(1, 0, 30, "9,18,27", 4, 76);
        cpu.addJob(2, 1, 20, "3,6,9,12,15", 5, 81);
        cpu.addJob(3, 2, 30, "5,10,15,20,25", 2, 136);
        cpu.addJob(4, 21, 21, "20", 4, 65);
        cpu.addJob(5, 42, 29, "28", 3, 217);
        cpu.addJob(6, 71, 18, "5,10,15", 3, 127);
        cpu.addJob(7, 89, 13, "4,8,12", 4, 119);
        cpu.addJob(8, 102, 19, "4,8,12,16", 2, 175);
        cpu.addJob(9, 121, 19, "9,18", 1, 182);
        cpu.addJob(10, 140, 26, "6,12,18,24", 1, 234);
    }
}
