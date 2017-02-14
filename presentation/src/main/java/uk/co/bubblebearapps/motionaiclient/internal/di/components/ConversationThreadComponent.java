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

package uk.co.bubblebearapps.motionaiclient.internal.di.components;

import dagger.Component;
import uk.co.bubblebearapps.motionaiclient.base.PresenterFactory;
import uk.co.bubblebearapps.motionaiclient.conversation.ConversationContract;
import uk.co.bubblebearapps.motionaiclient.conversation.ConversationDisplayFragment;
import uk.co.bubblebearapps.motionaiclient.internal.di.PerActivity;
import uk.co.bubblebearapps.motionaiclient.internal.di.modules.ConversationThreadModule;

/**
 * Created by joefr_000 on 24/01/2017.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ConversationThreadModule.class)
public interface ConversationThreadComponent {

    PresenterFactory<ConversationContract.Presenter> presenterFactory();

    void inject(ConversationDisplayFragment conversationDisplayFragment);

}
