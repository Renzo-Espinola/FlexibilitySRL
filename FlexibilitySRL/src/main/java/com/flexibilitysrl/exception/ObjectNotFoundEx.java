package com.flexibilitysrl.exception;

import javassist.tools.rmi.ObjectNotFoundException;

public class ObjectNotFoundEx extends ObjectNotFoundException {
        public ObjectNotFoundEx(String message) {super (message);}
    }
