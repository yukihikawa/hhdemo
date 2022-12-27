package com.example.hhdemo.lowlevelheuristicpool.impl;

import com.example.hhdemo.utils.Solution;
import com.example.hhdemo.lowlevelheuristicpool.LowLevelHeuristic;
import com.example.hhdemo.utils.Constrains;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @program: hhdemo
 * @description: 机器码简化领域搜索
 * @author: WRF
 * @create: 2022-11-19 22:50
 **/
@Component
public class LLH6 implements LowLevelHeuristic {
    @Override
    public void move(Solution solution) {
        int[] wp = solution.getWorkpieceList();
        int count = wp.length;
        int a = (int) (Math.random() * count);  //随机选择一个工序
        for(int i =0 ; i < count; i++){
            int[] tempArr = Arrays.copyOf(wp, count);

            /*更换机器*/
            Solution omegab = new Solution(tempArr, solution.getMachineList());
            if(Constrains.cMax(omegab) < Constrains.cMax(solution)){
                //比较两个解
                solution.setWorkpieceList(tempArr);
                break;
            }
        }
    }
}
