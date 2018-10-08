package mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario;

import java.io.Serializable;
import java.sql.Timestamp;

public class UsuarioTokenVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idUsuarioToken;
	private UsuarioVo usuario;
	private String tokenIngreso;
	private String tokenPeticion;
	private Timestamp fechaAcceso;

	public Long getIdUsuarioToken() {
		return idUsuarioToken;
	}

	public void setIdUsuarioToken(Long idUsuarioToken) {
		this.idUsuarioToken = idUsuarioToken;
	}

	public UsuarioVo getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVo usuario) {
		this.usuario = usuario;
	}

	public String getTokenIngreso() {
		return tokenIngreso;
	}

	public void setTokenIngreso(String tokenIngreso) {
		this.tokenIngreso = tokenIngreso;
	}

	public String getTokenPeticion() {
		return tokenPeticion;
	}

	public void setTokenPeticion(String tokenPeticion) {
		this.tokenPeticion = tokenPeticion;
	}

	public Timestamp getFechaAcceso() {
		return fechaAcceso;
	}

	public void setFechaAcceso(Timestamp fechaAcceso) {
		this.fechaAcceso = fechaAcceso;
	}

	@Override
	public String toString() {
		return "UsuarioToken [idUsuarioToken=" + idUsuarioToken + ", usuario="
				+ usuario + ", tokenIngreso=" + tokenIngreso
				+ ", tokenPeticion=" + tokenPeticion + ", fechaAcceso="
				+ fechaAcceso + "]";
	}

}
