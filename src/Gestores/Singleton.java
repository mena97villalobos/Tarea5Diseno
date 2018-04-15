package Gestores;

/**
 * Created by Javier on 4/13/2018.
 */
public class Singleton {
    private static Singleton singleton ;
    private InterfazGestores gestorBase;

    private Singleton() {
        gestorBase = new GestorBD();//Se cambia el gestor DB dependiendo de la base a la que se desea conectar
    }

    public static Singleton getInstance() {
        if (singleton == null)
            singleton = new Singleton();
        return singleton;
    }

    public InterfazGestores getGestor(){
        return getInstance().gestorBase;
    }
}
