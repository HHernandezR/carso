package mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario;

import java.io.Serializable;
import java.sql.Timestamp;

public class UsuarioVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idUsuario;
	private String usuario;
	private char rol;
	private char estatus;
	private String sistemaOrigen;
	private String usuarioPadre;
	private Timestamp fechaModificacion;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public char getRol() {
		return rol;
	}

	public void setRol(char rol) {
		this.rol = rol;
	}

	public char getEstatus() {
		return estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	public String getSistemaOrigen() {
		return sistemaOrigen;
	}

	public void setSistemaOrigen(String sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}

	public String getUsuarioPadre() {
		return usuarioPadre;
	}

	public void setUsuarioPadre(String usuarioPadre) {
		this.usuarioPadre = usuarioPadre;
	}

	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	@Override
	public String toString() {
		return "UsuarioVo [idUsuario=" + idUsuario + ", usuario=" + usuario
				+ ", rol=" + rol + ", estatus=" + estatus + ", sistemaOrigen="
				+ sistemaOrigen + ", usuarioPadre=" + usuarioPadre
				+ ", fechaModificacion=" + fechaModificacion
				+ "]";
	}	
	
}
