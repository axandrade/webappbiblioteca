package br.com.maralto.webappbiblioteca.util.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Component;

import br.com.maralto.webappbiblioteca.model.Pessoa;

@Component
public class EnderecoUtil {

	public Pessoa buscarCep(String cep) throws IOException {

		String json;
		Pessoa pessoa = new Pessoa();

		URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
		URLConnection urlConnection = url.openConnection();
		InputStream is;

		is = urlConnection.getInputStream();

		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		StringBuilder jsonSb = new StringBuilder();
		br.lines().forEach(l -> jsonSb.append(l.trim()));
		json = jsonSb.toString();

		json = json.replaceAll("[{},:]", "");
		json = json.replaceAll("\"", "\n");
		String array[] = new String[30];

		array = json.split("\n");

		pessoa.setCep(array[3]);
		pessoa.setLogradouro(array[7]);
		pessoa.setBairro(array[15]);
		pessoa.setCidade(array[19]);
		pessoa.setUf(array[23]);

		return pessoa;
	}

}
