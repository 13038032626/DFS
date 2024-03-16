package com.example.mydfs_back.virtualize;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Metrics {
    // JVM参数
    private final Long heapMemoryUsed;
    private final Long heapMemoryMax;
    private final Long nonHeapMemoryUsed;
    private final Long nonHeapMemoryMax;

    // 宏观硬件参数
    private final int availableProcessors;
    private final double systemLoadAverage;
    private final int threadCount;
    private final int peakThreadCount;
    private final long loadedClassCount;
    private final long totalLoadedClassCount;
    private final long unloadedClassCount;
    private final List<GarbageCollectorMXBean> garbageCollectorMXBeans;

    // 业务相关的统计量 / Counters
    private final Map<String, Integer> counters;

    private Metrics(Builder builder) {
        this.heapMemoryUsed = builder.heapMemoryUsed;
        this.heapMemoryMax = builder.heapMemoryMax;
        this.nonHeapMemoryUsed = builder.nonHeapMemoryUsed;
        this.nonHeapMemoryMax = builder.nonHeapMemoryMax;
        this.availableProcessors = builder.availableProcessors;
        this.systemLoadAverage = builder.systemLoadAverage;
        this.threadCount = builder.threadCount;
        this.peakThreadCount = builder.peakThreadCount;
        this.loadedClassCount = builder.loadedClassCount;
        this.totalLoadedClassCount = builder.totalLoadedClassCount;
        this.unloadedClassCount = builder.unloadedClassCount;
        this.garbageCollectorMXBeans = builder.garbageCollectorMXBeans;
        this.counters = Builder.counters;
    }

    public Long getHeapMemoryUsed() {
        return heapMemoryUsed;
    }

    public Long getHeapMemoryMax() {
        return heapMemoryMax;
    }

    public Long getNonHeapMemoryUsed() {
        return nonHeapMemoryUsed;
    }

    public Long getNonHeapMemoryMax() {
        return nonHeapMemoryMax;
    }

    public int getAvailableProcessors() {
        return availableProcessors;
    }

    public double getSystemLoadAverage() {
        return systemLoadAverage;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public int getPeakThreadCount() {
        return peakThreadCount;
    }

    public long getLoadedClassCount() {
        return loadedClassCount;
    }

    public long getTotalLoadedClassCount() {
        return totalLoadedClassCount;
    }

    public long getUnloadedClassCount() {
        return unloadedClassCount;
    }

    public List<GarbageCollectorMXBean> getGarbageCollectorMXBeans() {
        return garbageCollectorMXBeans;
    }

    public int getCount(String action) {
        return counters.getOrDefault(action, 0);
    }

    // Builder 类
    public static class Builder {
        // JVM参数
        private Long heapMemoryUsed;
        private Long heapMemoryMax;
        private Long nonHeapMemoryUsed;
        private Long nonHeapMemoryMax;

        // 宏观硬件参数
        private int availableProcessors;
        private double systemLoadAverage;
        private int threadCount;
        private int peakThreadCount;
        private long loadedClassCount;
        private long totalLoadedClassCount;
        private long unloadedClassCount;
        private List<GarbageCollectorMXBean> garbageCollectorMXBeans;

        // 业务相关的统计量 - 全局可见 / Counters
        private static Map<String, Integer> counters = new HashMap<>();

        public Builder() {
        }

        public Builder heapMemoryUsed(Long heapMemoryUsed) {
            this.heapMemoryUsed = heapMemoryUsed;
            return this;
        }

        public Builder heapMemoryMax(Long heapMemoryMax) {
            this.heapMemoryMax = heapMemoryMax;
            return this;
        }

        public Builder nonHeapMemoryUsed(Long nonHeapMemoryUsed) {
            this.nonHeapMemoryUsed = nonHeapMemoryUsed;
            return this;
        }

        public Builder nonHeapMemoryMax(Long nonHeapMemoryMax) {
            this.nonHeapMemoryMax = nonHeapMemoryMax;
            return this;
        }

        public Builder availableProcessors(int availableProcessors) {
            this.availableProcessors = availableProcessors;
            return this;
        }

        public Builder systemLoadAverage(double systemLoadAverage) {
            this.systemLoadAverage = systemLoadAverage;
            return this;
        }

        public Builder threadCount(int threadCount) {
            this.threadCount = threadCount;
            return this;
        }

        public Builder peakThreadCount(int peakThreadCount) {
            this.peakThreadCount = peakThreadCount;
            return this;
        }

        public Builder loadedClassCount(long loadedClassCount) {
            this.loadedClassCount = loadedClassCount;
            return this;
        }

        public Builder totalLoadedClassCount(long totalLoadedClassCount) {
            this.totalLoadedClassCount = totalLoadedClassCount;
            return this;
        }

        public Builder unloadedClassCount(long unloadedClassCount) {
            this.unloadedClassCount = unloadedClassCount;
            return this;
        }

        public Builder garbageCollectorMXBeans(List<GarbageCollectorMXBean> garbageCollectorMXBeans) {
            this.garbageCollectorMXBeans = garbageCollectorMXBeans;
            return this;
        }

        public Builder countAction(String action) {
            counters.put(action, counters.getOrDefault(action, 0) + 1);
            return this;
        }

        public Metrics build() {
            return new Metrics(this);
        }
    }
}

