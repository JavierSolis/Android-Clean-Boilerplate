package com.kodelabs.boilerplate.domain.interactors.impl;

import com.kodelabs.boilerplate.domain.executor.Executor;
import com.kodelabs.boilerplate.domain.executor.MainThread;
import com.kodelabs.boilerplate.domain.interactors.VerificarCobroInteractor;
import com.kodelabs.boilerplate.domain.interactors.base.AbstractInteractor;

import java.util.Random;

/**
 * by Javier Solis @JavierTwiteando
 * Github http://bit.ly/onGithub
 */
public class VerifcaCobroInteractorImpl extends AbstractInteractor implements VerificarCobroInteractor{



    public VerificarCobroInteractor.CallBack mCallBack;
    public String nameMoroso;




    public VerifcaCobroInteractorImpl(
            String nameMorso,
            CallBack mCallBack,
            Executor threadExecutor,
            MainThread mainThread) {
        super(threadExecutor, mainThread);

        this.mCallBack = mCallBack;
        this.nameMoroso = nameMorso;
    }




    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int ramdom  = ( new Random()).nextInt(1);
        if(ramdom==1)
        {
            notifyError();
        }
        else
        {
            notifySuccess(this.nameMoroso);
        }
    }




    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallBack.onVerificationError("Nothing to welcome you with :(");
            }
        });
    }


    private void notifySuccess(final String nameMoroso) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallBack.onVerificationSuccess(nameMoroso);
            }
        });
    }


}
