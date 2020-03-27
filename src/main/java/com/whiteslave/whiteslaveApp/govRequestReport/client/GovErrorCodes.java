package com.whiteslave.whiteslaveApp.govRequestReport.client;

public enum  GovErrorCodes {

    WL100("WL-100","An unexpected server error has occurred."),
    WL101("WL-101","The date filed can not be empty."),
    WL102("WL-102","The date filed has an invalid format."),
    WL103("WL-103","The date can not be future date."),
    WL104("WL-104","REGON field can not be empty."),
    WL105("WL-105","The REGON field has the wrong length. 9 or 14 characters required."),
    WL106("WL-106","REGON field contains illegal characters. Only digits required."),
    WL107("WL-107","Incorrect REGON."),
    WL108("WL-108","The 'account number' field can not be empty."),
    WL109("WL-109","The 'account number' field has the wrong length. 26 characters required."),
    WL110("WL-110","The 'account number' field contains illegal characters. Only digits required."),
    WL111("WL-111","Incorrect account number."),
    WL112("WL-112","NIP field can not be empty."),
    WL113("WL-113","The NIP field has the wrong length. 9 or 14 characters required."),
    WL114("WL-114","NIP field contains illegal characters. Only digits required."),
    WL115("WL-115","Incorrect NIP."),
    WL116("WL-116","The 'subject name' field cannot be empty."),
    WL117("WL-117","'Subject name' field too short. At least 5 characters required."),
    WL118("WL-118","Date before the scope of the register."),
    WL130("WL-130","Maximum number of query arguments exceeded."),
    WL190("WL-190","Invalid request."),
    WL191("WL-191","The request limit for this IP address has been exhausted today."),
    WL195("WL-195","We have updated the database. Reissue the request to receive current data."),
    WL196("WL-196","Database is being updated. Please try again later.");

    private String code;
    private String enMessage;

    GovErrorCodes(String code, String enMessage) {
        this.code = code;
        this.enMessage = enMessage;
    }

    public String getCode() {
        return code;
    }

    public String getEnMessage() {
        return enMessage;
    }
}
