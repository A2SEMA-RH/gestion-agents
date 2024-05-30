package org.gestionAgent.dao;

import org.gestionAgent.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentDao extends JpaRepository< Agent, Long> {
}
