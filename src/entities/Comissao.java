package entities;

import java.util.Set;

public class Comissao {

	private String tema;
	private Set<Pessoa> integrantes;
	
	public Comissao(String tema, Set<Pessoa> integrantes) {
		this.tema = tema;
		this.integrantes = integrantes;
	}
	
	@Override
	public int hashCode() {
		return this.tema.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comissao other = (Comissao) obj;
		if (tema == null) {
			if (other.tema != null)
				return false;
		} else if (!tema.equals(other.tema))
			return false;
		return true;
	}
	
}
