package uk.co.bubblebearapps.motionaiclient.mapper;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.co.bubblebearapps.motionaiclient.QuickReply;
import uk.co.bubblebearapps.motionaiclient.entity.QuickReplyEntity;

/**
 * Created by joefr_000 on 23/01/2017.
 */
@Singleton
public class QuickReplyEntityMapper {


    @Inject
    public QuickReplyEntityMapper() {

    }

    public QuickReply map(QuickReplyEntity quickReplyEntity) {

        return new QuickReply()
                .setId(quickReplyEntity.getId())
                .setTextContent(quickReplyEntity.getTitle()
                );


    }

    public List<QuickReply> map(QuickReplyEntity[] quickReplies) {


        if (quickReplies != null && quickReplies.length > 0) {

            List<QuickReply> result = new ArrayList<>(quickReplies.length);
            for (QuickReplyEntity quickReplyEntity : quickReplies) {
                result.add(map(quickReplyEntity));
            }
            return result;
        } else {
            return Lists.newArrayList();
        }

    }
}
