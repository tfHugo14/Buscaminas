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
        abrirPrimeraCasillaVacia();
    }

    private void abrirPrimeraCasillaVacia(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (campoDeMinas[i][j] == '-'){
                    destaparCasilla(i, j);
                    return;
                }
            }
        }
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
                    campoDeMinas[i][j] = cuantasMinasRodenLaCasilla(i,j);
            }
        }
    }

    private char cuantasMinasRodenLaCasilla(int fila, int columna){
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
            return '-';
    }

    public void marcarCasilla(int fila, int columna){
        tablero[fila][columna] = 'M';
    }

    public boolean destaparCasilla(int fila, int columna){
        if (campoDeMinas[fila][columna] == '-'){
            destaparAdyacentes(fila, columna);
            return true;
        }
        else if (campoDeMinas[fila][columna] == '*'){
            tablero[fila][columna] = campoDeMinas[fila][columna];
            return false;
        }
        else {
            tablero[fila][columna] = campoDeMinas[fila][columna];
            return true;
        }
    }

    private void destaparAdyacentes(int fila,int columna){
        for (int i = fila - 1; i <= fila + 1; i++){
            for (int j = columna - 1; j <= columna + 1; j++){
                if (i >= 0 && i < 8 && j >= 0 && j < 8){ // evitar index out of bounds
                    if (campoDeMinas[i][j] == '-'){
                        tablero[i][j] = campoDeMinas[i][j];
                        campoDeMinas[i][j] = '_'; // marcamos la casilla destapada con otro caracter para no destaparla 2 veces
                        destaparAdyacentes(i, j);
                    }
                    else {
                        tablero[i][j] = campoDeMinas[i][j];
                    }
                }
            }
        }
    }
}
