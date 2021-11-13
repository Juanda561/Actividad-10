
package entidades;

public class Equipo implements Comparable<Equipo>{
    private String nombre;
    private Integer partidos_jugados;
    private Integer partidos_ganados;
    private Integer partidos_empatados;
    private Integer partidos_perdidos;
    private Integer puntos;

    public Equipo() {
    }

    public Equipo(String nombre, int partidos_jugados, int partidos_ganados, int partidos_empatados, int partidos_perdidos, int puntos) {
        this.nombre = nombre;
        this.partidos_jugados = partidos_jugados;
        this.partidos_ganados = partidos_ganados;
        this.partidos_empatados = partidos_empatados;
        this.partidos_perdidos = partidos_perdidos;
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPartidos_jugados() {
        return partidos_jugados;
    }

    public void setPartidos_jugados(int partidos_jugados) {
        this.partidos_jugados = partidos_jugados;
    }

    public int getPartidos_ganados() {
        return partidos_ganados;
    }

    public void setPartidos_ganados(int partidos_ganados) {
        this.partidos_ganados = partidos_ganados;
    }

    public int getPartidos_empatados() {
        return partidos_empatados;
    }

    public void setPartidos_empatados(int partidos_empatados) {
        this.partidos_empatados = partidos_empatados;
    }

    public int getPartidos_perdidos() {
        return partidos_perdidos;
    }

    public void setPartidos_perdidos(int partidos_perdidos) {
        this.partidos_perdidos = partidos_perdidos;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    public String getDatosFileText(){
        return  this.nombre+";"+
                this.partidos_jugados+";"+
                this.partidos_ganados+";"+
                this.partidos_empatados+";"+
                this.partidos_perdidos+";"+
                this.puntos+"";
    }

    @Override
    public int compareTo(Equipo e) {
        return puntos.compareTo(e.getPuntos());
    }
    
}
