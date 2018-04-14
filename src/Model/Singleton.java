package Model;

import Gestores.GestorBD;

/**
 * Created by Javier on 4/13/2018.
 */
public class Singleton {
    private static Singleton singleton ;
    private GestorBD gestorBase;

    private Singleton() {
        gestorBase = new GestorBD();
    }

    public static Singleton getInstance() {
        if (singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }

    public GestorBD getController(){
        return getInstance().gestorBase;
    }
}
