package uk.co.bubblebearapps.motionaiclient.internal.di.modules;

import android.content.Context;

import org.joda.time.DateTimeComparator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.bubblebearapps.motionaiclient.AndroidApplication;
import uk.co.bubblebearapps.motionaiclient.UIThread;
import uk.co.bubblebearapps.motionaiclient.executor.JobExecutor;
import uk.co.bubblebearapps.motionaiclient.executor.PostExecutionThread;
import uk.co.bubblebearapps.motionaiclient.executor.ThreadExecutor;

/**
 * Created by joefr_000 on 24/01/2017.
 */

@Module
public class DomainModule {

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    DateTimeComparator provideDateTimeComparator() {
        return DateTimeComparator.getInstance();
    }

}
