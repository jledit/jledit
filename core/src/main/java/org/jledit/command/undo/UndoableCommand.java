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

package org.jledit.command.undo;

import org.jledit.command.Command;

public interface UndoableCommand extends Command {

    /**
     * Executes the {@link Command}.
     */
    void doExecute();


    /**
     * Undo the {@link Command}.
     */
    void undo();

    /**
     * Executes the {@link Command} again.
     */
    void redo();
    /**
     * The line the cursor was pointing before the {@link Command} execution.
     * @return
     */
    int getBeforeLine();

    /**
     * The columns the cursor was pointing before the {@link Command} execution.
     * @return
     */
    int getBeforeColumn();

    /**
     * The line the cursor was pointing after the {@link Command} execution.
     * @return
     */
    int getAfterLine();

    /**
     * The columns the cursor was pointing after the {@link Command} execution.
     * @return
     */
    int getAfterColumn();
}
