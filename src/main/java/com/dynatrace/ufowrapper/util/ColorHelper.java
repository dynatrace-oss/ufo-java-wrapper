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

package com.dynatrace.ufowrapper.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import com.dynatrace.ufowrapper.enums.LedColor;

/**
 * Helpers for reading color codes by string and enums but also multiplied colors like "orange 4"
 */
public final class ColorHelper {
    public static String getRandomHexColor() {
        int rand_num = new Random().nextInt(0xffffff + 1);
        return String.format("%06x", rand_num);
    }

    public static List<String> getHexColors(LedColor... colors) {
        return getHexColors(Arrays.stream(colors).map(LedColor::getHex).collect(Collectors.toList()));
    }

    public static List<String> getHexColors(List<String> colorPattern) {
        final List<String> hexColors = new ArrayList<>();
        for (String item : colorPattern) {
            final String[] parts = item.split(" ");
            int count = 1;
            if (parts.length == 2) {
                count = Integer.parseInt(parts[1]);
            }
            for (int i = 0; i < count; i++) {
                hexColors.add(getHexColor(item));
            }
        }
        return hexColors;
    }

    public static String getHexColor(String color) {
        if (color == null) {
            return "";
        }
        final String[] parts = color.split(" ");
        if (parts.length == 0) {
            return "";
        }
        final Optional<LedColor> predefinedColor =
                Arrays.stream(LedColor.values()).filter(c -> c.name().equalsIgnoreCase(parts[0])).findFirst();
        if (predefinedColor.isPresent()) {
            return predefinedColor.get().getHex();
        }

        return parts[0];
    }
}
