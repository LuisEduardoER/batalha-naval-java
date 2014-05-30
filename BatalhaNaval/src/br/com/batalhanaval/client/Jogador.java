package br.com.batalhanaval.client;

// Possui os dados de cada jogador
public class Jogador {
    
    private Integer qtdPortaAvioes;
    private Integer qtdFragata;
    private Integer qtdTorpedeiros;
    private Integer qtdContraTorpedeiros;
    private Integer qtdSubmarios;

    public Jogador() {
        this.qtdPortaAvioes = 1;
        this.qtdFragata = 1;
        this.qtdTorpedeiros = 2;
        this.qtdContraTorpedeiros = 3;
        this.qtdSubmarios = 4;
    }

    /**
     * @return the qtdPortaAvioes
     */
    public Integer getQtdPortaAvioes() {
        return qtdPortaAvioes;
    }

    /**
     * @param qtdPortaAvioes the qtdPortaAvioes to set
     */
    public void setQtdPortaAvioes(Integer qtdPortaAvioes) {
        this.qtdPortaAvioes = qtdPortaAvioes;
    }

    /**
     * @return the qtdFragata
     */
    public Integer getQtdFragata() {
        return qtdFragata;
    }

    /**
     * @param qtdFragata the qtdFragata to set
     */
    public void setQtdFragata(Integer qtdFragata) {
        this.qtdFragata = qtdFragata;
    }

    /**
     * @return the qtdTorpedeiros
     */
    public Integer getQtdTorpedeiros() {
        return qtdTorpedeiros;
    }

    /**
     * @param qtdTorpedeiros the qtdTorpedeiros to set
     */
    public void setQtdTorpedeiros(Integer qtdTorpedeiros) {
        this.qtdTorpedeiros = qtdTorpedeiros;
    }

    /**
     * @return the qtdContraTorpedeiros
     */
    public Integer getQtdContraTorpedeiros() {
        return qtdContraTorpedeiros;
    }

    /**
     * @param qtdContraTorpedeiros the qtdContraTorpedeiros to set
     */
    public void setQtdContraTorpedeiros(Integer qtdContraTorpedeiros) {
        this.qtdContraTorpedeiros = qtdContraTorpedeiros;
    }

    /**
     * @return the qtdSubmarios
     */
    public Integer getQtdSubmarios() {
        return qtdSubmarios;
    }

    /**
     * @param qtdSubmarios the qtdSubmarios to set
     */
    public void setQtdSubmarios(Integer qtdSubmarios) {
        this.qtdSubmarios = qtdSubmarios;
    }
    
    
    
}
