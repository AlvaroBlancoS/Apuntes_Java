package removeassing.good;

import removeassing.bad.Order;

public class Main {

    double calculateDiscount(Order order, double totalPrecio) {
        double result = totalPrecio;
        if (order == null || order.getPrice() == null) {//programación defensiva
            return result;
        }

        if (order.getPrice() > 100) {
            result += order.getPrice() * order.getOffer();//sobreescribe totalPrice

        }
        return result;
    }
}
