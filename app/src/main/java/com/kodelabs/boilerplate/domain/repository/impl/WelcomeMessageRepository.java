package com.kodelabs.boilerplate.domain.repository.impl;

import com.kodelabs.boilerplate.domain.repository.MessageRepository;

/**
 * Created on 23/06/16.
 * by Javier Solis @JavierTwiteando
 * Github http://bit.ly/onGithub
 * Company @PineappleLab
 * Contact hellopineapplelab@gmail.com
 */
public class WelcomeMessageRepository implements MessageRepository {
    @Override
    public String getWelcomeMessage() {
        String msg = "Hello Clean Arquitecture!"; // let's be friendly

        // let's simulate some network/database lag
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return msg;
    }
}