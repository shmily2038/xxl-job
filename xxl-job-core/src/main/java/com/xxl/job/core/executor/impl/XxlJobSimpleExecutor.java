package com.xxl.job.core.executor.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.handler.annotation.XxlJob;


/**
 * xxl-job executor (for frameless)
 *
 * @author xuxueli 2020-11-05
 */
public class XxlJobSimpleExecutor extends XxlJobExecutor {
//    private static final Logger logger = LoggerFactory.getLogger(XxlJobSimpleExecutor.class);


    private List<Object> xxlJobBeanList = new ArrayList<>();
    public List<Object> getXxlJobBeanList() {
        return xxlJobBeanList;
    }
    public void setXxlJobBeanList(List<Object> xxlJobBeanList) {
        this.xxlJobBeanList = xxlJobBeanList;
    }


    @Override
    public void start() {

        // init JobHandler Repository (for method)
        initJobHandlerMethodRepository(xxlJobBeanList);

        // super start
        try {
            super.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }


    private void initJobHandlerMethodRepository(List<Object> xxlJobBeanList) {
        if (xxlJobBeanList==null || xxlJobBeanList.isEmpty()) {
            return;
        }

        // init job handler from method
        for (Object bean: xxlJobBeanList) {
            // method
            Method[] methods = bean.getClass().getDeclaredMethods();
            if (methods.length == 0) {
                continue;
            }
            for (Method executeMethod : methods) {
                XxlJob xxlJob = executeMethod.getAnnotation(XxlJob.class);
                // registry
                registJobHandler(xxlJob, bean, executeMethod);
            }

        }

    }

}
