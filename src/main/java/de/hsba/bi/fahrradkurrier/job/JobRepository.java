package de.hsba.bi.fahrradkurrier.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface JobRepository extends JpaRepository<JobEntity, Long> {

    List<JobEntity> findAllByStatus(JobStatusEnum status);

    List<JobEntity> findAllByCourierId(Long courierId);

    List<JobEntity> findAllByCustomerId(Long customerId);

    List<JobEntity> findAllByCustomerIdOrCourierId(Long customerId, Long courierId);


    @Query("select j from JobEntity j where j.status = :status and j.courierId = :userId or j.customerId = :userId")
    List<JobEntity> findAllByUserIdAndStatus(@Param(value = "userId") Long userId, @Param(value = "status") JobStatusEnum status);

    @Modifying
    @Query("update JobEntity j set j.status = :newStatus where j.id = :jobId")
    void updateStatus(@Param(value = "jobId") Long jobId, @Param(value = "newStatus") JobStatusEnum newStatus);
}
