package com.c4a.wisell.utils;

/**
 * Created by ousmaneo on 12/22/13.
 */
public class Billing {
    private static final String TAG = "Billing";
    private static final int Unite = 25;


    public static int getCredit(){
        int value = 0;
        /* TODO Get the actual amount of credit */
        int lower = 50;
        int higher = 1000;
        value =(int) (Math.random()*(higher-lower))+lower;
        return value;
    }

    public static int verifyCredit(int credit){
        return credit/Unite;
    }

}
