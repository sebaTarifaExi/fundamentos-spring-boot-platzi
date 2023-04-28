package com.fundamentosplatzi.springboot.fundamentos.bean;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    private final Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("Hemos ingresado al metodo printWithDependency");
        int numero = 1;
        LOGGER.debug("El numero enviado a la dependencia como operacion es: "+numero);
        System.out.println(myOperation.suma(numero));
        System.out.println("Hola desde la implementacion de un bean con dependencia");
    }
}
