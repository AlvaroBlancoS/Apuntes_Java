import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Emisor emisor = new Emisor();

        Receptor receptor = new Receptor();
        emisor.meteReceptor(receptor);

        ReceptorMusical receptorMusical = new ReceptorMusical();

        emisor.meteReceptor(receptorMusical);
        emisor.saluda();
    }
}

interface Mensajero{
    void hanSaludado();
}
class Emisor{
    private ArrayList<Mensajero> receptores= new ArrayList<>();

    public void meteReceptor(Mensajero receptor){
        receptores.add(receptor);
    }

    public void saluda(){
//        System.out.println("Hola!!!");
        for (Mensajero ml: receptores) {
            ml.hanSaludado();
        }
    }
}

class Receptor implements Mensajero{

    @Override
    public void hanSaludado() {
        System.out.println("Hola en clase de receptor");
    }
}

class ReceptorMusical implements Mensajero{

    @Override
    public void hanSaludado() {
        System.out.println("Soy receptor musical");
    }
}
