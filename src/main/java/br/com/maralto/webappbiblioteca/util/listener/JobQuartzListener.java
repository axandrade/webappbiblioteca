package br.com.maralto.webappbiblioteca.util.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import br.com.maralto.webappbiblioteca.util.job.IndexacaoAutoresLivrosJob;

public class JobQuartzListener  implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try 
		{
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			
			JobDetail autoresLivrosJob = JobBuilder.newJob(IndexacaoAutoresLivrosJob.class)
					  .withIdentity("IndexacaoAutoresLivrosJob", scheduler.DEFAULT_GROUP)
					  .build();
			
			
			Trigger autoresLivrosTrigger = TriggerBuilder.newTrigger()
					  .withIdentity("IndexacaoAutoresLivrosTrigger", scheduler.DEFAULT_GROUP)
					  .startNow()
					  .build();
			
						
			scheduler.scheduleJob(autoresLivrosJob, autoresLivrosTrigger);
			
			scheduler.start();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
