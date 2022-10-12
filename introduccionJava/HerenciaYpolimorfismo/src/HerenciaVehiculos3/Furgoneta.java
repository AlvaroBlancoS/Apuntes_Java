package HerenciaVehiculos3;

public class Furgoneta extends Vehiculo {
	private int capacidad_carga;

	private int plazas_extra;

	public Furgoneta(int plazas_extra, int capacidad_carga) {
		super();// Llamar al constructor de la clase padre (Coche)
		this.capacidad_carga = capacidad_carga;
		this.plazas_extra = plazas_extra;
	}

	public String dimeDatosFurgoneta() {
		return "La capacidad es: " + capacidad_carga + " y las plazas son: " + plazas_extra;
	}
	
	

}
