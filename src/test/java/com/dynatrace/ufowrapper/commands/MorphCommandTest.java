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
import com.dynatrace.ufowrapper.enums.MorphSpeed;

public class MorphCommandTest {

    @Test
    public void testMorphSlow() {
        // given, when
        Command command = new MorphCommand(Lanes.Top, MorphSpeed.Slow);

        // then
        Assert.assertEquals("top_morph=8|1", command.toRequestParam());
    }

    @Test
    public void testMorphMedium() {
        // given, when
        Command command = new MorphCommand(Lanes.Top, MorphSpeed.Medium);

        // then
        Assert.assertEquals("top_morph=8|5", command.toRequestParam());
    }

    @Test
    public void testMorphFast_Bottom() {
        // given, when
        Command command = new MorphCommand(Lanes.Bottom, MorphSpeed.Fast);

        // then
        Assert.assertEquals("bottom_morph=8|10", command.toRequestParam());
    }

    @Test
    public void testLogo() {
        // given, when
        Command command = new MorphCommand(Lanes.Logo, MorphSpeed.Medium);

        // then
        Assert.assertNull(command.toRequestParam());
    }
}