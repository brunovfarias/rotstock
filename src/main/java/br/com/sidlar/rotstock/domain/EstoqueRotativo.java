package br.com.sidlar.rotstock.domain;

import java.util.List;

public class EstoqueRotativo {
    private List<ItemEstoqueRotativo> itensEstoqueRotativo;
    private EstoqueMinimo estoqueMnimo;
    private Enum<ItemConsumo> itemConsumo;
}

