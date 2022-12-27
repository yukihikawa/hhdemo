package com.example.hhdemo.lowlevelheuristicpool.impl;

import com.example.hhdemo.utils.Solution;
import com.example.hhdemo.lowlevelheuristicpool.LowLevelHeuristic;
import org.springframework.stereotype.Component;

/**
 * @program: hhdemo
 * @description: 随机前移工序码子序列
 * @author: WRF
 * @create: 2022-11-18 20:43
 **/
@Component
public class LLH3 implements LowLevelHeuristic {
    @Override
    public void move(Solution solution) {
        int[] wp = solution.getWorkpieceList();
        int count = wp.length;
        int[] nwp = new int[count];
        int a = (int) (Math.random() * count);
        int b = (int) (Math.random() * count);
        if(a > b){
            int t = a;
            a = b;
            b = t;
        }
        int idx = 0;
        for (int i = 0; i < a; i++, idx++) {
            nwp[idx] = wp[i];
        }
        for (int i = b + 1; i < count; i++, idx++) {
            nwp[idx] = wp[i];
        }
        for (int i = a + 1; i < b + 1; i++){
            nwp[idx] = wp[i];
        }
        solution.setWorkpieceList(nwp);
    }
}
