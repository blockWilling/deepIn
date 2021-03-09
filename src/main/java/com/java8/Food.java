package com.java8;

/**
 * @author kangjin
 * @since 2021/3/9 8:49
 */
public class Food {

    public static final String name = "Food";
    private static int num = 2;
    public Food() {
        System.out.println("Delicious Food");
    }

    public Active getEat() {
        return new EatActive();
    }
    private class EatActive implements Active {
        @Override
        public void eat() {
            if (num == 0) {
                System.out.println("吃货，已经吃没了");
            }
            num --;
            System.out.println("吃货，你吃了一份了");
        }
    }

    public void currentNum() {
        System.out.println("还剩:"+num+"份");
    }

    public static void main(String[] args) {
        Active eat = new Food().getEat();
        eat.eat();
        eat.eat();
        eat.eat();
        eat.eat();
        eat.eat();
    }

}

interface Active{
    void eat();
}
