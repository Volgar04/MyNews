package com.nicolappli.mynews;

import android.support.test.runner.AndroidJUnit4;
import com.nicolappli.mynews.Models.NewYorkTimesAPI;
import com.nicolappli.mynews.Utils.NYTStreams;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class FragmentTest {

    @Test
    public void fetchTopStories() throws Exception {
        Observable<NewYorkTimesAPI> observable = NYTStreams.streamFetchTopStories("home");

        TestObserver<NewYorkTimesAPI> testObserver = new TestObserver<>();

        observable.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        NewYorkTimesAPI resultsFetched = testObserver.values().get(0);

        assertThat("The result of Top Stories is bigger than 0.",!resultsFetched.getResults().equals(0));
    }

    @Test
    public void fetchMostPopular() throws Exception {
        Observable<NewYorkTimesAPI> observable = NYTStreams.streamFetchMostPopular();

        TestObserver<NewYorkTimesAPI> testObserver = new TestObserver<>();

        observable.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        NewYorkTimesAPI resultsFetched = testObserver.values().get(0);

        assertThat("The result of Most Popular is bigger than 0.",!resultsFetched.getResults().equals(0));
    }

    @Test
    public void fetchSearchArticles() throws Exception {
        Observable<NewYorkTimesAPI> observable = NYTStreams.streamFetchSearchArticles("trump","news_desk:(\"Politics\")", "20120101", "20180101");

        TestObserver<NewYorkTimesAPI> testObserver = new TestObserver<>();

        observable.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        NewYorkTimesAPI resultsFetched = testObserver.values().get(0);

        assertThat("The result of Search Articles is bigger than 0.",!resultsFetched.getResponse().equals(0));
    }
}
