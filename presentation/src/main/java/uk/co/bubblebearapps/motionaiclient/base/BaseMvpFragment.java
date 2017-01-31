package uk.co.bubblebearapps.motionaiclient.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

/**
 */
public abstract class BaseMvpFragment<P extends BasePresenter<V>, V extends BaseView> extends Fragment {

    private static final int LOADER_ID = 101;

    private BasePresenter<V> presenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // LoaderCallbacks as an object, so no hint regarding loader will be leak to the subclasses.
        getLoaderManager().initLoader(loaderId(), null, new LoaderManager.LoaderCallbacks<P>() {
            @Override
            public final Loader<P> onCreateLoader(int id, Bundle args) {
                return new PresenterLoader<>(getContext(), getPresenterFactory());
            }

            @Override
            public final void onLoadFinished(Loader<P> loader, P presenter) {
                BaseMvpFragment.this.presenter = presenter;
                onPresenterPrepared(presenter);
            }

            @Override
            public final void onLoaderReset(Loader<P> loader) {
                BaseMvpFragment.this.presenter = null;
                onPresenterDestroyed();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(getPresenterView());
    }

    @Override
    public void onPause() {
        presenter.detachView();
        super.onPause();
    }

    /**
     * Instance of {@link PresenterFactory} use to create a Presenter when needed. This instance should
     * not contain {@link android.app.Activity} context reference since it will be keep on rotations.
     */
    @NonNull
    protected abstract PresenterFactory<P> getPresenterFactory();

    /**
     * Hook for subclasses that deliver the {@link BasePresenter} before its View is attached.
     * Can be use to initialize the Presenter or simple hold a reference to it.
     */
    protected abstract void onPresenterPrepared(@NonNull P presenter);

    /**
     * Hook for subclasses before the screen gets destroyed.
     */
    protected void onPresenterDestroyed() {
    }

    @NonNull
    protected abstract V getPresenterView();

    /**
     * Use this method in case you want to specify a spefic ID for the {@link PresenterLoader}.
     * By default its value would be {@link #LOADER_ID}.
     */
    protected int loaderId() {
        return LOADER_ID;
    }
}