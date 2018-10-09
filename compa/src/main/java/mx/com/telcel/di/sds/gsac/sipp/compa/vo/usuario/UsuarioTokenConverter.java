package mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario;

import mx.com.telcel.di.sds.gsac.sipp.compa.model.usuario.UsuarioEntity;
import mx.com.telcel.di.sds.gsac.sipp.compa.model.usuario.UsuarioTokenEntity;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class UsuarioTokenConverter {
	
	private static final Logger logger = Logger.getLogger(UsuarioTokenConverter.class);
	
	public UsuarioTokenVo convertUsuarioTokenEntityVo(UsuarioTokenEntity usuarioTokenEntity){
		
		UsuarioVo usuarioVo = null;
		UsuarioTokenVo usuarioTokenVo = null;
		
		if (usuarioTokenEntity != null & usuarioTokenEntity.getIdUsuarioToken() != null) {
			
			try {
				usuarioVo = new UsuarioVo();
				usuarioTokenVo = new UsuarioTokenVo();
				
				usuarioVo.setIdUsuario(usuarioTokenEntity.getUsuario().getIdUsuario());
				usuarioVo.setUsuario(usuarioTokenEntity.getUsuario().getUsuario());
				usuarioVo.setRol(usuarioTokenEntity.getUsuario().getRol());
				usuarioVo.setEstatus(usuarioTokenEntity.getUsuario().getEstatus());
				usuarioVo.setSistemaOrigen(usuarioTokenEntity.getUsuario().getSistemaOrigen());
				usuarioVo.setUsuarioPadre(usuarioTokenEntity.getUsuario().getUsuarioPadre());
				
				usuarioTokenVo.setIdUsuarioToken(usuarioTokenEntity.getIdUsuarioToken());
				usuarioTokenVo.setUsuario(usuarioVo);
				usuarioTokenVo.setTokenIngreso(usuarioTokenEntity.getTokenIngreso());
				usuarioTokenVo.setTokenPeticion(usuarioTokenEntity.getTokenPeticion());
			} catch (Exception e) {
				usuarioVo = new UsuarioVo();
				usuarioTokenVo = new UsuarioTokenVo();
				usuarioTokenVo.setUsuario(usuarioVo);
				logger.error("Metodo convertUsuarioTokenEntityVo: ", e);
			}
			
		}else {	
			usuarioVo = new UsuarioVo();
			usuarioTokenVo = new UsuarioTokenVo();
			usuarioTokenVo.setUsuario(usuarioVo);
			logger.error("Metodo convertUsuarioTokenEntityVo UsuarioTokenEntity Vacio");
		}
		return usuarioTokenVo;
	}
	
	
	public UsuarioTokenEntity convertUsuarioTokenVoEntity(UsuarioTokenVo usuarioTokenVo){
		
		UsuarioEntity usuarioEntity = null;
		UsuarioTokenEntity usuarioTokenEntity = null;
		
		if (usuarioTokenVo != null & usuarioTokenVo.getUsuario().getIdUsuario() != null) {
			
			try {
				usuarioEntity = new UsuarioEntity();
				usuarioTokenEntity = new UsuarioTokenEntity();
				
				usuarioEntity.setIdUsuario(usuarioTokenVo.getUsuario().getIdUsuario());
				
				usuarioTokenEntity.setIdUsuarioToken(usuarioTokenVo.getIdUsuarioToken());
				usuarioTokenEntity.setUsuario(usuarioEntity);
				usuarioTokenEntity.setTokenIngreso(usuarioTokenVo.getTokenIngreso());
				usuarioTokenEntity.setTokenPeticion(usuarioTokenVo.getTokenPeticion());
				usuarioTokenEntity.setFechaAcceso(usuarioTokenVo.getFechaAcceso());
			} catch (Exception e) {
				usuarioTokenEntity = new UsuarioTokenEntity();
				logger.error("Metodo convertUsuarioTokenVoEntity: ", e);
			}
			
		}else {
			usuarioTokenEntity = new UsuarioTokenEntity();
			logger.error("Metodo convertUsuarioTokenVoEntity UsuarioTokenVo Vacio");
		}
		return usuarioTokenEntity;
	}
	

}
