import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//Se importa el objeto Scanner para poder tomar datos del teclado
public class NumeroFlotanteOrtiz {
    public static void main(String[] args) {
        //Se declaran los atributos numeroEntero y numeroDecimal
        int numeroEntero;
        float numeroDecimal;
//se inicializa el objeto scanner
        Scanner input= new Scanner(System.in);
        //Se solicita un numero decimal al usuario
        System.out.printf("Ingrese el numero decimal deseado: ");
        numeroDecimal=input.nextFloat();
        //se cambia el tipo de dato de numeroDecimal para que el tipo de dato int tome el entero
        numeroEntero = (int) numeroDecimal;
        //Se muestra el dato numeroEntero
        System.out.printf("El valor entero es : "+ numeroEntero);
        //Se resta el entero a numeroDecimal y se muestra el resultado.
        System.out.println("\nEl valor decimal es: "+(numeroDecimal-numeroEntero));
        //Se cierra el objeto Scanner para evitar fallos.
        input.close();
    }

}