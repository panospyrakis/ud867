package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.spyrakis.jokeactivity.JokeActivity;
import com.udacity.spyrakis.jokes.Jokes;

import java.io.IOException;

/**
 * Created by pspyrakis on 20/6/18.
 */
public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myJokeService = null;
    private Context context;
    private static String joke = null;
    @Override
    protected String doInBackground(Context... params) {
        if(myJokeService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myJokeService = builder.build();
        }

        context = params[0];
        try {
            return myJokeService.sayHi().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public String getJoke(){
        try {
            return myJokeService.sayHi().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Jokes jokeTeller = new Jokes();
        Intent intent = new Intent(context, JokeActivity.class);
        String joke = jokeTeller.getJoke();
        intent.putExtra(JokeActivity.EXTRA_JOKE, joke);
        context.startActivity(intent);
    }
}