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
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger logger = Logger.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioTokenService usuarioTokenService;

	@Autowired
	private UsuarioConverter usuarioConverter;

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Autowired
	private Commons commons;

	@Override
	public Usuario_UsuarioTokenVo getUsuarioAccess(String usuario, String tokenIngreso) {

		UsuarioVo usuarioVo = null;
		UsuarioTokenVo usuarioTokenVo = null;
		Usuario_UsuarioTokenVo usuario_UsuarioTokenVo = null;
		
		if ((usuario != null & usuario != "") & (tokenIngreso != null & tokenIngreso != "")) {
		
			if (commons.validateUuid(tokenIngreso.trim()) ){

				try {
					
					usuario_UsuarioTokenVo = new Usuario_UsuarioTokenVo();
					
					String usuarioDecode = commons.decodeStringBase64(usuario.trim());
					usuarioVo = usuarioConverter.convertUsuarioEntityVo(usuarioRepo.findByUsuario(usuarioDecode.trim()));
					
					usuario_UsuarioTokenVo.setUsuarioVo(usuarioVo);
					
					if (usuario_UsuarioTokenVo.getUsuarioVo() != null & usuario_UsuarioTokenVo.getUsuarioVo().getIdUsuario() != null) {
						usuarioTokenVo = usuarioTokenService.save(usuarioVo.getIdUsuario(), tokenIngreso);
						usuario_UsuarioTokenVo.setUsuarioTokenVo(usuarioTokenVo);
					}else{
						usuarioTokenVo = new UsuarioTokenVo();
						usuario_UsuarioTokenVo.setUsuarioTokenVo(usuarioTokenVo);
					}
					
				} catch (Exception e) {
					usuarioTokenVo = new UsuarioTokenVo();
					logger.error("Metodo getUsuarioAccess", e);
				}

			} else {
				usuarioTokenVo = new UsuarioTokenVo();
				logger.error("Nombre de Usuario o Token de Acceso Vacios Metodo getUsuarioAccess");
			}
			
		} else {
			logger.error("Metodo getUsuarioAccess TokenIngreso o UsuarioIngreso Vacio");
		}
		
		return usuario_UsuarioTokenVo;
	}

	@Override
	public UsuarioVo save(UsuarioVo usuarioVo) {

		UsuarioEntity usuarioEntity = null;

		try {
			usuarioEntity = usuarioConverter.convertUsuarioVoEntity(usuarioVo);
			usuarioRepo.save(usuarioEntity);
		} catch (Exception e) {
			logger.error("Metodo saveUsuario: ", e);
			usuarioVo = new UsuarioVo();
		}

		return usuarioVo;
	}

}
