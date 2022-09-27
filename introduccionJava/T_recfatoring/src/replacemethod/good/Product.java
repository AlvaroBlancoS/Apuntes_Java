package replacemethod.good;

public class Product {
    double price;
    int quantify;
    double discount;
    double shipping;

    //Contructores

    //Metodos

    //Tostring


    public double calculatePrice() {


        return new ProductPriceCalculator(this).calculatePrice();
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
