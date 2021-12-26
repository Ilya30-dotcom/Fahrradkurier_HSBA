package de.hsba.bi.fahrradkurier.web.job;

import de.hsba.bi.fahrradkurier.common.AddressEntity;
import de.hsba.bi.fahrradkurier.common.CityEnum;
import de.hsba.bi.fahrradkurier.job.JobEntity;
import org.springframework.stereotype.Component;

@Component
public class JobFormConverter {

    JobForm enrichCity(CityEnum city) {
        JobForm form = new JobForm();
        form.setFromCity(city.toString());
        form.setToCity(city.toString());
        return form;
    };

    JobForm convertToJobForm(JobEntity job) {
        JobForm form = new JobForm();

        form.setFromStreet(job.getPickUpAddress().getStreet());
        form.setFromStreetNumber(job.getPickUpAddress().getStreetNumber());
        form.setFromZip(job.getPickUpAddress().getZipCode());
        form.setFromCity(job.getPickUpAddress().getCity().toString());

        form.setToStreet(job.getDeliveryAddress().getStreet());
        form.setToStreetNumber(job.getDeliveryAddress().getStreetNumber());
        form.setToZip(job.getDeliveryAddress().getZipCode());
        form.setToCity(job.getDeliveryAddress().getCity().toString());

        form.setType(job.getType());
        return form;
    }

    JobEntity updateJob(JobEntity job, JobForm form) {
        AddressEntity pickupAddress = AddressEntity.builder().
                city(CityEnum.valueOf(form.getFromCity().toUpperCase()))
                .zipCode(form.getFromZip())
                .street(form.getFromStreet())
                .streetNumber(form.getFromStreetNumber())
                .build();
        AddressEntity deliveryAddress = AddressEntity.builder().
                city(CityEnum.valueOf(form.getToCity().toUpperCase()))
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
