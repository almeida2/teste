package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.demo.model.Fatura;

class TestaFatura2Tests {
	Fatura fatura = new Fatura();

	public String obtemDataAtual() {
		DateTime data = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
		// DateTime dataVencimento = dataAtual.plusDays(10);
		return data.toString(fmt);
	}

	String dataVenc = obtemDataAtual();

	@ParameterizedTest
	@CsvSource({

			"1,71112917000126, 28/09/2023, moveis planejados, 1500, fatura not null",  //classe valida
			"1,71112917000126, 15/01/2023, moveis planejados, 1500, Data de vencimento invalida" // dt venc domingo
			
	})

	// é possivel criar um arquivo csv com a massa de dados no source folder de teste
	// @CsvFileSource(resources = "\\fatura.csv", numLinesToSkip = 1)
	void validaFatura(int numero, String cnpj, String dataVencimento, String desc, String valor, String re) {
		try {
			fatura = new Fatura(numero, cnpj, dataVencimento, desc, valor);
			assertNotNull(fatura);
			assertTrue(obtemDataAtual().equals(fatura.getDataEmissao()));

		} catch (Exception e) {
			assertEquals(re, e.getMessage());
		}
	}

}
