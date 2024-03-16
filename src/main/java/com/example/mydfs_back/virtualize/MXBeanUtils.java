package com.example.mydfs_back.virtualize;

import org.springframework.stereotype.Component;

import java.lang.management.*;
import java.util.Collections;
import java.util.List;

@Component
public class MXBeanUtils {

    private final MemoryMXBean memoryMXBean= ManagementFactory.getMemoryMXBean();
    private final OperatingSystemMXBean osMXBean= ManagementFactory.getOperatingSystemMXBean();
    private final ThreadMXBean threadMXBean= ManagementFactory.getThreadMXBean();
    private final ClassLoadingMXBean classLoadingMXBean= ManagementFactory.getClassLoadingMXBean();
    private final List<GarbageCollectorMXBean> garbageCollectorMXBean= ManagementFactory.getGarbageCollectorMXBeans();

    // 获取堆内存使用情况
    public Long getHeapMemoryUsage() {
        try {
            return memoryMXBean.getHeapMemoryUsage().getUsed();
        } catch (Exception e) {
            System.err.println("数据获取失败，请稍后重试");
            return -1L;
        }
    }

    // 获取非堆内存使用情况
    public Long getNonHeapMemoryUsage() {
        try {
            return memoryMXBean.getNonHeapMemoryUsage().getUsed();
        } catch (Exception e) {
            System.err.println("数据获取失败，请稍后重试");
            return -1L;
        }
    }

    // 获取系统 CPU 数量
    public int getAvailableProcessors() {
        try {
            return osMXBean.getAvailableProcessors();
        } catch (Exception e) {
            System.err.println("数据获取失败，请稍后重试");
            return 0;
        }
    }

    // 获取系统负载
    public double getSystemLoadAverage() {
        try {
            return osMXBean.getSystemLoadAverage();
        } catch (Exception e) {
            System.err.println("数据获取失败，请稍后重试");
            return 0.0;
        }
    }

    // 获取线程数
    public int getThreadCount() {
        try {
            return threadMXBean.getThreadCount();
        } catch (Exception e) {
            System.err.println("数据获取失败，请稍后重试");
            return 0;
        }
    }

    // 获取峰值线程数
    public int getPeakThreadCount() {
        try {
            return threadMXBean.getPeakThreadCount();
        } catch (Exception e) {
            System.err.println("数据获取失败，请稍后重试");
            return 0;
        }
    }

    // 获取已加载的类数
    public long getLoadedClassCount() {
        try {
            return classLoadingMXBean.getLoadedClassCount();
        } catch (Exception e) {
            System.err.println("数据获取失败，请稍后重试");
            return 0;
        }
    }

    // 获取总加载类数
    public long getTotalLoadedClassCount() {
        try {
            return classLoadingMXBean.getTotalLoadedClassCount();
        } catch (Exception e) {
            System.err.println("数据获取失败，请稍后重试");
            return 0;
        }
    }

    // 获取未加载的类数
    public long getUnloadedClassCount() {
        try {
            return classLoadingMXBean.getUnloadedClassCount();
        } catch (Exception e) {
            System.err.println("数据获取失败，请稍后重试");
            return 0;
        }
    }

    // 获取垃圾回收器信息
    public List<GarbageCollectorMXBean> getGarbageCollectorMXBeans() {
        try {
            return garbageCollectorMXBean;
        } catch (Exception e) {
            System.err.println("数据获取失败，请稍后重试");
            return Collections.emptyList();
        }
    }
}
