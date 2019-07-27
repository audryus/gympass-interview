package gympass.interview;

import java.util.Calendar;

public class Piloto implements Comparable<Piloto>{

	Calendar hora;
	Calendar inicio;
	String id;
	String nome;
	int volta;
	long tempo;
	float velocidade;
	long tempoTotal;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Piloto other = (Piloto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Piloto [hora=" + Main.hora.format(hora.getTime()) + ", id=" + id + ", nome=" + nome + ", volta=" + volta + ", tempo=" + tempo
				+ ", velocidade=" + velocidade + ", tempoTotal = "+tempoTotal+"]";
	}

	public int getVolta() {
		return volta;
	}

	public void setVolta(int volta) {
		this.volta = volta;
	}

	@Override
	public int compareTo(Piloto o) {
		if (this.volta > o.volta) {
			return 1;
		} else if (this.volta == o.volta) {
			if (this.tempoTotal > o.tempoTotal) {
				return 1;
			} else if(this.tempoTotal < o.tempoTotal) {
				return -1;
			}
		}
			
		return 0;
	}

}
