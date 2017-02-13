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

package uk.co.bubblebearapps.motionaiclient.internal.di.modules;

import dagger.Module;
import dagger.Provides;
import uk.co.bubblebearapps.motionaiclient.base.PresenterFactory;
import uk.co.bubblebearapps.motionaiclient.conversation.ConversationBubbleDecorator;
import uk.co.bubblebearapps.motionaiclient.conversation.ConversationContract;
import uk.co.bubblebearapps.motionaiclient.conversation.ConversationPresenter;
import uk.co.bubblebearapps.motionaiclient.conversation.model.ConversationBubbleVisitor;
import uk.co.bubblebearapps.motionaiclient.executor.PostExecutionThread;
import uk.co.bubblebearapps.motionaiclient.executor.ThreadExecutor;
import uk.co.bubblebearapps.motionaiclient.interactor.MessageBot;
import uk.co.bubblebearapps.motionaiclient.internal.di.PerActivity;
import uk.co.bubblebearapps.motionaiclient.mapper.BotInfoModelMapper;
import uk.co.bubblebearapps.motionaiclient.mapper.BotResponseModelMapper;
import uk.co.bubblebearapps.motionaiclient.mapper.UserInfoModelMapper;
import uk.co.bubblebearapps.motionaiclient.model.BotInfoModel;
import uk.co.bubblebearapps.motionaiclient.model.UserInfoModel;
import uk.co.bubblebearapps.motionaiclient.repository.ConversationsRepository;

/**
 * Created by joefr_000 on 23/01/2017.
 */
@Module
public class ConversationThreadModule {

    private final BotInfoModel botInfoModel;
    private final UserInfoModel userInfoModel;

    public ConversationThreadModule(BotInfoModel botInfoModel, UserInfoModel userInfoModel) {
        this.botInfoModel = botInfoModel;
        this.userInfoModel = userInfoModel;
    }

    @Provides
    @PerActivity
    MessageBot provideMessageBot(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ConversationsRepository conversationsRepository) {
        return new MessageBot(threadExecutor, postExecutionThread, conversationsRepository);
    }

    @Provides
    @PerActivity
    ConversationBubbleVisitor provideConversationBubbleVisitor() {
        return new ConversationBubbleDecorator(botInfoModel);
    }


    @Provides
    @PerActivity
    ConversationContract.Presenter provideConversationPresenter(MessageBot messageBot,
                                                                BotResponseModelMapper botResponseModelMapper,
                                                                BotInfoModelMapper botInfoModelMapper,
                                                                UserInfoModelMapper userInfoModelMapper,
                                                                ConversationBubbleVisitor conversationBubbleVisitor) {



        return new ConversationPresenter(
                messageBot,
                botResponseModelMapper,
                conversationBubbleVisitor,
                botInfoModelMapper.map(botInfoModel),
                userInfoModelMapper.map(userInfoModel)
        );
    }

    @Provides
    @PerActivity
    PresenterFactory<ConversationContract.Presenter> provideConversationPresenterFactory(final ConversationContract.Presenter presenter) {
        return new PresenterFactory<ConversationContract.Presenter>() {
            @Override
            public ConversationContract.Presenter providePresenter() {
                return presenter;
            }
        };
    }

}
