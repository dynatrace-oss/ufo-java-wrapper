/*
 * Copyright 2020 Dynatrace LLC
 *
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

package com.dynatrace.ufowrapper.commands;

import org.junit.Assert;
import org.junit.Test;

import com.dynatrace.ufowrapper.enums.Lanes;
import com.dynatrace.ufowrapper.enums.LedColor;

public class ColumnColorCommandTest {

    @Test
    public void testTopThreeLedsRed() {
        // given, when
        Command command = new ColumnColorCommand(Lanes.Top, 0, 3, LedColor.Red.getHex());

        // then
        Assert.assertEquals("top=0|3|ff0000", command.toRequestParam());
    }

    @Test
    public void testBottomSecondLedBlue() {
        // given, when
        Command command = new ColumnColorCommand(Lanes.Bottom, 1,1, LedColor.Blue.getHex());

        // then
        Assert.assertEquals("bottom=1|1|0000ff", command.toRequestParam());
    }

    @Test
    public void testLowerBoundary() {
        // given, when
        Command command = new ColumnColorCommand(Lanes.Top, -1, 10, LedColor.Blue.getHex());

        // then
        Assert.assertEquals("top=0|10|0000ff", command.toRequestParam());
    }

    @Test
    public void testUpperBoundary() {
        // given, when
        Command command = new ColumnColorCommand(Lanes.Top, 10, 10, LedColor.Blue.getHex());

        // then
        Assert.assertEquals("top=10|5|0000ff", command.toRequestParam());
    }

    @Test
    public void testColorNull() {
        // given, when
        Command command = new ColumnColorCommand(Lanes.Top, 10, 10, (String)null);

        // then
        Assert.assertNull(command.toRequestParam());
    }

    @Test
    public void testLogo() {
        // given, when
        Command command = new ColumnColorCommand(Lanes.Logo, 0, 2, LedColor.Aqua.getHex());

        // then
        Assert.assertNull(command.toRequestParam());
    }
}