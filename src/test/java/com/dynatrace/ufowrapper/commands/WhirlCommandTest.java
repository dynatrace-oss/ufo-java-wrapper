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
import com.dynatrace.ufowrapper.enums.WhirlDirection;
import com.dynatrace.ufowrapper.enums.WhirlSpeed;

public class WhirlCommandTest {

    @Test
    public void testWhirlSlow() {
        // given, when
        Command command = new WhirlCommand(Lanes.Top, WhirlSpeed.Slow);

        // then
        Assert.assertEquals("top_whirl=110", command.toRequestParam());
    }

    @Test
    public void testWhirlMedium() {
        // given, when
        Command command = new WhirlCommand(Lanes.Top, WhirlSpeed.Medium, WhirlDirection.ClockWise);

        // then
        Assert.assertEquals("top_whirl=200", command.toRequestParam());
    }

    @Test
    public void testWhirlFast_Bottom_CounterClockWise() {
        // given, when
        Command command = new WhirlCommand(Lanes.Bottom, WhirlSpeed.UltraFast, WhirlDirection.CounterClockWise);

        // then
        Assert.assertEquals("bottom_whirl=255|ccw", command.toRequestParam());
    }


    @Test
    public void testLogo() {
        // given, when
        Command command = new WhirlCommand(Lanes.Logo, WhirlSpeed.Medium);

        // then
        Assert.assertNull(command.toRequestParam());
    }
}