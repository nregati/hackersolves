/*
 * Copyright (c) 2017. [Author nitin_regati] [File VisitorPattern.java]
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
 * Created by nitin_regati on 22/07/17.
 * <p>
 * In Visitor pattern, we use a visitor class which changes the executing algorithm of an element class.
 * By this way, execution algorithm of element can vary as and when visitor varies. This pattern comes
 * under behavior pattern category. As per the pattern, element object has to accept the visitor object
 * so that visitor object handles the operation on the element object.
 * <p>
 * UML: https://www.tutorialspoint.com/design_pattern/images/visitor_pattern_uml_diagram.jpg
 */
public class VisitorPattern {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        ComputerPartDisplayVisitor visitor = new ComputerPartDisplayVisitor();
        Computer computer = new Computer();
        computer.accept(visitor);
        Keyboard keyboard = new Keyboard();
        keyboard.accept(visitor);
        Mouse mouse = new Mouse();
        mouse.accept(visitor);
        Monitor monitor = new Monitor();
        monitor.accept(visitor);

        ComputerPartRemoveVisitor removeVisitor = new ComputerPartRemoveVisitor();
        computer.accept(removeVisitor);
        keyboard.accept(removeVisitor);
        mouse.accept(removeVisitor);
        monitor.accept(removeVisitor);
    }


    /**
     * The interface Computer part visitor.
     */
    interface ComputerPartVisitor {
        /**
         * Visit.
         *
         * @param computer the computer
         */
        void visit(Computer computer);

        /**
         * Visit.
         *
         * @param keyboard the keyboard
         */
        void visit(Keyboard keyboard);

        /**
         * Visit.
         *
         * @param mouse the mouse
         */
        void visit(Mouse mouse);

        /**
         * Visit.
         *
         * @param monitor the monitor
         */
        void visit(Monitor monitor);
    }

    /**
     * The interface Computer part.
     */
    interface ComputerPart {
        /**
         * Accept.
         *
         * @param visitor the visitor
         */
        void accept(ComputerPartVisitor visitor);
    }

    /**
     * The type Computer part display visitor.
     */
    static class ComputerPartDisplayVisitor implements ComputerPartVisitor {

        @Override
        public void visit(Computer computer) {
            String[] parts = computer.getParts();
            System.out.println("Computer has following parts [ ");
            for (String part : parts) {
                System.out.println(part + ",");
            }
            System.out.println(" ]");
        }

        @Override
        public void visit(Keyboard keyboard) {
            System.out.println("Displaying Keyboard");
        }

        @Override
        public void visit(Mouse mouse) {
            System.out.println("Displaying Mouse");
        }

        @Override
        public void visit(Monitor monitor) {
            System.out.println("Displaying Monitor");
        }
    }

    /**
     * The type Computer part remove visitor.
     */
    static class ComputerPartRemoveVisitor implements ComputerPartVisitor {


        @Override
        public void visit(Computer computer) {
            String[] parts = computer.getParts();
            System.out.println("Removing following parts [ ");
            for (String part : parts) {
                System.out.println(part + ",");
            }
            System.out.println(" ]");
        }

        @Override
        public void visit(Keyboard keyboard) {
            System.out.println("Removed Keyboard");
        }

        @Override
        public void visit(Mouse mouse) {
            System.out.println("Removed Mouse");
        }

        @Override
        public void visit(Monitor monitor) {
            System.out.println("Removed Monitor");
        }
    }

    /**
     * The type Computer.
     */
    static class Computer implements ComputerPart {

        private String[] parts = {"Keyboard, Mouse, Monitor"};

        @Override
        public void accept(ComputerPartVisitor visitor) {
            visitor.visit(this);
        }

        /**
         * Get parts string [ ].
         *
         * @return the string [ ]
         */
        String[] getParts() {
            return parts;
        }
    }

    /**
     * The type Keyboard.
     */
    static class Keyboard implements ComputerPart {
        @Override
        public void accept(ComputerPartVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * The type Mouse.
     */
    static class Mouse implements ComputerPart {
        @Override
        public void accept(ComputerPartVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * The type Monitor.
     */
    static class Monitor implements ComputerPart {
        @Override
        public void accept(ComputerPartVisitor visitor) {
            visitor.visit(this);
        }
    }


}
