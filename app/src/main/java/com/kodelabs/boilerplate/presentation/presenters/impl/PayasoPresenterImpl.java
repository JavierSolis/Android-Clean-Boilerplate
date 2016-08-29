package com.kodelabs.boilerplate.presentation.presenters.impl;

import com.kodelabs.boilerplate.domain.executor.Executor;
import com.kodelabs.boilerplate.domain.executor.MainThread;
import com.kodelabs.boilerplate.domain.interactors.VerificarCobroInteractor;
import com.kodelabs.boilerplate.domain.interactors.WelcomeInteractor;
import com.kodelabs.boilerplate.domain.interactors.impl.VerifcaCobroInteractorImpl;
import com.kodelabs.boilerplate.domain.interactors.impl.WelcomingInteractorImpl;
import com.kodelabs.boilerplate.presentation.presenters.PayasoPresenter;
import com.kodelabs.boilerplate.presentation.presenters.base.AbstractPresenter;

/**
 * by Javier Solis @JavierTwiteando
 * Github http://bit.ly/onGithub
 */
public class PayasoPresenterImpl extends AbstractPresenter
        implements PayasoPresenter,VerificarCobroInteractor.CallBack {

    PayasoPresenter.View mView;

    public PayasoPresenterImpl(Executor executor, MainThread mainThread,PayasoPresenter.View view) {
        super(executor, mainThread);
        mView = view;
    }


    @Override
    public void onClickCobrar(String nameMoroso) {
        mView.showProgress();
        // initialize the interactor
        VerificarCobroInteractor interactor = new VerifcaCobroInteractorImpl(
                nameMoroso,
                this,
                mExecutor,
                mMainThread
        );
        // run the interactor
        interactor.execute();
    }



    @Override
    public void onVerificationSuccess(String nameMoroso) {
        mView.hideProgress();
        mView.showDialogCobroExitoso();
    }

    @Override
    public void onVerificationError(String error) {
        mView.hideProgress();
        mView.showError(error);
    }












    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }


}
