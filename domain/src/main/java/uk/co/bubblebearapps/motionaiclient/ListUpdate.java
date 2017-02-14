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

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joefr_000 on 07/12/2016.
 */

public final class ListUpdate<T> {

    private final Kind kind;

    private final T value;

    public ListUpdate(Kind kind, T value) {
        this.value = checkNotNull(value);
        this.kind = checkNotNull(kind);
    }

    public static <T> ListUpdate<T> createAdd(T t) {
        return new ListUpdate<>(Kind.ADD, t);
    }

    public static <T> ListUpdate<T> createChange(T t) {
        return new ListUpdate<>(Kind.CHANGE, t);
    }

    public static <T> ListUpdate<T> createDelete(T t) {
        return new ListUpdate<>(Kind.DELETE, t);
    }

    /**
     * Retrieves the item associated with this ListUpdate.
     */
    public T getValue() {
        return value;
    }

    /**
     * @return the kind of the ListUpdate: {@code ADD}, {@code CHANGE}, or {@code DELETE}
     */
    public Kind getKind() {
        return kind;
    }

    public void accept(ListUpdateObserver<T> observer) {
        switch (kind) {
            case ADD:
                observer.onItemAdded(value);
                break;
            case CHANGE:
                observer.onItemChanged(value);
                break;
            case DELETE:
                observer.onItemDeleted(value);
                break;
        }
    }

    public enum Kind {
        ADD, CHANGE, DELETE
    }

    public interface ListUpdateObserver<T> {

        void onItemAdded(T item);

        void onItemChanged(T item);

        void onItemDeleted(T item);
    }

}
