package mx.com.telcel.di.sds.gsac.sipp.complan.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name="USUARIO")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
	@SequenceGenerator(sequenceName = "usuario_seq", allocationSize = 1, name = "USUARIO_SEQ")
	@Column(name = "ID_USUARIO")
	private int idUsuario;
	
	@Column(name = "USUARIO", nullable = false, length = 24)
	private String usuario;
	
	@Column(name = "ROL", nullable = false, length = 1)
	private char rol;
	
	@Column(name = "ESTATUS", nullable = true, length = 1)
	private char estatus;
	
	@Column(name = "SISTEMA_ORIGEN", nullable = true, length = 24)
	private String sistemaOrigen;
	
	@Column(name = "USUARIO_PADRE", nullable = true, length = 24)
	private String usuarioPadre;
	
	@Column(name = "FECHA_MODIFICACION", nullable = false)
	private Date fechaModificacion;
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
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
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", usuario=" + usuario
				+ ", rol=" + rol + ", estatus=" + estatus + ", sistemaOrigen="
				+ sistemaOrigen + ", usuarioPadre=" + usuarioPadre
				+ ", fechaModificacion=" + fechaModificacion + "]";
	}
	
}
