package com.acme.fastbite.platform.planning.domain.model.commands;

import com.acme.fastbite.platform.planning.domain.model.valueobjects.AcmeCompanyRecordId;
import com.acme.fastbite.platform.planning.domain.model.valueobjects.AcmeRestaurantRecordId;

public record CreateCompanyCommand(String name,String email,String address,String schedule,String image,Long userId){
}
