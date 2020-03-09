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
import com.dynatrace.ufowrapper.enums.WhirlDirection;
import com.dynatrace.ufowrapper.enums.WhirlSpeed;

/**
 * Command for setting whirl animation on a lane. Does not work with logo
 */
public class WhirlCommand extends BaseCommand {
    private WhirlSpeed speed;
    private WhirlDirection direction;

    public WhirlCommand(Lanes lane, WhirlSpeed speed) {
        this(lane, speed, WhirlDirection.ClockWise);
    }

    public WhirlCommand(Lanes lane, WhirlSpeed speed, WhirlDirection direction) {
        super(lane);
        this.speed = speed;
        this.direction = direction;
    }

    @Override
    String getCommandText() {
        if (getLane() == Lanes.Logo) {
            return null;
        }
        return String.format("%s_whirl=%d%s", getLane().getKey(), speed.getSpeed(), direction.getArg());
    }
}
