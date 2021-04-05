package com.study.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntBinaryOperator;

/**
 * @author
 * 过滤苹果的颜色或者重量
 */
public class AppleFilter {

    public interface FitlerApple{
        boolean filter(Apple apple) ;
    }

    public static List<Apple> findApples(List<Apple> apples, FitlerApple filterApple){
        List<Apple> list = new ArrayList<>();

        for (Apple apple: apples){
            if (filterApple.filter(apple)){
                list.add(apple);
            }
        }
        return list ;
    }

    public static class greenAnd150WeightFilter implements FitlerApple{

        @Override
        public boolean filter(Apple apple) {
            return apple.getColor().equals("green") &&
                    apple.getWeight().equals("150");
        }
    }

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green","150"),
                new Apple("green","160"),
                new Apple("yellow","150")) ;

        // 采用策略模式
        List<Apple> result = findApples(list,new greenAnd150WeightFilter()) ;
        System.out.println("result:"+result);

        // 采用匿名内部类
        List<Apple> r = findApples(list,new FitlerApple(){
            @Override
            public boolean filter(Apple apple) {
                return apple.getColor().equals("yellow");
            }
        });

        System.out.println("r："+r);

        // lambda
        List<Apple> r2 = findApples(list,(Apple apple) ->{
            return apple.getColor().equals("yellow") ;
        });

        System.out.println("r2"+r2);


        IntBinaryOperator i =  (int a, int b)->{
            return a + b;
        };

        System.out.println(i.applyAsInt(1,3));
    }
}
