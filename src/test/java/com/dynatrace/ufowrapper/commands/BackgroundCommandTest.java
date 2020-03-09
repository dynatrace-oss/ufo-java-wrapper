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

public class BackgroundCommandTest {
    @Test
    public void testTopLedColor() {
        // given, when
        Command command = new BackgroundCommand(Lanes.Top, LedColor.Red);

        // then
        Assert.assertEquals("top_bg=ff0000", command.toRequestParam());
    }

    @Test
    public void testBottomStringColor() {
        // given, when
        Command command = new BackgroundCommand(Lanes.Bottom, "012345");

        // then
        Assert.assertEquals("bottom_bg=012345", command.toRequestParam());
    }

    @Test
    public void testLogo() {
        // given, when
        Command command = new BackgroundCommand(Lanes.Logo, LedColor.Aqua);

        // then
        Assert.assertNull(command.toRequestParam());
    }
}