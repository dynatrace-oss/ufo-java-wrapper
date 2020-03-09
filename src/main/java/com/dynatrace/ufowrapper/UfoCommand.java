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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.dynatrace.ufowrapper.commands.BackgroundCommand;
import com.dynatrace.ufowrapper.commands.ColumnColorBulkCommand;
import com.dynatrace.ufowrapper.commands.ColumnColorCommand;
import com.dynatrace.ufowrapper.commands.Command;
import com.dynatrace.ufowrapper.commands.InitCommand;
import com.dynatrace.ufowrapper.commands.LogoCommand;
import com.dynatrace.ufowrapper.commands.MorphCommand;
import com.dynatrace.ufowrapper.commands.WhirlCommand;
import com.dynatrace.ufowrapper.enums.Lanes;
import com.dynatrace.ufowrapper.enums.LedColor;
import com.dynatrace.ufowrapper.enums.MorphSpeed;
import com.dynatrace.ufowrapper.enums.WhirlDirection;
import com.dynatrace.ufowrapper.enums.WhirlSpeed;
import com.dynatrace.ufowrapper.util.ColorHelper;
import com.dynatrace.ufowrapper.util.UfoConstants;

/**
 * (Central) Ufo command and builder class
 */
public class UfoCommand {

    private static final Logger LOG = Logger.getLogger(UfoCommand.class.getName());

    private final String ip;
    private final List<Command> commands;

    UfoCommand(String ip, List<Command> commands) {
        this.ip = ip;
        this.commands = commands;
    }

    public static UfoCommandBuilder newBuilder() {
        return new UfoCommand.UfoCommandBuilder();
    }

    public void describeRequest() {
        final String command = getRequestedUri();
        LOG.info(command);
    }

