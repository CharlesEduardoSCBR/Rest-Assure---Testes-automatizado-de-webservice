package br.com.caelum.leilao.teste;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;

import br.com.caelum.leilao.modelo.Leilao;
import br.com.caelum.leilao.modelo.Usuario;

public class LeiloesWSTest {

	Usuario usuario;
	
	@Before
	public void init(){
		usuario = new Usuario(1L, "Mauricio Aniche", "mauricio.aniche@caelum.com.br");
	}
	
	@Test
	public void deveRetornarLeilaoPorId() {
		
		JsonPath path = given()
							.parameter("leilao.id", 1)
							.header("Accept", "application/json")
							.get("/leiloes/show")
							.andReturn()
							.jsonPath();
		
		Leilao leilao = path.getObject("leilao", Leilao.class);
		Leilao esperado = new Leilao(1L, "l1", 100.00, usuario, true);
		
		assertEquals(esperado.getId(), leilao.getId());					
	}
	
	@Test
	public void deveRetornarTotalDeLeiloesExistentes(){
		XmlPath path = given()
                .header("Accept", "application/xml")
                .get("/leiloes/total")
                .andReturn().xmlPath();

        int total = path.getInt("int");

        assertEquals(2, total);
	}
	
	@Test
	public void deveAdicionarUmLeilao(){
		Leilao l1 = new Leilao(123L, "l1", 100.00, usuario, true);
		
		XmlPath path = given()
					   		.header("Accept", "application/xml")
					   		.contentType("application/xml")
					   		.body(l1)
					   	.expect()
					   		.statusCode(200)
					   	.when()
					   		.post("/leiloes")
					   	.andReturn()
					   		.xmlPath();
		
		Leilao resposta = path.getObject("leilao", Leilao.class);
		
		assertEquals(l1, resposta);
		
		given()
				.contentType("application/xml")
				.body(resposta)
			.expect()
				.statusCode(200)
			.when()
				.delete("leiloes/deletar")
			.andReturn()
				.asString();
		
	}
}
