package Models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Moneda {
    @SerializedName("base_code")
    private String nombre;

    @SerializedName("conversion_rates")
    private Map<String, Double> conversion;


    public Moneda( String nombre, Map<String, Double> conversion) {
        this.conversion = conversion;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<String, Double> getConversion() {
        return conversion;
    }

    public void setConversion(Map<String, Double> conversion) {
        this.conversion = conversion;
    }

    @Override
    public String toString() {
        return "Moneda{" +
                "nombre='" + nombre + '\'' +
                ", conversion='" + conversion + '\'' +
                '}';
    }
}
