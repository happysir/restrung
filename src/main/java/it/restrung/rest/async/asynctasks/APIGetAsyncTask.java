/*
 * Copyright (C) 2012 47 Degrees, LLC
 * http://47deg.com
 * hello@47deg.com
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

package it.restrung.rest.async.asynctasks;

import it.restrung.rest.async.AsyncOperation;
import it.restrung.rest.client.APICredentialsDelegate;
import it.restrung.rest.client.APIDelegate;
import it.restrung.rest.client.RestClientFactory;
import it.restrung.rest.marshalling.response.JSONResponse;

import java.util.concurrent.Callable;

/**
 * AsyncTask for GET requests
 *
 * @param <T> an implementer of JSONResponse
 */
public class APIGetAsyncTask<T extends JSONResponse> extends APIAsyncTask<T> {


    /**
     * @param url                    the service endpoint
     * @param delegate               the delegate that will be notified on successful requests
     * @param apiCredentialsDelegate an optional delegate to handle invalid credentials
     * @param params                 the params to be replaced on the url placeholders
     */
    public APIGetAsyncTask(String url, APIDelegate<T> delegate, APICredentialsDelegate apiCredentialsDelegate, Object... params) {
        super(url, delegate, apiCredentialsDelegate, params);
    }

    /**
     * @see android.os.AsyncTask#doInBackground(Object[])
     */
    @Override
    protected T doInBackground(String... args) {
        return getOperation().executeWithExceptionHandling(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return RestClientFactory.getClient().get(getOperation().getApiDelegate(), getOperation().getUrl(), AsyncOperation.DEFAULT_REQUEST_TIMEOUT);
            }
        });
    }

}
