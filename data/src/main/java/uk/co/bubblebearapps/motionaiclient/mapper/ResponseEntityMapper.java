package uk.co.bubblebearapps.motionaiclient.mapper;

import android.text.TextUtils;
import android.util.Log;

import com.google.common.base.Strings;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.co.bubblebearapps.motionaiclient.BotResponse;
import uk.co.bubblebearapps.motionaiclient.entity.ResponseEntity;

/**
 * Created by joefr_000 on 23/01/2017.
 */

@Singleton
public class ResponseEntityMapper {

    private static final Pattern IMG_TAG_PATTERN = Pattern.compile("\\[(img|video|youtube)\\]([\\s\\S]+?)\\[\\/\\1\\]");
    private static final String BB_CODE_REMOVER_REGEX = "(\\[\\/?(img|video|youtube)])";

    private static final String TAG = "ResponseEntityMapper";

    private final CardEntityMapper cardEntityMapper;
    private final QuickReplyEntityMapper quickReplyEntityMapper;

    @Inject
    public ResponseEntityMapper(CardEntityMapper cardEntityMapper, QuickReplyEntityMapper quickReplyEntityMapper) {

        this.cardEntityMapper = cardEntityMapper;
        this.quickReplyEntityMapper = quickReplyEntityMapper;
    }

    public List<BotResponse> map(ResponseEntity responseEntity) {

        final String responseText = responseEntity.getBotResponse();

        if (TextUtils.isEmpty(responseText)) {
            return Collections.emptyList();
        } else {
            List<String> splitResponses = split(responseText, IMG_TAG_PATTERN);
            ArrayList<BotResponse> botResponseList = new ArrayList<>();

            for (String response : splitResponses) {

                BotResponse.Type type;

                if (response.startsWith("[img]")) {
                    type = BotResponse.Type.IMAGE;
                } else if (response.startsWith("[video]")) {
                    type = BotResponse.Type.VIDEO;
                } else if (response.startsWith("[youtube]")) {
                    type = BotResponse.Type.YOUTUBE;
                } else {
                    type = BotResponse.Type.TEXT;
                }

                String target = response.replaceAll(BB_CODE_REMOVER_REGEX, "").trim();

                if (Strings.isNullOrEmpty(target)) {
                    continue;
                }

                botResponseList.add(new BotResponse()
                        .setTimeStamp(DateTime.now())
                        .setSessionId(responseEntity.getSession())
                        .setType(type)
                        .setTarget(target)
                );

            }

            // add cards and replies to last one
            if (botResponseList.size() == 0) {

                botResponseList.add(
                        new BotResponse()
                                .setTimeStamp(DateTime.now())
                                .setSessionId(responseEntity.getSession())
                                .setType(BotResponse.Type.UNKNOWN)
                                .setTarget(responseEntity.getBotResponse())
                );
            }

            botResponseList.get(botResponseList.size() - 1)
                    .setQuickReplies(quickReplyEntityMapper.map(responseEntity.getQuickReplies()))
                    .setCards(cardEntityMapper.map(responseEntity.getCards()));


            return botResponseList;


        }

    }

    // recursive -- the first patten finds whatever it can then hands off to the next pattern on the list when it comes across an unknown bit
    private List<String> split(String responseText, Pattern pattern) {


        Matcher matcher = pattern.matcher(responseText);

        ArrayList<String> result = new ArrayList<>();

        int trailingIndex = 0;
        while (matcher.find()) {
            if (matcher.start() > trailingIndex) {
                //found plain text
                String substring = responseText.substring(trailingIndex, matcher.start()-1);
                result.add(substring);

            }
            // found match
            String match = responseText.substring(matcher.start(), matcher.end());
            result.add(match);

            trailingIndex = matcher.end();
        }

        if (trailingIndex < responseText.length() - 1) {
            // found plain text
            String substring = responseText.substring(trailingIndex, responseText.length());
            result.add(substring);
        }

        return result;


    }

}
