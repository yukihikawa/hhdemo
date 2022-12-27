package com.example.hhdemo.highlevelheuristic.impl;

import com.example.hhdemo.highlevelheuristic.HyperHeuristic;
import com.example.hhdemo.lowlevelheuristicpool.LowLevelHeuristic;
import com.example.hhdemo.utils.Constrains;
import com.example.hhdemo.utils.LLHHolder;
import com.example.hhdemo.utils.ProblemDomain;
import com.example.hhdemo.utils.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: hhdemo
 * @description: 纯随机超启发式
 * @author: WRF
 * @create: 2022-11-29 00:43
 **/
@Component
public class RandomHyperHeuristic extends HyperHeuristic {
    private final LLHHolder llhHolder;
    private final ProblemDomain problemDomain;

    public RandomHyperHeuristic(LLHHolder llhHolder, Constrains constrains, ProblemDomain problemDomain) {
        super();
        this.llhHolder = llhHolder;
        this.problemDomain = problemDomain;
    }


    @Override
    public Solution initSolution(ProblemDomain problem) {
        return null;
    }

    @Override
    public LowLevelHeuristic selector() {
        int count = rng.nextInt();
        return llhHolder.getHeuristic(count);
    }

    @Override
    public boolean acceptor(Solution solution) {
        return problemDomain.cMax(solution) < problemDomain.getBestSolutionValue() ;
    }

    @Override
    public Solution solve(ProblemDomain problemDomain, long time_in_milliseconds) {
        Solution solution = initSolution(problemDomain);
        setTimeLimit(time_in_milliseconds);
        while(!hasTimeExpired()){
            Solution temp;
            try {
                temp = (Solution) solution.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }

            this.selector().move(temp);
            if(acceptor(temp)){
                solution = temp;
                problemDomain.setSolution(temp);
            }
        }
        return solution;
    }

    @Override
    public Solution solve(ProblemDomain problemDomain, int iterations) {
        Solution solution = initSolution(problemDomain);
        while(iterations-- > 0){
            Solution temp;
            try {
                temp = (Solution) solution.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }

            this.selector().move(temp);
            if(acceptor(temp)){
                solution = temp;
                problemDomain.setSolution(temp);
            }
        }
        return solution;
    }
}
