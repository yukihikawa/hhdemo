package com.example.hhdemo.lowlevelheuristicpool.impl;

import com.example.hhdemo.utils.Solution;
import com.example.hhdemo.lowlevelheuristicpool.LowLevelHeuristic;
import com.example.hhdemo.utils.Constrains;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @program: hhdemo
 * @description: 简化邻域搜索
 *
 * @author: WRF
 * @create: 2022-11-18 23:16
 **/
@Component
public class LLH4 implements LowLevelHeuristic {
    @Override
    public void move(Solution solution) {
        int[] wp = solution.getWorkpieceList();
        int count = wp.length;
        int a = (int) (Math.random() * count);  //随机选择一个工序
        for(int i =0 ; i < count; i++){
            int[] tempArr = Arrays.copyOf(wp, count);
            insert(tempArr, i, a);//依次插入每个位置
            Solution omegab = new Solution(tempArr, solution.getMachineList());
            if(Constrains.cMax(omegab) < Constrains.cMax(solution)){
                //比较两个解
                solution.setWorkpieceList(tempArr);
                break;
            }
        }
    }

    private static void insert(int[] wp, int idx, int pos){
        if(idx == pos){
            return;
        }
        int temp = wp[pos];
        if(idx < pos){
            for(int i = pos; i > idx; i--){
                wp[i] = wp[i - 1];
            }
            wp[idx] = temp;
        }else {
            for(int i = pos; i < idx; i++){
                wp[i] = wp[i + 1];
            }
            wp[idx] = temp;
        }
    }
}
