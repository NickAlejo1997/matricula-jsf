# matricula-jsf — Desarrollo Web Integrado (Semana 11)

Proyecto base para la sesión **JSF: navegación, etiquetas, EL y tablas de datos**.

## Requisitos
- Java 21 (probado con 21.0.x)
- Maven 3.9+
- Jetty 12 EE10 (lo trae `jetty-ee10-maven-plugin`, no se instala aparte)
- BootsFaces 2.0.1 para las vistas de listado y detalle

## Ejecutar
```
mvn jetty:run
```
Abrir: http://localhost:8080/matricula/

Detener: `Ctrl + C`

## Estructura
```
matricula-jsf/
├── pom.xml                         <- Jetty 12 EE10 + Java 21 + Mojarra 4 + BootsFaces 2 + Weld CDI
└── src/main/
    ├── java/pe/utp/matricula/
    │   ├── model/Curso.java        <- POJO (getters para EL)
    │   └── bean/
    │       ├── CursoBean.java      <- lista de cursos + buscar(id)
    │       └── MatriculaBean.java  <- formulario, guardar() devuelve outcome
    └── webapp/
        ├── index.xhtml             <- inicio (navegación implícita)
        ├── listado.xhtml           <- h:dataTable + h:outputLink + f:param
        ├── detalle.xhtml           <- #{param.id} + bean.buscar(...)
        ├── matricula.xhtml         <- h:inputText + f:validateLength
        ├── confirmacion.xhtml      <- destino del outcome "exito"
        ├── resources/css/estilos.css
        └── WEB-INF/
            ├── web.xml             <- FacesServlet para *.xhtml
            └── faces-config.xml    <- navigation-rule (navegación explícita)
```

## Qué diapositiva practica cada archivo
| Diapositiva | Tema | Dónde verlo |
|---|---|---|
| 5 | Botón → action → outcome | `matricula.xhtml` + `MatriculaBean.guardar()` |
| 6 | Navegación implícita / explícita | `index.xhtml` (implícita) y `faces-config.xml` (explícita, con redirect) |
| 7 | Etiquetas `h:` y `f:` | todas las vistas |
| 8 | Expression Language | `#{cursoBean...}`, `#{matriculaBean...}`, `#{param.id}` |
| 9 | `b:dataTable`, columna Créditos y estado calculado | `listado.xhtml` |
| 10-11 | Listado → detalle con `f:param` | `listado.xhtml` → `detalle.xhtml` |
| 13 | Validar el id en el servidor | `CursoBean.buscar()` (id no numérico o inexistente → null) |

## Pruebas para los alumnos (evidencia de la diapositiva 11)
1. `/matricula/listado.xhtml` muestra 4 filas.
2. Clic en "Ver" → URL `detalle.xhtml?id=2` y datos del curso 2.
3. Probar `detalle.xhtml?id=999` y `detalle.xhtml?id=abc` → mensaje "El curso solicitado no existe", sin excepciones.
4. Formulario vacío → vuelve a `matricula.xhtml` (outcome `error`). Completo → `confirmacion.xhtml` y la URL cambia (redirect).

## Notas
- Jetty 12 EE10 usa Jakarta EE 10 (`jakarta.*`), por eso el proyecto utiliza Mojarra **4.0.x** y BootsFaces **2.0.1**. No usar imports `javax.*`.
- Los beans usan CDI con `@Named`; Weld proporciona CDI dentro del contenedor Jetty.
- El primer curso tiene 0 vacantes para demostrar el estado **Agotado**; los demás muestran **Disponible**.
