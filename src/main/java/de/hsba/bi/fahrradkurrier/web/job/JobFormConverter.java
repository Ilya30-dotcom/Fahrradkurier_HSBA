package de.hsba.bi.fahrradkurrier.web.job;

import de.hsba.bi.fahrradkurrier.Common.AddressEntity;
import de.hsba.bi.fahrradkurrier.job.JobEntity;
import org.springframework.stereotype.Component;

@Component
public class JobFormConverter {

    JobForm enrichCity(String city) {
        JobForm form = new JobForm();
        form.setFromCity(city);
        form.setToCity(city);
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
