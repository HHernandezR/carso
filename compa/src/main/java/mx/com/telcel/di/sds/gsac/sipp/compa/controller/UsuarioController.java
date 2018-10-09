package mx.com.telcel.di.sds.gsac.sipp.compa.controller;

import mx.com.telcel.di.sds.gsac.sipp.compa.service.usuario.UsuarioService;
import mx.com.telcel.di.sds.gsac.sipp.compa.service.usuario.UsuarioTokenService;
import mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario.UsuarioVo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	private static final Logger logger = Logger.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioTokenService usuarioTokenService;
	
//	@PostMapping(value = "/add",produces = MediaType.APPLICATION_XML_VALUE)
	@PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioVo> addUsuario(@RequestBody UsuarioVo usuarioVoInput) {

		ResponseEntity<UsuarioVo> responseEntity = null;
		
		if (usuarioVoInput != null & usuarioVoInput.getUsuario() != null) {
			
			try {
				usuarioService.save(usuarioVoInput);
				responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
			} catch (Exception e) {
				logger.error("Metodo addUsuario",e);
				responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			
		} else {
			logger.error("Acceso a Peticiones Denegado Metodo add");
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		return responseEntity;
	}
	
	@PostMapping(value = "/delete",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioVo> deleteUsuario(@RequestHeader String tokenPeticion,@RequestHeader Long idUsuarioPadre,@RequestBody UsuarioVo usuarioVoInput) {

		ResponseEntity<UsuarioVo> responseEntity = null;
		
		try {
			
			if (usuarioTokenService.validateTokenPeticion(idUsuarioPadre, tokenPeticion)) {
				usuarioService.delete(usuarioVoInput);
				responseEntity = new ResponseEntity<>(HttpStatus.ACCEPTED);
			} else {
				logger.error("Acceso a Peticiones Denegado Metodo edit");
				responseEntity = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
			
		} catch (Exception e) {
			logger.error("Metodo editUsuario",e);
			responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		return responseEntity;
	}
	
	@PostMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioVo> editUsuario(@RequestHeader String tokenPeticion,@RequestHeader Long idUsuarioPadre,@RequestBody UsuarioVo usuarioVoInput) {

		ResponseEntity<UsuarioVo> responseEntity = null;
		
		try {
			
			if (usuarioTokenService.validateTokenPeticion(idUsuarioPadre, tokenPeticion)) {
				usuarioService.update(usuarioVoInput);
				responseEntity = new ResponseEntity<>(HttpStatus.ACCEPTED);
			} else {
				logger.error("Acceso a Peticiones Denegado Metodo edit");
				responseEntity = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
			
		} catch (Exception e) {
			logger.error("Metodo editUsuario",e);
			responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		return responseEntity;
	}
	

}
