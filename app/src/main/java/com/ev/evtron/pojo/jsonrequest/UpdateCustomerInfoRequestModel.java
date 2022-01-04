package com.ev.evtron.pojo.jsonrequest;

import java.io.Serializable;

public class UpdateCustomerInfoRequestModel implements Serializable {
    public String Authorization;
    public String role;
    public String firstName;
    public String lastName;
    public String carModel;
    public String carManufacturer;
    public String email;
    public String mobile;
    public String code;
}
