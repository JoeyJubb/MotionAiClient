package uk.co.bubblebearapps.motionaiclient;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.co.bubblebearapps.motionaiclient.entity.CardEntity;
import uk.co.bubblebearapps.motionaiclient.entity.QuickReplyEntity;
import uk.co.bubblebearapps.motionaiclient.entity.ResponseEntity;

/**
 * Created by joefr_000 on 25/01/2017.
 */

/**
 * Manually does deserialization of this class.
 *
 * This is because the "quick replies" array is normally full of "quick reply" objects, except for when we're supposed to automatically reply then it's just an array with a single blank string >:-(
 */
@Singleton
public class MessageEntityDeserializer implements JsonDeserializer<ResponseEntity> {

    @Inject
    public MessageEntityDeserializer(){

    }

    @Override
    public ResponseEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject asJsonObject = json.getAsJsonObject();

        Type cardCollectionType = new TypeToken<CardEntity[]>(){}.getType();
        Type quickReplyCollectionType = new TypeToken<QuickReplyEntity[]>(){}.getType();


        ResponseEntity responseEntity = new ResponseEntity()
                .setBotResponse(asJsonObject.get("botResponse").getAsString())
                .setResult(asJsonObject.get("result").isJsonNull() ? null : asJsonObject.get("result").toString())
                .setInReplyTo(asJsonObject.get("inReplyTo").getAsString())
                .setSession(asJsonObject.get("session").getAsString())
                .setModule(asJsonObject.get("module").getAsInt())
                .setCards((CardEntity[]) context.deserialize(asJsonObject.get("cards"), cardCollectionType));

        try{
            responseEntity.setQuickReplies(
                    (QuickReplyEntity[]) context.deserialize(asJsonObject.get("quickReplies"), quickReplyCollectionType));
            responseEntity.setAutoReply(false);
        }catch (JsonParseException e){
            responseEntity.setQuickReplies(null);
            responseEntity.setAutoReply(true);
        }


        return responseEntity;


    }

}
