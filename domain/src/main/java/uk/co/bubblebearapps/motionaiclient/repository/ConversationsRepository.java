package uk.co.bubblebearapps.motionaiclient.repository;

import rx.Observable;
import uk.co.bubblebearapps.motionaiclient.BotResponse;
import uk.co.bubblebearapps.motionaiclient.User;

/**
 * Created by joefr_000 on 23/01/2017.
 */

public interface ConversationsRepository {
    Observable<BotResponse> messageBot(String botId, String message, User user);
}
