package de.hsba.bi.fahrradkurier.job;

import de.hsba.bi.fahrradkurier.common.CityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<JobEntity, Long> {

    @Query("FROM JobEntity j WHERE j.status = :status AND j.deliveryAddress.city = :city")
    List<JobEntity> findAllByStatusAndCityIsResidentOrderByOrderDateDesc(@Param(value = "status") JobStatusEnum status,
                                                                              @Param(value = "city") CityEnum city);

    List<JobEntity> findAllByCustomerIdOrCourierIdOrderByOrderDateDesc(Long customerId, Long courierId);


    @Modifying
    @Query("update JobEntity j set j.status = :newStatus where j.id = :jobId")
    void updateStatus(@Param(value = "jobId") Long jobId, @Param(value = "newStatus") JobStatusEnum newStatus);
}
