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

import com.dynatrace.ufowrapper.enums.LedColor;

public class LogoCommandTest {

    @Test
    public void testLogoOneColor() {
        // given, when
        Command command = new LogoCommand(LedColor.White);

        // then
        Assert.assertEquals("logo=ffffff|ffffff|ffffff|ffffff", command.toRequestParam());
    }

    @Test
    public void testLogoTwoColors() {
        // given, when
        Command command = new LogoCommand(LedColor.White, LedColor.Red);

        // then
        Assert.assertEquals("logo=ffffff|ff0000|ffffff|ff0000", command.toRequestParam());
    }

    @Test
    public void testLogoThreeColors() {
        // given, when
        Command command = new LogoCommand(LedColor.White, LedColor.Red, LedColor.Green);

        // then
        Assert.assertEquals("logo=ffffff|ff0000|008000|ffffff", command.toRequestParam());
    }

    @Test
    public void testLogoFourColors() {
        // given, when
        Command command = new LogoCommand(LedColor.White, LedColor.Red, LedColor.Green, LedColor.Blue);

        // then
        Assert.assertEquals("logo=ffffff|ff0000|008000|0000ff", command.toRequestParam());
    }
}