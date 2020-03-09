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

import com.dynatrace.ufowrapper.enums.Lanes;
import com.dynatrace.ufowrapper.enums.LedColor;
import com.dynatrace.ufowrapper.util.ColorHelper;

/**
 * Command to set the logo colors (limited to 4 LEDs)
 */
public class LogoCommand extends BaseCommand {
    private List<String> colors;

    public LogoCommand(List<String> colors) {
        super(Lanes.Logo);
        this.colors = ColorHelper.getHexColors(colors);
    }

    public LogoCommand(LedColor... colors) {
        super(Lanes.Logo);
        this.colors = ColorHelper.getHexColors(colors);
    }

    public LogoCommand(String... colors) {
        this(Arrays.asList(colors));
    }

    @Override
    String getCommandText() {
        if (colors == null || colors.size() == 0) {
            return null;
        }

        String color1 = colors.get(0);
        String color2 = colors.get(1 % colors.size());
        String color3 = colors.get(2 % colors.size());
        String color4 = colors.get(3 % colors.size());

        return String.format("logo=%s|%s|%s|%s", color1, color2, color3, color4);
    }
}
