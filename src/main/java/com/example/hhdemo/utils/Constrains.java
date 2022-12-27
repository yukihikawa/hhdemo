package com.example.hhdemo.utils;

import org.springframework.stereotype.Component;

/**
 * @program: hhdemo
 * @description: 约束条件判断类
 * @author: WRF
 * @create: 2022-11-18 23:32
 **/
@Component
public class Constrains {
    ProblemDomain problemDomain;
    public static int cMax(Solution solution){

        return 0;
    }

    public static boolean valid(Solution solution){
        return true;
    }
}
