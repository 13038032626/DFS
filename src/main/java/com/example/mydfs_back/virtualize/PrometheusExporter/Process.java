package com.example.mydfs_back.virtualize.PrometheusExporter;

import com.example.mydfs_back.utils.JSONUtils;
import com.example.mydfs_back.virtualize.MXBeanUtils;
import com.example.mydfs_back.virtualize.Metrics;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Process {
    //通过push到消息队列的方式被监控

    @Value("${startPrometheusExporter:true}") // 默认为true
    Boolean startPrometheusExporter;

    @Autowired
    MXBeanUtils mxBeanUtils;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.mxbean.queue}")
    String mxbeanQueue;

    @Scheduled(fixedRateString = "${mxbean.sendInterval:60000}") // 默认每60秒执行一次
    public void sendMXBeanMessage() {
        if (startPrometheusExporter) {
            // 获取消息
            Metrics metrics = new Metrics.Builder()
                    .heapMemoryUsed(mxBeanUtils.getHeapMemoryUsage())
                    .heapMemoryMax(mxBeanUtils.getHeapMemoryUsage())
                    .nonHeapMemoryUsed(mxBeanUtils.getNonHeapMemoryUsage())
                    .nonHeapMemoryMax(mxBeanUtils.getNonHeapMemoryUsage())
                    .availableProcessors(mxBeanUtils.getAvailableProcessors())
                    .systemLoadAverage(mxBeanUtils.getSystemLoadAverage())
                    .threadCount(mxBeanUtils.getThreadCount())
                    .peakThreadCount(mxBeanUtils.getPeakThreadCount())
                    .loadedClassCount(mxBeanUtils.getLoadedClassCount())
                    .totalLoadedClassCount(mxBeanUtils.getTotalLoadedClassCount())
                    .unloadedClassCount(mxBeanUtils.getUnloadedClassCount())
                    .garbageCollectorMXBeans(mxBeanUtils.getGarbageCollectorMXBeans())

                    .build();

            // 使用 metrics 对象做进一步操作，例如转换为 JSON 字符串返回
            String info = JSONUtils.toJson(metrics);
            rabbitTemplate.convertAndSend(mxbeanQueue, info);
        }
    }
}
