package jobs;


import util.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MatrixAdditionJob extends Job {
    private Integer row;
    private Integer column;
    private AtomicInteger count;
    private Integer totalCount;
    private Float result;
    private AtomicBoolean interrupt;

    private Long sleepTime;

 //   private float[][] matrix;

    /**
     *
     * @param fileName
     * @param row
     * @param column
     * @param count
     * @param matrix
     */
    public MatrixAdditionJob(double[] A) {
    	super("X");
    }
        /*for(int i=0;i<row;++i) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, column);
        }*/
    /*    result = 0f;
        this.count = new AtomicInteger(count);
        totalCount = this.count.get();
        interrupt = new AtomicBoolean(false);
        sleepTime = 0L;
    */
   
    

    public MatrixAdditionJob(boolean isRequest) {
        super(isRequest);
    }

    @Override
    public void run() {
        while(count.get()>0 && !interrupt.get()) {
            count.decrementAndGet();
            for(int i=0;i<row;++i) {
                for(int j=0;j<column;++j)
                    result += matrix[i][j];
            }
        }
        Integer finish = totalCount - count.get();
        System.out.println("Progress: " + getID() + ", " + (((100*finish) / totalCount)) + "%" );
        Util.sleep(sleepTime);
    }

    @Override
    public boolean isFinished() {
        synchronized (this) {
            return count.get() == 0;
        }
    }

    @Override
    public void suspend(Long sleepTime) {
        this.sleepTime = sleepTime;
        interrupt.set(true);
    }

    @Override
    public void resume() {
        interrupt.set(false);
    }

    @Override
    public String toString() {
        return jobName;
    }

    @Override
    public JobResult getResult() {
        return new MatrixAdditionResult(result);
    }

  /*  public static List<Job> splitJobs(String fileName) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileInputStream(fileName));
        Integer row,column,count;
        row = in.nextInt();
        column = in.nextInt();
        count = in.nextInt();
        float[][] matrix = new float[row][column];
        readMatrix(in, row, column,  matrix);
        List<Job> list = new LinkedList<Job>();

        for(int i=0;i<row;++i) 
        {
            float[][] smallMatrix = new float[1][column];
            smallMatrix[0] = Arrays.copyOf(matrix[i], column);
//            System.out.println("job " + i + ", " + Arrays.deepToString(smallMatrix));
            MatrixAdditionJob maj = new MatrixAdditionJob(fileName, 1, column, count, smallMatrix);
            list.add(maj);
        }
        in.close();
        return list;
    }
*/
    public static List<Job> splitJobs()
    {	double[] A= new double[1024*1024*16];
    	Arrays.fill(A,1.111111);
    	double[] smallArray = new double[1024*32];
    	int j=0;
    	List<Job> list = new LinkedList<Job>();
        for(int i=0;i< A.length;i++)
        {	smallArray[j]=A[i];
            if(i%(1024*32)==0)
            {	
            	MatrixAdditionJob maj = new MatrixAdditionJob(smallArray);
            	list.add(maj);
            	j=0;
            }      
        }
        return list;
    }
/*   private static void readMatrix(Scanner in, int row, int column, float[][] matrix) {
        for(int i=0;i<row;++i)
            for(int j=0;j<column;++j)
                matrix[i][j] = in.nextFloat();
    }
  */  
    public static void main(String[] args) throws FileNotFoundException {
        MatrixAdditionJob.splitJobs();
    }

}
