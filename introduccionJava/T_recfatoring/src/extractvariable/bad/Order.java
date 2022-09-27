package extractvariable.bad;

public class Order {

    /*
        Tiene atributos y métodos de getters y setters
        No hay que modificar nada, está bien para practicar
        la clase Main.
     */

    private Double price;
    private Integer quantify;
    private Double offer;
    private Double shipping;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantify() {
        return quantify;
    }

    public void setQuantify(Integer quantify) {
        this.quantify = quantify;
    }

    public Double getOffer() {
        return offer;
    }

    public void setOffer(Double offer) {
        this.offer = offer;
    }

    public Double getShipping() {
        return shipping;
    }

    public void setShipping(Double shipping) {
        this.shipping = shipping;
    }
}
