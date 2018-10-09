package mx.com.telcel.di.sds.gsac.sipp.compa.repository.usuario;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import mx.com.telcel.di.sds.gsac.sipp.compa.model.usuario.UsuarioTokenEntity;

import org.apache.log4j.Logger;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UsuarioTokenRepoImpl implements UsuarioTokenRepo {
	
	private static final Logger logger = Logger.getLogger(UsuarioTokenRepoImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public UsuarioTokenEntity save(UsuarioTokenEntity usuarioTokenEntity) {
		
		UsuarioTokenEntity usuarioTokenEntityReturn = null;
		
		if (usuarioTokenEntity != null & usuarioTokenEntity.getUsuario().getIdUsuario() != null) {
			
			try {
				em.persist(usuarioTokenEntity);
				logger.info("Datos de UsuarioToken Guardados");
			} catch (CannotGetJdbcConnectionException e) {
				usuarioTokenEntityReturn = new UsuarioTokenEntity();
				logger.error("UsuarioTokenEntity save: ", e);
			}
			
		}else {
			usuarioTokenEntityReturn = new UsuarioTokenEntity();
			logger.error("UsuarioTokenEntity Vacio Metodo saveUsuarioToken");
		}
		
		return usuarioTokenEntityReturn;
	}
	
	@Override
	public UsuarioTokenEntity update(UsuarioTokenEntity usuarioTokenEntity) {
		
		UsuarioTokenEntity usuarioTokenEntityReturn = null;
		
		if (usuarioTokenEntity != null & usuarioTokenEntity.getUsuario().getIdUsuario() != null) {
			try {
				em.merge(usuarioTokenEntity);
				usuarioTokenEntityReturn = usuarioTokenEntity;
				logger.info("Datos de UsuarioToken Actualizados");
			} catch (CannotGetJdbcConnectionException e) {
				usuarioTokenEntityReturn = new UsuarioTokenEntity();
				logger.error("Metodo update: ", e.getCause());
			}
		} else {
			usuarioTokenEntityReturn = new UsuarioTokenEntity();
			logger.error("UsuarioTokenEntity Vacio Metodo saveUsuarioToken");
		}
		return usuarioTokenEntityReturn;
	}

	@Override
	public UsuarioTokenEntity findByIdUsuario(Long idUsuario) {
		
		UsuarioTokenEntity usuarioTokenEntityReturn = null;

		if (idUsuario != null & idUsuario != 0) {
			
			try {
				usuarioTokenEntityReturn = (UsuarioTokenEntity) em.createQuery(
					    "SELECT c FROM UsuarioTokenEntity c WHERE c.usuario.idUsuario = :idUsuario")
					    .setParameter("idUsuario", idUsuario)
					    .getSingleResult();
			}catch (CannotGetJdbcConnectionException ex) {
				logger.error("Metodo findByIdUsuario: ", ex.getCause());
			} catch (NoResultException e) {
				usuarioTokenEntityReturn = new UsuarioTokenEntity();
				logger.info("UsuarioToken no encontrado en la Base de Batos");
				logger.error("Metodo findByIdUsuario: ", e.getCause());
			}
			
		} else {
			usuarioTokenEntityReturn = new UsuarioTokenEntity();
			logger.error("UsuarioTokenEntity Vacio Metodo findUsuarioToken");
		}
		return usuarioTokenEntityReturn;
	}

}
