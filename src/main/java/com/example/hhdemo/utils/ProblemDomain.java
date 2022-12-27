package com.example.hhdemo.utils;

import com.example.hhdemo.utils.entity.Machine;
import com.example.hhdemo.utils.entity.Workpiece;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: hhdemo
 * @description: 原始信息
 * @author: WRF
 * @create: 2022-11-17 20:07
 **/
@Data
@Component
public class ProblemDomain {
    private volatile static ProblemDomain instance;

    private static Double bestSolutionValue = 0.00;
    Solution solution = null;

    Map<Integer, Workpiece> wpl = new HashMap<>();
    Map<Integer, Machine> ml = new HashMap<>();

    private ProblemDomain(){

    }

    public static ProblemDomain getInstance(){
        if(instance == null){
            synchronized (ProblemDomain.class){
                if(instance == null){
                    instance = new ProblemDomain();
                }
            }
        }
        return instance;
    }

    public double cMax(Solution solution){
        double cmax = 0.0;
        int[] wpHash = new int[wpl.size()];
        for(int i = 0; i < solution.workpieceList.length; i++){

            //按次序解码工件和机台编号
            int wp = solution.workpieceList[i];
            int mc = solution.machineList[i];
            Workpiece workpiece = wpl.get(wp);  //获取工件
            Machine machine = ml.get(mc);  //获取机台
            int wpSeq = workpiece.getProcedure()[wpHash[wp]++]; //获取工序,同时更新工序
            if(!workpiece.getAvailableMachine().get(wpSeq).containsKey(mc)){
                return Double.MAX_VALUE;
            }
            double lastFinishProcedure = wpSeq == 0 ? 0 : workpiece.getFinishTime()[wpSeq - 1]; //上一工序完成时间
            double lastFinishMachine = machine.getLastFinishTime(); //机器上一道工序的完成时间
            double startTime = Math.max(lastFinishMachine, lastFinishProcedure);
            workpiece.getStartTime()[wpSeq] = startTime;
            double endTime = startTime + workpiece.getAvailableMachine().get(wpSeq).get(mc);
            workpiece.getFinishTime()[wpSeq] = endTime;
            machine.setLastFinishTime(endTime);
            if(endTime > cmax){
                cmax = endTime;
            }
        }
        if(bestSolutionValue.equals(0.00) || cmax < bestSolutionValue){
            bestSolutionValue = cmax;
        }
        return cmax;
    }
    public  double getBestSolutionValue(){
        return bestSolutionValue;
    }


}
