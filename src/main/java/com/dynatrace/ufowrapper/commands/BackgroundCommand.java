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
import com.dynatrace.ufowrapper.util.ColorHelper;

/**
 * Command for setting background color of a lane
 */
public class BackgroundCommand extends BaseCommand {
    private String color;

    public BackgroundCommand(Lanes lane, String color) {
        super(lane);
        this.color = ColorHelper.getHexColor(color);
    }

    public BackgroundCommand(Lanes lane, LedColor color) {
        super(lane);
        this.color = color.getHex();
    }

    @Override
    String getCommandText() {
        if (getLane() == Lanes.Logo) {
            return null;
        }
        return String.format("%s_bg=%s", getLane().getKey(), color);
    }
}
