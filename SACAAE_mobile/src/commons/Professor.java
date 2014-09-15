package commons;

public class Professor {
	String Nombre;

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	@Override
	public String toString() {
		return  Nombre;
	}
	

}
