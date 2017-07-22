/*
 * Copyright (c) 2017. [Author nitin_regati] [File IteratorPattern.java]
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
 * Iterator pattern is very commonly used design pattern in Java and .Net programming environment.
 * This pattern is used to get a way to access the elements of a collection object in sequential
 * manner without any need to know its underlying representation.
 * <p>
 * Iterator pattern falls under behavioral pattern category.
 * <p>
 * UML: https://www.tutorialspoint.com/design_pattern/images/iterator_pattern_uml_diagram.jpg
 */
public class IteratorPattern {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        NamesRepository repository = new NamesRepository();
        Iterator repoItr = repository.getIterator();
        while (repoItr.hasNext())
            System.out.println(String.valueOf(repoItr.next()));
    }

    /**
     * The interface Iterator.
     */
    public interface Iterator {
        /**
         * Has next boolean.
         *
         * @return the boolean
         */
        boolean hasNext();

        /**
         * Next object.
         *
         * @return the object
         */
        Object next();
    }

    /**
     * The interface Container.
     */
    public interface Container {
        /**
         * Gets iterator.
         *
         * @return the iterator
         */

        /*
        Container has-a iterator
         */
        Iterator getIterator();
    }

    /**
     * The type Names repository.
     */
    public static class NamesRepository implements Container {

        private String[] names = {"abc", "bcd", "cde", "def"};

        @Override
        public Iterator getIterator() {
            /*
            Forcing to implement Iterator
             */
            return new NameIterator();
        }

        private class NameIterator implements Iterator {

            private int index;

            @Override
            public boolean hasNext() {
                return index < names.length;
            }

            @Override
            public Object next() {
                return this.hasNext() ? names[index++] : null;
            }
        }
    }

}
