package kpi.trspo.clientapp.testingutil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

public class RandomUtility {
    static Random rand = new Random();

    public RandomUtility() {
    }

    public static String getRandomName() {
        return Name.values()[rand.nextInt(Name.values().length)].getName();
    }

    public static String getRandomSurname() {
        return Surname.values()[rand.nextInt(Surname.values().length)].getSurname();
    }

    public static Date getRandomDate(Date lowerBound, Date upperBound) {
        long range = (long)(rand.nextDouble() * (double)(upperBound.getTime() - lowerBound.getTime()));
        return new Date(range + lowerBound.getTime());
    }

    public static float getRandomWeight(float lowWeight, float upperWeight ){
        return (rand.nextFloat() * (upperWeight - lowWeight) + lowWeight);
    }

    public static int getRandomInt(int number){
        return rand.nextInt(number);
    }

}
