package edu.unipampa.es.rp2.marco1;

import java.util.List;

public class Ovelha extends Animal {

    public Ovelha(boolean randomAge, Campo campo, Localizacao localizacao) {

        IDADE_PROCRIACAO = 5;
        IDADE_MAXIMA = 40;
        PROBABILIDADE_PROCRIACAO = 0.15;
        TAMANHO_MAXIMO_NINHADA = 4;
        this.campo = campo;
        setLocalizacao(localizacao);
        if (randomAge) {
            idade = RAND.nextInt(IDADE_MAXIMA);
        }
    }

    public void corre(List<Ovelha> novasOvelhas) {
        incrementaIdade();

        if (vivo) {
            daALuz(novasOvelhas);
            Localizacao newLocalizacao = campo.localizacaoAdjacenteLivre(localizacao);

            if (newLocalizacao != null) {
                setLocalizacao(newLocalizacao);
            } else {
                setMorte();
            }
        }
    }

    public boolean estaViva() {
        return vivo;
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

    private void daALuz(List<Ovelha> novasOvelhas) {
        List<Localizacao> livre = campo.localizacoesAdjacentesLivres(localizacao);
        int nascimentos = procria();
        int n = livre.size();

        for (int b = 0; b < n && b < nascimentos; b++) {
            Localizacao loc = livre.remove(0);
            Ovelha jovem = new Ovelha(false, campo, loc);
            novasOvelhas.add(jovem);
        }
    }

    protected int procria() {
        int nascimentos = 0;

        if (podeProcriar() && RAND.nextDouble() <= PROBABILIDADE_PROCRIACAO) {
            nascimentos = RAND.nextInt(TAMANHO_MAXIMO_NINHADA) + 1;
        }

        return nascimentos;
    }

    @Override
    protected boolean podeProcriar() {
        return idade >= IDADE_PROCRIACAO;
    }

}
