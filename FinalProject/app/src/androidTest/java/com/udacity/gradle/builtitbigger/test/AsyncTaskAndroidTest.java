package com.udacity.gradle.builtitbigger.test;

/**
 * Created by pspyrakis on 20/6/18.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskAndroidTest {
    @Rule
    public final ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testVerifyAsyncTaskResponse() {

        EndpointsAsyncTask testTask = new EndpointsAsyncTask();
        testTask.execute();
        String joke = testTask.getJoke();
        assertNotNull(joke);
        assertFalse(joke.isEmpty());
    }

}
