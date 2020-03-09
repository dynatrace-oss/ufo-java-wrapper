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

import com.dynatrace.ufowrapper.enums.Lanes;
import com.dynatrace.ufowrapper.enums.LedColor;
import com.dynatrace.ufowrapper.util.UfoConstants;

/**
 * Sets specific colors to the lanes, by defining offset, length and color
 */
public class ColumnColorCommand extends BaseCommand {
    private int offset;
    private int length;
    private String color;

    public ColumnColorCommand(Lanes lane, int offset, int length, String color) {
        super(lane);
        this.offset = Math.max(Math.min(offset, UfoConstants.LEDS_ON_LANE - 1), 0);
        this.length = Math.min(length, UfoConstants.LEDS_ON_LANE - offset);
        this.color = color;
    }

    public ColumnColorCommand(Lanes lane, int offset, int length, LedColor color) {
        this(lane, offset, length, color.getHex());
    }

    @Override
    String getCommandText() {
        if (getLane() == Lanes.Logo || color == null) {
            return null;
        }
        return String.format("%s=%d|%d|%s", getLane().getKey(), offset, length, color);
    }
}
