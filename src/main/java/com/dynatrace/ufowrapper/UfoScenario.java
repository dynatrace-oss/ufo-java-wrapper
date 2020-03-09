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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.dynatrace.ufowrapper.enums.LedColor;
import com.dynatrace.ufowrapper.enums.MorphSpeed;
import com.dynatrace.ufowrapper.enums.WhirlDirection;
import com.dynatrace.ufowrapper.enums.WhirlSpeed;
import com.dynatrace.ufowrapper.util.ColorHelper;

/**
 * (Central) Ufo scenario and builder class
 */
public class UfoScenario {
    private List<String> columnColors;
    private int columnWidth;
    private String backgroundColor;
    private WhirlSpeed whirlSpeed;
    private WhirlDirection whirlDirection;
    private MorphSpeed morphSpeed;
    private boolean fill;

    private UfoScenario(UfoScenarioBuilder builder) {
        this.columnColors = builder.columns;
        this.columnWidth = builder.columnWidth;
        this.backgroundColor = builder.backgroundColor;
        this.whirlSpeed = builder.whirlSpeed;
        this.whirlDirection = builder.whirlDirection;
        this.morphSpeed = builder.morphSpeed;
        this.fill = builder.fill;
    }

    public List<String> getColumnColors() {
        return columnColors;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public WhirlSpeed getWhirlSpeed() {
        return whirlSpeed;
    }

    public boolean getFill() {
        return fill;
    }

    public WhirlDirection getWhirlDirection() {
        return whirlDirection;
    }

    public MorphSpeed getMorphSpeed() {
        return morphSpeed;
    }

    public static UfoScenarioBuilder newBuilder() {
        return new UfoScenarioBuilder();
    }


    public static class UfoScenarioBuilder {
        private List<String> columns = null;
        private int columnWidth = 1;
        private String backgroundColor = null;
        private WhirlSpeed whirlSpeed = WhirlSpeed.None;
        private WhirlDirection whirlDirection = WhirlDirection.ClockWise;
        private MorphSpeed morphSpeed = MorphSpeed.None;
        private boolean fill = false;

        public UfoScenarioBuilder columns(LedColor... colors) {
            this.columns = Arrays.stream(colors).map(LedColor::getHex).collect(Collectors.toList());
            return this;
        }

        public UfoScenarioBuilder columns(String... colors) {
            this.columns = ColorHelper.getHexColors(Arrays.asList(colors));
            return this;
        }

        public UfoScenarioBuilder columnWidth(int columnWidth) {
            this.columnWidth = columnWidth;
            return this;
        }

        public UfoScenarioBuilder backgroundColor(LedColor backgroundColor) {
            this.backgroundColor = backgroundColor.getHex();
            return this;
        }

        public UfoScenarioBuilder backgroundColor(String backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public UfoScenarioBuilder whirl(WhirlSpeed speed) {
            whirl(speed, WhirlDirection.ClockWise);
            return this;
        }

        public UfoScenarioBuilder morph(MorphSpeed speed) {
            this.morphSpeed = speed;
            return this;
        }

        public UfoScenarioBuilder fill(boolean fill) {
            this.fill = fill;
            return this;
        }

        public UfoScenarioBuilder whirl(WhirlSpeed speed, WhirlDirection direction) {
            this.whirlSpeed = speed;
            this.whirlDirection = direction;

            return this;
        }

        public UfoScenario build() {
            return new UfoScenario(this);
        }
    }
}