    public void execute() {
        final String command = getCommand();
        LOG.info(command);

        try {
            final String uri = getRequestedUri();
            final HttpURLConnection connection = (HttpURLConnection) new URL(uri).openConnection();
            LOG.info(connection.getResponseMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*package*/ String getRequestedUri() {
        final String command = getCommand();
        return String.format("http://%s/api?%s", ip, command);
    }

    /*package*/ String getCommand() {
        return commands.stream()
                .map(Command::toRequestParam)
                .filter(Objects::nonNull)
                .collect(Collectors.joining("&"));
    }

    public static class UfoCommandBuilder {

        private String ufoIp = UfoConstants.DEFAULT_IP_ADDRESS;
        private List<Command> commands = new ArrayList<>();

        /*
         * Builder pattern
         */

        public UfoCommand build() {
            return new UfoCommand(this.ufoIp, this.commands);
        }

        /*
         * Ufo
         */

        public UfoCommandBuilder withUfoIp(String ip) {
            ufoIp = ip;
            return this;
        }

        /*
         * Whirl
         */

        public UfoCommandBuilder topWhirl(WhirlSpeed speed) {
            commands.add(new WhirlCommand(Lanes.Top, speed));
            return this;
        }

        public UfoCommandBuilder topWhirl(WhirlSpeed speed, WhirlDirection direction) {
            commands.add(new WhirlCommand(Lanes.Top, speed, direction));
            return this;
        }

        public UfoCommandBuilder bottomWhirl(WhirlSpeed speed) {
            commands.add(new WhirlCommand(Lanes.Bottom, speed));
            return this;
        }

        public UfoCommandBuilder bottomWhirl(WhirlSpeed speed, WhirlDirection direction) {
            commands.add(new WhirlCommand(Lanes.Bottom, speed, direction));
            return this;
        }

        public UfoCommandBuilder lanesWhirl(WhirlSpeed speed) {
            topWhirl(speed);
            bottomWhirl(speed);
            return this;
        }

        public UfoCommandBuilder lanesWhirl(WhirlSpeed speed, WhirlDirection direction) {
            topWhirl(speed, direction);
            bottomWhirl(speed, direction);
            return this;
        }

        public UfoCommandBuilder lanesWhirlOpposite(WhirlSpeed speed) {
            topWhirl(speed);
            bottomWhirl(speed, WhirlDirection.CounterClockWise);
            return this;
        }

        /*
         * Morph
         */

        public UfoCommandBuilder topMorph(MorphSpeed speed) {
            commands.add(new MorphCommand(Lanes.Top, speed));
            return this;
        }

        public UfoCommandBuilder bottomMorph(MorphSpeed speed) {
            commands.add(new MorphCommand(Lanes.Bottom, speed));
            return this;
        }

        public UfoCommandBuilder lanesMorph(MorphSpeed speed) {
            topMorph(speed);
            bottomMorph(speed);
            return this;
        }

        /*
         * Background
         */

        public UfoCommandBuilder topBackground(String color) {
            commands.add(new BackgroundCommand(Lanes.Top, color));
            return this;
        }

        public UfoCommandBuilder topBackground(LedColor color) {
            commands.add(new BackgroundCommand(Lanes.Top, color.getHex()));
            return this;
        }

        public UfoCommandBuilder bottomBackground(String color) {
            commands.add(new BackgroundCommand(Lanes.Bottom, color));
            return this;
        }

        public UfoCommandBuilder bottomBackground(LedColor color) {
            commands.add(new BackgroundCommand(Lanes.Bottom, color.getHex()));
            return this;
        }

        public UfoCommandBuilder lanesBackground(String color) {
            topBackground(color);
            bottomBackground(color);
            return this;
        }

        public UfoCommandBuilder lanesBackground(LedColor color) {
            topBackground(color);
            bottomBackground(color);
            return this;
        }

        /*
         * Fill
         */

        public UfoCommandBuilder topFill(LedColor color) {
            commands.add(new ColumnColorBulkCommand(Lanes.Top, 1, true, color));
            return this;
        }

        public UfoCommandBuilder topFill(String color) {
            commands.add(new ColumnColorBulkCommand(Lanes.Top, 1, true, color));
            return this;
        }

        public UfoCommandBuilder bottomFill(LedColor color) {
            commands.add(new ColumnColorBulkCommand(Lanes.Bottom, 1, true, color));
            return this;
        }

        public UfoCommandBuilder bottomFill(String color) {
            commands.add(new ColumnColorBulkCommand(Lanes.Bottom, 1, true, color));
            return this;
        }

        /*
         * Colors
         */

        public UfoCommandBuilder topColor(int offset, int length, String color) {
            commands.add(new ColumnColorCommand(Lanes.Top, offset, length, color));
            return this;
        }

        public UfoCommandBuilder topColor(int offset, int length, LedColor color) {
            commands.add(new ColumnColorCommand(Lanes.Top, offset, length, color));
            return this;
        }

        public UfoCommandBuilder bottomColor(int offset, int length, String color) {
            commands.add(new ColumnColorCommand(Lanes.Bottom, offset, length, color));
            return this;
        }

        public UfoCommandBuilder bottomColor(int offset, int length, LedColor color) {
            commands.add(new ColumnColorCommand(Lanes.Bottom, offset, length, color));
            return this;
        }

        public UfoCommandBuilder topColors(int columnWidth, String... colors) {
            commands.add(new ColumnColorBulkCommand(Lanes.Top, columnWidth, false, colors));
            return this;
        }

        public UfoCommandBuilder topColors(int columnWidth, LedColor... colors) {
            commands.add(new ColumnColorBulkCommand(Lanes.Top, columnWidth, false, colors));
            return this;
        }

        public UfoCommandBuilder bottomColors(int columnWidth, String... colors) {
            commands.add(new ColumnColorBulkCommand(Lanes.Bottom, columnWidth, false, colors));
            return this;
        }

        public UfoCommandBuilder bottomColors(int columnWidth, LedColor... colors) {
            commands.add(new ColumnColorBulkCommand(Lanes.Bottom, columnWidth, false, colors));
            return this;
        }

        public UfoCommandBuilder lanesColors(int columnWidth, LedColor... colors) {
            topColors(columnWidth, colors);
            bottomColors(columnWidth, colors);
            return this;
        }

        public UfoCommandBuilder lanesColors(int columnWidth, String... colors) {
            topColors(columnWidth, colors);
            bottomColors(columnWidth, colors);
            return this;
        }

        /*
         * Random
         */

        public UfoCommandBuilder topRandom() {
            random(Lanes.Top);
            return this;
        }

        public UfoCommandBuilder bottomRandom() {
            random(Lanes.Bottom);
            return this;
        }

        public UfoCommandBuilder random() {
            reset();
            topRandom();
            bottomRandom();
            topWhirl(WhirlSpeed.Medium);
            bottomWhirl(WhirlSpeed.Medium, WhirlDirection.CounterClockWise);
            return this;
        }

        /*
         * Reset
         */

        public UfoCommandBuilder reset() {
            topReset();
            bottomReset();
            logoReset();
            return this;
        }

        public UfoCommandBuilder topReset() {
            commands.add(new InitCommand(Lanes.Top));
            return this;
        }

        public UfoCommandBuilder bottomReset() {
            commands.add(new InitCommand(Lanes.Bottom));
            return this;
        }

        public UfoCommandBuilder logoReset() {
            commands.add(new InitCommand(Lanes.Logo));
            return this;
        }

        public UfoCommandBuilder lanesReset() {
            topReset();
            bottomReset();
            return this;
        }

        /*
         * Logo
         */

        public UfoCommandBuilder logo(LedColor... colors) {
            commands.add(new LogoCommand(colors));
            return this;
        }

        public UfoCommandBuilder logo(String... colors) {
            commands.add(new LogoCommand(colors));
            return this;
        }

        public UfoCommandBuilder logo(UfoScenario scenario) {
            commands.add(new LogoCommand(scenario.getColumnColors()));
            return this;
        }

        /*
         * Scenarios
         */

        public UfoCommandBuilder topScenario(UfoScenario scenario) {
            useScenario(Lanes.Top, scenario);
            return this;
        }

        public UfoCommandBuilder bottomScenario(UfoScenario scenario) {
            useScenario(Lanes.Bottom, scenario);
            return this;
        }

        public UfoCommandBuilder lanesScenario(UfoScenario scenario) {
            useScenario(Lanes.Top, scenario);
            useScenario(Lanes.Bottom, scenario);
            return this;
        }

        private void useScenario(Lanes lane, UfoScenario scenario) {
            // init
            commands.add(new InitCommand(lane));

            // background
            if (scenario.getBackgroundColor() != null) {
                commands.add(new BackgroundCommand(lane, scenario.getBackgroundColor()));
            }

            // foreground
            commands.add(
                    new ColumnColorBulkCommand(lane, scenario.getColumnWidth(), scenario.getFill(), scenario.getColumnColors()));

            // whirl
            if (scenario.getWhirlSpeed() != WhirlSpeed.None) {
                commands.add(new WhirlCommand(lane, scenario.getWhirlSpeed(), scenario.getWhirlDirection()));
            }

            // morph
            if (scenario.getMorphSpeed() != MorphSpeed.None) {
                commands.add(new MorphCommand(lane, scenario.getMorphSpeed()));
            }
        }

        /*
         * Random
         */

        private void random(Lanes lane) {
            for (int i = 0; i < UfoConstants.LEDS_ON_LANE; i++) {
                commands.add(new ColumnColorCommand(lane, i, 1, ColorHelper.getRandomHexColor()));
            }
        }
    }
}
