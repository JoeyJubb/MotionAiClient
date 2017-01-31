package uk.co.bubblebearapps.motionaiclient.base;

public interface PresenterFactory<P extends BasePresenter> {


    /**
     * Provide an instance of the presenter
     */
    P providePresenter();
}
