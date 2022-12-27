package com.example.hhdemo.utils;

import java.util.Random;

/**
 * @program: hhdemo
 * @description: 简单遗传算法实现
 * 以下展示的是遗传算法在 Java 中的示例实现，我们可以随意调试和修改这些代码。给定一组五个基因，每一个基因可以保存一个二进制值 0 或 1。这里的适应度是基因组中 1 的数量。如果基因组内共有五个 1，则该个体适应度达到最大值。如果基因组内没有 1，那么个体的适应度达到最小值。该遗传算法希望最大化适应度，并提供适应度达到最大的个体所组成的群体。注意：本例中，在交叉运算与突变运算之后，适应度最低的个体被新的，适应度最高的后代所替代。
 * @author: WRF
 * @create: 2022-12-05 22:33
 **/
public class SimpleGA {

    Population population = new Population();
    Individual fittest;
    Individual secondFittest;
    int generationCount = 0;
    public static void main(String[] args) {
        Random rn = new Random();
        SimpleGA demo = new SimpleGA();
        //初始化种群,大小 10
        demo.population.initializePopulation(10);
        //为所有个体计算适应度
        demo.population.calculateFitness();
        System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);

        while(demo.population.fittest < 5){
            demo.selection();

            demo.crossover();

            //按概率变异
            if(rn.nextInt() % 7 < 5){
                demo.mutation();
            }

            demo.addFittestOffSpring();

            demo.population.calculateFitness();

            System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);
        }
        System.out.println("\nSolution found in generation " + demo.generationCount);
        System.out.println("Fitness: "+demo.population.getFittest().fitness);
        System.out.print("Genes: ");

        for (int i = 0; i < 5; i++) {
            System.out.print(demo.population.getFittest().genes[i]);
        }

        System.out.println("");
    }

    //选择
    void selection(){
        fittest = population.getFittest();
        secondFittest = population.getSecondFittest();
    }

    //交叉,最适应的两个
    void crossover(){
        Random rn = new Random();
        int crossOverPoint = rn.nextInt(population.individuals[0].geneLength);

        for(int i = 0; i < crossOverPoint; i++){
            int temp = fittest.genes[i];
            fittest.genes[i] = secondFittest.genes[i];
            secondFittest.genes[i] = temp;
        }
    }

    //变异,0-1
    void mutation(){
        Random rn = new Random();
        int mutationPoint = rn.nextInt(population.individuals[0].geneLength);
        fittest.genes[mutationPoint] = (fittest.genes[mutationPoint] + 1) % 2;

        mutationPoint = rn.nextInt(population.individuals[0].geneLength);
        secondFittest.genes[mutationPoint] = (secondFittest.genes[mutationPoint] + 1) % 2;
    }

    //Get fittest offspring 选择合适后代
    Individual getFittestOffSpring(){
        if(fittest.fitness > secondFittest.fitness)
            return fittest;
        else
            return secondFittest;
    }

    //用最好后代取代最坏
    void addFittestOffSpring(){
        fittest.calcFitness();
        secondFittest.calcFitness();

        //获取最差索引
        int leastFittestIndex = population.getLeastFitnessIndex();

        //替换
        population.individuals[leastFittestIndex] = getFittestOffSpring();
    }


}

    //定义个体
class Individual{
    int fitness = 0;
    int[] genes = new int[5];
    int geneLength = 5;

    public Individual(){
        Random rn = new Random();
        //为每个个体随机设置基因
        for(int i = 0; i < genes.length; i++){
            genes[i] = rn.nextInt() % 2;
        }
        fitness = 0;
    }

    //计算适应度
    public void calcFitness(){

        fitness = 0;
        for(int i = 0; i < 5; i++){
            if(genes[i] == 1){
                ++fitness;
            }
        }
    }

}
//种群
class Population{

    int popSize = 10;
    Individual[] individuals = new Individual[10];
    int fittest = 0;

    //初始化种群
    public void initializePopulation(int size){
        for (int i = 0; i < individuals.length; i++) {
            individuals[i] = new Individual();
        }
    }

    //获得适应度最高的个体?
    public Individual getFittest(){
        int maxFit = Integer.MIN_VALUE;
        for (int i = 0; i < individuals.length; i++) {
            if(maxFit <= individuals[i].fitness){
                maxFit = i;
            }
        }
        fittest = individuals[maxFit].fitness;
        return individuals[maxFit];
    }

    public Individual getSecondFittest(){
        int maxFit1 = 0;
        int maxFit2 = 0;
        for(int i = 0; i < individuals.length; i++){
            if(individuals[i].fitness > individuals[maxFit1].fitness){
                maxFit2 = maxFit1;
                maxFit1 = i;
            }
            if(individuals[i].fitness > individuals[maxFit2].fitness){
                maxFit2 = i;
            }
        }
        return individuals[maxFit2];
    }

    public int getLeastFitnessIndex(){
        int minFit = 0;
        for (int i = 0; i < individuals.length; i++) {
            if(individuals[minFit].fitness >= individuals[i].fitness){
                minFit = i;
            }
        }
        return minFit;
    }

    public void calculateFitness(){
        for(Individual i : individuals){
            i.calcFitness();
        }
        getFittest();
    }
}