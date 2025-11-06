package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.*;

import com.tecsup.petclinic.dtos.VetSpecialtyDTO;
import com.tecsup.petclinic.exceptions.VetSpecialtyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Slf4j
public class VetSpecialtyServiceTest {

    @Autowired
    private VetSpecialtyService vetSpecialtyService;

    @Test
    public void testFindVetSpecialtyById_azanero() {
        Integer VET_ID = 2;
        Integer SPECIALTY_ID = 1;
        String EXPECTED_NOTES = "Expert in X-ray imaging";

        VetSpecialtyDTO vetSpecialty = null;

        try {
            vetSpecialty = this.vetSpecialtyService.findById(VET_ID, SPECIALTY_ID);
        } catch (VetSpecialtyNotFoundException e) {
            fail(e.getMessage());
        }

        log.info("VetSpecialty found: " + vetSpecialty);

        assertNotNull(vetSpecialty);
        assertEquals(VET_ID, vetSpecialty.getVetId());
        assertEquals(SPECIALTY_ID, vetSpecialty.getSpecialtyId());
        assertEquals(EXPECTED_NOTES, vetSpecialty.getNotes());
    }


    /**
     * Test: Create a new vet-specialty relationship
     */
    @Test
    public void testCreateVetSpecialty_azanero() {
        Integer VET_ID = 1; // James Carter
        Integer SPECIALTY_ID = 3; // Dentistry
        LocalDate CERTIFICATION_DATE = LocalDate.of(2020, 5, 15);
        Integer YEARS_EXPERIENCE = 3;
        Boolean IS_PRIMARY = true;
        String NOTES = "Recently certified in dentistry";

        VetSpecialtyDTO vetSpecialtyDTO = VetSpecialtyDTO.builder()
                .vetId(VET_ID)
                .specialtyId(SPECIALTY_ID)
                .certificationDate(CERTIFICATION_DATE)
                .yearsExperience(YEARS_EXPERIENCE)
                .isPrimary(IS_PRIMARY)
                .notes(NOTES)
                .build();

        VetSpecialtyDTO newVetSpecialty = this.vetSpecialtyService.create(vetSpecialtyDTO);

        log.info("VetSpecialty created: " + newVetSpecialty);

        assertNotNull(newVetSpecialty);
        assertEquals(VET_ID, newVetSpecialty.getVetId());
        assertEquals(SPECIALTY_ID, newVetSpecialty.getSpecialtyId());
        assertEquals(CERTIFICATION_DATE, newVetSpecialty.getCertificationDate());
        assertEquals(YEARS_EXPERIENCE, newVetSpecialty.getYearsExperience());
        assertEquals(IS_PRIMARY, newVetSpecialty.getIsPrimary());
        assertEquals(NOTES, newVetSpecialty.getNotes());
    }

    /**
     * Test: Update an existing vet-specialty relationship
     */
    @Test
    public void testUpdateVetSpecialty_RuizCarlos() {
        Integer VET_ID = 5; // Henry Stevens
        Integer SPECIALTY_ID = 1; // Radiology

        // Original values
        Integer ORIGINAL_YEARS_EXPERIENCE = 7;

        // Updated values
        Integer UPDATED_YEARS_EXPERIENCE = 10;
        String UPDATED_NOTES = "Now expert in advanced imaging techniques";

        VetSpecialtyDTO vetSpecialtyDTO = null;

        // Find existing relationship
        try {
            vetSpecialtyDTO = this.vetSpecialtyService.findById(VET_ID, SPECIALTY_ID);
        } catch (VetSpecialtyNotFoundException e) {
            fail(e.getMessage());
        }

        log.info("Original VetSpecialty: " + vetSpecialtyDTO);

        // Update values
        vetSpecialtyDTO.setYearsExperience(UPDATED_YEARS_EXPERIENCE);
        vetSpecialtyDTO.setNotes(UPDATED_NOTES);

        // Execute update
        VetSpecialtyDTO updatedVetSpecialty = this.vetSpecialtyService.update(vetSpecialtyDTO);

        log.info("Updated VetSpecialty: " + updatedVetSpecialty);

        assertNotNull(updatedVetSpecialty);
        assertEquals(VET_ID, updatedVetSpecialty.getVetId());
        assertEquals(SPECIALTY_ID, updatedVetSpecialty.getSpecialtyId());
        assertEquals(UPDATED_YEARS_EXPERIENCE, updatedVetSpecialty.getYearsExperience());
        assertEquals(UPDATED_NOTES, updatedVetSpecialty.getNotes());
    }




}