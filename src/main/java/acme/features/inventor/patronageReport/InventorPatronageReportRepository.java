package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.entities.patronageReport.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository {

	@Query("select patronageReport from PatronageReport patronageReport where patronageReport.id = :id")
	PatronageReport findOnePatronageReportById(int id);
	
	@Query("select patronageReport.sequenceNumber from PatronageReport patronageReport where patronageReport.patronage.id = :id")
	Collection<String> getPatronageReportSequenceNumbersByPatronageId(int id);

	@Query("select patronageReport from PatronageReport patronageReport where patronageReport.patronage.inventor.id = :id")
	Collection<PatronageReport> findPatronageReportsByInventorId(int id);
	
	@Query("select patronageReport.patronage.inventor from PatronageReport patronageReport where patronageReport.patronage.inventor.id = :id")
	Inventor findOneInventorById(int id);
	
	@Query("select patronage from Patronage patronage where patronage.id = :id")
	Patronage findOnePatronageById(int id);
}
