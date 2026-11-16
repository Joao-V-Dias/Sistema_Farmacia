package com.joao.sistemafarmacia.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    private static final String ALGORITMO = "MD5";

    private Hash() {
    }

    public static String gerarHash(String senha) throws NoSuchAlgorithmException {
        MessageDigest digestor = MessageDigest.getInstance(ALGORITMO);
        byte[] digest = digestor.digest(senha.getBytes());
        return new BigInteger(1, digest).toString(16);
    }

    public static boolean validarSenha(String senha, String senhaCriptografada) throws NoSuchAlgorithmException {
        return gerarHash(senha).equals(senhaCriptografada);
    }
}
