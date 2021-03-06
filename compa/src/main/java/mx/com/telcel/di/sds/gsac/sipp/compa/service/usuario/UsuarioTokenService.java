package mx.com.telcel.di.sds.gsac.sipp.compa.service.usuario;

import mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario.UsuarioTokenVo;

public interface UsuarioTokenService {
	
	public boolean grantAccesUsuarioToken(String usuario, String tokenIngreso);
	public boolean validateTokenPeticion(Long idUsuario, String tokenPeticion);
	public UsuarioTokenVo save(Long idUsuario, String tokenIngreso);

}
