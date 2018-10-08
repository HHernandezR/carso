package mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario;

import java.io.Serializable;

public class Usuario_UsuarioTokenVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UsuarioVo usuarioVo;
	private UsuarioTokenVo usuarioTokenVo;
	
	public UsuarioVo getUsuarioVo() {
		return usuarioVo;
	}
	public void setUsuarioVo(UsuarioVo usuarioVo) {
		this.usuarioVo = usuarioVo;
	}
	public UsuarioTokenVo getUsuarioTokenVo() {
		return usuarioTokenVo;
	}
	public void setUsuarioTokenVo(UsuarioTokenVo usuarioTokenVo) {
		this.usuarioTokenVo = usuarioTokenVo;
	}
	@Override
	public String toString() {
		return "Usuario_UsuarioTokenVo [usuarioVo=" + usuarioVo
				+ ", usuarioTokenVo=" + usuarioTokenVo + "]";
	}
	
}
