package org.quartz.examples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.examples.example13.SimpleJob;
import org.quartz.impl.SchedulerRepository;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class JobTools {

	public final static String DATE_PATTERN_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	//配置文件配置的
	public final static String QUARTZ_INSTANCE_NAME="QuartzScheduler";
	
	private static Scheduler scheduler = null;
	
	public static Scheduler getScheduler() throws SchedulerException{
		if(scheduler == null){
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler = SchedulerRepository.getInstance().lookup(QUARTZ_INSTANCE_NAME);
			if(scheduler == null)
				scheduler = StdSchedulerFactory.getDefaultScheduler();
		}
		return scheduler;
	}
	
	/**
	 * 将1435373580000类型时间转为yyyy-MM-dd HH:mm:ss格式
	 * @param times 1435373580000类型的时间
	 * @return
	 */
	public static String fmtLongTimeToString(String times){
		SimpleDateFormat sf = new SimpleDateFormat(DATE_PATTERN_FORMAT);
		Date date = new Date();
		date.setTime(Long.valueOf(times));
		return sf.format(date);
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss格式日期字符串转化为Date对象
	 * @param time yyyy-MM-dd HH:mm:ss格式
	 * @return
	 */
	public static Date paraseToDate(String time){
		SimpleDateFormat sf = new SimpleDateFormat(DATE_PATTERN_FORMAT);
		try {
			Date date = sf.parse(time);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args){
		try {
			Scheduler scheduler =JobTools.getScheduler();
			 //if(scheduler.isStarted())
				
				 
			     for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(Scheduler.DEFAULT_GROUP))) {
			 
				  String jobName = jobKey.getName();
				  String jobGroup = jobKey.getGroup();
				  
					//scheduler.triggerJob(jobKey);
					
					
				  //get job's trigger
				  List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
				  if(triggers.size()>0){
				  Date nextFireTime = triggers.get(0).getNextFireTime(); 
			 
					System.out.println("[jobName] : " + jobName + " [groupName] : "
						+ jobGroup + " - " + nextFireTime);
				  }else
						System.out.println("[jobName] : " + jobName + " [groupName] : "
								+ jobGroup );
			 
				  }
			 
			    
			//Job名称
/*			List<String> groupNames = scheduler.getJobGroupNames();
			for(String s : groupNames){
				System.out.println(s);
			}
			//触发器名称
			List<String> triggerNames = scheduler.getTriggerGroupNames();
			for(String s : triggerNames){
				System.out.println("triggerNames:"+s);
			}*/
			System.out.println(JobTools.fmtLongTimeToString("1463990540000"));
			

			  // job 1 will run every 20 seconds
 	     JobDetail job = newJob(SimpleJob.class).withIdentity("job4_newadd", Scheduler.DEFAULT_GROUP).build();

		    CronTrigger trigger = newTrigger().withIdentity("trigger4", Scheduler.DEFAULT_GROUP).withSchedule(cronSchedule("0/20 * * * * ?"))
		        .build();
		    
		    Date ft = scheduler.scheduleJob(job, trigger);
		    System.out.println(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
		             + trigger.getCronExpression()); 
		 
		    
		    
		   scheduler.start();
		   
		   try {
			      // sleep for 30 seconds
			      Thread.sleep(30L * 1000L);
			    } catch (Exception e) {
			      //
			    }
		   
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
