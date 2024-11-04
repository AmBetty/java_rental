package com.amkiing.paytonkawa.dto;

import com.amkiing.paytonkawa.customer.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest (
         String id,
         @NotNull(message = "firstname required")
         String firstname,
         @NotNull(message = "lastname required")
         String lastname,
         @NotNull(message = "email required")
         @Email(message = "Not a valid email")
         String email,
         Address address
){
}
