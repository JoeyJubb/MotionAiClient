package uk.co.bubblebearapps.motionaiclient.repository;

import android.content.Context;
import android.util.Log;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import uk.co.bubblebearapps.motionaiclient.BotResponse;
import uk.co.bubblebearapps.motionaiclient.User;
import uk.co.bubblebearapps.motionaiclient.datasource.BotDataSource;
import uk.co.bubblebearapps.motionaiclient.entity.ResponseEntity;
import uk.co.bubblebearapps.motionaiclient.mapper.ResponseEntityMapper;

/**
 * Created by joefr_000 on 23/01/2017.
 */

public class ConversationsDataRepository implements ConversationsRepository {

    private static final String TAG = "ConvoRepo";
    private final Context context;
    private final BotDataSource moviesDataSource;
    private final ResponseEntityMapper entityMapper;

    public ConversationsDataRepository(Context context, BotDataSource moviesDataSource, ResponseEntityMapper entityMapper) {
        this.context = context;
        this.moviesDataSource = moviesDataSource;
        this.entityMapper = entityMapper;
    }

    @Override
    public Observable<BotResponse> messageBot(final String botId, String message, final User user) {
        return moviesDataSource.messageBot(Integer.valueOf(botId), message, user.getId(), user.getTitle())
                .flatMap(new Func1<ResponseEntity, Observable<BotResponse>>() {
                    @Override
                    public Observable<BotResponse> call(ResponseEntity responseEntity) {

                        Observable<BotResponse> result = Observable.from(entityMapper.map(responseEntity));

                        if(responseEntity.isAutoReply() && !responseEntity.getInReplyTo().equals(" ")){
                            return result.concatWith(messageBot(botId, " ", user));
                        }else{
                            return result;
                        }

                    }
                });

    }
}
