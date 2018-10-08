package mx.com.telcel.di.sds.gsac.sipp.compa.controller;

import mx.com.telcel.di.sds.gsac.sipp.compa.commons.Commons;
import mx.com.telcel.di.sds.gsac.sipp.compa.service.usuario.UsuarioService;
import mx.com.telcel.di.sds.gsac.sipp.compa.service.usuario.UsuarioTokenService;
import mx.com.telcel.di.sds.gsac.sipp.compa.vo.usuario.Usuario_UsuarioTokenVo;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Controller
public class MainController {

	private static final Logger logger = Logger.getLogger(MainController.class);

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioTokenService usuarioTokenService;
	
	@Autowired
	private Commons commons;

	@Value("${titulo}")
	private String titulo;

	@Value("${mensaje}")
	private String mensaje;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String grantAccessDefault(Model model) {
		model.addAttribute("titulo", titulo);
		model.addAttribute("mensaje", mensaje);
		return "error";
	}

	@RequestMapping(value = "/access/{tokenIngreso}/{usuarioIngreso}/{fuerzaVentas}/{sistemaAcceso}", method = RequestMethod.GET)
	public String grantAccess(Model model,
			@PathVariable("tokenIngreso") String tokenIngreso,
			@PathVariable("usuarioIngreso") String usuarioIngreso,
			@PathVariable("fuerzaVentas") String fuerzaVentas,
			@PathVariable("sistemaAcceso") String sistemaAcceso) {

		String vista = "index";

		if ((usuarioIngreso != null & usuarioIngreso != "")	& (tokenIngreso != null & tokenIngreso != "")) {

			if ((usuarioTokenService.grantAccesUsuarioToken(usuarioIngreso, tokenIngreso)) & (commons.validateUuid(tokenIngreso))) {
				
				Usuario_UsuarioTokenVo usuario_UsuarioTokenVo = null;

				try {
					usuario_UsuarioTokenVo = usuarioService.getUsuarioAccess(usuarioIngreso, tokenIngreso);

					if ((usuario_UsuarioTokenVo.getUsuarioTokenVo() != null & usuario_UsuarioTokenVo.getUsuarioVo().getIdUsuario() != null)) {

						model.addAttribute("IdUsuarioToken", usuario_UsuarioTokenVo.getUsuarioTokenVo().getIdUsuarioToken());
						model.addAttribute("IdUsuario", usuario_UsuarioTokenVo.getUsuarioVo().getIdUsuario());
						model.addAttribute("Usuario", usuario_UsuarioTokenVo.getUsuarioVo().getUsuario());
						model.addAttribute("RolUsuario", usuario_UsuarioTokenVo.getUsuarioVo().getRol());
						model.addAttribute("TokenIngreso", usuario_UsuarioTokenVo.getUsuarioTokenVo().getTokenIngreso());
						model.addAttribute("TokenPeticion", usuario_UsuarioTokenVo.getUsuarioTokenVo().getTokenPeticion());

					} else {
						vista = "error";
					}

				} catch (Exception e) {
					logger.error("Metodo grantAccess", e);
				}
				
			} else {
				vista = "error";
				logger.info("El Token de Acceso no Cumple con la Estructura Correcta");
				logger.info("El Usuario tiene una Sesion Activa");
			}

		} else {
			vista = "error";
			logger.error("Nombre de Usuario o Token de Acceso Vacios Metodo getUsuarioAccess");
			logger.info("Retornando Vista Error");
		}

		return vista;
	}

}
