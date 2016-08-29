package com.kodelabs.boilerplate.domain.interactors;

import com.kodelabs.boilerplate.domain.interactors.base.Interactor;

/**
 * by Javier Solis @JavierTwiteando
 * Github http://bit.ly/onGithub
 */
public interface VerificarCobroInteractor extends Interactor {

    interface CallBack
    {
        void onVerificationSuccess(String nameMoroso);
        void onVerificationError(String error);
    }
}
