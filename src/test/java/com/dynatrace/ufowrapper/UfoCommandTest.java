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

package com.dynatrace.ufowrapper;

import org.junit.Assert;
import org.junit.Test;

import com.dynatrace.ufowrapper.enums.LedColor;
import com.dynatrace.ufowrapper.enums.MorphSpeed;
import com.dynatrace.ufowrapper.enums.WhirlDirection;
import com.dynatrace.ufowrapper.enums.WhirlSpeed;

public class UfoCommandTest {

    @Test
    public void testRequestedUriDefault() {
        // given, when
        String result = UfoCommand.newBuilder()
                .logoReset()
                .build()
                .getRequestedUri();

        // then
        Assert.assertEquals("http://192.168.4.1/api?logo_reset", result);
    }

    @Test
    public void testRequestedUriSet() {
        // given, when
        String result = UfoCommand.newBuilder()
                .withUfoIp("1.1.1.1")
                .logoReset()
                .build()
                .getRequestedUri();

        // then
        Assert.assertEquals("http://1.1.1.1/api?logo_reset", result);
    }

    @Test
    public void testReset() {
        // given, when
        String result = UfoCommand.newBuilder()
                .reset()
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_init&bottom_init&logo_reset", result);
    }

    @Test
    public void testTopBackground() {
        // given, when
        String result = UfoCommand.newBuilder()
                .topBackground(LedColor.Red)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_bg=ff0000", result);
    }

    @Test
    public void testBottomBackground() {
        // given, when
        String result = UfoCommand.newBuilder()
                .bottomBackground(LedColor.Red)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("bottom_bg=ff0000", result);
    }

    @Test
    public void testLanesBackground() {
        // given, when
        String result = UfoCommand.newBuilder()
                .lanesBackground(LedColor.Red)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_bg=ff0000&bottom_bg=ff0000", result);
    }

    @Test
    public void testTopWhirlFast() {
        // given, when
        String result = UfoCommand.newBuilder()
                .topWhirl(WhirlSpeed.UltraFast)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_whirl=255", result);
    }

    @Test
    public void testBottomWhirlSlowCounterClockWise() {
        // given, when
        String result = UfoCommand.newBuilder()
                .bottomWhirl(WhirlSpeed.Slow, WhirlDirection.CounterClockWise)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("bottom_whirl=110|ccw", result);
    }

    @Test
    public void testTopMorphSlow() {
        // given, when
        String result = UfoCommand.newBuilder()
                .topMorph(MorphSpeed.Slow)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_morph=8|1", result);
    }

    @Test
    public void testTopMorphMedium() {
        // given, when
        String result = UfoCommand.newBuilder()
                .topMorph(MorphSpeed.Medium)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_morph=8|5", result);
    }

    @Test
    public void testBottomMorphFast() {
        // given, when
        String result = UfoCommand.newBuilder()
                .bottomMorph(MorphSpeed.Fast)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("bottom_morph=8|10", result);
    }

    @Test
    public void testLanesMorph() {
        // given, when
        String result = UfoCommand.newBuilder()
                .lanesMorph(MorphSpeed.Fast)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_morph=8|10&bottom_morph=8|10", result);
    }

    @Test
    public void testTopColors() {
        // given, when
        String result = UfoCommand.newBuilder()
                .topColors(2, LedColor.Green)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top=0|2|008000", result);
    }
}