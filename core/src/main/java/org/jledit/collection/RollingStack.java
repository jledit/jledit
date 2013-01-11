/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jledit.collection;

import java.util.Deque;
import java.util.LinkedList;

/**
 * A fixed size Stack implementation which removes oldest item when overflows.
 *
 * @param <I>
 */
public class RollingStack<I> {

    private static final int DEFAULT_SIZE = 100;
    private int capacity = DEFAULT_SIZE;
    private final Deque<I> list = new LinkedList<I>();

    /**
     * Constructor
     */
    public RollingStack() {
    }

    /**
     * Constructor
     *
     * @param capacity
     */
    public RollingStack(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void push(I item) {
        if (list.size() >= capacity) {
            list.removeFirst();
        }
        list.addLast(item);
    }

    public synchronized I pop() {
        if (list.isEmpty()) {
            return null;
        } else {
            return list.removeLast();
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int size() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }
}
