package com.zaperoko.notas.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaperoko.notas.model.Usuario;
import com.zaperoko.notas.service.UsuarioService;
import com.zaperoko.notas.util.JwtUtil;

@RestController
@RequestMapping("/api/autenticacion")
public class AuthController {

	@Autowired
	private UsuarioService servicio;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping
	public ResponseEntity<?> verificarUsuario(@RequestBody Usuario user) {
		List<Usuario> resultado = servicio.verificarUsuario(user.getUsuario(), user.getClave());
		if (resultado.size() > 0) {
			String token = jwtUtil.generarToken(user.getUsuario(), user.getIdRol());
			return ResponseEntity.ok((new JSONObject().put("token", token).put("usuario",
					servicio.verificarUsuario(user.getUsuario(), user.getClave()))).toString());
		} else {
			return ResponseEntity.ok((new JSONObject().put("token", "0")).toString());
		}
	}
}
