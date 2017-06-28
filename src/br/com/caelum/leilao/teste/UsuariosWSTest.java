package br.com.caelum.leilao.teste;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;

import br.com.caelum.leilao.modelo.Usuario;

public class UsuariosWSTest {

	@Test
	public void deveRetornarListaDeUsuarios() {
		XmlPath path = given().header("Accept", "application/xml").get("/usuarios").andReturn().xmlPath();

		List<Usuario> usuarios = path.getList("list.usuario", Usuario.class);

		Usuario esperado1 = new Usuario(1L, "Mauricio Aniche", "mauricio.aniche@caelum.com.br");
		Usuario esperado2 = new Usuario(2L, "Guilherme Silveira", "guilherme.silveira@caelum.com.br");

		assertEquals(esperado1, usuarios.get(0));
		assertEquals(esperado2, usuarios.get(1));
	}
	
	@Test
	public void  deveRetornarUsuarioPeloId(){
		JsonPath path = given().parameter("usuario.id", 1).header("Accept", "application/json").get("/usuarios/show").andReturn().jsonPath();
		
		Usuario usuario = path.getObject("usuario", Usuario.class);
		Usuario esperado = new Usuario(1L, "Mauricio Aniche", "mauricio.aniche@caelum.com.br");
		
		assertEquals(esperado, usuario);
	}

}
