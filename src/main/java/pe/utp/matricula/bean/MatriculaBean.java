package pe.utp.matricula.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

/**
 * Managed Bean del formulario de matricula.
 * guardar() devuelve un OUTCOME (texto). JSF busca la regla de navegacion
 * con ese nombre en faces-config.xml (navegacion explicita).
 *
 * Es SessionScoped para que el dato sobreviva al redirect hacia confirmacion.xhtml.
 */
@Named("matriculaBean")
@SessionScoped
public class MatriculaBean implements Serializable {

    private String codigo;
    private String nombre;

    public String guardar() {
        if (codigo == null || codigo.isBlank() || nombre == null || nombre.isBlank()) {
            return "error";   // vuelve al formulario
        }
        return "exito";       // abre confirmacion.xhtml
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
