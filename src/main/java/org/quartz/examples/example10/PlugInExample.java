/* 
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 * 
 */
 
package org.quartz.examples.example10;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.SchedulerRepository;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example will spawn a large number of jobs to run
 * 
 * @author James House, Bill Kratzer
 */
public class PlugInExample {


	//配置文件配置的
	public final static String QUARTZ_INSTANCE_NAME="QuartzScheduler";
	
	private static Scheduler scheduler = null;
	
	public static Scheduler getScheduler() throws SchedulerException{
		if(scheduler == null){
//			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler = SchedulerRepository.getInstance().lookup(QUARTZ_INSTANCE_NAME);
			if(scheduler == null)
				scheduler = StdSchedulerFactory.getDefaultScheduler();
		}
		return scheduler;
	}
	
	

  public static void main(String[] args) throws Exception {

	  Scheduler scheduler = PlugInExample.getScheduler();
	  //JobKey arg0 = new JobKey("job1","group1");
	//scheduler.deleteJob(arg0);
	scheduler.start();
	scheduler.shutdown(true);
//    while(true){
//    	Thread.currentThread().sleep(5000);
//    }
  }

}
