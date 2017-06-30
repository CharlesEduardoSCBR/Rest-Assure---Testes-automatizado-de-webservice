package br.com.caelum.leilao.teste;

import static com.jayway.restassured.RestAssured.expect;

import org.junit.Test;

public class OutrosTest {

	@Test
	public void deveGerarUmCookie() {
		expect()
			.cookie("rest-assured", "funciona")
		 .get("cookie/teste");
	}
	
	@Test
	public void deveGerarUmHeader(){
		expect()
			.header("novo-header", "abc")
		 .get("cookie/teste");
	}
}
