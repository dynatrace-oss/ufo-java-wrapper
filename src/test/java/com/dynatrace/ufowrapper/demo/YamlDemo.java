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

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dynatrace.ufowrapper.PredefinedScenarios;
import com.dynatrace.ufowrapper.UfoCommand;
import com.dynatrace.ufowrapper.UfoScenario;

public class YamlDemo {

    // Replace it with your own ufo IP
    // Visit https://github.com/Dynatrace/ufo-esp32 for instructions on how to determine your IP properly.
    private static String UFO_IP = "192.168.4.1";

    private UfoCommand.UfoCommandBuilder builder;
    private Map<String, UfoScenario> scenarioMap;

    @Before
    public void Startup() {
        scenarioMap = PredefinedScenarios.loadYaml(UfoCommand.class.getClassLoader().getResourceAsStream("demo-scenarios.yaml"));
        builder = UfoCommand.newBuilder()
                .withUfoIp(UFO_IP);
    }

    @After
    public void Teardown() throws InterruptedException {
        Thread.sleep(5000); // just for demo casing, wait 5sec between each test
    }

    @Test
    public void demoYamlItalyRotateFast() {
        // given
        UfoCommand command = builder
                .lanesScenario(scenarioMap.get("it-rotate-fast"))
                .build();

        // when, then
        command.execute();
    }
}