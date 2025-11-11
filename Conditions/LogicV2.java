public class LogicV2 {
    public static void main(String[] args) {
        int edad = 23;
        boolean es_vip = true;
        boolean tiene_entrada = false;

        if (edad <18) {
            System.out.println("No puedes entrar: eres menor de edad");
        }else if(edad < 25 && es_vip){
            System.out.println("Puedes entrar: menor de 25 y eres VIP");
        }else if (edad >=25 && tiene_entrada){
            System.out.println("Puedes entrar: mayor de 25 y tienes entrada");
        }else{
            System.out.println("No puedes entrar: no cumples los requisitos");
        }
    }
}
