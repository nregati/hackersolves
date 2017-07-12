/*
 * Copyright (c) 2017. [Author nitin_regati] [File LibFine.java]
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

package com.nitin.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nitin_regati on 12/07/17.
 */
public class LibFine {


    public static void solve() throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
        Calendar actualDate = Calendar.getInstance();
        Calendar expectedDate = Calendar.getInstance();
        actualDate.setTime(format.parse(br.readLine()));
        expectedDate.setTime(format.parse(br.readLine()));
        if (actualDate.compareTo(expectedDate) <= 0)
            System.out.println(0);
        else {
            if (expectedDate.get(Calendar.YEAR) == actualDate.get(Calendar.YEAR)) {
                if (expectedDate.get(Calendar.MONTH) == actualDate.get(Calendar.MONTH)) {
                    System.out.println(15 * (actualDate.get(Calendar.DAY_OF_MONTH) - expectedDate.get(Calendar.DAY_OF_MONTH)));
                } else
                    System.out.println(500 * (actualDate.get(Calendar.MONTH) - expectedDate.get(Calendar.MONTH)));

            } else
                System.out.println(10000);
        }
    }
}
