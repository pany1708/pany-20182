package com.kingthy.conf;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.kingthy.job.MaterialSynchronizeJob;

/**
 * MaterialSynchronizeConfig
 *
 * @author yuanml
 * @version 1.0.0
 * @date 2017/12/4
 */
@Configuration
public class MaterialSynchronizeConfig
{
    @Resource
    private ZookeeperRegistryCenter regCenter;
    
    @Bean
    public SimpleJob materialSynchronizeJob()
    {
        return new MaterialSynchronizeJob();
    }
    
    @Bean(initMethod = "init")
    public JobScheduler materialSynchronizeJobScheduler(final SimpleJob materialSynchronizeJob,
        @Value("${materialSynchronizeJob.cron}")
        final String cron, @Value("${materialSynchronizeJob.shardingTotalCount}")
        final int shardingTotalCount, @Value("${materialSynchronizeJob.shardingItemParameters}")
        final String shardingItemParameters)
    {
        return new SpringJobScheduler(materialSynchronizeJob, regCenter, getLiteJobConfiguration(
            materialSynchronizeJob.getClass(), cron, shardingTotalCount, shardingItemParameters));
    }
    
    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass, final String cron,
        final int shardingTotalCount, final String shardingItemParameters)
    {
        return LiteJobConfiguration
            .newBuilder(
                new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount)
                    .shardingItemParameters(shardingItemParameters)
                    .build(), jobClass.getCanonicalName()))
            .overwrite(true)
            .build();
    }
}
