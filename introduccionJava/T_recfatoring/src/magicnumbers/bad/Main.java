package magicnumbers.bad;

public class Main {
    /*
        Observamos que el método calculateShipping tiene un operador
        ternario que indica que si el precio es menor que cien entonces
       el precio de envío sería 4.99 o gratis. Sin embargo, es muy
       recomable crear variables constantes para que no permita modificar
       los valores para este método.
       Y por supuesto el método calculaDiscount también es necesario de agregar
       una variable constante que es descuento
     */
    public static void main(String[] args) {
        double price = 129.99;
        double discountPrince = calculateDiscount(price);
        double shippingPrice = calculateShipping(price);

    }

    private static double calculateShipping(double price) {
        return price < 100 ? 4.99 : 0;//hard coded
    }

    private static double calculateDiscount(double price) {
        return price * 0.1;
    }

}
