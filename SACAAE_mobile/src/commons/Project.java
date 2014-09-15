package commons;

public class Project {
	int ID;
	String Nombre;
	String Inicio;
	String Fin;
	int Estado;
	String Link;

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

	public String getLink() {
		return Link;
	}

	public void setLink(String link) {
		Link = link;
	}

	@Override
	public String toString() {
		return Nombre;
	}

}
