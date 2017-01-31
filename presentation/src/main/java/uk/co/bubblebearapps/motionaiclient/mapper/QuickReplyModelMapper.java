package uk.co.bubblebearapps.motionaiclient.mapper;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.co.bubblebearapps.motionaiclient.BotResponse;
import uk.co.bubblebearapps.motionaiclient.QuickReply;
import uk.co.bubblebearapps.motionaiclient.conversation.model.MessageModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModel;
import uk.co.bubblebearapps.motionaiclient.internal.di.PerActivity;

/**
 * Created by joefr_000 on 23/01/2017.
 */
@PerActivity
public class QuickReplyModelMapper {

    @Inject
    public QuickReplyModelMapper(){}

    public QuickReplyModel map(QuickReply quickReply) {
        return new QuickReplyModel()
                .setId(quickReply.getId())
                .setTextContent(quickReply.getTextContent());
    }


    public List<QuickReplyModel> map(List<QuickReply> quickReplies) {

        if(quickReplies == null){
            return Collections.emptyList();
        }

        List<QuickReplyModel> result = new ArrayList<>();
        for(QuickReply quickReply : quickReplies){
            result.add(map(quickReply));
        }
        return result;

    }
}
