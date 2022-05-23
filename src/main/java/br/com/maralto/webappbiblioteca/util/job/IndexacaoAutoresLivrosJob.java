package br.com.maralto.webappbiblioteca.util.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import br.com.maralto.webappbiblioteca.config.HibernateConfig;

public class IndexacaoAutoresLivrosJob implements Job{
	
	@Autowired
	HibernateConfig config;
	

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		
		System.out.println(entityManagerFactory);
		
//		SearchSession searchSession = Search.session( entityManager );
//		MassIndexer indexer = searchSession.massIndexer( Livro.class );
//		indexer.startAndWait();
		
	}

}
