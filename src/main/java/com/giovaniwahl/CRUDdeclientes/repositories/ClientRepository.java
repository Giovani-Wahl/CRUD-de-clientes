package com.giovaniwahl.CRUDdeclientes.repositories;

import com.giovaniwahl.CRUDdeclientes.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
