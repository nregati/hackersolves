/*
 * Copyright (c) 2017. [Author nitin_regati] [File TimerExample.java]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package com.nitin.learnings;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by nitin_regati on 17/07/17.
 */
public class TimerExample extends TimerTask {


    public TimerExample(int timesPerSecond) {

        Timer timer = new Timer();
        timer.schedule(this, 200, 1000 / timesPerSecond);
    }

    public static void main(String[] args) {
        new TimerExample(10);
    }

    @Override
    public void run() {
        System.out.println("Testing");
    }
}
