package com.xxl.job.core.biz;

import com.xxl.job.core.biz.model.*;

/**
 * Created by xuxueli on 17/3/1.
 */
public interface ExecutorBiz {

    /**
     * beat 心跳
     * @return
     */
    public ReturnT<String> beat();

    /**
     * idle beat 空闲心跳
     *
     * @param idleBeatParam
     * @return
     */
    public ReturnT<String> idleBeat(IdleBeatParam idleBeatParam);

    /**
     * run  运行
     * @param triggerParam
     * @return
     */
    public ReturnT<String> run(TriggerParam triggerParam);

    /**
     * kill 杀进程
     * @param killParam
     * @return
     */
    public ReturnT<String> kill(KillParam killParam);

    /**
     * log 获取日志
     * @param logParam
     * @return
     */
    public ReturnT<LogResult> log(LogParam logParam);

}
