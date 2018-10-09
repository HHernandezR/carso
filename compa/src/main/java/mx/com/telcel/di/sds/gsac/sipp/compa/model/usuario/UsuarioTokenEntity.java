package mx.com.telcel.di.sds.gsac.sipp.compa.model.usuario;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TG_USUARIO_TOKEN")
public class UsuarioTokenEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TG_USUARIO_TOKEN_SEQ")
	@SequenceGenerator(sequenceName = "tg_usuario_token_seq", allocationSize = 1, name = "TG_USUARIO_TOKEN_SEQ")
	@Column(name = "ID_USUARIO_TOKEN")
	private Long idUsuarioToken;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
	private UsuarioEntity usuario;
	
	@Column(name = "TOKEN_INGRESO", unique = true)
	private String tokenIngreso;
	
	@Column(name = "TOKEN_PETICION", unique = true)
	private String tokenPeticion;
	
	@Column(name = "FECHA_ACCESO", nullable = false)
	private Timestamp fechaAcceso;

	public Long getIdUsuarioToken() {
		return idUsuarioToken;
	}

	public void setIdUsuarioToken(Long idUsuarioToken) {
		this.idUsuarioToken = idUsuarioToken;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
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
