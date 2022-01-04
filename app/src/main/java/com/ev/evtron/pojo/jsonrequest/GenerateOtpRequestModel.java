package com.ev.evtron.pojo.jsonrequest;

import java.io.Serializable;

public class GenerateOtpRequestModel implements Serializable {
    public int flag;
    public String code;
    public String mobile;
    public String email;
}
