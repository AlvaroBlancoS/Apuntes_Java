package extractmethod.bad;

public class Main {

    /*
     Observamos que tenemos dos métodos.
     Sin embargo aparecen cuatro salidas por pantalla y
     no es correcto.
     La manera de hacer correcto es seleccionar cuatro salidas
     por pantalla. Un clic de botón de derecho y seleccionamos en Refactor...
     y después, seleccionamos en ExtractMethod
     */

    void printHTML() {
        printHead();
        printBody();
        System.out.println("This is the footer");
        System.out.println("Script");
        System.out.println("Copyright");
        System.out.println("");
    }


    public void printBody() {

    }

    public void printHead() {

    }

}

