package com.bankingsystem.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditConfig {

    public static final Logger AUDIT_LOGGER =
            LoggerFactory.getLogger("AUDIT_LOGGER");
}