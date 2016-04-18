package state;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;

public class HardwareMonitor extends Thread
{
	private double cpuUtilization;
	public boolean isRunning;
	
	public HardwareMonitor(){
		isRunning = true;
		start();
	}
	
	public synchronized double getCpuUtilization(){
		return cpuUtilization;
	}
	
	private synchronized void setCpuUtilization(double value){
		cpuUtilization = value;
	}
	/*
	 * ref: http://stackoverflow.com/questions/5907519/measure-cpu-usage-of-the-jvm-java-code
	 */
	
	public void run(){
		while(isRunning){
			setCpuUtilization(getUpdatedCpuUtilization());
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static double getUpdatedCpuUtilization()
	{
		
		OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
	    int availableProcessors = operatingSystemMXBean.getAvailableProcessors();
	    long prevUpTime = runtimeMXBean.getUptime();
	   // long prevProcessCpuTime = operatingSystemMXBean.getProcessCpuTime();
	    double cpuUsage;
	    
	    try 
	    {
	        Thread.sleep(500);
	    } 
	    catch (Exception ignored) { }

	    operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	    long upTime = runtimeMXBean.getUptime();
	    //long processCpuTime = operatingSystemMXBean.getProcessCpuTime();
	    //long elapsedCpu = processCpuTime - prevProcessCpuTime;
	    long elapsedTime = upTime - prevUpTime;

	   // cpuUsage = Math.min(99F, elapsedCpu / (elapsedTime * 10000F * availableProcessors));
	    cpuUsage = operatingSystemMXBean.getSystemLoadAverage();
	    
	    return cpuUsage;
	}
}
