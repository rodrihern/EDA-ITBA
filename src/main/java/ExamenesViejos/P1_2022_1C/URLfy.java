package ExamenesViejos.P1_2022_1C;

public class URLfy {

    public static void main(String[] args) {

        URLfy urlfy = new URLfy();

        char [] ejemplo = new char[] { 'e', 's', ' ', 'u', 'n', ' ', 'e', 'j', 'e', 'm', 'p', 'l', 'o', '\0', '\0', '\0', '\0'};
        urlfy.reemplazarEspacios(ejemplo);
        System.out.println(ejemplo);


        ejemplo= new char [] {'a', ' ', 'b', ' ', 'c', ' ', 'd', ' ', 'e', ' ', 'f', ' ', 'g', ' ', 'h', 'o', 'l', 'a', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0'};
        urlfy.reemplazarEspacios(ejemplo);
        System.out.println(ejemplo);


        ejemplo= new char [] {' ', ' ', 'e', 's', 'p', 'a', 'c', 'i', 'o', 's', ' ', ' ', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0'};
        urlfy.reemplazarEspacios(ejemplo);
        System.out.println(ejemplo);

    }

    public void reemplazarEspacios(char [] str) {
        // tengo suficiente espacio para guardar las cosas
        // cada vez que encuentro ' ' tengo que insertar '%' '2' '0'
        // complejidad espacial O(1)
        // complejidad temporal O(n)

        // como tiene el espacio justo puedo aprobecharlo para empezar desde el final
        // usar una estrategia de double pointer
        // j apunta a donde estoy escribiendo, i a donde estoy leyendo, i <= j

        int i = str.length-1, j = str.length-1;
        // ubico i
        while (str[i] == '\0') {
            i--;
        }
        // ahora copio en j al elementardo
        while (i >= 0) {
            if (str[i] == ' ') {
                // escribo los otros 3 caracteres
                str[j--] = '0';
                str[j--] = '2';
                str[j--] = '%';
            } else {
                // copio
                str[j--] = str[i];
            }
            i--;
        }

    }
}

// TLT lo de justificar la complejidad con cuentas