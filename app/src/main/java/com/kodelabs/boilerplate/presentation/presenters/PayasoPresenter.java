package com.kodelabs.boilerplate.presentation.presenters;

import com.kodelabs.boilerplate.presentation.presenters.base.BasePresenter;
import com.kodelabs.boilerplate.presentation.ui.BaseView;

/**
 * by Javier Solis @JavierTwiteando
 * Github http://bit.ly/onGithub
 */
public interface PayasoPresenter extends BasePresenter {
    interface View extends BaseView
    {
        void showDialogCobroExitoso();
    }

    void onClickCobrar(String nameMoroso);
}
