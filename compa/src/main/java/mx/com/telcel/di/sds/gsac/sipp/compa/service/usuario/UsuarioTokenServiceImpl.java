package mx.com.telcel.di.sds.gsac.sipp.compa.service.usuario;

import mx.com.telcel.di.sds.gsac.sipp.compa.commons.Commons;
import mx.com.telcel.di.sds.gsac.sipp.compa.model.usuario.UsuarioTokenEntity;
import mx.com.telcel.di.sds.gsac.sipp.compa.repository.usuario.UsuarioRepo;
import mx.com.telcel.di.sds.gsac.sipp.compa.repository.usuario.UsuarioTokenRepo;
import mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario.UsuarioConverter;
import mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario.UsuarioTokenConverter;
import mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario.UsuarioTokenVo;
import mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario.UsuarioVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioTokenServiceImpl implements UsuarioTokenService {

	private static final Logger logger = Logger
			.getLogger(UsuarioTokenServiceImpl.class);

	@Autowired
	private UsuarioTokenRepo usuarioTokenRepo;

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Autowired
	private UsuarioTokenConverter usuarioTokenConverter;

	@Autowired
	private UsuarioConverter usuarioConverter;

	@Autowired
	private Commons commons;

	@Override
	public UsuarioTokenVo save(Long idUsuario, String tokenIngreso) {

		UsuarioVo usuarioVo = null;
		UsuarioTokenVo usuarioTokenVo = null;

		UsuarioTokenEntity usuarioTokenEntity = null;
		UsuarioTokenEntity usuarioTokenEntityResult = null;

		if ((idUsuario != null) & (tokenIngreso != null & tokenIngreso != "")) {

			try {
				usuarioVo = new UsuarioVo();
				usuarioTokenVo = new UsuarioTokenVo();

				usuarioVo.setIdUsuario(idUsuario);
				usuarioTokenVo.setIdUsuarioToken(null);
				usuarioTokenVo.setUsuario(usuarioVo);
				usuarioTokenVo.setTokenIngreso(tokenIngreso.trim());
				usuarioTokenVo.setTokenPeticion(commons.genUuid());
				usuarioTokenVo.setFechaAcceso(commons.getTimestamp());

				usuarioTokenEntity = usuarioTokenConverter.convertUsuarioTokenVoEntity(usuarioTokenVo);

				usuarioTokenEntityResult = usuarioTokenRepo.findByIdUsuario(usuarioTokenEntity.getUsuario().getIdUsuario());

				if (usuarioTokenEntityResult != null & usuarioTokenEntityResult.getIdUsuarioToken() != null) {
					usuarioTokenEntity.setIdUsuarioToken(usuarioTokenEntityResult.getIdUsuarioToken());
					usuarioTokenRepo.update(usuarioTokenEntity);
				} else {
					usuarioTokenRepo.save(usuarioTokenEntity);
				}

			} catch (Exception e) {
				usuarioTokenVo = new UsuarioTokenVo();
				logger.error("Metodo saveUsuarioToken", e);
			}

		} else {
			usuarioTokenVo = new UsuarioTokenVo();
			logger.error("Metodo saveUsuarioToken IdUsuario o TokenIngreso Vacio");
		}

		return usuarioTokenVo;

	}

	@Override
	public boolean grantAccesUsuarioToken(String usuario, String tokenIngreso) {

		boolean permitirAcceso = false;
		UsuarioVo usuarioVo = null;
		UsuarioTokenEntity usuarioTokenEntity = null;

		String usuarioDecode = commons.decodeStringBase64(usuario.trim());

		try {

			usuarioVo = usuarioConverter.convertUsuarioEntityVo(usuarioRepo.findByUsuario(usuarioDecode));
			
			if (usuarioVo.getIdUsuario() != null) {
				usuarioTokenEntity = new UsuarioTokenEntity();
				usuarioTokenEntity = usuarioTokenRepo.findByIdUsuario(usuarioVo.getIdUsuario());
				
				if (usuarioTokenEntity.getIdUsuarioToken() != null) {
					
					if (!usuarioTokenEntity.getTokenIngreso().equals(tokenIngreso)) {
						permitirAcceso = true;
					}
					
				}else{
					permitirAcceso = true;
				}
				
			}

		} catch (Exception e) {
			logger.error("Metodo grantAccesUsuarioToken", e);
		}
		return permitirAcceso;
	}

	@Override
	public boolean validateTokenPeticion(Long idUsuario, String tokenPeticion) {
		
		boolean tokenPeticionValido = false;
		
		UsuarioTokenEntity usuarioTokenEntity= null;
		
		if ((commons.validateUuid(tokenPeticion)) & (idUsuario != null)) {
			
			try {
				usuarioTokenEntity = new UsuarioTokenEntity();
				usuarioTokenEntity = usuarioTokenRepo.findByIdUsuario(idUsuario);
				
				if ((usuarioTokenEntity.getIdUsuarioToken() != null) & (usuarioTokenEntity.getTokenPeticion().equals(tokenPeticion))) {
					tokenPeticionValido = true;
				}
				
			} catch (Exception e) {
				logger.error("Metodo validateTokenPeticion", e.getCause());
			}
			
		} else {
			logger.error("Datos de Peticion Incorrectos Metodo validateTokenPeticion");
		}
		
		return tokenPeticionValido;
	}

}
