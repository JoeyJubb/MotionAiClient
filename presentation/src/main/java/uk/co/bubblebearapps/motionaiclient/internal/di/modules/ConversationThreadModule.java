package uk.co.bubblebearapps.motionaiclient.internal.di.modules;

import java.util.UUID;

import dagger.Module;
import dagger.Provides;
import uk.co.bubblebearapps.motionaiclient.User;
import uk.co.bubblebearapps.motionaiclient.base.PresenterFactory;
import uk.co.bubblebearapps.motionaiclient.conversation.ConversationContract;
import uk.co.bubblebearapps.motionaiclient.conversation.ConversationPresenter;
import uk.co.bubblebearapps.motionaiclient.data.BuildConfig;
import uk.co.bubblebearapps.motionaiclient.executor.PostExecutionThread;
import uk.co.bubblebearapps.motionaiclient.executor.ThreadExecutor;
import uk.co.bubblebearapps.motionaiclient.interactor.MessageBot;
import uk.co.bubblebearapps.motionaiclient.internal.di.PerActivity;
import uk.co.bubblebearapps.motionaiclient.mapper.MessageModelMapper;
import uk.co.bubblebearapps.motionaiclient.repository.ConversationsRepository;

/**
 * Created by joefr_000 on 23/01/2017.
 */
@Module
public class ConversationThreadModule {

    public ConversationThreadModule() {
    }

    @Provides
    @PerActivity
    MessageBot provideMessageBot(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ConversationsRepository conversationsRepository) {
        return new MessageBot(threadExecutor, postExecutionThread, conversationsRepository);
    }

    @Provides
    @PerActivity
    ConversationContract.Presenter provideConversationPresenter(MessageBot messageBot, MessageModelMapper messageModelMapper) {
        return new ConversationPresenter(
                messageBot,
                messageModelMapper,
                BuildConfig.BOT_ID, // hard-wired to connect to a specifc bot for now
                new User().setId(UUID.randomUUID().toString()).setTitle("John Doe") // create some random user credentials for now
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
