package com.example.hhdemo.lowlevelheuristicpool.impl;

import com.example.hhdemo.utils.Solution;
import com.example.hhdemo.lowlevelheuristicpool.LowLevelHeuristic;
import org.springframework.stereotype.Component;

/**
 * @program: hhdemo
 * @description: 随机反转工序码子序列
 * @author: WRF
 * @create: 2022-11-18 19:32
 **/
@Component
public class LLH2 implements LowLevelHeuristic {
    @Override
    public void move(Solution solution) {
        int[] wp = solution.getWorkpieceList();
        int count = wp.length;
        int a = (int) (Math.random() * count);
        int b = (int) (Math.random() * count);
        if(a > b){
            int t = a;
            a = b;
            b = t;
        }
        int[] temp = new int[b - a + 1];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = wp[a + i];
        }
        for (int i = 0; i < temp.length; i++) {
            wp[b - i] = temp[i];
        }
    }
}
