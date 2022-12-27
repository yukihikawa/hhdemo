package com.example.hhdemo.utils.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: hhdemo
 * @description: 工件
 * @author: WRF
 * @create: 2022-11-16 21:49
 **/
@Data
public class Workpiece {
    int[] procedure; //包含 e_i 个工序
    double[] startTime; //每道工序的开始时间
    double[] finishTime; //每道工序的结束时间
    Map<Integer, Map<Integer, Double>> availableMachine; //对应可选机器集,map 为机器序号和加工时间的对应
    //int[] avaMach;
    int[] x; //在哪个机器上加工
    double ci;

}
