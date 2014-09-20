package commons;

public class Comision {
	int ID;
	String Nombre;
	String Inicio;
	String Fin;
	int Estado;
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getInicio() {
		return Inicio;
	}

	public void setInicio(String inicio) {
		Inicio = inicio;
	}

	public String getFin() {
		return Fin;
	}

	public void setFin(String fin) {
		Fin = fin;
	}

	public int getEstado() {
		return Estado;
	}

	public void setEstado(int estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return Nombre;
	}

}
