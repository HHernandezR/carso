package mx.com.telcel.di.sds.gsac.sipp.compa.repository.usuario;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import mx.com.telcel.di.sds.gsac.sipp.compa.model.usuario.UsuarioEntity;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UsuarioRepoImpl implements UsuarioRepo {
	
	private static final Logger logger = Logger.getLogger(UsuarioRepoImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@Value("${usuario.estatus.activo}")
	private char estatusActivo;

	@Override
	public UsuarioEntity findByUsuario(String usuario) {
		
		UsuarioEntity usuarioEntityResult = null;
		
		if (usuario != null & usuario != "") {
			
			try {
				usuarioEntityResult = (UsuarioEntity) em.createQuery(
					    "SELECT c FROM UsuarioEntity c WHERE c.usuario = :usuario AND c.estatus = :estatus")
					    .setParameter("usuario", usuario.trim())
					    .setParameter("estatus", estatusActivo)
					    .getSingleResult();
			}catch (CannotGetJdbcConnectionException ex) {
				logger.error("Metodo findByUsuario: ", ex.getCause());
			}catch (NoResultException  e) {
				usuarioEntityResult = new UsuarioEntity();
				logger.info("Usuario no encontrado en la Base de Batos : " + usuario.trim());
				logger.error("Metodo findByUsuario: ", e.getCause());
			}
		} else {
			usuarioEntityResult = new UsuarioEntity();
			logger.error("Nombre de usuario Vacio Metodo getUsuarioAccess");
		}

		return usuarioEntityResult;
	}

	@Override
	public UsuarioEntity save(UsuarioEntity usuarioEntity) {

		try {
			em.persist(usuarioEntity);
			logger.info("Datos de Usuario Guardados");
			em.flush();
		} catch (Exception e) {
			logger.error("Metodo save: ", e.getCause());
		}
		return null;
	}

}
