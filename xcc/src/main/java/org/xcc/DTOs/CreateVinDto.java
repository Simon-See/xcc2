package org.xcc.DTOs;

import org.xcc.entities.Vin;

public class CreateVinDto {

        public String vin;

        public CreateVinDto(Vin vin_) {
                this.vin = vin_.vin;
        }

        public String getVin() {
                return vin;
        }
}
