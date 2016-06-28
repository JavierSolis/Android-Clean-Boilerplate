package com.kodelabs.boilerplate.domain.interactors.impl;

import com.kodelabs.boilerplate.domain.executor.Executor;
import com.kodelabs.boilerplate.domain.executor.MainThread;
import com.kodelabs.boilerplate.domain.interactors.WelcomeInteractor;
import com.kodelabs.boilerplate.domain.interactors.base.AbstractInteractor;
import com.kodelabs.boilerplate.domain.repository.MessageRepository;

/**
 * Created on 23/06/16.
 * by Javier Solis @JavierTwiteando
 * Github http://bit.ly/onGithub
 * Company @PineappleLab
 * Contact hellopineapplelab@gmail.com
 */

public class WelcomingInteractorImpl extends AbstractInteractor implements WelcomeInteractor {

    public MessageRepository mMessageRepository;
    public WelcomeInteractor.Callback mCallback;

    public WelcomingInteractorImpl(Executor threadExecutor,
                                   MainThread mainThread,
                                   WelcomeInteractor.Callback mCallback,
                                   MessageRepository mMessageRepository) {
        super(threadExecutor, mainThread);

        this.mMessageRepository = mMessageRepository;
        this.mCallback = mCallback;
    }



    @Override
    public void run( ) {

        // retrieve the message
        final String message = mMessageRepository.getWelcomeMessage();

        // check if we have failed to retrieve our message
        if (message == null || message.length() == 0) {

            // notify the failure on the main thread
            notifyError();

            return;
        }

        // we have retrieved our message, notify the UI on the main thread
        postMessage(message);
    }



    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievalFailed("Nothing to welcome you with :(");
            }
        });
    }
    private void postMessage(final String msg) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onMessageRetrieved(msg);
            }
        });
    }
}
