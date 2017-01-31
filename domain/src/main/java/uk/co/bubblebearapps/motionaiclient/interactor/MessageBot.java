package uk.co.bubblebearapps.motionaiclient.interactor;

import rx.Observable;
import uk.co.bubblebearapps.motionaiclient.BotResponse;
import uk.co.bubblebearapps.motionaiclient.User;
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
                requestValue.getBotId(),
                requestValue.getMessage(),
                requestValue.getUser());
    }

    public static final class RequestValue implements UseCase.RequestValue {

        private String botId;
        private String message;

        public User getUser() {
            return user;
        }

        public RequestValue setUser(User user) {
            this.user = user;
            return this;
        }

        private User user;

        public RequestValue() {
        }

        public String getBotId() {
            return botId;
        }

        public RequestValue setBotId(String botId) {
            this.botId = botId;
            return this;
        }

        public String getMessage() {
            return message;
        }

        public RequestValue setMessage(String message) {
            this.message = message;
            return this;
        }


    }
}
