/*
 * Copyright (c) 2017. [Author nitin_regati] [File AmzTest3.java]
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

import java.util.*;

/**
 * Created by nitin_regati on 13/07/17.
 */
public class AmzTest3 {


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        List<Usage> usageList = new ArrayList<>();
        for (int i = 0; i < testCases; i++) {
            int inputSize = sc.nextInt();
            for (int j = 0; j < inputSize; j++) {
                usageList.add(new Usage(new Date(sc.nextLong()), new Date(sc.nextLong()), sc.nextInt()));
            }
            Collections.sort(usageList);
            int maxBandWidth = 0;
            for (int k = 0, f = 1; k < inputSize && f < inputSize; k++, f++) {
                if (usageList.get(k).getEndDate().compareTo(usageList.get(f).getStartDate()) == 1)
                    maxBandWidth += usageList.get(k).getBandWidth() + usageList.get(f).getBandWidth();
            }
            System.out.println(maxBandWidth);
        }

    }

    public static class Usage implements Comparable {

        private Date startDate, endDate;
        private int bandWidth;

        public Usage(Date startDate, Date endDate, int bandWidth) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.bandWidth = bandWidth;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public int getBandWidth() {
            return bandWidth;
        }

        @Override
        public int compareTo(Object o) {
            return this.getStartDate().compareTo(((Usage) o).getStartDate());
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }
    }


}
