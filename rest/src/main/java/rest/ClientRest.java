package rest;

import javax.ws.rs.core.Response.Status;

import org.javalite.http.Get;
import org.javalite.http.Http;
import org.javalite.http.Post;

public class ClientRest {

	public static final boolean DEBUG = false;

	public static void main(String[] args) {
//		String url = "https://itrportal.des.receita.fazenda.gov.br:8443/itrportal/rest/convservice/test";
//		String url = "https://itrportal.hom.receita.fazenda.gov.br/itrportal/rest/convservice/test";
//		String url = "https://itrportal.hom.receita.fazenda.gov.br/itrportal/rest/nrdservice/test";
//		String url = "https://itrportal.hom.receita.fazenda.gov.br/itrportal/rest/convservice/termoIndicacao/0003";
//		String url = "http://127.0.0.1:8080/itrintegrado/rest/convservice/termoIndicacao/9903";
//		String url = "http://127.0.0.1:8080/itrintegrado/rest/nrdservice/test"
//		String url = "http://127.0.0.1:8080/itrintegrado/rest/convservice/test";
		
		String param = "PARAM1=0010000000000005503468536DC5F842D5514EE2DAC7F048AA42EC9ABCDEFGAJLMDNDDSKSDSNDKSDSK";
		
		enviarGet("https://itrportal.hom.receita.fazenda.gov.br/itrportal/rest/nrdservice/test");	
		enviarPost("https://itrportal.hom.receita.fazenda.gov.br/itrportal/rest/nrdservice/enviar", param);
	}

	public static void enviarGet(String url) {
		carregarKeystore();

		try {
			Get get = Http.get(url);

			if (get.responseCode() != Status.OK.getStatusCode()) {

				if (get.responseCode() == Status.NOT_FOUND.getStatusCode()) {
					System.out.println("Falhou");
				}
				String msg = get.responseCode() + " - " + get.responseMessage() + " - " + url;
				System.out.println("======================================================================");
				System.out.println(msg);
				System.out.println("======================================================================");
			}
			String textoResposta = get.text();

			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println(textoResposta);
		} catch (Exception e) {
			System.out.println("Deu pau...");
			e.printStackTrace();
		}
	}
	
	public static void enviarPost(String url, String param) {
		carregarKeystore();

		try {
			Post post = Http.post(url, param)
					.header("Content-Type", "text/plain");
					

			if (post.responseCode() != Status.OK.getStatusCode()) {

				if (post.responseCode() == Status.NOT_FOUND.getStatusCode()) {
					System.out.println("Falhou");
				}
				String msg = post.responseCode() + " - " + post.responseMessage() + " - " + url;
				System.out.println("======================================================================");
				System.out.println(msg);
				System.out.println("======================================================================");
			}
			String textoResposta = post.text();

			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println(textoResposta);
		} catch (Exception e) {
			System.out.println("Deu pau...");
			e.printStackTrace();
		}
	}
	
	private static void carregarKeystore() {

		String path = "/home/80114504504/Desenvolvimento/portal/des/";
//		String path = "/home/80114504504/Desenvolvimento/portal/";
		String senha = "serpro2021";

		if (DEBUG) {
			System.setProperty("javax.net.debug", "ssl");
		}

		System.setProperty("javax.net.ssl.keyStoreProvider", "SUN");
		System.setProperty("javax.net.ssl.keyStoreType", "jks");
		System.setProperty("javax.net.ssl.trustStoreType", "jks");

		// Limpeza das propriedades
		System.clearProperty("javax.net.ssl.keyStore");
		System.clearProperty("javax.net.ssl.keyStorePassword");
		System.clearProperty("javax.net.ssl.trustStore");
		System.clearProperty("javax.net.ssl.trustStorePassword");

		// Certificaddo
		System.setProperty("javax.net.ssl.trustStore", path.concat("truststore.jks"));
		System.setProperty("javax.net.ssl.trustStorePassword", senha);
		System.setProperty("javax.net.ssl.keyStore", path.concat("keystore.jks"));
		System.setProperty("javax.net.ssl.keyStorePassword", senha);
	}

}
