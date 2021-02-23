package main.java.edu.unipampa.es.rp2.marco1;

import java.util.List;
import java.util.Iterator;

public class LoboGuara extends Animal{

    private static final int VALOR_FOME_OVELHA = 7;
    private int nivelFome;

    public LoboGuara(boolean idadeRandomica, Campo campo, Localizacao localizacao) {
        idade = 0;
        vivo = true;
        this.campo = campo;
        
        IDADE_PROCRIACAO = 10;
        IDADE_MAXIMA = 150;
        PROBABILIDADE_PROCRIACAO = 0.75;
        TAMANHO_MAXIMO_NINHADA = 5;
        setLocalizacao(localizacao);

        if (idadeRandomica) {
            idade = rand.nextInt(IDADE_MAXIMA);
            nivelFome = rand.nextInt(VALOR_FOME_OVELHA);
        } else {
            nivelFome = VALOR_FOME_OVELHA;
        }
    }

    public void caca(List<LoboGuara> novosLobos) {
        incrementaIdade();

        if (vivo) {
            daALuz(novosLobos);

            Localizacao newLocalizacao = procuraComida(localizacao);

            if (newLocalizacao == null) {
                newLocalizacao = campo.localizacaoAdjacenteLivre(localizacao);
                incrementaFome();
            }

            if (newLocalizacao != null && vivo) {
                setLocalizacao(newLocalizacao);
            } else {
                setMorte();
            }
        }
    }

    public boolean estaVivo() {
        return vivo;
    }

    @Override
    protected Localizacao getLocalizacao() {
        return localizacao;
    }

    @Override
    protected void setLocalizacao(Localizacao newLocalizacao) {
        if (localizacao != null) {
            campo.limpa(localizacao);
        }

        localizacao = newLocalizacao;
        campo.lugar(this, newLocalizacao);
    }

    @Override
    protected void incrementaIdade() {
        idade++;

        if (idade > IDADE_MAXIMA) {
            setMorte();
        }
    }

    private void incrementaFome() {
        nivelFome--;

        if (nivelFome == 0) {
            setMorte();
        }
    }

    private Localizacao procuraComida(Localizacao localizacao) {
        List<Localizacao> adjacente = campo.localizacoesAdjacentes(localizacao);
        Iterator<Localizacao> it = adjacente.iterator();

        while (it.hasNext()) {
            Localizacao onde = it.next();
            Object animal = campo.getObjectAt(onde);

            if (animal != null && animal instanceof Ovelha) {
                Ovelha ovelha = (Ovelha) animal;

                ovelha.setMorte();
                nivelFome = VALOR_FOME_OVELHA;

                return onde;
            }
        }

        return null;
    }

    private void daALuz(List<LoboGuara> novosLobos) {
        List<Localizacao> livre = campo.localizacoesAdjacentesLivres(localizacao);
        int nascimentos = procria();
        int n = livre.size();

        for (int b = 0; b < n && b < nascimentos; b++) {
            Localizacao loc = livre.remove(0);
            LoboGuara jovem = new LoboGuara(false, campo, loc);
            novosLobos.add(jovem);
        }
    }

    @Override
    protected int procria() {
        int nascimentos = 0;

        if (podeProcriar() && rand.nextDouble() <= PROBABILIDADE_PROCRIACAO) {
            nascimentos = rand.nextInt(TAMANHO_MAXIMO_NINHADA) + 1;
        }

        return nascimentos;
    }

    @Override
    protected boolean podeProcriar() {
        return idade >= IDADE_PROCRIACAO;
    }

    @Override
    protected void setMorte() {
        vivo = false;

        if (localizacao != null) {
            campo.limpa(localizacao);
            localizacao = null;
            campo = null;
        }
    }
}
