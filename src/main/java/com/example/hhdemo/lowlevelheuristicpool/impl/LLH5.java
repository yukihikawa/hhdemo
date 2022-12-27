package com.example.hhdemo.lowlevelheuristicpool.impl;

import com.example.hhdemo.utils.Solution;
import com.example.hhdemo.utils.entity.Workpiece;
import com.example.hhdemo.lowlevelheuristicpool.LowLevelHeuristic;
import com.example.hhdemo.utils.ProblemDomain;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: hhdemo
 * @description: 随机改变单个机器码
 * @author: WRF
 * @create: 2022-11-19 21:08
 **/
@Component
public class LLH5 implements LowLevelHeuristic {
    @Override
    public void move(Solution solution) {
        int[] ml = solution.getMachineList();
        int[] wp = solution.getWorkpieceList();
        int count = ml.length;
        int a = (int) (Math.random() * count);  //随机选择一个工序
        int workpieceNumber = 0;
        for(int i = 0; i <= a; i++){
            if(wp[i] == wp[a]){
                workpieceNumber++;
            }
        }
        //ml[a] = validMachine(workpieceNumber, wp[a]);
    }

    private static Double validMachine(int workpieceNumber, int procedure){
        ProblemDomain problemDomain = ProblemDomain.getInstance();
        Workpiece workpiece = problemDomain.getWpl().get(workpieceNumber);
        Map<Integer, Map<Integer, Double>> avaMc = workpiece.getAvailableMachine();
        Map<Integer, Double> list = avaMc.get(procedure);
        int[] x = workpiece.getX();
        int a = (int) (Math.random() * list.size());
        while (x[a] == 1){
            a = (int) (Math.random() * list.size());
        }
        return list.get(a);
    }
}
