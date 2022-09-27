package consolidatedconditional.good;

public class Main {
    private double extraSalary;
    private int seniority;
    private int education;
    private int incidents;
    private boolean certification;
    /*
        Para hacer un método extra, simplemente seleccionar las condicionales que se repiten
        y dar un clic de botón derecho de ratón y seleccinar en refatorinc...
        Abrirá la siguiente ventana y simplemente un clic en Extract Method...
     */

    double calculateExtraSalary() {
        double result = 0;
        Integer x = getInteger();
        if (x != null) return x;
        return result;

    }
    private Integer getInteger() {
        if (seniority < 1) {
            return 0;
        }

        if (education < 1) {
            return 0;
        }

        if (incidents < 1) {
            return 0;
        }

        if (!certification) {
            return 0;
        }
        return null;
    }

    /*
        Sin embargo, esta es la mejor manera de hacer porque
        el código es corto
     */
    double calculateExtraSalary2() {
        double result = 0;
        if (!isEligibleExtraSalary()) {
            return result;
        }
        //Calculamos el salario exta:
        return result;
    }

    public boolean isEligibleExtraSalary() {
        //Agrupar condiciones utilizando AND (&&) Y OR (||)
        boolean estudios = seniority < 1 || education < 1;
        boolean antiguedad = incidents > 10 && !certification;
        return estudios || antiguedad;
    }

}
