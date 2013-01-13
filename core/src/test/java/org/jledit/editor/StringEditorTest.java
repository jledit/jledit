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

package org.jledit.editor;


import junit.framework.Assert;
import org.jledit.Editor;
import org.jledit.StringEditor;
import org.jledit.utils.Resources;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;


public class StringEditorTest {

    @Test
    public void testMoveToEnd() throws IOException {
        Editor<String> editor = createEditor();
        editor.move(9, 27);
    }

    @Test
    public void testMoveBeyoundColumnBounds() throws IOException {
        Editor<String> editor = createEditor();
        editor.move(5, 100);
        Assert.assertEquals(35, editor.getColumn());
        Assert.assertEquals("X", editor.backspace());

        editor.move(8, 28);
        Assert.assertEquals(26, editor.getColumn());
        Assert.assertEquals("e", editor.backspace());
    }

    @Test
    public void testDelete() throws IOException {
        Editor<String> editor = createEditor();
        Assert.assertTrue(editor.getContent().contains("This character should be deleted:X"));
        editor.move(5, 34);
        Assert.assertEquals("X", editor.delete());
        editor.put("Y");
        Assert.assertTrue(editor.getContent().contains("This character should be deleted:Y"));
        editor.move(5, 1);
        Assert.assertEquals("T", editor.delete());
    }

    @Test
    public void testBackspace() throws IOException {
        Editor<String> editor = createEditor();
        Assert.assertTrue(editor.getContent().contains("The X character should be deleted here:XY"));
        editor.move(6, 41);
        Assert.assertEquals("X", editor.backspace());
        editor.put("A");
        Assert.assertTrue(editor.getContent().contains("The X character should be deleted here:AY"));
        editor.move(5, 2);
        Assert.assertEquals("T", editor.backspace());
    }

    @Test
    public void testMergeLines() throws IOException {
        Editor<String> editor = createEditor();
        editor.move(8, 1);
        editor.mergeLine();
        Assert.assertTrue(editor.getContent().contains("These two lines should bemerged into a single line."));
    }

    @Test
    public void testGetLine() throws IOException {
        Editor<String> editor = createEditor();
        String line = editor.getContent(1);
        Assert.assertEquals("Simple Text File", line);
        line = editor.getContent(2);
        Assert.assertEquals("----------------", line);
        line = editor.getContent(3);
        Assert.assertEquals("This is a simple text file, with a couple of lines used for testing.", line);
    }

    public StringEditor createEditor() throws IOException {
        return new StringEditor(Resources.toString(getClass().getResource("/testfile.txt"), Charset.forName("UTF-8")));
    }
}
