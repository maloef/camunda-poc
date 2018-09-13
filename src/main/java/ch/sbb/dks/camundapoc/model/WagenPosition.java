/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.model;

import java.util.List;

/**
 * @author ue85191 (Markus Loeffler)
 */
public class WagenPosition {

    private List<Wagenbewegungsblock> bloecke;

    private long kommerzielleGesamtdauer;
    private long einheiten;

    public WagenPosition() {
    }

    public WagenPosition(List<Wagenbewegungsblock> bloecke) {
        this.bloecke = bloecke;
        kommerzielleGesamtdauer = 43000;
    }

    public List<Wagenbewegungsblock> getBloecke() {
        return bloecke;
    }

    public void setBloecke(List<Wagenbewegungsblock> bloecke) {
        this.bloecke = bloecke;
    }

    public long getKommerzielleGesamtdauer() {
        return kommerzielleGesamtdauer;
    }

    public void setKommerzielleGesamtdauer(long kommerzielleGesamtdauer) {
        this.kommerzielleGesamtdauer = kommerzielleGesamtdauer;
    }

    public long getEinheiten() {
        return einheiten;
    }

    public void setEinheiten(long einheiten) {
        this.einheiten = einheiten;
    }
}
