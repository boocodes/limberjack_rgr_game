public class Demon extends Thread{
    private Runnable task;



    public void setTask(Runnable taskArg){
        this.task = taskArg;
    }

    public void run(){
        if(Thread.currentThread().isDaemon()){//checking for daemon thread
            task.run();
        }
        else{

        }
    }


}
