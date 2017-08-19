/*
 * Copyright (c) 2017. [Author nitin_regati] [File ChainOfCommand.java]
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

package com.nitin.designpatterns;

/**
 * Created by nitin_regati on 01/08/17
 */
public class ChainOfCommand {

  private static AbstractLogger getLogger() {
    AbstractLogger error = new ErrorLogger();
    AbstractLogger file = new FileLogger();
    AbstractLogger console = new ConsoleLogger();
    error.nextLogger = file;
    file.nextLogger = console;
    return error;
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {

    AbstractLogger logger = getLogger();
    logger.logMessage(AbstractLogger.ERROR, "Error + Debug + Info");
    logger.logMessage(AbstractLogger.DEBUG, "Debug + Info");
    logger.logMessage(AbstractLogger.INFO, "Info");

  }

  private abstract static class AbstractLogger {

    private static int INFO = 1;
    private static int DEBUG = 2;
    private static int ERROR = 3;

    /**
     * The Level.
     */
    int level;
    /**
     * The Next logger.
     */
    AbstractLogger nextLogger;

    /**
     * Log message.
     *
     * @param level the level
     * @param msg the msg
     */
    void logMessage(int level, String msg) {

      if (this.level <= level) {
        write(msg);
      }
      if (null != nextLogger) {
        nextLogger.logMessage(level, msg);
      }
    }

    /**
     * Write.
     *
     * @param msg the msg
     */
    public abstract void write(String msg);
  }

  private static class ConsoleLogger extends AbstractLogger {

    /**
     * Instantiates a new Console logger.
     */
    ConsoleLogger() {
      this.level = AbstractLogger.INFO;
    }

    @Override
    public void write(String msg) {
      System.out.println("ConsoleLogger: " + msg);
    }
  }

  private static class FileLogger extends AbstractLogger {

    /**
     * Instantiates a new File logger.
     */
    FileLogger() {
      this.level = AbstractLogger.DEBUG;
    }

    @Override
    public void write(String msg) {
      System.out.println("FileLogger: " + msg);
    }
  }

  private static class ErrorLogger extends AbstractLogger {

    /**
     * Instantiates a new Error logger.
     */
    ErrorLogger() {
      this.level = AbstractLogger.ERROR;
    }

    @Override
    public void write(String msg) {
      System.out.println("ErrorLogger: " + msg);
    }
  }

}
