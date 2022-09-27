package replacemethod.bad;

public class Product {
    double price;
    int quantify;
    double discount;
    double shipping;

    //Contructores

    //Metodos

    //Tostring


    public double calculatePrice() {
        double result = 0;
        double priceQuantify = this.price * this.quantify;
        double priceDiscount = this.price * this.discount;
        double priceShipping = this.shipping * 0.50;

        return result;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantify() {
        return quantify;
    }

    public void setQuantify(int quantify) {
        this.quantify = quantify;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }
}
