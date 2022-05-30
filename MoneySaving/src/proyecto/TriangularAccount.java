package proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class TriangularAccount {
	public String nombre = "";
	public float cantidad = 0F;
	public float ajuste = 0F;  
	public ArrayList<TriangularAccount> a = new ArrayList<TriangularAccount>(MAX);
	public int tam = 0;
	public static final int MAX = 10;
	public static  int contenido(final TriangularAccount d, final String cadena) {
		int i = 0;
		while (i < d.tam && !d.a.get(i).nombre.equals(cadena)) {
			++i;
		}
		return i;
	}
	
	public static void mostrarGastosComunes(final TriangularAccount d) {
		for (int i = 0; i < d.tam; i++) {
			System.out.print(d.a.get(i).nombre);
			System.out.print(" ha gastado en comun ");
			System.out.print(d.a.get(i).cantidad);
			System.out.print("\n");
		}
	}
	
	public static float calcularMedia(final TriangularAccount d) {
		float suma = 0F;
		for (int i = 0; i < d.tam; i++) {
			suma += d.a.get(i).cantidad;
		}
		return (suma / d.tam);
	}
	
	public static void pagarRecibir(TriangularAccount  d, float media) {
		float cant;
		for (int i = 0; i < d.tam; i++) {
			cant = d.a.get(i).cantidad - media;
			if (cant < 0F) {
				d.a.get(i).ajuste = cant;
				System.out.print(d.a.get(i).nombre);
				System.out.print(" debe pagar ");
				System.out.print(-cant);
				System.out.print("\n");
			}
			else if (cant > 0F) {
				d.a.get(i).ajuste = cant;
				System.out.print(d.a.get(i).nombre);
				System.out.print(" debe recibir ");
				System.out.print(cant);
				System.out.print("\n");
			}
			else {
				System.out.print(d.a.get(i).nombre);
				System.out.print(" esta a la par");
				System.out.print("\n");

			}

		}
	}
	
	public static boolean gastoMayorMenor(final TriangularAccount  d, float media) {
		int i = 0;
		boolean res = true;
		float a = (float) (media - 0.01);
		float b = (float) (media + 0.01);
		while (i < d.tam && res) {
			if (!((d.a.get(i).cantidad > a) && (d.a.get(i).cantidad < b))) {
				res = false;
			}
			++i;
		}
		return res;
	}
			
	public static int MaxPagar(final TriangularAccount  d) {
		int num = -1; // buscamos el numero mas negativo.
		int pos = 0;
		for (int i = 0; i < d.tam; i++) {
			if (num > d.a.get(i).ajuste);
			pos = i;

		}
		return pos;
	}
           
	public static int MaxRecibir(final TriangularAccount  d) {
		int num = -1; 
		int pos = 0;
		for (int i = 0; i < d.tam; i++) {
			if (num < d.a.get(i).ajuste) {
				num = (int)(d.a.get(i).ajuste);
				pos = i;	
			}
		}
		return pos;
	}
	public static float valorAbsoluto(float num) {
		if (num < 0F) {
			num = -num;
		}
		return num;
	}
	
	public static void pagarA(TriangularAccount  d) {
		int posMaxPa = MaxPagar(d); // El que mas debe.
		int posMaxRe = MaxRecibir(d); // El que mas recibe.
		
		if (posMaxRe != -1 || posMaxPa != -1) {
			if (valorAbsoluto(d.a.get(posMaxRe).ajuste) >= valorAbsoluto(d.a.get(posMaxPa).ajuste)) {
				System.out.print(d.a.get(posMaxPa).nombre);
				System.out.print(" paga ");
				System.out.print(-d.a.get(posMaxPa).ajuste);
				System.out.print(" a ");
				System.out.print(d.a.get(posMaxRe).nombre);
				System.out.print("\n");
				d.a.get(posMaxRe).cantidad -= valorAbsoluto(d.a.get(posMaxPa).ajuste);
				d.a.get(posMaxRe).ajuste -= valorAbsoluto(d.a.get(posMaxPa).ajuste);
				d.a.get(posMaxPa).cantidad += valorAbsoluto(d.a.get(posMaxPa).ajuste);
				d.a.get(posMaxPa).ajuste = 0F;

			} else {
				System.out.print(d.a.get(posMaxPa).nombre);
				System.out.print(" paga ");
				System.out.print(d.a.get(posMaxRe).ajuste);
				System.out.print(" a ");
				System.out.print(d.a.get(posMaxRe).nombre);
				System.out.print("\n");
					}

				}
	}	
				
	private static boolean goodLastRead = false;
	public static boolean lastReadWasGood() {
		return goodLastRead;
	}
	public static String readToWhiteSpace(boolean skipLeadingWhiteSpace) throws IOException {
		String input = "";
		char nextChar;
		while (Character.isWhitespace(nextChar = (char)System.in.read())) {
			if (!skipLeadingWhiteSpace)
			{
				input += nextChar;
			}
		}
		input += nextChar;
		try {
			while (!Character.isWhitespace(nextChar = (char)System.in.read())) {
				input += nextChar;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		goodLastRead = input.length() > 0;
		return input;
	}
	public static String scanfRead() throws IOException {
		return scanfRead(null, -1);
	}
	public static String scanfRead(String unwantedSequence) throws IOException {
		return scanfRead(unwantedSequence, -1);
	}
	public static String scanfRead(String unwantedSequence, int maxFieldLength) throws IOException {
		String input = "";

		char nextChar;
		if (unwantedSequence != null) {
			nextChar = '\0';
			for (int charIndex = 0; charIndex < unwantedSequence.length(); charIndex++) {
				if (Character.isWhitespace(unwantedSequence.charAt(charIndex))) {
					while (Character.isWhitespace(nextChar = (char)System.in.read())) {
				}

			} else {
				nextChar = (char)System.in.read();
				if (nextChar != unwantedSequence.charAt(charIndex))
					return null;
			}

		}
			input = (new Character(nextChar)).toString();
			if (maxFieldLength == 1)
				return input;
	}
		while (!Character.isWhitespace(nextChar = (char)System.in.read())) {
			input += nextChar;
			if (maxFieldLength == input.length())
				return input;
		}
		return input;
 	}
}			
