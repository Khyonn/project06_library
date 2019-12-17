package fr.nmocs.library.loanperemptionwarn;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

import fr.nmocs.library.loanperemptionwarn.jobs.items.LoanReader;
import fr.nmocs.library.loanperemptionwarn.jobs.items.LoanToMessageProcesor;
import fr.nmocs.library.loanperemptionwarn.jobs.items.MessageWriter;
import fr.nmocs.library.loanperemptionwarn.ws.LoanDTO;
import fr.nmocs.library.loanperemptionwarn.ws.LoanService;
import fr.nmocs.library.loanperemptionwarn.ws.TokenService;

@Configuration
@EnableBatchProcessing
public class SpringWarnJobConfig extends DefaultBatchConfigurer {

	@Override
	public void setDataSource(DataSource dataSource) {
	}

	// ===== Services
	@Autowired
	private LoanService loanService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	// ========== Configuration des Jobs

	@Bean
	public LoanReader reader() {
		return new LoanReader(loanService, tokenService);
	}

	@Bean
	public LoanToMessageProcesor processor() {
		return new LoanToMessageProcesor();
	}

	@Bean
	public MessageWriter writer() {
		return new MessageWriter();
	}

	// ===== JOB

	@Bean
	public Job sendEmailToBorrowers(EmailWarnJobCompletionListener completionListener, Step loanPeremptionWarn) {
		return jobBuilderFactory.get("sendEmailToBorrowers").incrementer(new RunIdIncrementer())
				.listener(completionListener).flow(loanPeremptionWarn).end().build();
	}

	// ===== STEP

	@Bean
	public Step loanPeremptionWarn() {
		return stepBuilderFactory.get("loanPeremptionWarn").<LoanDTO, SimpleMailMessage>chunk(5).reader(reader())
				.processor(processor()).writer(writer()).build();
	}

	// =====

}
