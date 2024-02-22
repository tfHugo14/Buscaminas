package principal;

import buscaminas.BuscaMinas;

import java.util.Scanner;

public class Interface {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Interface ic = new Interface();
        BuscaMinas bm = new BuscaMinas();

        String verde = "\u001B[32m";
        String rojo = "\u001B[31m";
        String violeta = "\u001B[35m";
        String reset = "\u001B[0m";

        // texto irrelevante e inicializacion del juego
        System.out.println(verde+"Bienvenido al juego del Buscaminas!"+reset);
        System.out.println("Este tablero de 8x8 es donde jugarás: ");
        ic.printTablero(bm);
        bm.empezarJuego();
        System.out.println(
                "\n"+verde+"Como Jugar: \n"+reset+
                "Elige una casilla para destapar, si destapas una "+rojo+"Mina"+reset+" pierdes. \n" +
                "Si destapas una "+verde+"casilla vacia"+reset+", se destaparan las casillas adyacentes. \n" +
                "Si destapas una casilla con un "+violeta+"numero"+reset+", este te indicara cuantas minas hay alrededor. \n" +
                "Tu objetivo es destapar todas las casillas sin encontrar ninguna mina, Buena suerte! \n"
        );
        System.out.println("Vamos a abir una casilla vacia para empezar el juego:");
        ic.printTablero(bm);

        // bucle del juego
        String accionJugador = ""; String resultadoPartida = "";
        while (!accionJugador.equalsIgnoreCase("S")) {
            System.out.println("Que quieres hacer, destapar una casilla [" + verde + "D" + reset + "], marcar una mina [" + rojo + "M" + reset + "] o salir del juego [" + violeta + "S" + reset + "]?");
            accionJugador = input.next();
            if (accionJugador.equalsIgnoreCase("D")) {
                System.out.println("Introduce la fila y columna de la casilla que quieres " + verde + "Destapar" + reset + " [1 - 8]:");
                int fila = input.nextInt();
                int columna = input.nextInt();
                if ( !bm.destaparCasilla(fila - 1, columna - 1) ){ resultadoPartida="Derrota"; break;}
                ic.printTablero(bm);
            } else if (accionJugador.equalsIgnoreCase("M")) {
                System.out.println("Introduce la fila y columna de la casilla que quieres " + rojo + "Marcar" + reset + " [1 - 8]:");
                int fila = input.nextInt();
                int columna = input.nextInt();
                bm.marcarCasilla(fila - 1, columna - 1);
                ic.printTablero(bm);
            } else if (!accionJugador.equalsIgnoreCase("s")){
                System.out.println(rojo + "Esa accion no es valida! cerrando el juego..." + reset);
                break;
            }
        }
        if ( resultadoPartida.equals("Derrota") ){
            System.out.println("###################################################");
            System.out.println("   "+rojo+"La partida ha terminado, has pisado una mina"+reset+"      ");
            System.out.println("###################################################");
            ic.printTablero(bm);
        }
    }

    private void printTablero(BuscaMinas bm){
        String verde = "\u001B[32m";
        String rojo = "\u001B[31m";
        String azul = "\u001B[34m";
        String violeta = "\u001B[35m";
        String reset = "\u001B[0m";

        for (int i = 0; i < bm.getTablero().length; i++) {
            for (int j = 0; j < bm.getTablero()[i].length; j++) {
                if (bm.getTablero()[i][j] == '_')
                    System.out.print(" "+verde+bm.getTablero()[i][j]+reset);
                else if (bm.getTablero()[i][j] == '*')
                    System.out.print(" "+rojo+bm.getTablero()[i][j]+reset);
                else if (bm.getTablero()[i][j] == 'M')
                    System.out.print(" "+rojo+bm.getTablero()[i][j]+reset);
                else if (bm.getTablero()[i][j] == '█')
                    System.out.print(" "+azul+bm.getTablero()[i][j]+reset);
                else
                    System.out.print(" "+violeta+bm.getTablero()[i][j]+reset);
            }
            System.out.println();
        }
    }
}
