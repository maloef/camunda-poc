/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.mariadb.repository;

import org.springframework.data.repository.CrudRepository;

import ch.sbb.dks.camundapoc.mariadb.model.MariaWagenEvent;

/**
 * @author ue85191 (Markus Loeffler)
 */
public interface MariaWagenEventRepository extends CrudRepository<MariaWagenEvent, Integer> {
}

