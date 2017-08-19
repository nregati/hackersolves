/*
 * Copyright (c) 2017. [Author nitin_regati] [File AmzTest3.java]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.nitin.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by nitin_regati on 13/07/17.
 */
public class AmzTest3 {


  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
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
        if (usageList.get(k).getEndDate().compareTo(usageList.get(f).getStartDate()) == 1) {
          maxBandWidth += usageList.get(k).getBandWidth() + usageList.get(f).getBandWidth();
        }
      }
      System.out.println(maxBandWidth);
    }

  }

  /**
   * The type Usage.
   */
  public static class Usage implements Comparable {

    private Date startDate, endDate;
    private int bandWidth;

    /**
     * Instantiates a new Usage.
     *
     * @param startDate the start date
     * @param endDate the end date
     * @param bandWidth the band width
     */
    public Usage(Date startDate, Date endDate, int bandWidth) {
      this.startDate = startDate;
      this.endDate = endDate;
      this.bandWidth = bandWidth;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
      return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(Date endDate) {
      this.endDate = endDate;
    }

    /**
     * Gets band width.
     *
     * @return the band width
     */
    public int getBandWidth() {
      return bandWidth;
    }

    @Override
    public int compareTo(Object o) {
      return this.getStartDate().compareTo(((Usage) o).getStartDate());
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
      return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(Date startDate) {
      this.startDate = startDate;
    }
  }


}
