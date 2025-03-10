package modelo;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class LineaEstacionPK implements Serializable {

	@Column(name = "codLinea")
    private int codLinea;
	
	@Column(name = "codEstacion")
    private int codEstacion;

    public LineaEstacionPK(int codLinea, int codEstacion) {
        this.codLinea = codLinea;
        this.codEstacion = codEstacion;
    }
    
    public LineaEstacionPK() {
    	
    }

    public int getCodLinea() {
        return codLinea;
    }

    public void setCodLinea(int codLinea) {
        this.codLinea = codLinea;
    }

    public int getCodEstacion() {
        return codEstacion;
    }

    public void setCodEstacion(int codEstacion) {
        this.codEstacion = codEstacion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codLinea, codEstacion);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        LineaEstacionPK other = (LineaEstacionPK) obj;
        return codLinea == other.codLinea && codEstacion == other.codEstacion;
    }

	@Override
	public String toString() {
		return "LineaEstacionPK [codLinea=" + codLinea + ", codEstacion=" + codEstacion + "]";
	}
    
    
    
}
