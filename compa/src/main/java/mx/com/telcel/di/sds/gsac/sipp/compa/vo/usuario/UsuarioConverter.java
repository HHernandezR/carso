package mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import mx.com.telcel.di.sds.gsac.sipp.compa.model.usuario.UsuarioEntity;
import mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario.UsuarioVo;

@Component
public class UsuarioConverter {

	private static final Logger logger = Logger
			.getLogger(UsuarioConverter.class);

	public UsuarioVo convertUsuarioEntityVo(UsuarioEntity usuarioEntity) {

		UsuarioVo usuarioVo = null;

		if (usuarioEntity != null & usuarioEntity.getIdUsuario() != null) {

			try {
				usuarioVo = new UsuarioVo();
				usuarioVo.setIdUsuario(usuarioEntity.getIdUsuario());
				usuarioVo.setUsuario(usuarioEntity.getUsuario());
				usuarioVo.setRol(usuarioEntity.getRol());
				usuarioVo.setEstatus(usuarioEntity.getEstatus());
				usuarioVo.setSistemaOrigen(usuarioEntity.getSistemaOrigen());
				usuarioVo.setUsuarioPadre(usuarioEntity.getUsuarioPadre());
				usuarioVo.setFechaModificacion(usuarioEntity.getFechaModificacion());
			} catch (Exception e) {
				usuarioVo = new UsuarioVo();
				logger.error("Metodo convertUsuarioEntityVo: ", e);
			}

		} else {
			usuarioVo = new UsuarioVo();
			logger.error("Metodo convertUsuarioEntityVo UsuarioEntity Vacio");
		}
		
		return usuarioVo;
	}

	public UsuarioEntity convertUsuarioVoEntity(UsuarioVo usuarioVo) {

		UsuarioEntity usuarioEntity = null;

		if (usuarioVo != null & usuarioVo.getIdUsuario() != null) {

			try {
				usuarioEntity = new UsuarioEntity();

				usuarioEntity.setIdUsuario(usuarioVo.getIdUsuario());
				usuarioEntity.setUsuario(usuarioVo.getUsuario());
				usuarioEntity.setRol(usuarioVo.getRol());
				usuarioEntity.setEstatus(usuarioVo.getEstatus());
				usuarioEntity.setSistemaOrigen(usuarioVo.getSistemaOrigen());
				usuarioEntity.setUsuarioPadre(usuarioVo.getUsuarioPadre());
				usuarioEntity.setFechaModificacion(usuarioVo.getFechaModificacion());
			} catch (Exception e) {
				usuarioEntity = new UsuarioEntity();
				logger.error("Metodo convertUsuarioVoEntity: ", e);
			}

		} else {
			usuarioEntity = new UsuarioEntity();
			logger.error("Metodo convertUsuarioVoEntity UsuarioVo Vacio");
		}

		return usuarioEntity;
	}

	public UsuarioEntity convertUsuarioVoEntitySave(UsuarioVo usuarioVo) {

		UsuarioEntity usuarioEntity = null;
		
		if (usuarioVo != null) {
			System.out.println("-----> usuarioVo: " + usuarioVo.toString());

			try {
				usuarioEntity = new UsuarioEntity();

				usuarioEntity.setIdUsuario(usuarioVo.getIdUsuario());
				usuarioEntity.setUsuario(usuarioVo.getUsuario());
				usuarioEntity.setRol(usuarioVo.getRol());
				usuarioEntity.setEstatus(usuarioVo.getEstatus());
				usuarioEntity.setSistemaOrigen(usuarioVo.getSistemaOrigen());
				usuarioEntity.setUsuarioPadre(usuarioVo.getUsuarioPadre());
				usuarioEntity.setFechaModificacion(usuarioVo.getFechaModificacion());
				
				System.out.println("-----> usuarioEntity: " + usuarioEntity.toString());
				
			} catch (Exception e) {
				usuarioEntity = new UsuarioEntity();
				logger.error("Metodo convertUsuarioVoEntity: ", e);
			}
		} else {
			usuarioEntity = new UsuarioEntity();
			logger.error("Metodo convertUsuarioVoEntity UsuarioVo Vacio");
		}

		return usuarioEntity;
	}

}
