package br.com.caelum.leilao.teste;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.path.json.JsonPath;

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

}