package ge.workshops.workshop1.dto;

import ge.workshops.workshop1.entity.Collateral;
import lombok.Data;

@Data
public class CollateralDto {
    private Collateral.CollateralType type;
    private  float value;

}
