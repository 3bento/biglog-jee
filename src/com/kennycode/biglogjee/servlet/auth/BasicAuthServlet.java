package com.kennycode.biglogjee.servlet.auth;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;

@WebServlet(name="BasicAuth", urlPatterns={"/basic-auth"})
@ServletSecurity(@HttpConstraint(transportGuarantee = TransportGuarantee.CONFIDENTIAL, rolesAllowed= {"manager"}))
public class BasicAuthServlet {

}
