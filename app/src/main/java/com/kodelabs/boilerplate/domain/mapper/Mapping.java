package com.kodelabs.boilerplate.domain.mapper;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created on 21/06/16.
 * by Javier Solis @JavierTwiteando
 * Github http://bit.ly/onGithub
 * Company @PineappleLab
 * Contact hellopineapplelab@gmail.com
 *
 *
 * Anotacion para escoger que campo de la clase A se debe enlazar con la clase B
 *
 * Example:
 * @Mapping( "campoA5:campoB5" )
 *
 * Cuando lea el campoA5 de A, lo setear al campoB5 de la clase B
 */
@Retention(RUNTIME)
public @interface Mapping {
    String[] value();

}
