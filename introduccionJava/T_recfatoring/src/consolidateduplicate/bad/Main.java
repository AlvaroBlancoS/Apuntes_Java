package consolidateduplicate.bad;

public class Main {
    private double extraSalary;
    private int seniority;
    private int education;
    private int incidents;
    private boolean certification;

    /*
        Observamos que el método sentMessage() ser repite dos veces
     */
    double calculateExtraSalary() {
        double result = 0;
        if (isEligibleExtraSalary()) {
            result = 500;
            sendMessage();
        }else {
            result = 0;
            sendMessage();
        }
        //Calculamos el salario exta:
        return result;
    }

    private void sendMessage(){
        // Send email
        //  Connect smtp
        System.out.println("Sending message");
    }

    public boolean isEligibleExtraSalary() {
        //Agrupar condiciones utilizando AND (&&) Y OR (||)
        boolean estudios = seniority < 1 || education < 1;
        boolean antiguedad = incidents > 10 && !certification;
        return estudios || antiguedad;
    }
}
