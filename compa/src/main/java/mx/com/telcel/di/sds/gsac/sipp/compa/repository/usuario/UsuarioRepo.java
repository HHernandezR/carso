package mx.com.telcel.di.sds.gsac.sipp.compa.repository.usuario;

import mx.com.telcel.di.sds.gsac.sipp.compa.model.usuario.UsuarioEntity;

public interface UsuarioRepo {
	
	public UsuarioEntity findByUsuario(String usuario);
	public UsuarioEntity save(UsuarioEntity usuarioEntity);

}
