package consolidatedconditional.bad;

public class Main {
    private double extraSalary;
    private int seniority;
    private int education;
    private int incidents;
    private boolean certification;


    double calculateExtraSalary() {
        double result = 0;
        if(seniority<1) {
            return 0;
        }

        if(education<1) {
            return 0;
        }

        if(incidents<1) {
            return 0;
        }

        if(!certification) {
            return 0;
        }
        return result;

    }
}
