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

/**
 * Abstract - Base class for all commands
 */
public abstract class BaseCommand implements Command {
    private Lanes lane;

    public BaseCommand(Lanes lane) {
        this.lane = lane;
    }

    public Lanes getLane() {
        return lane;
    }

    @Override
    public String toRequestParam() {
        return getCommandText();
    }

    abstract String getCommandText();
}
