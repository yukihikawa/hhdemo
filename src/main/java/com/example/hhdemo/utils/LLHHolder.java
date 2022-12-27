package com.example.hhdemo.utils;

import com.example.hhdemo.lowlevelheuristicpool.LowLevelHeuristic;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: hhdemo
 * @description: 持有所有的低层启发式方法
 * @author: WRF
 * @create: 2022-11-29 10:36
 **/
@Component
@Data
public class LLHHolder {

    LowLevelHeuristic LLH1;
    LowLevelHeuristic LLH2;
    LowLevelHeuristic LLH3;
    LowLevelHeuristic LLH4;
    LowLevelHeuristic LLH5;
    LowLevelHeuristic LLH6;
    LowLevelHeuristic LLH7;

    @Autowired
    public void setLLH1(LowLevelHeuristic LLH1) {
        this.LLH1 = LLH1;
    }
    @Autowired
    public void setLLH2(LowLevelHeuristic LLH2) {
        this.LLH2 = LLH2;
    }
    @Autowired
    public void setLLH3(LowLevelHeuristic LLH3) {
        this.LLH3 = LLH3;
    }
    @Autowired
    public void setLLH4(LowLevelHeuristic LLH4) {
        this.LLH4 = LLH4;
    }
    @Autowired
    public void setLLH5(LowLevelHeuristic LLH5) {
        this.LLH5 = LLH5;
    }
    @Autowired
    public void setLLH6(LowLevelHeuristic LLH6) {
        this.LLH6 = LLH6;
    }
    @Autowired
    public void setLLH7(LowLevelHeuristic LLH7) {
        this.LLH7 = LLH7;
    }

    public LowLevelHeuristic getHeuristic(int i){
        return switch (i) {
            case 1 -> getLLH1();
            case 2 -> getLLH2();
            case 3 -> getLLH3();
            case 4 -> getLLH4();
            case 5 -> getLLH5();
            case 6 -> getLLH6();
            case 7 -> getLLH7();
            default -> null;
        };
    }

}
