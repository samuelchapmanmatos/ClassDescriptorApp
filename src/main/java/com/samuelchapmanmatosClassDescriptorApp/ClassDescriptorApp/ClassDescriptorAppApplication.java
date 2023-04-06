package com.samuelchapmanmatosClassDescriptorApp.ClassDescriptorApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClassDescriptorAppApplication {
	
	private static int utlCadena;

	public static void main(String[] args) {
		SpringApplication.run(ClassDescriptorAppApplication.class, args);	

		try {

			FileReader archivo;
			BufferedReader lector;

			try {
				String nameTxtFile = args[0];
				archivo = new FileReader(nameTxtFile);
				if (archivo.ready()) {
					lector = new BufferedReader(archivo);
					String cadena;

					while ((cadena = lector.readLine()) != null) {

						String[] indice = cadena.split("[{]");

						for (int i = 0; i < indice.length; i++) {

							if (!indice[i].toString().equals("") && !indice[i].toString().equals(" ")
									&& !indice[i].toString().contains("\\n}\\r")) {

								String cadena3 = cadena.replaceAll("}", "");
								cadena3 = cadena3.trim();

								int indiceLength = indice.length;
								String cadenaRecorrida = indice[i].toString();

								imprimeCadena(cadena3, indiceLength, cadenaRecorrida);
							}

						}

					}
				} else {
					System.out.println("El archivo no esta listo para ser leido...");
				}

			} catch (FileNotFoundException e) {
				e.getMessage();
			} catch (IOException e) {
				e.getMessage();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	
	
	private static void imprimeCadena(String cadena3, int indiceLength, String par) {
		if (!cadena3.equals("") && indiceLength >= 0) {
			if (par.contains("class")) {

				String cadena4 = cadena3.replaceAll("[{]", "");
				String cadena5 = cadena4.replaceAll("[;]", "");
				String cadena6 = cadena5.trim();
				String[] newStr = cadena6.split("\\s+");

				for (int y = 0; y < newStr.length; y++) {
					ClassDescriptorAppApplication.utlCadena = y;
				}

				System.out.println("CLASS NAME : " + newStr[ClassDescriptorAppApplication.utlCadena]);
				System.out.println("SCOPE : " + newStr[0]);
				System.out.println("CONSTRUCTOR : " + newStr[ClassDescriptorAppApplication.utlCadena]);

				System.out.println("_______________________________________________________________");
				System.out.println("|Name      |VARI  |SCOPE       |SIGNATURE                     |");
				System.out.println("|__________|______|____________|______________________________|");
			}

			if (((!par.contains("class") && par.length() >= 2 && (par.contains("(")))
					|| (par.contains(";") && par.length() >= 2))
					&& (par.contains("String") || par.contains("boolean") || par.contains("byte")
							|| par.contains("long") || par.contains("float") || par.contains("chart")
							|| par.contains("short") || par.contains("ArrayList") || par.contains("Vector")
							|| par.contains("Byte") || par.contains("Short") || par.contains("Integer")
							|| par.contains("Long") || par.contains("Float") || par.contains("Double")
							|| par.contains("Character") || par.contains("Boolean") || par.contains("void")
							|| par.contains("int"))) {
				String cadena4 = cadena3.replaceAll("[{]", "");
				String vari = "";
				if (cadena4.contains(";")) {
					vari = "A";
				}
				if (cadena4.contains("(")) {
					vari = "M";
				}
				String cadena5 = cadena4.replaceAll("[;]", "");
				String cadena6 = cadena5.trim();
				String[] newStr = cadena6.split("\\s+");

				for (int y = 0; y < newStr.length; y++) {
					ClassDescriptorAppApplication.utlCadena = y;
				}

				if ("A".equals(vari)) {
					System.out.println("|" + newStr[2] + "    |" + vari + "     |" + newStr[0] + "      |" + "TYPE : "
							+ newStr[1]);
				} else {

					int iAbrPar2 = cadena6.indexOf('(');

					int iCerrPar = cadena6.indexOf(')');
					int iAbrPar = newStr[2].indexOf('(');
					int iCerrPar2 = cadena6.indexOf('(');
					String paramMetName = cadena6.substring(iAbrPar2, iCerrPar + 1);
					System.out.println("|" + String.format("%." + iAbrPar + "s", newStr[2]) + "    |" + vari + "     |"
							+ newStr[0] + "      |" + "TYPE : " + newStr[1] + ", PARAMS : " + paramMetName);
				}
				System.out.println("|__________|______|____________|______________________________|");
			}

		}
	}


}
