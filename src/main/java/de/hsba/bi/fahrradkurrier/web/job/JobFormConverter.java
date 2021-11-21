package de.hsba.bi.fahrradkurrier.web.job;

import de.hsba.bi.fahrradkurrier.Common.AddressEntity;
import de.hsba.bi.fahrradkurrier.job.JobEntity;
import de.hsba.bi.fahrradkurrier.job.JobTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class JobFormConverter {

/**
    JobForm toForm(JobTypeEnum type) {
        JobForm form = new JobForm();
        form.setType(type);
        form.setDescription(entry.getDescription());
        form.setCreditor(entry.getCreditor());
        form.setDebitors(entry.getDebitors());
        return form;
    }
 */
    JobForm enrichAddress(JobEntity job) {
        JobForm form = new JobForm();
        form.setFromCity(job.getPickUpAddress().getCity());
        form.setFromZip(job.getPickUpAddress().getZipCode());
        form.setFromStreet(job.getPickUpAddress().getStreet());
        form.setFromStreetNumber(job.getPickUpAddress().getStreetNumber());
        return form;
    };

    JobEntity updateJob(JobEntity job, JobForm form) {
        AddressEntity pickupAddress = AddressEntity.builder().
                city(form.getFromCity())
                .zipCode(form.getFromZip())
                .street(form.getFromStreet())
                .streetNumber(form.getFromStreetNumber())
                .build();
        AddressEntity deliveryAddress = AddressEntity.builder().
                city(form.getToCity())
                .zipCode(form.getToZip())
                .street(form.getToStreet())
                .streetNumber(form.getToStreetNumber())
                .build();
        job.setPickUpAddress(pickupAddress);
        job.setDeliveryAddress(deliveryAddress);
        job.setType(form.getType());
        return job;
    }


}
