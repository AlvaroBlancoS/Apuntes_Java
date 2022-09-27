package extractmethod.good;

public class Main {

        /*
        Esta es la manera de hacer correcto. También se puede trabajar
        manualmente. Depende del programador
     */

    void printHTML() {
        printHead();
        printBody();
        printFooter();
    }

    private void printFooter() {
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

