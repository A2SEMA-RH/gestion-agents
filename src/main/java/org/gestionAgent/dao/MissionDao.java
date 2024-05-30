package org.gestionAgent.dao;

import org.gestionAgent.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionDao extends JpaRepository<Mission,Long> {
}
