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

import org.vial.collection.RollingStack;

public class UndoContext {

    private static final int DEFAULT_UNDO_DEPTH = 500;
    private Boolean everDirty = false;
    private final RollingStack<UndoableCommand> undoStack;
    private final RollingStack<UndoableCommand> redoStack;

    public UndoContext() {
        this(DEFAULT_UNDO_DEPTH);
    }

    public UndoContext(int size) {
        undoStack = new RollingStack<UndoableCommand>(size);
        redoStack = new RollingStack<UndoableCommand>(size);
    }


    public synchronized void undoPush(UndoableCommand item) {
        if (undoStack.getCapacity() == undoStack.size()) {
            everDirty = true;
        }
        undoStack.push(item);
    }

    public synchronized UndoableCommand undoPop() {
        return undoStack.pop();
    }

    public void redoPush(UndoableCommand item) {
        redoStack.push(item);
    }

    public UndoableCommand redoPop() {
        return redoStack.pop();
    }


    public Boolean isDirty() {
        return everDirty || undoStack.size() > 0;
    }

    public void clear() {
        everDirty = false;
        redoStack.clear();
        undoStack.clear();
    }
}
