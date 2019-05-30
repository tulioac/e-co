package entities;

import interfaces.CargoPolitico;

public class SemCargo implements CargoPolitico {

	private String cargo;
	
	@Override
	public String getCargo() {
		return "Sem cargo";
	}

}
