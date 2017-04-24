package com.pokesgi.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ostro on 25/03/2017.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CreationFailedException extends RuntimeException {
}
