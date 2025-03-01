package com.dexwin.currencyconverter.DTO;

public class CurrencyDTO {
    private boolean success;
    private String terms;
    private String privacy;
/*    private QueryDTO query;
    private InfoDTO info;*/
    private Object query;
    private Object info;
    private double result;

    // No-args constructor
    public CurrencyDTO() {
    }

    // All-args constructor
    public CurrencyDTO(boolean success, String terms, String privacy, Object query, Object info, double result) {
        this.success = success;
        this.terms = terms;
        this.privacy = privacy;
        this.query = query;
        this.info = info;
        this.result = result;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public Object getQuery() {
        return query;
    }

    public void setQuery(Object query) {
        this.query = query;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}


