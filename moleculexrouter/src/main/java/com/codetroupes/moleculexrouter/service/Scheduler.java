package com.codetroupes.moleculexrouter.service;

import android.os.Handler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Scheduler for post runnable to ui thread or worker thread
 * @author MondyXue <a href="mailto:mondyxue@gmail.com">E-Mail</a>
 */
public interface Scheduler{

    String PATH = "/xrouter/service/scheduler";

    void setUiHandler(Handler handler);
    void setExecutor(ThreadPoolExecutor excutor);

    void runOnUiThread(Runnable r);
    void runOnUiThreadDelayed(Runnable r, long millis);
    void removeUiRunnable(Runnable r);

    void runOnWorkerThread(Runnable r);
    void removeWorkerThreadRunnable(Runnable r);

}
