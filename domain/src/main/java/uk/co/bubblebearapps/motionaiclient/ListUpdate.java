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
