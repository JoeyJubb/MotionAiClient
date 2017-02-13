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

package uk.co.bubblebearapps.motionaiclient.interactor;

import rx.Observable;
import uk.co.bubblebearapps.motionaiclient.BotResponse;
import uk.co.bubblebearapps.motionaiclient.UserInfo;
import uk.co.bubblebearapps.motionaiclient.executor.PostExecutionThread;
import uk.co.bubblebearapps.motionaiclient.executor.ThreadExecutor;
import uk.co.bubblebearapps.motionaiclient.repository.ConversationsRepository;

/**
 * Created by joefr_000 on 23/01/2017.
 */

public class MessageBot extends UseCase<MessageBot.RequestValue, BotResponse> {

    private ConversationsRepository conversationsRepository;

    public MessageBot(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ConversationsRepository conversationsRepository) {
        super(threadExecutor, postExecutionThread);
        this.conversationsRepository = conversationsRepository;
    }

    @Override
    protected Observable<BotResponse> buildUseCaseObservable(RequestValue requestValue) {
        return conversationsRepository.messageBot(
                requestValue.getApiKey(),
                requestValue.getBotId(),
                requestValue.getMessage(),
                requestValue.getUserInfo());
    }

    public static final class RequestValue implements UseCase.RequestValue {

        private final String apiKey;
        private final String botId;
        private final String message;
        private final UserInfo userInfo;

        RequestValue(String apiKey, String botId, String message, UserInfo userInfo) {
            this.apiKey = apiKey;
            this.botId = botId;
            this.message = message;
            this.userInfo = userInfo;
        }


        public UserInfo getUserInfo() {
            return userInfo;
        }


        public String getBotId() {
            return botId;
        }


        public String getMessage() {
            return message;
        }


        public String getApiKey() {
            return apiKey;
        }


        public static class Builder {

            private String apiKey;
            private UserInfo userInfo;
            private String botId;
            private String message;

            public Builder setApiKey(String apiKey) {
                this.apiKey = apiKey;
                return this;
            }

            public Builder setUserInfo(UserInfo userInfo) {
                this.userInfo = userInfo;
                return this;
            }

            public Builder setBotId(String botId) {
                this.botId = botId;
                return this;
            }

            public Builder setMessage(String message) {
                this.message = message;
                return this;
            }


            public RequestValue build() {
                return new RequestValue(apiKey, botId, message, userInfo);
            }
        }
    }
}
