package de.hsba.bi.fahrradkurier.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface JobRepository extends JpaRepository<JobEntity, Long> {

    List<JobEntity> findAllByStatusOrderByOrderTimeStampDesc(JobStatusEnum status);

    List<JobEntity> findAllByCourierId(Long courierId);

    List<JobEntity> findAllByCustomerId(Long customerId);

    List<JobEntity> findAllByCustomerIdOrCourierIdOrderByOrderTimeStampDesc(Long customerId, Long courierId);


    @Modifying
    @Query("update JobEntity j set j.status = :newStatus where j.id = :jobId")
    void updateStatus(@Param(value = "jobId") Long jobId, @Param(value = "newStatus") JobStatusEnum newStatus);
}