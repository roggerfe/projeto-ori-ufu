package br.ufu.ori.models;

import java.util.ArrayList;
import java.util.List;

public class Documento {
	
	private String nome;
	private List<Termo> termos;
	
	public Documento(String nome){
		this.nome = nome;
		termos = new ArrayList<>();
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Termo> getTermos() {
		return termos;
	}
	
	public void setTermos(List<Termo> termos) {
		this.termos = termos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Documento [nome=" + nome + ", termos=" + termos + "]";
	}

	
	
}
