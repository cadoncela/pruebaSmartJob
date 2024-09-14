package com.doncel.pruebaUsuarios.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

  NEW_EMAIL_FOUND("ERR_USER_001", "El correo ya registrado"),
  INVALID_USUARIO("ERR_USER_002", "Parametros de usuario incorrectos."),
  GENERIC_ERROR("ERR_GEN_001", "Ocurrió un error inesperado.");

  private final String code;
  private final String message;

  ErrorCatalog(String code, String message) {
    this.code = code;
    this.message = message;
  }

}
