package mx.com.telcel.di.sds.gsac.sipp.compa.service.usuario;

import mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario.UsuarioVo;
import mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario.Usuario_UsuarioTokenVo;

public interface UsuarioService {
	
	public Usuario_UsuarioTokenVo getUsuarioAccess(String usuario,String tokenIngreso);
	public UsuarioVo save(UsuarioVo usuarioVo);

}
