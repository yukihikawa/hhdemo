package com.example.hhdemo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: hhdemo
 * @description: 解的编码
 * @author: Rifu Wu
 * @create: 2022-11-16 16:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solution implements Cloneable{
    int[] workpieceList;
    int[] machineList;

    @Override
    public Object clone() throws CloneNotSupportedException {
        Solution newSolution = (Solution) super.clone();
        newSolution.machineList = (int[]) machineList.clone();
        newSolution.workpieceList = (int[]) workpieceList.clone();
        return newSolution;
    }
}