package com.raj.sample.services;

import java.io.Serializable;
public interface Task extends Serializable {

    public Object doAction(Object... dependencies);

}
