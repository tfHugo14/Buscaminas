package buscaminas;

import java.util.Random;

public class BuscaMinas {
    Random random = new Random();
    private char[][] campoDeMinas = new char[8][8];
    private char[][] tablero =
    {
        {'█','█','█','█','█','█','█','█',},
        {'█','█','█','█','█','█','█','█',},
        {'█','█','█','█','█','█','█','█',},
        {'█','█','█','█','█','█','█','█',},
        {'█','█','█','█','█','█','█','█',},
        {'█','█','█','█','█','█','█','█',},
        {'█','█','█','█','█','█','█','█',},
        {'█','█','█','█','█','█','█','█',},
    };

    public char[][] getTablero() {
        BuscaMinas bm = new BuscaMinas();
        bm.tablero = this.tablero;
        return bm.tablero;
    }
    public char[][] getCampoDeMinas() {
        BuscaMinas bm = new BuscaMinas();
        bm.campoDeMinas = this.campoDeMinas;
        return bm.campoDeMinas;
    }

    public void empezarJuego(){
        plantarMinas();
        colocarNumeros();
    }

    private void plantarMinas(){
        int minaX;
        int minaY;
        int numMinas = 10;

        for (int j = 0; j < numMinas; j++) {
            minaX = random.nextInt(8);
            minaY = random.nextInt(8);
            campoDeMinas[minaX][minaY] = '*';
        }
    }

    private void colocarNumeros(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (campoDeMinas[i][j] == '\u0000')
                    campoDeMinas[i][j] = numeroMinas(i,j);
            }
        }
    }

    private char numeroMinas(int fila, int columna){
        //recorre alrededor de la casilla para saber cuantas minas le rodean
        int minas = 0;
        for (int i = fila - 1; i <= fila + 1; i++){
            for (int j = columna - 1; j <= columna + 1; j++){
                if (i >= 0 && i < 8 && j >= 0 && j < 8){ // evitar index out of bounds
                    if (campoDeMinas[i][j] == '*'){
                        minas++;
                    }
                }
            }
        }
        // pasamos minas a char
        if (minas > 0)
            return (char) (minas + '0');
        else
            return '_';
    }

    public void destaparCasilla(int fila, int columna){
        if (campoDeMinas[fila][columna] == '_'){
            destaparAdyacentes(fila, columna);
            //tablero[fila][columna] = '_';
        }
        else if (campoDeMinas[fila][columna] == '*'){
            tablero[fila][columna] = campoDeMinas[fila][columna];
        }
        else {
            // si la casilla tiene un numero, destapamos solo esa casilla
            tablero[fila][columna] = campoDeMinas[fila][columna];
        }
    }

    private void destaparAdyacentes(int fila,int columna){
        for (int i = fila - 1; i <= fila + 1; i++){
            for (int j = columna - 1; j <= columna + 1; j++){
                if (i >= 0 && i < 8 && j >= 0 && j < 8){ // evitar index out of bounds
                    if (campoDeMinas[i][j] == '_'){
                        tablero[i][j] = campoDeMinas[i][j];
                        campoDeMinas[i][j] = 'd'; // marcamos la casilla como destapada
                        destaparAdyacentes(i, j);
                    }
                    else {
                        // si la casilla tiene un numero, destapamos solo esa casilla
                        tablero[i][j] = campoDeMinas[i][j];
                    }
                }
            }
        }
    }
}
