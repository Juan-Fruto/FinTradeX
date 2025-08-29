package com.trading.project.config;

import com.trading.project.model.Stock;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableScheduling
public class BatchConfig {

    // add new stock price to historical values

    @Bean
    public ItemReader<Stock> getNewStockPriceFromAPI() {
        return new ItemReader<Stock>() {
            @Override
            public Stock read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                return null;
            }
        };
    }

    @Bean
    public ItemWriter<Stock> addNewStockPriceToList() {
        return null;
    }

    @Bean
    public Step addNewStockPriceStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("addNewStockPriceStep", jobRepository)
                .<Stock, Stock>chunk(10, transactionManager)
                .reader(getNewStockPriceFromAPI())
                .writer(addNewStockPriceToList())
                .build();
    }

    @Bean
    public Job addNewStockPriceJob(JobRepository jobRepository, Step addNewStockPriceStep){
        return new JobBuilder("addNewStockPriceJob", jobRepository)
                .start(addNewStockPriceStep)
                .build();
    }

   // delete stock prices older than 30 days

    @Bean
    public Step getHistoricalPricesStep() {
        return null;
    }

    @Bean
    public Step deletePricesOlderThan30DaysStep() {
        return null;
    }

    @Bean
    public Step sendErrorReportStep(){ // tasklet
        return null;
    }

    @Bean Job deleteOldPricesJob(JobRepository jobRepository,
                                 Step getHistoricalPricesStep,
                                 Step deletePricesOlderThan30DaysStep,
                                 Step sendErrorReportStep){
        return new JobBuilder("deleteOldPricesJob", jobRepository)
                .start(getHistoricalPricesStep)
                .on("*").to(deletePricesOlderThan30DaysStep)
                .from(getHistoricalPricesStep)
                .on("FAILED").to(sendErrorReportStep)
                .end()
                .build();
    }
}
