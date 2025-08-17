package com.teste.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Formatters {
    private static final Locale PT_BR = new Locale("pt", "BR");
    private static final DateTimeFormatter DATE_BR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat CURRENCY_BR = NumberFormat.getCurrencyInstance(PT_BR);
    private static final NumberFormat NUMBER_BR = NumberFormat.getNumberInstance(PT_BR);

    private Formatters() {}

    public static String data(LocalDate date) {
        return date.format(DATE_BR);
    }

    public static String moeda(BigDecimal valor) {
        return CURRENCY_BR.format(valor);
    }

    public static String numero(BigDecimal valor) {
        return NUMBER_BR.format(valor);
    }
}
