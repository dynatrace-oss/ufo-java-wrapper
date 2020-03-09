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

package com.dynatrace.ufowrapper.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dynatrace.ufowrapper.PredefinedScenarios;
import com.dynatrace.ufowrapper.UfoCommand;
import com.dynatrace.ufowrapper.UfoScenario;
import com.dynatrace.ufowrapper.enums.LedColor;
import com.dynatrace.ufowrapper.enums.WhirlDirection;
import com.dynatrace.ufowrapper.enums.WhirlSpeed;

public class Demo {

    // Replace it with your own ufo IP
    // Visit https://github.com/Dynatrace/ufo-esp32 for instructions on how to determine your IP properly.
    private static String UFO_IP = "192.168.4.1";

    private UfoCommand.UfoCommandBuilder builder;

    @Before
    public void Startup() {
        builder = UfoCommand.newBuilder()
                .withUfoIp(UFO_IP);
    }

    @After
    public void Teardown() throws InterruptedException {
        Thread.sleep(5000); // just for demo casing, wait 5sec between each test
    }

    @Test
    public void random() {
        // given
        UfoCommand command = builder.topRandom().topWhirl(WhirlSpeed.Medium, WhirlDirection.ClockWise)
                .bottomRandom().bottomWhirl(WhirlSpeed.Medium, WhirlDirection.CounterClockWise).build();

        // when, then
        command.execute();
    }

    @Test
    public void pink() {
        // given
        UfoCommand command = builder
                .reset()
                .topFill(LedColor.Pink)
                .bottomFill(LedColor.Pink)
                .build();

        // when, then
        command.execute();
    }

    @Test
    public void austria() {
        // given
        UfoCommand command = builder
                .lanesScenario(PredefinedScenarios.FLAG_AUSTRIA_ROTATE)
                .build();

        // when, then
        command.execute();
    }

    @Test
    public void italy() {
        // given
        UfoCommand command = builder
                .lanesScenario(PredefinedScenarios.FLAG_ITALY_ROTATE)
                .build();

        // when, then
        command.execute();
    }

    @Test
    public void dt() {
        // given
        UfoCommand command = builder
                .lanesScenario(UfoScenario.newBuilder()
                        .columns(LedColor.DtGreen, LedColor.DtPurple, LedColor.DtBlue, LedColor.LightGreen)
                        .columnWidth(4)
                        .whirl(WhirlSpeed.Medium)
                        .build())
                .logoReset()
                .build();

        // when, then
        command.execute();
    }

    @Test
    public void shine() {
        // given
        UfoCommand command = builder
                .reset()
                .topFill(LedColor.White)
                .bottomFill(LedColor.White)
                .logo(LedColor.White)
                .build();

        // when, then
        command.execute();
    }

    @Test
    public void readMe() {
        // given
        UfoCommand command = builder
                .lanesScenario(PredefinedScenarios.PIPELINE_COMPILING)
                .build();

        // when, then
        command.execute();
    }
}