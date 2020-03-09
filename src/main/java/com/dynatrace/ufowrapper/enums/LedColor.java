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

package com.dynatrace.ufowrapper.enums;

/**
 * Common color codes - values taken from
 * https://htmlcolorcodes.com/
 */
public enum LedColor {
    White("ffffff"),
    Silver("c0c0c0"),
    Gray("808080"),
    Black("000000"),
    Red("ff0000"),
    Maroon("800000"),
    Yellow("ffff00"),
    Olive("808000"),
    Lime("00ff00"),
    Green("008000"),
    Aqua("00ffff"),
    Teal("008080"),
    Blue("0000ff"),
    Navy("000080"),
    Fuchsia("ff00ff"),
    Purple("800080"),
    // additional colors,
    Orange("ff8300"),
    LightBlue("4444ff"),
    LightGreen("00ff00"),
    Pink("ff00ff"), // fuchsia
    // Dynatrace colors
    DtBlue("1496ff"),
    DtLimeGreen("b4dc00"),
    DtGreen("73be28"),
    DtPurple("6f2da8"),
    DtDarkGray("1a1a1a"); // pink, fuchsia

    private final String hex;

    LedColor(String hex) {
        this.hex = hex;
    }

	public String getHex() {
		return hex;
	}
}
