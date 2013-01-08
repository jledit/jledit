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

package org.vial.command.undo;

import static junit.framework.Assert.*;

import org.vial.collection.RollingStack;
import org.junit.Test;

public class RollingStackTest {

    @Test
    public void testPushAndPop() throws Exception {
        RollingStack<Integer> stack = new RollingStack<Integer>();
        for (int i=0; i < 100; i++) {
            stack.push(i);
        }
        for (int i=99; i>=0;i--) {
            assertEquals(stack.pop().intValue(), i);
        }
    }

    @Test
    public void testPushAndPopWithOverflow() throws Exception {
        RollingStack<Integer> stack = new RollingStack<Integer>();
        for (int i=0; i < 200; i++) {
            stack.push(i);
        }
        for (int i=199; i>=100;i--) {
            assertEquals(stack.pop().intValue(), i);
        }
    }

    @Test
    public void testPopOnEmptyStack() throws Exception {
        RollingStack stack = new RollingStack();
        assertNull(stack.pop());
    }
}
