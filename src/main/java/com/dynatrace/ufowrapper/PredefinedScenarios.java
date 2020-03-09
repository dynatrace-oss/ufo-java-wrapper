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

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.yaml.snakeyaml.Yaml;

import com.dynatrace.ufowrapper.enums.LedColor;
import com.dynatrace.ufowrapper.enums.MorphSpeed;
import com.dynatrace.ufowrapper.enums.WhirlDirection;
import com.dynatrace.ufowrapper.enums.WhirlSpeed;
import com.dynatrace.ufowrapper.yaml.ScenarioContent;
import com.dynatrace.ufowrapper.yaml.YamlContent;

/**
 * Predefined scenarios
 */
public final class PredefinedScenarios {

    private static final Logger LOG = Logger.getLogger(PredefinedScenarios.class.getName());

        // PIPELINE
    public static UfoScenario ALL_WHITE = UfoScenario.newBuilder()
                .columns(LedColor.White)
                .fill(true)
                .build();

    public static UfoScenario ALL_BLACK = UfoScenario.newBuilder()
            .columns(LedColor.Black)
            .fill(true)
            .build();

    public static UfoScenario PIPELINE_FAILED = UfoScenario.newBuilder()
            .columns(LedColor.Red)
            .fill(true)
            .build();

    public static UfoScenario PIPELINE_SUCCEEDED = UfoScenario.newBuilder()
            .columns(LedColor.Green)
            .fill(true)
            .build();

    public static UfoScenario PIPELINE_COMPILING = UfoScenario.newBuilder()
            .columns(LedColor.Blue)
            .fill(true)
            .morph(MorphSpeed.Medium)
            .build();

    public static UfoScenario OCCUPIED = UfoScenario.newBuilder()
            .columns(LedColor.Orange)
            .fill(true)
            .build();

    // FLAGS
    public static UfoScenario FLAG_AUSTRIA_ROTATE = UfoScenario.newBuilder()
            .columns(LedColor.Red, LedColor.White, LedColor.Red)
            .columnWidth(3)
            .whirl(WhirlSpeed.Medium)
            .build();

    public static UfoScenario FLAG_ITALY_ROTATE = UfoScenario.newBuilder()
            .columns(LedColor.Green, LedColor.White, LedColor.Red)
            .columnWidth(3)
            .whirl(WhirlSpeed.Medium)
            .build();


    // YAML SUPPORT
    public static Map<String, UfoScenario> loadYaml(InputStream yamlFileInputStream) {
        final Map<String, UfoScenario> result = new HashMap<>();
        YamlContent yamlContent = null;
        try {
            Yaml yaml = new Yaml();
            yamlContent = yaml.load(yamlFileInputStream);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Could not load scenario file", e);
        }
        if (yamlContent != null) {
            for (ScenarioContent content : yamlContent.getScenarios()) {
                final UfoScenario scenario = UfoScenario.newBuilder()
                        .columns(content.colors)
                        .backgroundColor(content.background)
                        .whirl(WhirlSpeed.valueOf(content.getWhirl()), WhirlDirection.valueOf(content.getWhirlDirection()))
                        .morph(MorphSpeed.valueOf(content.getMorph()))
                        .fill(content.isFill())
                        .columnWidth(content.getColumnWidth())
                        .build();
                result.put(content.getName(), scenario);
            }
        }

        return result;
    }
}
