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

public class UfoScenarioTest {

    @Test
    public void testCommandSingleLedTopLane() {
        // given
        UfoScenario scenario = UfoScenario.newBuilder().columns(LedColor.Green).build();

        // when
        String result = UfoCommand.newBuilder()
                .topScenario(scenario)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_init&top=0|1|008000", result);
    }

    @Test
    public void testLanesWhirl() {
        // given, when
        UfoCommand command = UfoCommand.newBuilder()
                .lanesWhirl(WhirlSpeed.UltraFast)
                .build();

        // then
        Assert.assertEquals("top_whirl=255&bottom_whirl=255", command.getCommand());
    }

    @Test
    public void testLanesWhirlCounterClockWise() {
        // given, when
        UfoCommand command = UfoCommand.newBuilder()
                .lanesWhirl(WhirlSpeed.UltraFast, WhirlDirection.CounterClockWise)
                .build();

        // then
        Assert.assertEquals("top_whirl=255|ccw&bottom_whirl=255|ccw", command.getCommand());
    }

    @Test
    public void testLanesWhirlOpposite() {
        // given, when
        UfoCommand command = UfoCommand.newBuilder()
                .lanesWhirlOpposite(WhirlSpeed.UltraFast)
                .build();

        // then
        Assert.assertEquals("top_whirl=255&bottom_whirl=255|ccw", command.getCommand());
    }

    @Test
    public void testLanesBackground() {
        // given, when
        UfoCommand command = UfoCommand.newBuilder()
                .lanesBackground("abcdef")
                .build();

        // then
        Assert.assertEquals("top_bg=abcdef&bottom_bg=abcdef", command.getCommand());
    }

    @Test
    public void testTopFill() {
        // given, when
        UfoCommand command = UfoCommand.newBuilder()
                .topFill("abcdef")
                .build();

        // then
        Assert.assertEquals("top=0|15|abcdef", command.getCommand());
    }

    @Test
    public void testBottomFill() {
        // given, when
        UfoCommand command = UfoCommand.newBuilder()
                .bottomFill("abcdef")
                .build();

        // then
        Assert.assertEquals("bottom=0|15|abcdef", command.getCommand());
    }

    @Test
    public void testTopColor() {
        // given, when
        UfoCommand command = UfoCommand.newBuilder()
                .topColor(1, 2, "abcdef")
                .build();

        // then
        Assert.assertEquals("top=1|2|abcdef", command.getCommand());
    }

    @Test
    public void testTopColorLedColor() {
        // given, when
        UfoCommand command = UfoCommand.newBuilder()
                .topColor(1, 2, LedColor.Blue)
                .build();

        // then
        Assert.assertEquals("top=1|2|0000ff", command.getCommand());
    }

    @Test
    public void testBottomColor() {
        // given, when
        UfoCommand command = UfoCommand.newBuilder()
                .bottomColor(1, 2, "abcdef")
                .build();

        // then
        Assert.assertEquals("bottom=1|2|abcdef", command.getCommand());
    }

    @Test
    public void testBottomColorLedColor() {
        // given, when
        UfoCommand command = UfoCommand.newBuilder()
                .bottomColor(1, 2, LedColor.Blue)
                .build();

        // then
        Assert.assertEquals("bottom=1|2|0000ff", command.getCommand());
    }

    @Test
    public void testLanesColors() {
        // given, when
        UfoCommand command = UfoCommand.newBuilder()
                .lanesColors(2, "abcdef", "ffffff")
                .build();

        // then
        Assert.assertEquals("top=0|2|abcdef&top=2|2|ffffff&bottom=0|2|abcdef&bottom=2|2|ffffff", command.getCommand());
    }

    @Test
    public void testLanesColorsLedColors() {
        // given, when
        UfoCommand command = UfoCommand.newBuilder()
                .lanesColors(2, LedColor.Blue, LedColor.Aqua)
                .build();

        // then
        Assert.assertEquals("top=0|2|0000ff&top=2|2|00ffff&bottom=0|2|0000ff&bottom=2|2|00ffff", command.getCommand());
    }

    @Test
    public void testRandom() {
        // given, when
        UfoCommand command = UfoCommand.newBuilder()
                .random()
                .build();

        // then
        Assert.assertEquals(571, command.getCommand().length());
    }

    @Test
    public void testCommandSingleLedBothLanes() {
        // given
        UfoScenario scenario = UfoScenario.newBuilder().columns(LedColor.Green).build();

        // when
        String result = UfoCommand.newBuilder()
                .lanesScenario(scenario)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_init&top=0|1|008000&bottom_init&bottom=0|1|008000", result);
    }

