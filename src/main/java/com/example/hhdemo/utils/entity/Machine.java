package com.example.hhdemo.utils.entity;

import lombok.Data;

import java.util.List;

/**
 * @program: hhdemo
 * @description:
 * @author: Rifu Wu
 * @create: 2022-11-03 15:41
 **/
@Data
public class Machine {
    Integer type;
    //最后一道工序的完成对象
    double lastFinishTime;
}