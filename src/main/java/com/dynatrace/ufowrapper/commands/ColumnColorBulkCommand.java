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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.dynatrace.ufowrapper.enums.Lanes;
import com.dynatrace.ufowrapper.enums.LedColor;
import com.dynatrace.ufowrapper.util.ColorHelper;
import com.dynatrace.ufowrapper.util.UfoConstants;

/**
 * (Nonnative) bulk command in order to set sequences of colors set to the lane
 * @see com.dynatrace.ufowrapper.commands.ColumnColorCommand
 */
public class ColumnColorBulkCommand extends BaseCommand {
    private int columnWidth;
    private List<String> colors;
    private boolean fill;

    public ColumnColorBulkCommand(Lanes lane, int columnWidth, boolean fill, List<String> columnColors) {
        super(lane);
        this.colors = ColorHelper.getHexColors(columnColors);
        this.columnWidth = columnWidth;
        this.fill = fill;
    }

    public ColumnColorBulkCommand(Lanes lane, int columnWidth, boolean fill, String... columnColors) {
        this(lane, columnWidth, fill, Arrays.asList(columnColors));
    }

    public ColumnColorBulkCommand(Lanes lane, int columnWidth, boolean fill, LedColor... columnColors) {
        this(lane, columnWidth, fill, Arrays.stream(columnColors).map(LedColor::getHex).collect(Collectors.toList()));
    }

    @Override
    String getCommandText() {
        if (getLane() == Lanes.Logo || colors == null || colors.size() == 0 || columnWidth < 1) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String prevColor = colors.get(0);
        int offset = 0;
        int length = 0;

        int ledCount = fill
                ? UfoConstants.LEDS_ON_LANE
                : Math.min(UfoConstants.LEDS_ON_LANE, colors.size() * columnWidth);

        for (int i = 0; i < ledCount; i++) {
            String color = colors.get((i / columnWidth) % colors.size());
            if (!prevColor.equals(color)) {
                sb.append(String.format("%s=%d|%d|%s", getLane().getKey(), offset, length, prevColor));
                sb.append("&");
                offset = i;
                length = 0;
            }
            length ++;
            prevColor = color;
        }
        sb.append(String.format("%s=%d|%d|%s", getLane().getKey(), offset, length, prevColor));
        return sb.toString();
    }
}
