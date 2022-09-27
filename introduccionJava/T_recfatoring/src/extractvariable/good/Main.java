package extractvariable.good;

import extractvariable.bad.Order;

public class Main {
    /*
        Para ser más entendible, simplemnte hay que hacer los pasos de
        calcular precio total:
     */

    void printProductPrice(Order order) {
        //1. precio de los productos
        Double quantifyPrice = order.getPrice() * order.getQuantify();

        //2. Crear un descuento
        Double offerPrice = order.getPrice() * order.getOffer();

        //3. Calcular precio productos con el descuento incluido
        Double finalPrice = quantifyPrice - offerPrice;

        //4. Precio envío
        Double shippingCost =0.0;
        if (finalPrice < 50){
            shippingCost = 2.99;
        }
        //precio definitivo
        System.out.println(finalPrice+shippingCost);
    }
}
