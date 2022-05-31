package test;

import proyecto.Login;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class LoginTest {
	
	private static Login l;
	
	@BeforeAll
	static void initAll() {
		l = new Login();
	}
	
	@Test @Order(1)
	void notRegisterT() {
		// El usuario aun no está registrado
		File file1 = new File("./database.txt");
		File file2 = new File("./recuperacion.txt");
		assertTrue(!file1.exists() && !file2.exists());	
	}
	
	@Test @Order(2)
	void registerFalseT() throws IOException {
		// El usuario no introduce el nombre
		boolean aux = l.register(null, "1234", false);
		assertFalse(aux);
	}
	
	@Test @Order(3)
	void registerEmptyPinT() throws IOException {
		// No deberia poder registrarse con un pin vacio
		boolean aux = l.register("Javi", "", false);
		assertFalse(aux);
	}
	
	@Test @Order(4)
	void registerTrueT() throws IOException {
		// Los datos introducidos son correctos
		boolean aux = l.register("Javi", "1234", false);
		assertTrue(aux);
	}
	
	@Test @Order(5)
	void loginT() {
		// El usuario ya está registrado
		File file1 = new File("./database.txt");
		File file2 = new File("./recuperacion.txt");
		assertTrue(file1.exists() && file2.exists());
	}

}
