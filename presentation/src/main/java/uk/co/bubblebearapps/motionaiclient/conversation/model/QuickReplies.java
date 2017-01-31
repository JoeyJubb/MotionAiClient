package uk.co.bubblebearapps.motionaiclient.conversation.model;

import android.support.annotation.NonNull;

import org.joda.time.DateTime;

import java.util.List;

import uk.co.bubblebearapps.motionaiclient.QuickReply;

/**
 * Created by joefr_000 on 24/01/2017.
 */

public class QuickReplies extends MessageModel {

    private List<QuickReplyModel> list;


    public QuickReplies(String localId, DateTime timeStamp, List<QuickReplyModel> list) {
        super(localId, timeStamp);
        this.list = list;
    }


    @Override
    public String getContentsHash() {
        return getLocalId();
    }

    public List<QuickReplyModel> getList() {
        return list;
    }
}