/*
 * Copyright 2017 Bubblebear Apps Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.bubblebearapps.motionaiclient.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import uk.co.bubblebearapps.motionaiclient.CardButton;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardButtonModel;
import uk.co.bubblebearapps.motionaiclient.internal.di.PerActivity;

/**
 * Created by joefr_000 on 23/01/2017.
 */
@PerActivity
public class CardButtonModelMapper {

    @Inject
    public CardButtonModelMapper() {

    }

    public CardButtonModel map(CardButton card) {
        return new CardButtonModel()
                .setId(card.getId())
                .setLabel(card.getLabel())
                .setType(card.getType())
                .setTarget(card.getTarget());

    }

    public List<CardButtonModel> map(List<CardButton> cardButtons, int botColor) {

        List<CardButtonModel> result = new ArrayList<>();
        for (CardButton cardButton : cardButtons) {
            result.add(map(cardButton).setButtonColor(botColor));
        }

        return result;

    }
}
