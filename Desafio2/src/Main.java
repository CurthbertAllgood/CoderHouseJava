
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Crear 5 personas con nombres y apellidos aleatorios en español
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Carlos", "Ortiz"));
        personas.add(new Persona("Fernanda", "Zelarayan"));
        personas.add(new Persona("Julian", "Alvarez"));
        personas.add(new Persona("Enzo", "Fernandez"));
        personas.add(new Persona("Lionel", "Messiteamo"));

        // Listar personas de menor a mayor por el nombre
        System.out.println("Personas ordenadas por nombre (ascendente):");
        personas.sort(Comparator.comparing(Persona::getNombre));
        imprimirPersonas(personas);

        // Listar personas de menor a mayor por el apellido
        System.out.println("\nPersonas ordenadas por apellido (ascendente):");
        personas.sort(Comparator.comparing(Persona::getApellido));
        imprimirPersonas(personas);

        // Listar personas de mayor a menor por el apellido
        System.out.println("\nPersonas ordenadas por apellido (descendente):");
        personas.sort(Comparator.comparing(Persona::getApellido).reversed());
        imprimirPersonas(personas);
    }

    private static void imprimirPersonas(@org.jetbrains.annotations.NotNull List<Persona> personas) {
        for (Persona persona : personas) {
            System.out.println(persona.getNombre() + " " + persona.getApellido());
        }
    }
}