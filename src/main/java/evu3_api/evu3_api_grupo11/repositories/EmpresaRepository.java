package evu3_api.evu3_api_grupo11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import evu3_api.evu3_api_grupo11.models.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {}