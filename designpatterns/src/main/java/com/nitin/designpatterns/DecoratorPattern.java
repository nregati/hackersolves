/*
 * Copyright (c) 2017. [Author nitin_regati] [File DecoratorPattern.java]
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
 * Created by nitin_regati on 22/07/17. <p> Decorator pattern allows a user to add new functionality
 * to an existing object without altering its structure. This type of design pattern comes under
 * structural pattern as this pattern acts as a wrapper to existing class. <p> UML:
 * https://www.tutorialspoint.com/design_pattern/images/decorator_pattern_uml_diagram.jpg
 */
public class DecoratorPattern {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {

    Circle circle = new Circle();
    circle.draw();
    RedBorderDecorator redCircle = new RedBorderDecorator(new Circle());
    redCircle.draw();
    Rectangle rectangle = new Rectangle();
    rectangle.draw();

  }

  /**
   * The interface Shape.
   */
  interface Shape {

    /**
     * Draw.
     */
    void draw();
  }

  private static class Rectangle implements Shape {

    @Override
    public void draw() {
      System.out.println("Draw Rectangle ");
    }
  }

  private static class Circle implements Shape {

    @Override
    public void draw() {
      System.out.println("Draw Circle ");
    }
  }

  private static abstract class AbstractShapeDecorator implements Shape {

    /**
     * The Shape.
     */
    Shape shape;

    /**
     * Instantiates a new Abstract shape decorator.
     *
     * @param shape the shape
     */
    AbstractShapeDecorator(Shape shape) {
      this.shape = shape;
    }
  }

  private static class RedBorderDecorator extends AbstractShapeDecorator {

    /**
     * Instantiates a new Red border decorator.
     *
     * @param shape the shape
     */
    RedBorderDecorator(Shape shape) {
      super(shape);
    }

    @Override
    public void draw() {
      shapeWithRedBorder(shape);
    }

    /**
     * Shape with red border.
     *
     * @param shape the shape
     */
    void shapeWithRedBorder(Shape shape) {
            /*
             * Here we are adding border function without actually changing the Shape structure
             */
      shape.draw();
      System.out.println("With Red border ");
    }
  }
}