    @Test
    public void testCommandViaScenarioSingleLedBottomLane() {
        // given
        UfoScenario scenario = UfoScenario.newBuilder().columns(LedColor.Green).build();

        // when
        String result = UfoCommand.newBuilder()
                .bottomScenario(scenario)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("bottom_init&bottom=0|1|008000", result);
    }

    @Test
    public void testCommandViaScenarioSingleColorFilled() {
        // given
        UfoScenario scenario = UfoScenario.newBuilder().columns("Green 15").build();

        // when
        String result = UfoCommand.newBuilder()
                .topScenario(scenario)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_init&top=0|15|008000", result);
    }

    @Test
    public void testCommandViaScenarioThreeColorsFixed() {
        // given
        UfoScenario scenario = UfoScenario.newBuilder()
                .columns(LedColor.Red, LedColor.Orange, LedColor.Green)
                .build();

        // when
        String result = UfoCommand.newBuilder()
                .topScenario(scenario)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_init&top=0|1|ff0000&top=1|1|ff8300&top=2|1|008000", result);
    }
    @Test
    public void testCommandViaScenarioThreeColorsFixedDouble() {
        // given
        UfoScenario scenario = UfoScenario.newBuilder()
                .columns(LedColor.Red, LedColor.Orange, LedColor.Green)
                .columnWidth(2)
                .build();

        // when
        String result = UfoCommand.newBuilder()
                .topScenario(scenario)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_init&top=0|2|ff0000&top=2|2|ff8300&top=4|2|008000", result);
    }

    @Test
    public void testCommandViaScenarioThreeColorsFixedTripple() {
        // given
        UfoScenario scenario = UfoScenario.newBuilder()
                .columns(LedColor.Red, LedColor.Orange, LedColor.Green)
                .columnWidth(3)
                .build();

        // when
        String result = UfoCommand.newBuilder()
                .topScenario(scenario)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_init&top=0|3|ff0000&top=3|3|ff8300&top=6|3|008000", result);
    }

    @Test
    public void testCommandViaScenarioDuplicateColorGetsSimplified() {
        // given
        UfoScenario scenario = UfoScenario.newBuilder().columns(LedColor.Red, LedColor.Red).build();

        // when
        String result = UfoCommand.newBuilder()
                .topScenario(scenario)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_init&top=0|2|ff0000", result);
    }

    @Test
    public void testCommandViaScenarioTwoColorsFilled() {
        // given
        UfoScenario scenario = UfoScenario.newBuilder()
                .columns(LedColor.Red, LedColor.Green)
                .fill(true)
                .build();

        // when
        String result = UfoCommand.newBuilder()
                .topScenario(scenario)
                .build()
                .getCommand();

        // then
        Assert.assertEquals(
                "top_init&top=0|1|ff0000&top=1|1|008000&top=2|1|ff0000&top=3|1|008000&top=4|1|ff0000&top=5|1|008000&top=6|1|ff0000&top=7|1|008000&top=8|1|ff0000&top=9|1|008000&top=10|1|ff0000&top=11|1|008000&top=12|1|ff0000&top=13|1|008000&top=14|1|ff0000",
                result);
    }


    @Test
    public void testCommandViaScenarioMorphFast() {
        // given
        UfoScenario scenario = UfoScenario.newBuilder().columns(LedColor.Red)
                .morph(MorphSpeed.Fast)
                .build();

        // when
        String result = UfoCommand.newBuilder()
                .topScenario(scenario)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_init&top=0|1|ff0000&top_morph=8|10", result);
    }

    @Test
    public void testCommandViaScenarioWhirlFast() {
        // given
        UfoScenario scenario = UfoScenario.newBuilder().columns(LedColor.Red)
                .whirl(WhirlSpeed.UltraFast)
                .build();
        // when
        String result = UfoCommand.newBuilder()
                .topScenario(scenario)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_init&top=0|1|ff0000&top_whirl=255", result);
    }

    @Test
    public void testCommandViaScenarioWhirlFastCounterClock() {
        // given
        UfoScenario scenario = UfoScenario.newBuilder().columns(LedColor.Red)
                .whirl(WhirlSpeed.UltraFast, WhirlDirection.CounterClockWise)
                .build();
        // when
        String result = UfoCommand.newBuilder()
                .topScenario(scenario)
                .build()
                .getCommand();

        // then
        Assert.assertEquals("top_init&top=0|1|ff0000&top_whirl=255|ccw", result);
    }

}