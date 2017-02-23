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

package uk.co.bubblebearapps.motionaiclient;

public class CardButton {

    private final String id;
    private final String target;
    private final ButtonType type;
    private final String label;

    private CardButton(String id, String target, ButtonType type, String label) {
        this.id = id;
        this.target = target;
        this.type = type;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getTarget() {
        return target;
    }

    public ButtonType getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public void visit(Visitor visitor){

        switch(type){

            case URL:
                visitor.onVisitUrl(getTarget());
                break;
            case MESSAGE:
                visitor.onVisitMessage(getTarget());
                break;
        }

    }

    public enum ButtonType {
        URL, MESSAGE
    }

    /**
     * Created by joefr_000 on 23/01/2017.
     */
    public interface Visitor {
        void onVisitUrl(String target);

        void onVisitMessage(String target);
    }

    public static class Builder {
        private String id;
        private String target;
        private ButtonType type;
        private String label;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setTarget(String target) {
            this.target = target;
            return this;
        }

        public Builder setType(ButtonType type) {
            this.type = type;
            return this;
        }

        public Builder setLabel(String label) {
            this.label = label;
            return this;
        }

        public CardButton build() {
            return new CardButton(id, target, type, label);
        }
    }
}