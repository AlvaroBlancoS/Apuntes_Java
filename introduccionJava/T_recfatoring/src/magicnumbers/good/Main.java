package magicnumbers.good;

public class Main {

    /*
        Este es el resultado. Ahora aparecen todas las variables constantes
        con final. Todas tienen que ser mayúsculas para poder identificar qué
        variables son.
     */
    private static final double PRECIO_MINIMO_COMPRA = 100;
    private static final double PRECIO_ENVIO = 2.99;
    private static final double ENVIO_GRATIS = 0;
    private static final double DESCUENTO = 0.1;

    public static void main(String[] args) {
        double price = 129.99;
        double discountPrince = calculateDiscount(price);
        double shippingPrice = calculateShipping(price);

    }

    private static double calculateShipping(double price) {
        //Es un operador ternario
        return price < PRECIO_MINIMO_COMPRA ? PRECIO_ENVIO : ENVIO_GRATIS;//hard coded
    }

    private static double calculateDiscount(double price) {
        return price * DESCUENTO;
    }

}
