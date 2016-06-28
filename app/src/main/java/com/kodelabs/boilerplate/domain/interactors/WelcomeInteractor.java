package com.kodelabs.boilerplate.domain.interactors;

import com.kodelabs.boilerplate.domain.interactors.base.Interactor;

/**
 * Created on 23/06/16.
 * by Javier Solis @JavierTwiteando
 * Github http://bit.ly/onGithub
 * Company @PineappleLab
 * Contact hellopineapplelab@gmail.com
 */
public interface WelcomeInteractor extends Interactor {
    interface Callback {
        void onMessageRetrieved(String message);

        void onRetrievalFailed(String error);
    }
}
