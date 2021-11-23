import java.util.InputMismatchException;
import java.util.Scanner;

public class Galo {

	static char[][] tabuleiro;
	static final int TAMANHO = 4;

	public static void inicializacaoTabuleiro() {
		tabuleiro = new char[TAMANHO][TAMANHO]; // cria e define o tamanho do array

		for (int linha = 0; linha < TAMANHO; linha++) {
			for (int coluna = 0; coluna < TAMANHO; coluna++) {
				tabuleiro[linha][coluna] = ' ';
			}
		}
	}

	public static void mostraTabuleiro() {
		for (int linha = 0; linha < TAMANHO; linha++) {
			System.out.println("---------------------");
			System.out.print("| ");

			for (int coluna = 0; coluna < TAMANHO; coluna++) {
				System.out.print(tabuleiro[linha][coluna] + " | ");
			}

			System.out.println();
		}

		System.out.println("---------------------");

	}

	public static boolean fazerJogada(char simbolo) {
		Scanner teclado = new Scanner(System.in);
		int linha = 0;
		int coluna = 0;
		try {
			System.out.println("Insira a linha onde quer jogar");
			linha = teclado.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Erro nos tipos dos dados introduzidos");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado no programa");
		}

		if ((linha < 1) || (linha > TAMANHO)) {// linha invalida
			return false;
		}
		try {

			System.out.println("Insira a coluna onde quer jogar");
			coluna = teclado.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Erro nos tipos dos dados introduzidos");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado no programa");
		}

		if ((coluna < 1) || (coluna > TAMANHO)) {// coluna invalida
			return false;
		}

		if (tabuleiro[linha - 1][coluna - 1] != ' ') {// posicao ja preenchida
			return false;
		}

		// guardar a jogada que foi feita
		tabuleiro[linha - 1][coluna - 1] = simbolo;

		return true; // devolver sucesso

	}

	public static boolean jogoAcabado() {
		char[] diagonalEsquedaDireita = new char[TAMANHO];
		char[] diagonalDireitaEsquerda = new char[TAMANHO];
		int index = 0;
		for (int linha = 0; linha < TAMANHO; linha++) {
			char[] valoresLinhas = new char[TAMANHO];
			char[] valoresColunas = new char[TAMANHO];

			for (int coluna = 0; coluna < TAMANHO; coluna++) {
				valoresLinhas[index] = tabuleiro[linha][coluna];
				valoresColunas[index] = tabuleiro[coluna][linha];

				if ((linha == coluna)) {
					diagonalEsquedaDireita[index] = tabuleiro[linha][coluna];
					diagonalDireitaEsquerda[index] = tabuleiro[linha][(TAMANHO - (linha + 1))];
				}

				if (todosIguais(valoresLinhas) || todosIguais(valoresColunas) || todosIguais(diagonalEsquedaDireita)
						|| todosIguais(diagonalDireitaEsquerda)) {
					return true;
				}
				index++;
			}
			index = 0;
		}

		int camposPreenchidos = 0;
		for (int linha = 0; linha < TAMANHO; linha++) {
			for (int coluna = 0; coluna < TAMANHO; coluna++) {
				if ((tabuleiro[linha][coluna] != ' ')) {
					camposPreenchidos++;
				}

			}
			if (camposPreenchidos == (TAMANHO * TAMANHO)) {
				System.out.println("Empate");
				System.exit(0);
			}
		}

		return false;

	}

	public static boolean todosIguais(char[] valores) {
		char primeiroElemento = valores[0];
		boolean tudoIgual = true;

		for (int i = 1; i < valores.length; i++) {
			if (!(valores[i] == primeiroElemento) || (valores[i] == ' ')) {
				tudoIgual = false;
				break;
			}
		}
		return tudoIgual;
	}

	public static void main(String[] args) {

		inicializacaoTabuleiro();

		char simbolocorrente = 'X';
		boolean jogoterminado = false;
		while (jogoterminado == false) {
			while (fazerJogada(simbolocorrente) == false) {
				System.out.println("Jogada invalida, jogue de novo");
			}
			jogoterminado = jogoAcabado();

			// alterar o simbolo
			if (simbolocorrente == 'X') {
				simbolocorrente = 'O';
			} else {
				simbolocorrente = 'X';
			}

			mostraTabuleiro();
		}
		System.out.println("Parab�ns vo�� ganhou");

	}
}