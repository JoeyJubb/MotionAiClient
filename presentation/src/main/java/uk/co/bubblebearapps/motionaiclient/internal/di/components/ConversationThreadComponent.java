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
