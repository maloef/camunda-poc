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

    private int dauerInMinuten;
    private Integer einheitenGleis = 0;
    private Integer einheitenWagen = 0;
    private Integer einheiten = 0;
    private Boolean abgerechnet;

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

    public Integer getEinheiten() {
        return einheiten;
    }

    public void setEinheiten(Integer einheiten) {
        this.einheiten = einheiten;
    }

    private void updateEinheiten() {
        einheiten = einheitenGleis + einheitenWagen;
    }

    public Boolean getAbgerechnet() {
        return abgerechnet;
    }

    public void setAbgerechnet(Boolean abgerechnet) {
        this.abgerechnet = abgerechnet;
    }

    public String getGleistyp() {
        return gleistyp;
    }

    public void setGleistyp(String gleistyp) {
        this.gleistyp = gleistyp;
    }

    public Integer getEinheitenGleis() {
        return einheitenGleis;
    }

    public void setEinheitenGleis(Integer einheitenGleis) {
        this.einheitenGleis = einheitenGleis;
        updateEinheiten();
    }

    public Integer getEinheitenWagen() {
        return einheitenWagen;
    }

    public void setEinheitenWagen(Integer einheitenWagen) {
        this.einheitenWagen = einheitenWagen;
        updateEinheiten();
    }

    public void setDauerInMinuten(int dauerInMinuten) {
        this.dauerInMinuten = dauerInMinuten;
    }

    public int getDauerInMinuten() {
        return dauerInMinuten;
    }
}
