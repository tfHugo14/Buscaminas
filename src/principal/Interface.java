package principal;

import buscaminas.BuscaMinas;

import java.util.Scanner;

public class Interface {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Interface ic = new Interface();
        BuscaMinas bm = new BuscaMinas();

        System.out.println("Bienvenido al juego del Buscaminas!");
        System.out.println("Vamos a abir unas casillas vacias para empezar el juego");
        ic.printTablero(bm);
        bm.empezarJuego();
        System.out.println("Introduce la fila y columna de la casilla que quieres destapar:");
        int fila = input.nextInt();
        int columna = input.nextInt();
        bm.destaparCasilla(fila-1,columna-1);

        for (int i = 0; i < bm.getCampoDeMinas().length; i++) {
            for (int j = 0; j < bm.getCampoDeMinas()[i].length; j++) {
                System.out.print(" "+bm.getCampoDeMinas()[i][j]);
            }
            System.out.println();
        }
    }

    private void printTablero(BuscaMinas bm){
        for (int i = 0; i < bm.getTablero().length; i++) {
            for (int j = 0; j < bm.getTablero()[i].length; j++) {
                System.out.print(" "+bm.getTablero()[i][j]);
            }
            System.out.println();
        }
    }
}
