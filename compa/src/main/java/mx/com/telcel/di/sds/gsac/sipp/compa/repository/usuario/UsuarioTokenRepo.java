package mx.com.telcel.di.sds.gsac.sipp.compa.repository.usuario;

import mx.com.telcel.di.sds.gsac.sipp.compa.model.usuario.UsuarioTokenEntity;

public interface UsuarioTokenRepo {
	
	public UsuarioTokenEntity save(UsuarioTokenEntity usuarioTokenEntity);
	public UsuarioTokenEntity update(UsuarioTokenEntity usuarioTokenEntity);
	public UsuarioTokenEntity findByIdUsuario(Long idUsuario);

}
