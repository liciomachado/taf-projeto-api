package com.rest.taf.services;

import com.rest.taf.model.Usuario;

public interface UsuarioService {

	Usuario getUsuarioPorEmail(String email);
}
