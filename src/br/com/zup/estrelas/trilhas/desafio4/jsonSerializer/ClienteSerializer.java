package br.com.zup.estrelas.trilhas.desafio4.jsonSerializer;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.zup.estrelas.trilhas.desafio4.pojo.Cliente;

public class ClienteSerializer {
	
	public ClienteSerializer() {
		super();
	}

	public Cliente jsonToCliente (BufferedReader reader) throws IOException {
		
		StringBuffer jsonBuffer = new StringBuffer();
		String jsonLine;
		
		try {
			while ((jsonLine = reader.readLine()) != null) {
				jsonBuffer.append(jsonLine);
			}
		} catch (Exception e) {
			throw new IOException("Error parsing Json request string");
		}

		Gson gson = new GsonBuilder().create();
		Cliente cliente = gson.fromJson(jsonBuffer.toString(), Cliente.class);
		
		return cliente;
	}

}
