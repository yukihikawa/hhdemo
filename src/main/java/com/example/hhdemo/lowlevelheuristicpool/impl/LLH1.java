package com.example.hhdemo.lowlevelheuristicpool.impl;

import com.example.hhdemo.utils.Solution;
import com.example.hhdemo.lowlevelheuristicpool.LowLevelHeuristic;
import org.springframework.stereotype.Component;

/**
 * @program: hhdemo
 * @description: llh1，工序码随机交换,随机选取两个工序码对换
 * @author: Rifu Wu
 * @create: 2022-11-16 20:54
 **/
@Component
public class LLH1 implements LowLevelHeuristic {


    @Override
    public void move(Solution solution) {
        int[] wp = solution.getWorkpieceList();
        int count = wp.length;

        int a = (int) (Math.random() * count);
        int b = (int) (Math.random() * count);
        int temp = wp[a];
        wp[a] = wp[b];
        wp[b] = temp;
    }
}