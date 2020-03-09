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

package com.dynatrace.ufowrapper.yaml;

import com.dynatrace.ufowrapper.enums.MorphSpeed;
import com.dynatrace.ufowrapper.enums.WhirlDirection;
import com.dynatrace.ufowrapper.enums.WhirlSpeed;

/**
 * Intermediate class used for loading scenario from yaml.
 */
public class ScenarioContent {
    private String name;
    public String[] colors;
    public String background;
    private int columnWidth = 1;
    public boolean fill = false;
    private String whirl = WhirlSpeed.None.name();
    private String whirlDirection = WhirlDirection.CounterClockWise.name();
    private String morph = MorphSpeed.None.name();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colorPattern) {
        this.colors = colorPattern;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    public void setColumnWidth(int columnWidth) {
        this.columnWidth = columnWidth;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public String getWhirl() {
        return whirl;
    }

    public void setWhirl(String whirl) {
        this.whirl = whirl;
    }

    public String getWhirlDirection() {
        return whirlDirection;
    }

    public void setWhirlDirection(String whirlDirection) {
        this.whirlDirection = whirlDirection;
    }

    public String getMorph() {
        return morph;
    }

    public void setMorph(String morph) {
        this.morph = morph;
    }
}
