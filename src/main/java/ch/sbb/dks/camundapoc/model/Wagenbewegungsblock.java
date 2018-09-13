/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.model;

import java.io.Serializable;

/**
 * @author ue85191 (Markus Loeffler)
 */
public class Wagenbewegungsblock implements Serializable {

    private WagenEvent startEvent;
    private WagenEvent endEvent;

    private String wagentyp;
    private String cluster;
    private String gleistyp;

    private long dauerInMinuten;
    private long einheitenGleis = 0;
    private long einheitenWagen = 0;
    private long einheiten = 0;
    private boolean abgerechnet;

    public Wagenbewegungsblock() {
    }

    public Wagenbewegungsblock(WagenEvent startEvent, WagenEvent endEvent) {
        this.startEvent = startEvent;
        this.endEvent = endEvent;
        gleistyp = "Bahn";
    }

    public WagenEvent getStartEvent() {
        return startEvent;
    }

    public void setStartEvent(WagenEvent startEvent) {
        this.startEvent = startEvent;
    }

    public WagenEvent getEndEvent() {
        return endEvent;
    }

    public void setEndEvent(WagenEvent endEvent) {
        this.endEvent = endEvent;
    }

    public String getWagentyp() {
        return wagentyp;
    }

    public void setWagentyp(String wagentyp) {
        this.wagentyp = wagentyp;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public long getEinheiten() {
        return einheiten;
    }

    public void setEinheiten(int einheiten) {
        this.einheiten = einheiten;
    }

    private void updateEinheiten() {
        einheiten = einheitenGleis + einheitenWagen;
    }

    public boolean getAbgerechnet() {
        return abgerechnet;
    }

    public void setAbgerechnet(boolean abgerechnet) {
        this.abgerechnet = abgerechnet;
    }

    public String getGleistyp() {
        return gleistyp;
    }

    public void setGleistyp(String gleistyp) {
        this.gleistyp = gleistyp;
    }

    public long getEinheitenGleis() {
        return einheitenGleis;
    }

    public void setEinheitenGleis(long einheitenGleis) {
        this.einheitenGleis = einheitenGleis;
        updateEinheiten();
    }

    public long getEinheitenWagen() {
        return einheitenWagen;
    }

    public void setEinheitenWagen(long einheitenWagen) {
        this.einheitenWagen = einheitenWagen;
        updateEinheiten();
    }

    public void setDauerInMinuten(long dauerInMinuten) {
        this.dauerInMinuten = dauerInMinuten;
    }

    public long getDauerInMinuten() {
        return dauerInMinuten;
    }
}
