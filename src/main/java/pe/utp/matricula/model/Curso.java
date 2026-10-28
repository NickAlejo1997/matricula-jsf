package pe.utp.matricula.model;

/**
 * Modelo simple (POJO): un curso que se puede matricular.
 * Cada propiedad tiene getter, porque EL lee con getters (#{c.nombre}).
 */
public class Curso {

    private final int id;
    private final String nombre;
    private final String docente;
    private final int creditos;
    private final int vacantes;

    public Curso(int id, String nombre, String docente, int creditos, int vacantes) {
        this.id = id;
        this.nombre = nombre;
        this.docente = docente;
        this.creditos = creditos;
        this.vacantes = vacantes;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDocente() { return docente; }
    public int getCreditos() { return creditos; }
    public int getVacantes() { return vacantes; }

    /** Estado derivado de las vacantes disponibles, expuesto a JSF EL. */
    public String getEstadoCalculado() {
        return vacantes > 0 ? "Disponible" : "Agotado";
    }
}
