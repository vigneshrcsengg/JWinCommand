package com.vlr.tech.sys;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vignesh.rajendran
 */
public class EnvironmentVar {

    public String fName = null;
    public String fValue = null;

    public EnvironmentVar(String name, String value) {
        fName = name;
        fValue = value;
    }
}
