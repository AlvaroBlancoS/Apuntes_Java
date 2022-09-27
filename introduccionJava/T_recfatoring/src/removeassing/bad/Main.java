package removeassing.bad;

public class Main {

    double calculateDiscount(Order order, double totalPrecio) {
        if (order == null || order.getPrice() == null) {//programación defensiva
            return totalPrecio;
        }

        if (order.getPrice() > 100) {
            totalPrecio += order.getPrice() * order.getOffer();//sobreescribe totalPrice

        }
        return totalPrecio;
    }
}
