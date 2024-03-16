package com.example.mydfs_back.virtualize.PrometheusExporter;

import com.example.mydfs_back.utils.JSONUtils;
import com.example.mydfs_back.virtualize.MXBeanUtils;
import com.example.mydfs_back.virtualize.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PullController {
    @Autowired
    private MXBeanUtils mxBeanUtils;

    @GetMapping("/getScheduledMessage")
    public String getScheduledMessage() {
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
        return JSONUtils.toJson(metrics);
    }
}