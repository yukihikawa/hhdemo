package com.example.hhdemo.highlevelheuristic;

import com.example.hhdemo.lowlevelheuristicpool.LowLevelHeuristic;
import com.example.hhdemo.utils.ProblemDomain;
import com.example.hhdemo.utils.Solution;

import java.lang.management.ThreadMXBean;
import java.util.Random;

/**
 * @program: hhdemo
 * @description: 高层超启发式策略
 * @author: WRF
 * @create: 2022-11-28 17:41
 **/
public abstract class HyperHeuristic {
    protected Random rng;  //随机数

    private long timeLimit = 0L;  //运行时间限制
    private long initialTime;  //初始时间
    private ThreadMXBean bean;  //工具类,用于获取当前线程cpu 时间
    private ProblemDomain problem;
    private double printfraction;
    private double printlimit;
    private int lastprint;
    private boolean initialprint;
    private double lastbestsolution = -1.0;
    private double[] trace;
    private static boolean timeLimitSet;  //时间限制是否已设置

    public HyperHeuristic(long seed) {
        this.rng = new Random(seed);
        timeLimitSet = false;
    }

    public HyperHeuristic() {
        this.rng = new Random();
        timeLimitSet = false;
    }

    //设置时间限制
    public void setTimeLimit(long time_in_milliseconds) {
        if (!timeLimitSet) {
            this.timeLimit = time_in_milliseconds * 1000000L;
            this.printfraction = (double)(time_in_milliseconds * 10000L);
            this.printlimit = this.printfraction;
            this.initialprint = false;
            this.lastprint = 0;
            timeLimitSet = true;
        } else {
            System.err.println("The time limit cannot be set twice. " + this.toString());
            System.exit(-1);
        }

    }

    //获取时间限制
    public long getTimeLimit() {
        return this.timeLimit / 1000000L;
    }

    //是否已过期
    protected boolean hasTimeExpired() {
        long time = this.bean.getCurrentThreadCpuTime() - this.initialTime;
        if (!this.initialprint) {
            this.initialprint = true;
            double res = this.problem.getBestSolutionValue();
            this.trace[0] = res;
            this.lastbestsolution = res;
        } else if ((double)time >= this.printlimit) {
            int thisprint = (int)((double)time / this.printfraction);
            if (thisprint > 100) {
                thisprint = 100;
            }

            for(int x = 0; x < thisprint - this.lastprint; ++x) {
                if (time <= this.timeLimit) {
                    double res = this.problem.getBestSolutionValue();
                    this.trace[this.lastprint + x + 1] = res;
                    this.lastbestsolution = res;
                } else {
                    this.trace[this.lastprint + x + 1] = this.lastbestsolution;
                }

                this.printlimit += this.printfraction;
            }

            this.lastprint = thisprint;
        }

        if (time >= this.timeLimit) {
            return true;
        } else {
            this.lastbestsolution = this.problem.getBestSolutionValue();
            return false;
        }
    }

    //获取过期时间
    public long getElapsedTime() {
        return this.bean == null ? 0L : (long)((double)(this.bean.getCurrentThreadCpuTime() - this.initialTime) / 1000000.0);
    }

    //初始化解
    public abstract Solution initSolution(ProblemDomain problem);

    //算法选择器
    public abstract LowLevelHeuristic selector();

    //接受
    public abstract boolean acceptor(Solution solution);

    //求解主方法,使用时间限制
    public abstract Solution solve(ProblemDomain problemDomain, long time_in_milliseconds);

    //求解方法,使用迭代次数限制
    public abstract Solution solve(ProblemDomain problemDomain, int iterations);
}
