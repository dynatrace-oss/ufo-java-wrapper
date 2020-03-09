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

public class InitCommandTest {
    @Test
    public void testTop() {
        // given, when
        BaseCommand command = new InitCommand(Lanes.Top);

        // then
        Assert.assertEquals("top_init", command.getCommandText());
    }

    @Test
    public void testBottom() {
        // given, when
        BaseCommand command = new InitCommand(Lanes.Bottom);

        // then
        Assert.assertEquals("bottom_init", command.getCommandText());
    }

    @Test
    public void testLogo() {
        // given, when
        BaseCommand command = new InitCommand(Lanes.Logo);

        // then
        Assert.assertEquals("logo_reset", command.getCommandText());
    }
}