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

    private Integer einheiten;
    private Boolean abgerechnet;

    public Wagenbewegungsblock() {

    }

    public Wagenbewegungsblock(WagenEvent startEvent, WagenEvent endEvent) {
        this.startEvent = startEvent;
        this.endEvent = endEvent;
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

    public Boolean getAbgerechnet() {
        return abgerechnet;
    }

    public void setAbgerechnet(Boolean abgerechnet) {
        this.abgerechnet = abgerechnet;
    }
}
