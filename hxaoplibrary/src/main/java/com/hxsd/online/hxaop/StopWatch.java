package com.hxsd.online.hxaop;

/**
 * 作者：MuWenLei
 * 时间：2018/6/5 下午 12:42
 * 邮箱：itexplorer@126.com
 * 说明：
 */
public class StopWatch {

    private long startTime;
    private long endTime;
    private long elapsedTime;

    public StopWatch() {
    }

    private void reset() {
        startTime = 0;
        endTime = 0;
        elapsedTime = 0;
    }

    public void start() {
        reset();
        startTime = System.nanoTime();
    }

    public void stop() {
        if (startTime != 0) {
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
        } else {
            reset();
        }
    }

    /**
     * 返回花费的时间，单位是纳秒
     * @return
     */
    public long getElapsedTime() {

        return elapsedTime;
    }
}
