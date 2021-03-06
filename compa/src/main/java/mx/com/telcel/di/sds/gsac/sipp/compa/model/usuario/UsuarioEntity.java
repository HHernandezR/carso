package mx.com.telcel.di.sds.gsac.sipp.compa.model.usuario;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TG_USUARIO")
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TG_USUARIO_SEQ")
	@SequenceGenerator(sequenceName = "tg_usuario_seq", allocationSize = 1, name = "TG_USUARIO_SEQ")
	@Column(name = "ID_USUARIO")
	private Long idUsuario;

	@Column(name = "USUARIO", unique = true)
	private String usuario;
	
	@Column(name = "ROL")
	private char rol;

	@Column(name = "ESTATUS")
	private char estatus;

	@Column(name = "SISTEMA_ORIGEN")
	private String sistemaOrigen;

	@Column(name = "USUARIO_PADRE")
	private String usuarioPadre;
	
	@Column(name = "FECHA_MODIFICACION")
	private Timestamp fechaModificacion;

//	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private UsuarioTokenEntity usuarioToken;

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

//	public UsuarioTokenEntity getUsuarioToken() {
//		return usuarioToken;
//	}

//	public void setUsuarioToken(UsuarioTokenEntity usuarioToken) {
//		this.usuarioToken = usuarioToken;
//	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", usuario=" + usuario
				+ ", rol=" + rol + ", estatus=" + estatus + ", sistemaOrigen="
				+ sistemaOrigen + ", usuarioPadre=" + usuarioPadre
				+ ", fechaModificacion=" + fechaModificacion
				+ "]";
	}
	
	
}
