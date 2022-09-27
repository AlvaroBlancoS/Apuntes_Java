package extractclass.bad;

public class Person {
    /*
        Esto no es correcto para los atributos,
        Una cosa es la persona y la otra es la dirección.
        Quién sabe que la dirección tambien sirve para empresas,
        locales etc en vez de personas.
     */
    private long id;
    private String firtsName;
    private String lastName;
    private int age;
    //address
    private String street;
    private String postalCode;
    private String city;
    private String country;
    private String door;
}
