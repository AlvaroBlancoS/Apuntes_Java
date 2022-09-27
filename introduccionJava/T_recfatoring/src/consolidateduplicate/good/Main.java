package consolidateduplicate.good;

public class Main {
    private double extraSalary;
    private int seniority;
    private int education;
    private int incidents;
    private boolean certification;
    /*
        Con un sólo método de sendMessage() es
        suficiente, no tiene que porqué repetir
     */
    double calculateExtraSalary() {
        double result = 0;
        if (isEligibleExtraSalary()) {
            result = 500;
        }else {
            result = 0;
        }
        sendMessage();
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
