/*
 * Copyright 2017 Bubblebear Apps Ltd.
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

package uk.co.bubblebearapps.motionaiclient.mapper;

import uk.co.bubblebearapps.motionaiclient.BotResponse;

/**
 * Created by joefr_000 on 15/02/2017.
 */

public class DelayedResponse {
    private final long delay;
    private final BotResponse botResponse;

    public DelayedResponse(BotResponse botResponse, long delay) {
        this.delay = delay;
        this.botResponse = botResponse;
    }

    public long getDelay() {
        return delay;
    }

    public BotResponse getBotResponse() {
        return botResponse;
    }
}
