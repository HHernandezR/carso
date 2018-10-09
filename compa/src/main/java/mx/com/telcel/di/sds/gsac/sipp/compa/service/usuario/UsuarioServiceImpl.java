package mx.com.telcel.di.sds.gsac.sipp.compa.service.usuario;

import mx.com.telcel.di.sds.gsac.sipp.compa.commons.Commons;
import mx.com.telcel.di.sds.gsac.sipp.compa.model.usuario.UsuarioEntity;
import mx.com.telcel.di.sds.gsac.sipp.compa.repository.usuario.UsuarioRepo;
import mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario.UsuarioConverter;
import mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario.UsuarioTokenVo;
import mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario.UsuarioVo;
import mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario.Usuario_UsuarioTokenVo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger logger = Logger
			.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioTokenService usuarioTokenService;

	@Autowired
	private UsuarioConverter usuarioConverter;

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Autowired
	private Commons commons;

	@Value("${usuario.estatus.activo}")
	private char estatusActivo;
	
	@Value("${usuario.estatus.inactivo}")
	private char estatusInactivo;

	@Override
	public Usuario_UsuarioTokenVo getUsuarioAccess(String usuario,
			String tokenIngreso) {

		UsuarioVo usuarioVo = null;
		UsuarioTokenVo usuarioTokenVo = null;
		Usuario_UsuarioTokenVo usuario_UsuarioTokenVo = null;

		try {

			usuario_UsuarioTokenVo = new Usuario_UsuarioTokenVo();

			String usuarioDecode = commons.decodeStringBase64(usuario.trim());
			usuarioVo = usuarioConverter.convertUsuarioEntityVo(usuarioRepo.findByUsuario(usuarioDecode.trim()));

			usuario_UsuarioTokenVo.setUsuarioVo(usuarioVo);

			if (usuario_UsuarioTokenVo.getUsuarioVo() != null & usuario_UsuarioTokenVo.getUsuarioVo().getIdUsuario() != null) {

				usuarioTokenVo = usuarioTokenService.save(usuarioVo.getIdUsuario(), tokenIngreso);
				usuario_UsuarioTokenVo.setUsuarioTokenVo(usuarioTokenVo);

			} else {
				usuarioTokenVo = new UsuarioTokenVo();
				usuario_UsuarioTokenVo.setUsuarioTokenVo(usuarioTokenVo);
			}

		} catch (Exception e) {
			usuarioTokenVo = new UsuarioTokenVo();
			logger.error("Metodo getUsuarioAccess", e);
		}

		return usuario_UsuarioTokenVo;
	}

	@Override
	public UsuarioVo save(UsuarioVo usuarioVo) {

		UsuarioEntity usuarioEntity = null;

		try {
			usuarioVo.setEstatus(estatusActivo);
			usuarioVo.setFechaModificacion(commons.getTimestamp());

			usuarioEntity = usuarioConverter.convertUsuarioVoEntitySave(usuarioVo);
			usuarioRepo.save(usuarioEntity);
			logger.info("Usuario Guardado");
		} catch (Exception e) {
			usuarioVo = new UsuarioVo();
			logger.error("Metodo save: ", e);
		}

		return usuarioVo;
	}

	@Override
	public UsuarioVo update(UsuarioVo usuarioVo) {
		
		UsuarioEntity usuarioEntity = null;

		try {
			usuarioVo.setFechaModificacion(commons.getTimestamp());
			
			usuarioEntity = new UsuarioEntity();
			
			usuarioEntity.setIdUsuario(usuarioVo.getIdUsuario());
			usuarioEntity.setUsuario(usuarioVo.getUsuario());
			usuarioEntity.setRol(usuarioVo.getRol());
			usuarioEntity.setEstatus(usuarioVo.getEstatus());
			usuarioEntity.setSistemaOrigen(usuarioVo.getSistemaOrigen());
			usuarioEntity.setUsuarioPadre(usuarioVo.getUsuarioPadre());
			usuarioEntity.setFechaModificacion(usuarioVo.getFechaModificacion());

			usuarioRepo.update(usuarioEntity);
			logger.info("Usuario Actualizado");
		} catch (Exception e) {
			usuarioVo = new UsuarioVo();
			logger.error("Metodo save: ", e);
		}

		return usuarioVo;
	}
	
	@Override
	public UsuarioVo delete(UsuarioVo usuarioVo) {
		
		UsuarioEntity usuarioEntity = null;

		try {
			usuarioVo.setEstatus(estatusInactivo);
			usuarioVo.setFechaModificacion(commons.getTimestamp());
			
			usuarioEntity = new UsuarioEntity();
			
			usuarioEntity.setIdUsuario(usuarioVo.getIdUsuario());
			usuarioEntity.setUsuario(usuarioVo.getUsuario());
			usuarioEntity.setRol(usuarioVo.getRol());
			usuarioEntity.setEstatus(usuarioVo.getEstatus());
			usuarioEntity.setSistemaOrigen(usuarioVo.getSistemaOrigen());
			usuarioEntity.setUsuarioPadre(usuarioVo.getUsuarioPadre());
			usuarioEntity.setFechaModificacion(usuarioVo.getFechaModificacion());

			usuarioRepo.delete(usuarioEntity);
			logger.info("Usuario Eliminado");
		} catch (Exception e) {
			usuarioVo = new UsuarioVo();
			logger.error("Metodo save: ", e);
		}

		return usuarioVo;
	}

}
