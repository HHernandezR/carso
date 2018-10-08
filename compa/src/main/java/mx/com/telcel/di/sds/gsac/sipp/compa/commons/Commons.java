package mx.com.telcel.di.sds.gsac.sipp.compa.commons;

import java.sql.Timestamp;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Commons {
	
	final Logger logger = Logger.getLogger(Commons.class);

	public Timestamp getTimestamp() {
		java.util.Date fechaActual = new java.util.Date();
		return new java.sql.Timestamp(fechaActual.getTime());
	}

	public String genUuid() {
		UUID uuidPeticion = UUID.randomUUID();
		return uuidPeticion.toString();
	}

	public boolean validateUuid(String uuid) {

		String uuidEstructura = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";
		boolean uuidCorrecto = false;

		try {
			if (uuid.trim().matches(uuidEstructura.trim())) {
				uuidCorrecto = true;
			} else {
				logger.error("Estructura de Token de Acceso Incorrecta");
			}
		} catch (Exception e) {
			logger.error("Metodo validateUuid", e);
		}
		return uuidCorrecto;
	}

	public String codeStringBase64(String cadena) {

		String encoded = null;
		
		if (cadena != null & cadena != "") {
			try {
				byte[] message = cadena.getBytes("UTF-8");
				encoded = DatatypeConverter.printBase64Binary(message);
			} catch (Exception e) {
				logger.error("Metodo codeStringBase64", e);
			}
		} else {
			logger.error("Cadena a Codificar Vacia");
		}

		return encoded;
	}

	public String decodeStringBase64(String cadena) {

		String decoded = null;
		
		if (cadena != null & cadena != "") {
			try {
				byte[] message = DatatypeConverter.parseBase64Binary(cadena);
				decoded = new String(message, "UTF-8");
			} catch (Exception e) {
				logger.error("Error en Estructura de Cadena Base64");
			}
		}else{
			logger.error("Cadena a Decodificar Vacia");
		}

		return decoded;
	}

}
