package extractvariable.bad;

public class Main {
    /*
        El método que tenemos no entendemos muy bien lo que hace,
        este método tiene que ser algo sencillo para entender.
        Antes que nada, observamos que el parámetro tiene la
        clase Order para asignar los métodos get.
     */
    void printProductPrice(Order order) {
        Double totalPrice = order.getPrice() * order.getQuantify() - order.getOffer() + order.getShipping() * 2;
        System.out.println(totalPrice);
    }
}
