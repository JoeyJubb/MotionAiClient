package uk.co.bubblebearapps.motionaiclient.datasource;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import uk.co.bubblebearapps.motionaiclient.entity.ResponseEntity;

/**
 * Created by joefr_000 on 23/01/2017.
 */

public interface BotDataSource {


    String BASE_URL = "https://api.motion.ai/1.0/";

    @GET("messagebot")
    Observable<ResponseEntity> messageBot(
            @Query("bot") int botId,
            @Query("msg") String message,
            @Query("session") String sessionId,
            @Query("from") String from);

}
