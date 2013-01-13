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
package org.jledit.utils;

import junit.framework.Assert;
import org.junit.Test;

import java.io.File;

public class StringsTest {

    @Test
    public void testTrimPerfectFit() throws Exception {
        String testString = "testString";
        Assert.assertEquals(testString, Strings.tryToTrimToSize(testString, testString.length()));
    }

    @Test
    public void testTrimSmallString() throws Exception {
        String testString = "testString";
        Assert.assertEquals(testString, Strings.tryToTrimToSize(testString, testString.length() + 1));
    }

    @Test
    public void testTrimLargeString() throws Exception {
        String testString = "testString";
        Assert.assertEquals("..", Strings.tryToTrimToSize(testString, testString.length() - 1));
    }

    @Test
    public void testTrimPath() throws Exception {
        String testString = "longfoldername" + File.separator + "longfoldername"+ File.separator + "longfoldername" + File.separator + "file";
        String expectedResult = "longfoldername"  + File.separator + "longfoldername"+ File.separator + ".." + File.separator + "file";
        Assert.assertEquals(expectedResult, Strings.tryToTrimToSize(testString, 48));
        expectedResult = "longfoldername"  + File.separator + ".."+ File.separator + ".." + File.separator + "file";
        Assert.assertEquals(expectedResult, Strings.tryToTrimToSize(testString, 34));
        expectedResult = ".."  + File.separator + ".."+ File.separator + ".." + File.separator + "file";
        Assert.assertEquals(expectedResult, Strings.tryToTrimToSize(testString, 20));

    }
}
