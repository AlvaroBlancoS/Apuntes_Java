package com.futbol.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Validaciones {

	public boolean comprobarDNI(String dni) {
		List<Character> listCharDNI = Collections.unmodifiableList(Arrays.asList('T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F',
				'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'));
		StringBuilder num = new StringBuilder();

		if (dni.length() != 9 || !Character.isLetter(dni.charAt(8))) {
			return false;
		}

		for (int i = 0; i < 8; i++) {
			if (!Character.isDigit(dni.charAt(i))) {
				return false;
			}
			num.append(dni.charAt(i));
		}

		return Character.toUpperCase(dni.charAt(8)) == listCharDNI.get(Integer.parseInt(num.toString()) % 23);
	}

	public boolean comprobarNIE(String nie) {
		if (nie.length() == 9) {
			String nieModificado;
			char primeraLetra = nie.charAt(0);

			if (Character.toUpperCase(primeraLetra) == 'X') {
				nieModificado = "0" + nie.substring(1);
			} else if (Character.toUpperCase(primeraLetra) == 'Y') {
				nieModificado = "1" + nie.substring(1);
			} else if (Character.toUpperCase(primeraLetra) == 'Z') {
				nieModificado = "2" + nie.substring(1);
			} else {
				return false;
			}

			return comprobarDNI(nieModificado);
		}
		return false;
	}

}
